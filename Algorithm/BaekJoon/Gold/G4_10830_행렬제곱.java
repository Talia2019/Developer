package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G4_10830_행렬제곱 {

	public static int n;
	public static final int MOD = 1000;
	public static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());

		matrix = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken())%MOD;
			}
		}

		matrix = powerMatrix(b, matrix);
		StringBuilder sb = new StringBuilder();
		for (int[] is : matrix) {
			for (int i : is) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int[][] powerMatrix(long b, int[][] mat) {
		if (b == 1) {
			return mat;
		}
		mat = powerMatrix(b / 2, mat);

		if (b % 2 != 0) {
			return mulMatrix(mulMatrix(mat, mat), matrix);
		}
		return mulMatrix(mat, mat);
	}

	public static int[][] mulMatrix(int[][] mat1, int[][] mat2) {
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int sum = 0;
				for (int k = 0; k < n; k++) {
					sum = (sum + (mat1[i][k] * mat2[k][j]) % MOD) % MOD;
				}
				mat[i][j] = sum;
			}
		}
		return mat;
	}

}
