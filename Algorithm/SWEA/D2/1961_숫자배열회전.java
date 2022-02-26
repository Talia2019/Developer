package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D2_1961_숫자배열회전 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			String[][] arrays = new String[n][3];
			StringBuilder sb2 = new StringBuilder();
			int[][] map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 90도
			for (int i = 0; i < n; i++) {
				sb2.setLength(0);
				for (int j = n - 1; j >= 0; j--) {
					sb2.append(map[j][i]);
				}
				arrays[i][0] = sb2.toString();
			}

			// 180도
			for (int i = n - 1; i >= 0; i--) {
				sb2.setLength(0);
				for (int j = n - 1; j >= 0; j--) {
					sb2.append(map[i][j]);
				}
				arrays[n - i - 1][1] = sb2.toString();
			}

			// 270도
			for (int i = n - 1; i >= 0; i--) {
				sb2.setLength(0);
				for (int j = 0; j < n; j++) {
					sb2.append(map[j][i]);
				}
				arrays[n - i - 1][2] = sb2.toString();
			}

			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 3; j++) {
					sb.append(arrays[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

}
