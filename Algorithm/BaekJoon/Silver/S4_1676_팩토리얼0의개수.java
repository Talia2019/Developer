package boj;

import java.util.Scanner;

public class Main_S4_1676_팩토리얼0의개수 {

//	S4 1676 팩토리얼 0의 개수

//	N!에서 뒤에서부터 처음 0이 아닌 숫자가 나올 때까지 0의 개수를 구하는 프로그램을 작성하시오.
//	첫째 줄에 N이 주어진다. (0 ≤ N ≤ 500)
//	첫째 줄에 구한 0의 개수를 출력한다.

//	5*2가 있어야 뒤에0이 붙음 2가 항상5보다 많을수밖에없으니까 5의 개수만 구하면됨
//	n을 5로나눈 몫이 답일듯?
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

//		for (int i = 0; i < 500; i++) {
//			System.out.println(i+" "+findFive(i));
//		}
		int n = sc.nextInt();
		System.out.println(findFive(n));
		
//		while (N >= 5) {
//            totalZero += N / 5;
//            N /= 5;
//        }
//		그냥 이렇게 하면됨!
	}

	public static int findFive(int n) {
		int cur = 5;
		int answer = 0;
		while (cur <= n) {
			int num = cur;
			while(num>=5) {
				if(num%5==0)
					answer++;
				else 
					break;
				num/=5;
			}
			cur += 5;
		}
		return answer;
	}

}
