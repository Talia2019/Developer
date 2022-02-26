package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_G5_2981_검문 {

//	G5 2981 검문

//	숫자 N개를 종이에 적는다. 그 다음, 종이에 적은 수를 M으로 나누었을 때, 나머지가 모두 같게 되는 M을 모두 찾으려고 한다. M은 1보다 커야 한다.
//	N개의 수가 주어졌을 때, 가능한 M을 모두 찾는 프로그램을 작성하시오.

//	첫째 줄에 종이에 적은 수의 개수 N이 주어진다. (2 ≤ N ≤ 100)
//	다음 줄부터 N개 줄에는 종이에 적은 수가 하나씩 주어진다. 이 수는 모두 1보다 크거나 같고, 1,000,000,000보다 작거나 같은 자연수이다. 같은 수가 두 번 이상 주어지지 않는다.
//	항상 M이 하나 이상 존재하는 경우만 입력으로 주어진다.
//	첫째 줄에 가능한 M을 공백으로 구분하여 모두 출력한다. 이때, M은 증가하는 순서이어야 한다.

//	2부터 가장 작은 수까지 m을 찾음 
//	10억이 100번?..... 시간초과 날거같은뎅.. -> 시간초과

//	A1 = M*a1 + r
//	A2 = M*a2 + r
//	A3 = M*a3 + r
//	A3-A2 = M(a3-a2)
//	A2-A1 = M(a2-a1)
//	즉 M은 A3-A2와 A2-A1의 공약수! -> 둘의 최대공약수를 구하고 최대공약수의 약수의 수가 정답

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		int[] numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(numbers);

		int gcd = numbers[1] - numbers[0];
		for (int i = 2; i < n; i++) {
			gcd = gcd(gcd, numbers[i] - numbers[i - 1]);
		}

//		for (int i = 2; i <= gcd; i++) {
//			if (gcd % i == 0)
//				sb.append(i).append(" ");
//		}

		List<Integer> list = new ArrayList<>();
//		약수구하기2
//		n % i == 0 의 의미는 n/i 한 수와 i의 곱은 n 이다. -> 제곱근까지만 탐색
		for (int i = 2; i <= Math.sqrt(gcd); i++) {
			// i의 제곱이 gcd의 약수 - 중복방지를 위해 한번만 추가
			if (i * i == gcd)
				list.add(i);
			// i가 약수라면 i와 나눈 몫 추가
			else if (gcd % i == 0) {
				list.add(i);
				list.add(gcd / i);
			}
		}
		
		Collections.sort(list);
		for (Integer integer : list) {
			sb.append(integer).append(" ");
		}
		//최대공약수
		sb.append(gcd);
		
		System.out.println(sb);

	}

	public static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
}
