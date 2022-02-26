package boj;

import java.util.Scanner;

public class Main_B1_11050_이항계수1 {

//	B1 11050 이항 계수1

//	nCk구하기.. 분모분자 따로구해서 나눔
//	이항계수는 반드시 정수이기에 걍 나눠도될듯?
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		int up = 1, down = 1;
		for (int i = 0; i < k; i++) {
			up *= (n - i);
			down *= (i + 1);
		}

		System.out.println(up / down);
	}

//	이상적인 방법
//	파스칼의 법칙 + 메모라이징기법 사용
//	조합의 특징
//	n+1Cr+1 = nCr + cCr+1
//	(n r) = (n-1 r-1) + (n-1 r) => 파스칼의 법칙
	public static int n = 5, k = 3;
	public static int[][] dp = new int[n + 1][k + 1];

	public static int BC(int n, int k) {

		if (dp[n][k] > 0)
			return dp[n][k];

		if (n == k || k == 0)
			return 1;
		// nCn = nC0 = 1

		return dp[n][k] = BC(n - 1, k - 1) + BC(n - 1, k);
	}
}
