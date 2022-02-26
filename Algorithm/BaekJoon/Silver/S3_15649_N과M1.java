package boj;

import java.util.Scanner;

public class Main_S3_15649_N과M1 {

//	S3 15649 N과M(1) 
//	백트래킹

//	1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
	public static int n, m;
	public static int[] numbers;
	public static boolean[] visited;
	public static StringBuilder sb;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		m = sc.nextInt();
		numbers = new int[m];
		visited = new boolean[n+1];
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

		for (int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			numbers[cnt] = i;
			visited[i] = true;
			permutation(cnt + 1, i + 1);
			visited[i] = false;
		}
	}
}
