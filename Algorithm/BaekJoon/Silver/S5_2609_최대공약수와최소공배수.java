package boj;

import java.util.Scanner;

public class Main_S5_2609_최대공약수와최소공배수 {

//	S5 2609 최대공약수와최소공배수

//	두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
//	첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.
//	첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

//	약수는 작은수를 1씩 빼가면서 둘다 나눠지는 가장 큰수
//	배수는 큰수에 곱해가며 둘다 나눠지는 가장 작은수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		if (a > b) {
			int tmp = a;
			a = b;
			b = tmp;
		}

		int divisor = a;
		while (divisor > 0) {
			if (a % divisor == 0 && b % divisor == 0) {
				System.out.println(divisor);
				break;
			}
			divisor--;
		}

		int multiple;
		int m = 1;
		while (true) {
			multiple = b * m++;
			if (multiple % a == 0 && multiple % b == 0) {
				System.out.println(multiple);
				break;
			}
		}
	}

	// 이상적인 방법!!
	// 유클리드 호제법
	// r = a%b라 할때, (a,b)의 최대공약수는 (b,r)의 최대공약수와 같다
	// 최소공배수는 두수의 곱에 최대공약수를 나눠주면된다

	int gdc(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

	int lcm(int a, int b) {
		return a * b / gdc(a, b);
	}

}
