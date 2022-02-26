package boj;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main_G3_11444_피보나치수6 {

	// n번째 피보나치 수 구하기

	// fn+2 = [1 1][fn+1 fn]^T
	// fn+1 = [1 0][fn+1 fn]^T
	// [fn+2] = [1 1][fn+1]
	// [fn+1] [1 0][fn ]
	// -> 1110 행렬을 A라 할때
	// un = [fn+1]
	// [fn ]
	// un+1 = A un
	// 즉 un = A^n u0
	
	// 더 간소화
	// A^n = [fn+1 fn]
	// 		 [fn fn-1]

	public static final int MOD = 1000000007;
	public static int[] dp;
	public static long[][] matrix = { { 1, 1 }, { 1, 0 } };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long n = sc.nextLong();
//		Map<Long, Long> hash = new HashMap<Long, Long>();
//
//		hash.put(0L, 0L);
//		hash.put(1L, 1L);
//		hash.put(2L, 1L);
//		for (long i = 3; i <= n; i++) {
//			hash.put(i, (hash.get(i - 1) + hash.get(i - 2)) % MOD);
//		}
//		System.out.println(hash.get(n));

		matrix = powMatrix(n - 1, matrix);
		System.out.println(matrix[0][0]);
	}

	public static long[][] powMatrix(long p, long[][] mat) {
		if (p <= 1)
			return mat;

		long[][] half = powMatrix(p / 2, mat);

		if (p % 2 == 0)
			return mulMatrix(half, half);
		return mulMatrix(mulMatrix(half, half), matrix);
	}

	public static long[][] mulMatrix(long[][] mat1, long[][] mat2) {
		long[][] newMat = new long[2][2];

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				long sum = 0;
				for (int l = 0; l < 2; l++) {
					sum = (sum + (mat1[i][l] * mat2[l][j]) % MOD) % MOD;
				}
				newMat[i][j] = sum;
			}
		}

		return newMat;
	}
}
