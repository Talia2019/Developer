package jiyoung.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GreedyPanda {

	// G3 1937 욕심쟁이 판다

	// 음.. 뭘저장해야되지
	// 특정 칸에 갔을때 앞으로 움직일 수 있는 칸의 수를 저장?
	// -> 4668ms ㅋㅋㅋㅋㅋ
	// -> 방문처리 없애줬더니 520됨
	// dp는 dp배열자체로 방문여부 확인가능 -> 따로 방문체크할필요가없음 

	public static int n;
	public static int[][] map, mem;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static int totalCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		mem = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dfs(i, j);
			}
		}
		System.out.println(totalCnt);
	}

	public static int dfs(int r, int c) {
		if (mem[r][c] != 0)
			return mem[r][c];

		int bb = map[r][c];
		int nr, nc;
		int max = 0;
		for (int i = 0; i < 4; i++) {
			nr = r + dir[i][0];
			nc = c + dir[i][1];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n || map[nr][nc] <= bb)
				continue;
			if (mem[nr][nc] == 0)
				mem[nr][nc] = dfs(nr, nc);
			if (max < mem[nr][nc] + 1)
				max = mem[nr][nc] + 1;
		}
		if (max == 0) {
			mem[r][c] = 1;
			if (totalCnt < 1)
				totalCnt = 1;
		} else {
			mem[r][c] = max;
			if (totalCnt < max)
				totalCnt = max;
		}
		return mem[r][c];
	}

}
