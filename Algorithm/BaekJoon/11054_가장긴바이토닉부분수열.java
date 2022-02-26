package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_11054_G3 {
//	11054 가장 긴 바이토닉 부분 수열
//	수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 그 수열을 바이토닉 수열이라고 한다.
//	수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.

//	첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
//	첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.

//	10
//	1 5 2 1 4 3 4 5 2 1
//	출력 7

	// 증가부분수열과 감소부분수열을 따로구하고 각 인덱스별로 값 더해서 최대값찾기

	public static int[] LIS, LDS;
	public static int maxNum, maxIndex = -1;
	public static int max;
	private static int[] sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		LIS = new int[1002];
		LDS = new int[1002];
		sum = new int[1002];

		st = new StringTokenizer(br.readLine(), " ");
//		maxIndex = Integer.parseInt(st.nextToken());
//		LIS[maxIndex] = 1;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] += getLIS(arr[i]);
		}

		maxIndex = -1;
		maxNum = 0;
		for (int i = n - 1; i >= 0; i--) {
			sum[i] += getLDS(arr[i]);
		}

//		System.out.println(Arrays.toString(LIS));
//		System.out.println(Arrays.toString(LDS));

		for (int i = 0; i < 1000; i++) {
			max = max > sum[i] ? max : sum[i];
		}

		System.out.println(max - 1);
//		System.out.println(Arrays.toString(sum));
	}

	public static int getLIS(int n) {
		if (n > maxIndex) {
			LIS[n] = ++maxNum;
			maxIndex = n;
			return LIS[n];
		}
		LIS[n] = 1;
		int max = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (LIS[i] >= max) {
				max = LIS[i];
				LIS[n] = max + 1;
			}
		}
		maxNum = LIS[n] > maxNum ? LIS[n] : maxNum;
		return LIS[n];
	}

	public static int getLDS(int n) {
		if (n > maxIndex) {
			LDS[n] = ++maxNum;
			maxIndex = n;
//			System.out.println("N LDS[N] MAXNUM " + n + " " + LDS[n] + " " + maxNum);
			return LDS[n];
		}
		LDS[n] = 1;
		int max = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (LDS[i] >= max) {
				max = LDS[i];
				LDS[n] = max + 1;
//				System.out.println("I LDS N " + i + " " + LDS[n] + " " + i);
			}
		}
		maxNum = LDS[n] > maxNum ? LDS[n] : maxNum;
		return LDS[n];
	}

}
