package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S2_1780_종이의개수 {

	// 비슷하게 3으로 나누고 하면 될듯..

	public static int plus, zero, minus;
	public static int[][] paper;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		paper = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		check(0,0,n);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(minus).append("\n");
		sb.append(zero).append("\n");
		sb.append(plus).append("\n");
		System.out.println(sb);

	}

	public static void check(int r, int c, int len) {
		int flag = paper[r][c];

		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (paper[i][j] != flag) {
					int div = len / 3;
					check(r, c, div);
					check(r, c + div, div);
					check(r, c + div * 2, div);
					check(r + div, c, div);
					check(r + div, c + div, div);
					check(r + div, c + div * 2, div);
					check(r + div * 2, c, div);
					check(r + div * 2, c + div, div);
					check(r + div * 2, c + div * 2, div);
					return;
				}
			}
		}
		if (flag < 0)
			minus++;
		else if (flag == 0)
			zero++;
		else
			plus++;
	}

}
