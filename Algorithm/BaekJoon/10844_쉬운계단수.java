package boj;

import java.util.Scanner;

public class Main_Boj_10844_실버1 {
//	N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
//
//	입력
//	첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
//
//	출력
//	첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
	public static long[][] cnt = new long[10][101];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long sum = 0;
		for (int i = 1; i < 10; i++) {
			sum+=getCnt(i, n);
			sum%=1000000000;
		}
//		System.out.println(getCnt(n)%1000000000);s\
		System.out.println(sum);
	}

  //*********매 계산마다 나눠주는걸 안했어서 오버플로우 발생했었음*********
	public static long getCnt(int n, int d) { // 현재 숫자에서 나올수 있는 애들 더하기
		if (d == 1) {
//			if(n<1 || n>=9) return 1;
//			return 2;
			return 1;
		}
		if (cnt[n][d] == 0) {
			long back = 0, front = 0;
			if (n <= 0)
				front = 0;
			else
				front = getCnt(n - 1, d - 1) % 1000000000;
			if (n >= 9)
				back = 0;
			else
				back = getCnt(n + 1, d - 1) % 1000000000;
			cnt[n][d] = (back + front)%1000000000;
		}
		return cnt[n][d];
	}
  
}
