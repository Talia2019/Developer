package boj;

import java.util.Scanner;

public class Main_S1_1629_곱셈 {

	// a*b mod c
	public static long BASE, num, MOD;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BASE = sc.nextInt();
		num = sc.nextInt();
		MOD = sc.nextInt();

		BASE %= MOD;
		System.out.println(power(num));

	}

	public static long power(long n) {
		if (n == 1)
			return BASE;

		long tmp = power(n / 2);

		if (n % 2 == 1)
			return ((tmp * tmp) % MOD * BASE) % MOD;
		return (tmp * tmp) % MOD;
	}

}
