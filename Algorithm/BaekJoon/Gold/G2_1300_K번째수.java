package boj;

import java.util.Scanner;

public class Main_G2_1300_K번째수 {

	// N <= 100000
	// k <= min(1000000000, N^2)

	// 어렵당..
	// i*j <= S -> j<=S/i -> i행에서 S보다 작거나 같은 숫자의 갯수는 (int)S/i 임을 알 수 있음
	// 즉, 각 행에서 S보다 작거나 같은 숫자의 수 = min(S/i, N)
	// 또한 B[k]<=k라고함

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		long n = sc.nextInt();
		long k = sc.nextInt();

		long start = 1, end = k;
		long answer = k;
		while (start <= end) {
			long mid = (start + end) / 2;
			long cum = 0;
			for (int i = 1; i <= n; i++) {
				cum += Math.min(mid / i, n);
			}
			if (cum < k)
				start = mid + 1;
			else {
				answer = mid;
				end = mid - 1;
			}
		}
		
		System.out.println(answer);
	}

}
