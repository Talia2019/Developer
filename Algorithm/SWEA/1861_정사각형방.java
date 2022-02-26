package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1861_정사각형방_D4_정지영 {

	// 전부다른수
	// 1:N관계 -> 비선형자료구조
	// 하지만 1:1관계 : DFS로 안풀어도된다
	private static int[][] map;
	private static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	private static int[] dy = { 0, 0, -1, 1 };
	private static int n;
	private static int[][] memo; // 이동할 수 있는 최대 칸 수

	public static int go(int x, int y) {
		if (memo[x][y] > 0)
			return memo[x][y];
		memo[x][y] = 1;
		int num = map[x][y];
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] == num + 1) { // 인접한 칸이 내 방 숫자보다 1크면 이동
				memo[x][y] += go(nx, ny);
				break; // 외길이라서
			}
		}
		return memo[x][y];
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 로컬에서 선언부를 지워버리면 에러뜨면서 자동으로 전역으로 바꿔줄수있음
		int t = Integer.parseInt(br.readLine()); // 에러줄에서 ctrl + 1 + 엔터
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			memo = new int[n][n];
			for (int i = 0; i < map.length; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < map.length; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			int num = Integer.MAX_VALUE;
			for (int x = 0; x < map.length; x++) {
				for (int y = 0; y < map.length; y++) {
					int var = go(x, y); // 최대 몇칸까지 다녀올 수 있는지
					if (max < var || (max == var && num > map[x][y])) {
						max = var;
						num = map[x][y];
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(num).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	// 현재좌표에서 최대 갈 수 있는 이동 칸 수를 리턴
}
