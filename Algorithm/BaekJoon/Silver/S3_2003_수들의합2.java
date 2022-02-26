package jiyoung.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberSum {

	// S3 2003 수들의 합2
	
	// sum은 long이 될 수 있다 생각했었는데 m도 long일수있다는걸 한참늦게 깨달았따.....ㅠ삽질

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());

		int[] numbers = new int[n + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		long sum = 0, cnt = 0;
		int start = 0, end = 0;

		while (end <= n) {
			if (sum >= m)
				sum -= numbers[start++];
			else if (sum < m)
				sum += numbers[end++];
			if (sum == m)
				cnt++;
		}

		System.out.println(cnt);
	}

}
