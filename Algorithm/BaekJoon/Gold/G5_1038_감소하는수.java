package jiyoung.week7;

import java.util.Scanner;

public class DecreasingNumber {

//	G5 1038 감소하는 수 

//	음이 아닌 정수 X의 자릿수가 가장 큰 자릿수부터 작은 자릿수까지 감소한다면, 그 수를 감소하는 수라고 한다
//	N번째 감소하는 수를 출력하는 프로그램을 작성하시오. 0은 0번째 감소하는 수이고, 1은 1번째 감소하는 수이다. 만약 N번째 감소하는 수가 없다면 -1을 출력한다.

//	N은 1,000,000보다 작거나 같은 자연수 또는 0이다.

//	음.. 1부터 증가시키면서 해당 수가 감소하는수인지 확인?
//	시간초과날듯 ㅎㅎ.. -> 시간초과

//	앞자리수보다 작은 수만 선택 가능하도록 수를 생성
	
	public static int cnt = -1;
	public static int[] numbers;
	public static int n, length, answer = -1;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		int i = 0;
		if (n == 1022) 	//출력해보니까 얘가 끝
			System.out.println("9876543210");
		else if (n > 1022)
			System.out.println(-1);
		else {
			f: for (i = 0; i <= 10; i++) {	//숫자 길이
				length = i;	
				for (int j = 0; j < 10; j++) {	//시작 숫자
					makeDecreasingNum(0, j);
					if (cnt >= n)
						break f;
				}
			}

			System.out.println(answer);
		}
	}

//	시간초과
//	public static void isDecrease(int n) {
//		int prev = n % 10;
//		n /= 10;
//		int cur = n % 10;
//		while (n > 0) {
//			if (prev >= cur)
//				return;
//			prev = n % 10;
//			n /= 10;
//			cur = n % 10;
//		}
//		cnt++;
//		return;
//	}

//	앞자리수보다 작은 수만 선택 가능하도록 수를 생성
	public static void makeDecreasingNum(int l, int number) { // length : 자릿수, number : 현재 숫자
		if (l == length) {
			cnt++;
			if (cnt == n)	//정답
				answer = number;
			return;
		}
		for (int i = 0; i < number % 10; i++) {	//현재수의 마지막자리보다 작은수만 들어가도록
			makeDecreasingNum(l + 1, number * 10 + i);	
		}

	}

}
