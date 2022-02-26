package boj;

import java.util.Scanner;

/**
 * 1로 만들기 https://www.acmicpc.net/problem/1463
 */
public class Main_boj_1263_실버3 {
//	X가 3으로 나누어 떨어지면, 3으로 나눈다.
//	X가 2로 나누어 떨어지면, 2로 나눈다.
//	1을 뺀다.
//	정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 
//	연산을 사용하는 횟수의 최솟값을 출력하시오.
//
//	입력
//	첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
//
//	출력
//	첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.

	//
	//****모든경우의수를 생각해야함******
	public static int[] cnt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		cnt = new int[n + 1];
		System.out.println(getCnt(n));
//		for (int i = 0; i < 100; i++) {
//			cnt = new int[i + 1];
//			System.out.println("i : " + i + " " + getCnt(i));
//		}
	}

	public static int getCnt(int n) {
		if (n == 1)		//***********1일때는 0이출력돼야한다.................**********
			return 0;
		if (n < 4)
			return 1;

		if (cnt[n] == 0) {
			int div3 = 1000000, div2 = 1000000, div;
			if (n % 3 == 0) {
				div3 = getCnt(n / 3) + 1;
			}
			if (n % 2 == 0) {
				div2 = getCnt(n / 2) + 1;
			}
			cnt[n] = getCnt(n - 1) + 1 < getCnt(n - 2) + 2 ? getCnt(n - 1) + 1 : getCnt(n - 2) + 2;
			div = div3 < div2 ? div3 : div2;
			cnt[n] = cnt[n] < div ? cnt[n] : div;
		}

		return cnt[n];
	}

}
