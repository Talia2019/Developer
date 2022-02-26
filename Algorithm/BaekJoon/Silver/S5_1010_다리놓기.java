package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S5_1010_다리놓기 {

//	S5 1010 다리놓기

//	다리를 n개만큼 지을것
//	다리는 겹쳐질 수 없음 
//	다리 짓는 경우의 수

//	mCn아님?
//	nCk = n-1Ck + n-1Ck-1

	public static int[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		int n, m;
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			dp = new int[m + 1][n + 1];
			sb.append(bc(m, n)).append("\n");
		}
		System.out.println(sb);
	}

	public static int bc(int n, int k) {
		if (dp[n][k] > 0)
			return dp[n][k];
		if (n == k || k == 0)
			return dp[n][k] = 1;

		return dp[n][k] = bc(n - 1, k) + bc(n - 1, k - 1);
	}

}
