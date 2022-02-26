package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_S4_10816_숫자카드2 {
	
	// map으로 될거같은디
	// 이분탐색으로 하려했던건 set추가해서 메모이제이션 넣으면 시간안에 통과할듯

	public static int n;
	public static int[] numbers;
	public static Map<Integer, Integer> numberMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		numbers = new int[n];
		numberMap = new HashMap<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
//			numbers[i] = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());
			int value = numberMap.getOrDefault(key, 0);
			numberMap.put(key, ++value);
		}
//		Arrays.sort(numbers);

		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
//			sb.append(find(Integer.parseInt(st.nextToken()))).append(" ");
			sb.append(numberMap.getOrDefault(Integer.parseInt(st.nextToken()), 0)).append(" ");
		}
		System.out.println(sb);
	}

	public static int find(int num) {
		int start = 0, end = n - 1, mid;

		while (start <= end) {
			mid = (start + end) / 2;
			if (numbers[mid] == num) {
				int first = mid, last = mid;
				while (first >= 0 && numbers[first] == num) {
					first--;
				}
				while (last < n && numbers[last] == num) {
					last++;
				}
				return last - first - 1;
			}
			if (numbers[mid] < num)
				start = mid + 1;
			else
				end = mid - 1;
		}

		return 0;
	}

}
