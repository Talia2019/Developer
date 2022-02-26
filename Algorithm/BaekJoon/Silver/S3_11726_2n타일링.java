package jiyoung.week10;

import java.util.Scanner;

public class Tiling2n {

	// S3 11726 2n타일링

	// 이전거에 세로하나 붙이고, 이전이전거에 가로두개붙이고 -> an-1 + an-2일듯

	public static int[] dp;
	public static final int MOD = 10007;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		dp = new int[n + 1];

		dp[1] = 1;
		if(n>1)
			dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
		}
		
		System.out.println(dp[n]);
	}

}
