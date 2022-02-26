package jiyoung.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Electric {

	// G2 1365 꼬인 전깃줄
	// 최장증가수열(LIS)찾기
	// dp의 각 인덱스를 LIS길이라고 생각하고 해당 칸에 LIS길이를 가진 가장 작은 값을 넣음

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] electric = new int[n + 1];
		int[] mem = new int[n + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++) {
			electric[i] = Integer.parseInt(st.nextToken());
		}

		// 나보다 작은 값을 가진 최대인덱스를 찾아야함
		int longestLen = 1;
		mem[1] = electric[1];
		for (int i = 1; i <= n; i++) {
			if (electric[i] > mem[longestLen]) {
				mem[++longestLen] = electric[i];
			} else {
				int index = Arrays.binarySearch(mem, 0, longestLen, electric[i]);
				if (index < 0) {
					index = index * -1 - 1;
				}
				mem[index] = electric[i];
			}
		}
		System.out.println(n - longestLen);
	}

}
