package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G4_2580_스도쿠 {

	public static int[][] map = new int[9][9];
	public static int[] row = new int[9];
	public static int[] col = new int[9];
	public static int[] block = new int[9];
	public static List<int[]> blank = new ArrayList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				int k = map[i][j] = Integer.parseInt(st.nextToken());
				if (k != 0) {
					row[i] |= (1 << k);
					col[j] |= (1 << k);
					block[(i / 3) * 3 + j / 3] |= (1 << k);
				} else
					blank.add(new int[] { i, j });
			}
		}

		sudoku(0);
	}

	public static void sudoku(int cnt) {
		if (cnt == blank.size()) {
			StringBuilder sb = new StringBuilder();
			for (int[] is : map) {
				for (int i : is) {
					sb.append(i).append(" ");
				}
				sb.append("\n");
			}
			System.out.println(sb);
//			return;
			System.exit(0);
		}

		int r = blank.get(cnt)[0];
		int c = blank.get(cnt)[1];

		for (int i = 1; i <= 9; i++) {
			if (((block[(r / 3) * 3 + c / 3] | col[c] | row[r]) & (1 << i)) == 0) {
//				System.out.println(cnt + "번째, " + r + " 행 " + c + " 열 ," + i);
//				System.out.println(Integer.toBinaryString(row[r]));
//				System.out.println(Integer.toBinaryString(col[c]));
//				System.out.println(Integer.toBinaryString(block[(r / 3) * 3 + c / 3]));
//				System.out.println("-----------");
				map[r][c] = i;
				row[r] |= (1 << i);
				col[c] |= (1 << i);
				block[(r / 3) * 3 + c / 3] |= (1 << i);
				sudoku(cnt + 1);
				row[r] &= ~(1 << i);
				col[c] &= ~(1 << i);
				block[(r / 3) * 3 + c / 3] &= ~(1 << i);
			}
		}
	}

}
