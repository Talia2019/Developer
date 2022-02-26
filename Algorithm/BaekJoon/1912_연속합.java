package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_1912_S2 {

	private static int[] arr;
	private static int[] cSum;
//	1912 연속합
//	n개의 정수로 이루어진 임의의 수열이 주어진다. 우리는 이 중 연속된 몇 개의 수를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 단, 수는 한 개 이상 선택해야 한다.
//	첫째 줄에 정수 n(1 ≤ n ≤ 100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.

	// dp
	// 계속 더해다가 더한게 큰지 안더한게 큰지
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		arr = new int[n];
		cSum = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			int get = getContinuitySum(i);
//			System.out.println(i + " : " + get);
			max = max > get ? max : get;
		}
		System.out.println(max);
	}

	public static int getContinuitySum(int n) {
		if (n >= arr.length)
			return 0;
		if (cSum[n] == 0) {
			int get = getContinuitySum(n + 1);
			if (get > 0)
				cSum[n] = get + arr[n];
			else
				cSum[n] = arr[n];
		}
		return cSum[n];
	}
}
