package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D2_1979_어디에단어가들어갈수있을까 {

//	흰색1(글자), 검정0
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[][] map = new int[n][n];

			String str;
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = str.charAt(j * 2) - '0';
				}
//				행을 돌지 않고 split으로 각 원소에 들어간 string의 길이를 세서 할 수 있음!!
//				String[] splited = str.split("0");
//				System.out.println(Arrays.toString(splited));
			}
			
			//각 행/열별로 돌면서 공간의 수를 셈
			int space;
			int cnt = 0, j;
			for (int i = 0; i < n; i++) {
				j = 0;
				while (j < n) {
					space = 0;
					while (j < n && map[i][j++] == 1) {
						space++;
					}
					if (space == k)
						cnt++;
				}
			}

			for (int i = 0; i < n; i++) {
				j = 0;
				while (j < n) {
					space = 0;
					while (j < n && map[j++][i] == 1) {
						space++;
					}
					if (space == k)
						cnt++;
				}
			}
			

			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
	}

}
