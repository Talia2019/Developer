package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_6026_D4_성수의비밀번호공격 {

	// 나머지 숫자의 중복순열

	public static final long DIVNUM = 1000000007;
	public static int n, m;
	public static long[][] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());

			dp = new long[m + 1][m + 1];

			int m2 = m;
			int mul = 1;
			long result = 0, total = 0;

			while (m2 > 0) {
				result = (mul * ncr(m, m2) * power(m2--, n)) % DIVNUM;
				result %= DIVNUM;
				mul *= -1;
				total = (total + result + DIVNUM) % DIVNUM;
			}

			sb.append("#").append(tc).append(" ").append(total).append("\n");
		}
		System.out.println(sb);
	}

	public static long ncr(int n, int k) {
		if (dp[n][k] > 0)
			return dp[n][k];
		if (n == k || k == 0)
			return dp[n][k] = 1;
		return dp[n][k] = (ncr(n - 1, k - 1) + ncr(n - 1, k)) % DIVNUM;
	}

	public static long power(int n, int k) {
		if (k == 1)
			return n;

		long half = power(n, k / 2);
		if (k % 2 == 0)
			return (half * half) % DIVNUM;
		else
			return ((half * half) % DIVNUM * (n) % DIVNUM) % DIVNUM;
	}

}
