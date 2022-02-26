package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// 하->우->상->좌
	// 0.0부터 인덱스 벗어나면 방향바꾸고 visit했으면 정지
	// 회전수
	// min(N, M) mod 2 = 0 -> 작은값은짝수
	// 한 배열안에서 사이클은 n m중 작은값을 2로 나눈수
	//

	public static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } }; // 하우상좌
	public static int[][] map;
	public static int[][] rotatedMap;
	public static boolean[][] visit;
	public static int nowDir = 0;
	public static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int x = 0, y = 0;
		int startX = 0, startY = 0;
		int nx, ny;
		int cnt = 0;
		rotatedMap = new int[n][m];
		int[][] map1, map2;
		for (int rot = 0; rot < r; rot++) {
			visit = new boolean[n][m];
			visit[0][0] = true;
			cnt = 0;
			x = y = startX = startY = 0;
			nowDir = 0;
			if (rot % 2 == 0) {
				map1 = map;
				map2 = rotatedMap;
			} else {
				map1 = rotatedMap;
				map2 = map;
			}
			map2[0][0] = map1[0][1];
			while (true) {
				if (cnt == n * m - 1)
					break;
				nx = x + dir[nowDir][0];
				ny = y + dir[nowDir][1];
				if (isOut(nx, ny)) {
					nowDir++;
					if (nowDir >= 4) {
						x = ++startX;
						y = ++startY;
						nowDir = 0;
					}
					continue;
				}
				visit[nx][ny] = true;
				map2[nx][ny] = map1[x][y];
				x = nx;
				y = ny;
				cnt++;
			}
		}

		int[][] result;
		if (r % 2 == 0) {
			result = map;
		} else {
			result = rotatedMap;
		}

		StringBuilder sb = new StringBuilder();
		for (int[] rm : result) {
			for (int res : rm) {
				sb.append(res).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static boolean isOut(int x, int y) {
		if (x < 0 || x >= n | y < 0 || y >= m)
			return true;
		if (visit[x][y])
			return true;
		return false;
	}
}
