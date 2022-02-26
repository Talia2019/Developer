package jiyoung.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class NumberOfIsland {

	// S2 4963 섬의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int w = 1, h = 1;
		int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
		char[][] map;
		boolean[][] visited;
		int x, y, nx, ny;
		Queue<int[]> q = new LinkedList<int[]>();

		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if (w == 0 && h == 0)
				break;
			
			map = new char[h][w];
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j * 2);
				}
			}

			int cnt = 0;
			visited = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (visited[i][j] || map[i][j] == '0')
						continue;

					// 방문한적 없고, 섬인 부분에서부터 탐색 시작
					q.offer(new int[] { i, j });
					visited[i][j] = true;
					while (q.size() > 0) {
						int[] xy = q.poll();
						for (int k = 0; k < 8; k++) { // 8방 탐색
							ny = xy[0] + dir[k][0];
							nx = xy[1] + dir[k][1];
							if (isIn(w, h, nx, ny) && !visited[ny][nx]) {
								visited[ny][nx] = true;
								if (map[ny][nx] == '1') {
									q.offer(new int[] { ny, nx });
								}
							}
						}
					}
					cnt++;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);

	}

	public static boolean isIn(int w, int h, int x, int y) {
		if (x >= 0 && x < w && y >= 0 && y < h)
			return true;
		return false;
	}
}
//문제
//정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
//한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 
//두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
//입력
//입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
//둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
//입력의 마지막 줄에는 0이 두 개 주어진다.
//출력
//각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
