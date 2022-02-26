package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S3_2630_색종이만들기 {

	// S3 2630 색종이만들기

	// 검사하다가 다른게 나타나면 절반나눔
	// 다 같은 종이가 나타나면 cnt++

	public static char[][] paper;
	public static int white, blue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		paper = new char[n][n];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				paper[i][j] = input.charAt(j << 1);
			}
		}

		check(0,0,n);
		System.out.println(white);
		System.out.println(blue);
	}

	public static void check(int r, int c, int len) {
		char flag = paper[r][c];
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (paper[i][j] != flag) {
					int half = len >> 1;
					check(r, c, half);
					check(r, c + half, half);
					check(r + half, c, half);
					check(r + half, c + half, half);
					return;
				}
			}
		}
		if(flag == '1')
			blue++;
		else
			white++;
	}

}
