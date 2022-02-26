package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_G4_3349_스도쿠 {

	public static int[][] sudoku;
	public static List<int[]> candidates;
	public static boolean[][] row, col, square;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sudoku = new int[9][9];
		candidates = new ArrayList<int[]>();
		row = new boolean[9][10];
		col = new boolean[9][10];
		square = new boolean[9][10];

		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			for (int j = 0; j < 9; j++) {
				int num = input.charAt(j) - '0';
				sudoku[i][j] = num;
				if (num != 0) {
					row[i][num] = true;
					col[j][num] = true;
					square[(i / 3) * 3 + (j / 3)][num] = true;
				} else
					candidates.add(new int[] { i, j });
			}
		}

		go(0);

	}

	public static void go(int cnt) {
		if (cnt == candidates.size()) {
			StringBuilder sb = new StringBuilder();
			for (int[] sdk : sudoku) {
				for (int s : sdk) {
					sb.append(s);
				}
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0); // 이런식으로 플래그없이 나갈 수 있음
		}

		int[] cur = candidates.get(cnt);
		int r = cur[0];
		int c = cur[1];

		for (int i = 1; i <= 9; i++) {
			if (row[r][i] || col[c][i] || square[(r / 3) * 3 + (c / 3)][i])
				continue;
			row[r][i] = col[c][i] = square[(r / 3) * 3 + (c / 3)][i] = true;
			sudoku[r][c] = i;
			go(cnt + 1);
			sudoku[r][c] = 0;
			row[r][i] = col[c][i] = square[(r / 3) * 3 + (c / 3)][i] = false;
		}

	}
}
