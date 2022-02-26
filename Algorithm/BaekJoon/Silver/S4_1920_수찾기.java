package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_S4_1920_수찾기 {

	// 얘는 셋으로해도 될거같은데..

	// 셋으로 풀었을떼 556, 이분탐색으로 풀었을때 732
	public static int n;
	public static int[] a;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		a = new int[n];
		Set<String> set = new HashSet<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
//			set.add(st.nextToken());
			a[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(a);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
//			if (set.contains(st.nextToken()))
//				sb.append("1").append("\n");
//			else
//				sb.append("0").append("\n");
			sb.append(find(Integer.parseInt(st.nextToken()))).append("\n");
		}

		System.out.println(sb);

	}

	public static int find(int num) {
		int start = 0, end = n - 1;

		while (start <= end) {
			int mid = (start + end) / 2;

			if (a[mid] == num)
				return 1;
			if (a[mid] < num)
				start = mid + 1;
			else
				end = mid - 1;
		}
		return 0;
	}

}
