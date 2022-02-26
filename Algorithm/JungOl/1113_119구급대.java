package jongol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1113_119구급대 {

	// 최소 코너를 도는 횟수
	// bfs돌면서 코너도는 횟수 저장 - 완탐.. 최소횟수가 저장되면 최소보다 큰경우 탈락

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		char[][] map = new char[m][n];
		boolean[][] visited = new boolean[m][n];

		st = new StringTokenizer(br.readLine(), " ");
		int endX = Integer.parseInt(st.nextToken());
		int endY = Integer.parseInt(st.nextToken());

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j << 1);
			}
		}

		int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		// 상0 하1 좌2 우3
		Queue<int[]> q = new LinkedList<int[]>();

		if (map[0][1] == '1')
			q.add(new int[] { 0, 1, 3, 0 }); // r c d cnt
		if (map[1][0] == '1')
			q.add(new int[] { 1, 0, 1, 0 });
		visited[0][0] = true;

		int min = 987654321;
		while (q.size() > 0) {
			int[] cur = q.poll();
			int cnt = cur[3];

			visited[cur[0]][cur[1]] = true;

			if (cur[0] == endX && cur[1] == endY) {
				if (min > cnt)
					min = cnt;
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dir[i][0];
				int nc = cur[1] + dir[i][1];

				if (nr < 0 || nr >= m || nc < 0 || nc >= n || visited[nr][nc] || map[nr][nc] == '0')
					continue;

				if (i == cur[2])
					q.add(new int[] { nr, nc, i, cnt });
				else if (cnt + 1 < min)
					q.add(new int[] { nr, nc, i, cnt + 1 });
			}
		}

		System.out.println(min);
	}

}
