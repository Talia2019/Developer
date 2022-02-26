package boj;

import java.util.Scanner;

public class Main_S3_15650_N과M2 {

//	S3 15649 N과M(2) 
//	백트래킹

	public static int n, m;
	public static int[] numbers;
	public static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		numbers = new int[m];
		sb = new StringBuilder();

		permutation(0, 1);
		System.out.println(sb);
	}

	public static void permutation(int cnt, int start) {
		if (cnt == m) {
			for (int i : numbers) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i <= n; i++) {
			numbers[cnt] = i;
			permutation(cnt + 1, i + 1);
		}
	}
}
