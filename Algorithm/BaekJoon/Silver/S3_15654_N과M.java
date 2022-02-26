package jiyoung.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NandM {

//	S3 15654 N과M

//	N개의 자연수 중에서 M개를 고른 수열

	public static int[] numbers, perm;
	public static boolean[] selected;
	public static int n, m;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		numbers = new int[n];
		selected = new boolean[n];
		perm = new int[m];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		permutation(0);
		System.out.println(sb);
	}

	public static void permutation(int cnt) {
		if (cnt == m) {
			for (int i : perm) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i = 0; i < n; i++) {
			if (selected[i])
				continue;
			perm[cnt] = numbers[i];
			selected[i] = true;
			permutation(cnt + 1);
			selected[i] = false;
		}
	}
}
