package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_2805_나무자르기 {
	
	// 얘도 long으로 해야함!

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		long[] trees = new long[n];
		long max = 0;
		for (int i = 0; i < n; i++) {
			trees[i] = Integer.parseInt(st.nextToken());
			if (max < trees[i])
				max = trees[i];
		}

		long start = 0, end = max;

		while (start <= end) {
			long mid = (start + end) / 2;
			long cum = 0;

			for (int i = 0; i < n; i++) {
				if (trees[i] > mid)
					cum += trees[i] - mid;
			}
			if (cum < m)
				end = mid - 1;
			else {
				max = mid;
				start = mid + 1;
			}
		}

		System.out.println(max);

	}

}
