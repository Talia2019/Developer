package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_SWEA_1974_스도쿠검증_D2_102ms {

//	행, 열, 3*3용 binary flag
//	3*3의 인덱스
//	(0~2, 0~2) (0~2, 3~5) (0~2, 6~8)

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= tc; t++) {

			int[] row = new int[9];
			int[] col = new int[9];
			int[] square = new int[9];
			int input;
			
			for (int i = 0; i < 9; i++) {

				st = new StringTokenizer(br.readLine(), " ");

				for (int j = 0; j < 9; j++) {
					input = Integer.parseInt(st.nextToken());
					row[i] = row[i] | (1 << input);
					col[j] = col[j] | (1 << input);

					if (i < 3) {
						if (j < 3)
							square[0] = square[0] | (1 << input);
						else if (j < 6)
							square[1] = square[1] | (1 << input);
						else
							square[2] = square[2] | (1 << input);
					} else if (i < 6) {
						if (j < 3)
							square[3] = square[3] | (1 << input);
						else if (j < 6)
							square[4] = square[4] | (1 << input);
						else
							square[5] = square[5] | (1 << input);
					} else {
						if (j < 3)
							square[6] = square[6] | (1 << input);
						else if (j < 6)
							square[7] = square[7] | (1 << input);
						else
							square[8] = square[8] | (1 << input);
					}
				}
			}
			
			boolean flag = true;
			for (int i = 0; i < 9; i++) {
				if (row[i] != 1022 || col[i] != 1022 || square[i] != 1022) {
					flag = false;
					break;
				}
			}
			
			sb.append("#").append(t).append(" ");
			if (flag)
				sb.append(1);
			else
				sb.append(0);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
