package jiyoung.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberCard {

	// S4 10815 숫자카드

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		int[] deck = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			deck[i] = Integer.parseInt(st.nextToken());
		}

		int m = Integer.parseInt(br.readLine());
		int[] numbers = new int[m];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(deck);

		for (int i = 0; i < m; i++) {
			if (Arrays.binarySearch(deck, numbers[i]) >= 0)
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}
		System.out.println(sb);

	}

	// binarysearch말고 구현해보면 이런식으로
	public static boolean contains(int[] arr, int card) {
		int start = 0;
		int end = arr.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == card)
				return true;
			else if (arr[mid] < card)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return false;
	}

}
