package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S1_14888_연산자끼워넣기 {

	// 숫자 수열로.. 완탐한다면 n-1!*n = n!

	public static int n, max, min;
	public static int[] selected, numbers, oper;
	public static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		selected = new int[n - 1];
		oper = new int[n - 1]; // + - * /
		visited = new boolean[n];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int n = Integer.parseInt(st.nextToken());
			for (int j = 0; j < n; j++) {
				oper[cnt++] = i;
			}
		}

		permutation(numbers[0], 0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void permutation(int calc, int cnt) {
		if (cnt == n - 1) {
			if (calc > max)
				max = calc;
			if (calc < min)
				min = calc;

			return;
		}

		for (int i = 0; i < n - 1; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			switch (oper[i]) {
			case 0:
				permutation(calc + numbers[cnt + 1], cnt + 1);
				break;
			case 1:
				permutation(calc - numbers[cnt + 1], cnt + 1);
				break;
			case 2:
				permutation(calc * numbers[cnt + 1], cnt + 1);
				break;
			case 3:
				permutation(calc / numbers[cnt + 1], cnt + 1);
				break;
			}
			visited[i] = false;
		}
	}

}
