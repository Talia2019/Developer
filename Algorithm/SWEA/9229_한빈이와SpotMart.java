package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_9229_한빈이와SpotMart_D3 {

	public static int[] snackes;
	public static boolean[] isSelected;
	public static int[] weights;
	public static int n, m;
	public static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			snackes = new int[n];
			isSelected = new boolean[n];
			weights = new int[2];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				snackes[i] = Integer.parseInt(st.nextToken());
			}

			findMax(0, 0);
//			System.out.println(max);
			
			sb.append("#").append(t).append(" ").append(max).append("\n");

			max = -1;
		}
		System.out.println(sb);

	}

	public static void findMax(int depth, int start) {
		if (depth == 2) {
			int weight = 0;
			for (int i = 0; i < 2; i++) {
				weight += weights[i];
//				System.out.print(weights[i] + " ");
			}
//			System.out.println();
			if (weight > m)
				return;
			max = max > weight ? max : weight;
			return;
		}

		for (int i = start; i < n; i++) {
			weights[depth] = snackes[i];
			findMax(depth + 1, i + 1);
		}
	}

}
