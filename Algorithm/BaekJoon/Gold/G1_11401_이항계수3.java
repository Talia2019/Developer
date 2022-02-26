package boj;

import java.util.Scanner;

public class Main_G1_11401_이항계수3 {

	// 구하고자 하는것 = n!/(r!(n-r)!) mod p
	// n! (r!(n-r)!)^-1 mod p
	// (r!(n-r)!)mod p의 역원 구하기

	// 페르마의 정리 : a^p = a mod p
	// a^p mod p = a mod p
	// a^(p-1) mod p = 1 mod p
	// a * a^(p-2) mod p = 1 mod p
	// 즉 a mod p 의 역원은 a^(p-2)mod p

	// (n! * (r!(n-r)!)^(p-2)mod p) mod p
	// = (n! mod p * (r!(n-r)!)^(p-2)mod p) mod p

	public static final int MOD = 1000000007;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int k = sc.nextInt();

		long top = factorial(n);
		long bot = (factorial(k) * factorial(n - k)) % MOD;
		System.out.println((top * power(bot, MOD - 2)) % MOD);
	}

	public static long factorial(int n) {
		long res = 1;
		while (n > 1) {
			res = (res * n--) % MOD;
		}
		return res;
	}

	public static long power(long a, int n) {
		if (n == 1)
			return a;

		long tmp = power(a, n / 2);

		if (n % 2 != 0)
			return ((tmp * tmp) % MOD * a) % MOD;
		return (tmp * tmp) % MOD;
	}

}
