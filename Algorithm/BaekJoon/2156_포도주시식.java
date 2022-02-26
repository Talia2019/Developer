package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//https://www.acmicpc.net/problem/2156
public class Main_boj_2156_실버1 {
//	포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
//	연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
//	효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다. 
//	1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을 때, 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오. 
//
//	예를 들어 6개의 포도주 잔이 있고, 각각의 잔에 순서대로 6, 10, 13, 9, 8, 1 만큼의 포도주가 들어 있을 때, 첫 번째, 두 번째, 네 번째, 다섯 번째 포도주 잔을 선택하면 총 포도주 양이 33으로 최대로 마실 수 있다.
//
//	입력
//	첫째 줄에 포도주 잔의 개수 n이 주어진다. (1≤n≤10,000) 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다. 포도주의 양은 1,000 이하의 음이 아닌 정수이다.
//
//	출력
//	첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
	public static int[] wine;
	public static Integer[] sum;
	//int가 아닌 Integer로 한것은 포도주잔이 0이 가능했기 때문.. null이라는 새로운 비교값이 필요했음

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		wine = new int[n+1];
		sum = new Integer[n+1];
		
		for (int i = 1; i < n+1; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		sum[0] = 0;
		sum[1] = wine[1];
		if(n>1)
			sum[2] = wine[1] + wine[2];
		
		System.out.println(getSum(n));
//		int max = 0;
//		for (int i = 0; i < n; i++) {
//			int gs = getSum(i, 0);
//			max = max > gs ? max : gs;
//		}
//		System.out.println(max);
//		int k = getSum(0, 0);
//		int k2 = getSum(1, 0) > getSum(2, 0) ? getSum(1, 0) : getSum(2, 0);
//		k = k > k2 ? k : k2;
//		System.out.println(k);
//		System.out.println(getSum(0, 0) > getSum(1, 0) ? getSum(0, 0) : getSum(0, 1));
	}

	// 다른풀이참고....
	public static int getSum(int n) {
		// 세잔을 연속으로 못하니 나올수 있는 경우는 xoo, oxo, oox 셋중 하나
		if (n == 0)
			return 0;
//		if(n==0) return sum[n];
//		if(n==1) return sum[n];
		if (sum[n] == null) {
//			int xoo, oxo, oox, max = 0;
//			xoo = getSum(n - 3) + wine[n - 1] + wine[n];
//			oxo = getSum(n - 2) + wine[n];
//			oox = getSum(n - 1);
//			max = xoo > oxo ? xoo : oxo;
//			max = max > oox ? max : oox;
//			sum[n] = max;
			sum[n] = Math.max(Math.max(getSum(n - 3) + wine[n - 1] + wine[n], getSum(n - 2) + wine[n]), getSum(n - 1));
		}

		return sum[n];

		//시간초과
//		if (n >= sum.length) { // 넘어감
//			return 0;
//		}
//		if (n == sum.length - 1) { // 마지막칸
//			return wine[n];
//		}
//		
//		int one = 0, two = 0, three = 0;
//		if (sum[n][0] == 0)
//			sum[n][0] = wine[n] + getSum(n + 1, d + 1);
//		one = sum[n][0];
//		if (d < 1) { // 한칸 가능
//		}
//		if (sum[n][1] == 0)
//			sum[n][1] = wine[n] + getSum(n + 2, 0);
//		if (sum[n][2] == 0)
//			sum[n][2] = wine[n] + getSum(n + 3, 0);
//		two = sum[n][1];
//		three = sum[n][2];
//
//		int res = one > two ? one : two;
//		res = res > three ? res : three;
////		System.out.println("n" + n + " " + d + " " + one + " , " + two);
//		return res;

	}
}
