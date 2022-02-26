package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] use = new int[3];
		Arrays.fill(use, -1);

		if (n % 5 == 0)
			use[0] = n / 5;

		if (n % 3 == 0)
			use[1] = n / 3;

		int weight = n;
		while (weight > 5) {
			weight -= 5;
			if (weight % 3 == 0) {
				use[2] = (n - weight) / 5 + weight / 3;
			}
		}

		Arrays.sort(use);
		
		int result = use[2];
		if (use[0] != -1)	//-1이 아닌경우
			result = use[0];
		else if (use[1] != -1)	//-1이고, -1아닌경우
			result = use[1];

		System.out.println(result);

	}

}
