package boj;

import java.util.Scanner;

public class Main_S3_15652_N과M4 {

	public static int n, m;
	public static StringBuilder sb;
	public static int[] numbers;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		sb = new StringBuilder();
		numbers = new int[m];
		
		permutation(0, 1);
		System.out.println(sb);

	}
	
	public static void permutation(int cnt, int start) {
		if(cnt==m) {
			for (int i : numbers) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = start; i <= n; i++) {
			numbers[cnt] = i;
			permutation(cnt+1, i);
		}
	}

}
