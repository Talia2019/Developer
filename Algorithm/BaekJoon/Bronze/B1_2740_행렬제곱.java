package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1_2740_행렬제곱 {

	public static int[][] matrix1, matrix2, matrix3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		matrix1 = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				matrix1[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine(), " ");
		st.nextToken();
		int k = Integer.parseInt(st.nextToken());

		matrix2 = new int[m][k];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < k; j++) {
				matrix2[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		matrix3 = new int[n][k];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k; j++) {
				int sum = 0;
				for (int l = 0; l < m; l++) {
					sum += matrix1[i][l] * matrix2[l][j];
				}
				matrix3[i][j] = sum;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int[] is : matrix3) {
			for (int i : is) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
