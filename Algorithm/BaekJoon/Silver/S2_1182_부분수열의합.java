package jiyoung.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SumOfPowerSet {

//	S2 1182 부분수열의합

//	1<=n<=20 2^20이면 완탐되는건가?
//	음수값이 있어서 백트래킹하려면 정렬해야될듯

//	백트래킹으로 해보려다가 답이 음수인경우 sum기준을 잡기가 힘들어서 그냥 완탐으로했다!
	public static int n, s, totalCnt;
	public static int[] nums;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		nums = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(nums);
		subSet(0, 0);
		if(s==0) totalCnt--;
		System.out.println(totalCnt);
	}

	public static void subSet(int cnt, int sum) {
//		if (sum > s)
//			return;
		if (cnt == n) {
			System.out.println(sum);
			if (sum == s)
				totalCnt++;
			return;
		}
		subSet(cnt + 1, sum + nums[cnt]);
		subSet(cnt + 1, sum);
	}
}
