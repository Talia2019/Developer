package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_11315_오목판정 {

	public static int n;
	public static boolean[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					char c = str.charAt(j);
					if (c == 'o')
						map[i][j] = true;
				}
			}

			String answer = "NO";
			foI: for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (map[i][j] && findFive(i, j)) {
						answer = "YES";
						break foI;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	public static boolean findFive(int x, int y) {
		int cnt = 0;
		// 하
		for (int i = 0; i < 5; i++) {
			if (!isOut(x + i, y) && map[x + i][y]) {
				cnt++;
			}
		}
		if (cnt == 5)
			return true;
		cnt = 0;
		// 우
		for (int i = 0; i < 5; i++) {
			if (!isOut(x, y + i) && map[x][y + i]) {
				cnt++;
			}
		}
		if (cnt == 5)
			return true;
		cnt = 0;
		// 대각아래
		for (int i = 0; i < 5; i++) {
			if (!isOut(x + i, y + i) && map[x + i][y + i]) {
				cnt++;
			}
		}
		if (cnt == 5)
			return true;
		//대각 위
		cnt = 0;
		for (int i = 0; i < 5; i++) {
			if (!isOut(x + i, y - i) && map[x + i][y - i]) {
				cnt++;
			}
		}
		if (cnt == 5)
			return true;
		return false;
	}

	public static boolean isOut(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= n)
			return true;
		return false;
	}

}
