package boj;

import java.util.Scanner;

public class Main_S2_2004_조합0의개수 {

//	nCm구하고 5의 개수 찾으면된다 생각했었는데 잘보니 n<=2000000000
//	n-m! m!의 5의 개수랑 분모의 5의개수 구하고 분모 - m! - (n-m)!해주면 될듯
//  다시보니 약분이 있으니까 5랑 2중에 뭐가 더 클지모름... 둘다구해서 작은거로
	
	public static int[][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();

//		dp = new int[n + 1][m + 1];
//		System.out.println(getZero(bc(n, m)));

		int five = getCnt(n, 5) - getCnt(m, 5) - getCnt(n-m, 5);
		int two = getCnt(n, 2) - getCnt(m, 2) - getCnt(n-m, 2);
		System.out.println(Math.min(five, two));
	}

//	public static int bc(int n, int k) {
//		if (dp[n][k] > 0)
//			return dp[n][k];
//		if (n == k || k == 0)
//			return dp[n][k] = 1;
//		return dp[n][k] = bc(n - 1, k - 1) + bc(n - 1, k);
//	}

//	얘네는 시간초과
	public static int findFive(int n) {
		int cur = 5;
		int answer = 0;
		while (cur <= n) {
			int num = cur;
			while (num >= 5) {
				if (num % 5 == 0)
					answer++;
				else
					break;
				num /= 5;
			}
			cur += 5;
		}
		return answer;
	}

	public static int findTwo(int n) {

		int cur = 2;
		int answer = 0;
		while (cur <= n) {
			int num = cur;
			while (num >= 2) {
				if (num % 2 == 0)
					answer++;
				else
					break;
				num /= 2;
			}
			cur += 2;
		}
		return answer;
	}
	
	public static int getCnt(int n, int t) {
		int answer = 0;
		while(n>=t) {
			answer += n/t;
			n/=t;
		}
		return answer;
	}
}
