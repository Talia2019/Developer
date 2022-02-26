package boj;

import java.util.Scanner;

public class Main_S1_11051_이항계수2 {

//	S1 11051 이항 계수 2

//	이항계수(n k)를 10007로 나눈 나머지를 구하기
//	1<=N<=1000, 0<=K<=N

	public static int n, k;
	public static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		k = sc.nextInt();

		dp = new int[n + 1][k + 1];

		System.out.println(binomialCoefficient(n, k));
	}

	// n+1Cr+1 = nCr+nCr+1
	public static int binomialCoefficient(int n, int k) {
		if (dp[n][k] > 0)
			return dp[n][k];

		if (n == k || k == 0)
			return dp[n][k] = 1;

		return dp[n][k] = (binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k)) % 10007;
	}

}
