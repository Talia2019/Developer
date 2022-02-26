package jiyoung.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Budget {

	// S3 2512 예산

	// 이런문제에서 이분탐색이용하는거래
	// left 0, right 최대예산 두고 찾아감

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] areas = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		int maxArea = 0;
		for (int i = 0; i < n; i++) {
			areas[i] = Integer.parseInt(st.nextToken());
			if (areas[i] > maxArea)
				maxArea = areas[i];
		}

		int maxBuget = Integer.parseInt(br.readLine());

		int l = 0, r = maxArea;
		int maxLimit = 0;
		while (l <= r) {
			int limit = maxBuget;
			int mid = (l + r) / 2;
			for (int i = 0; i < n; i++) {
				if (limit < 0)
					break;
				if (areas[i] < mid)
					limit -= areas[i];
				else
					limit -= mid;
			}

			if (limit >= 0) {
				l = mid + 1;
				if (mid > maxLimit)
					maxLimit = mid;
			} else {
				r = mid - 1;
			}
		}
		System.out.println(maxLimit);
	}

}
