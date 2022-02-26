package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_G5_2110_공유기설치 {

	// 그럼 이분탐색으로 최대거리를 정하고, 그거에 맞춰서 설치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		long[] houses = new long[n];
		for (int i = 0; i < n; i++) {
			houses[i] = Long.parseLong(br.readLine());
		}

		Arrays.sort(houses);

		long start = 0, end = houses[n - 1], mid = 0;
		long max = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			int set = c - 1;
			long prev = houses[0];

			for (int i = 1; i < n; i++) {
				if (houses[i] - prev >= mid) {
					set--;
					prev = houses[i];
				}
			}

			if (set > 0)
				end = mid - 1;
			else {
				max = mid;
				start = mid + 1;
			}
		}

		System.out.println(max);

	}

}

// 1 2 4 8 9
