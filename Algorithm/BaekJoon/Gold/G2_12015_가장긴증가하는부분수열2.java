package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G2_12015_가장긴증가하는부분수열2 {

	// 기본적으로 n길이의 dp테이블의 원소 각각 n번의 비교를 했어야함
	// 모든 원소를 탐색하는 과정을 이분탐색으로 log n으로 줄일 수 있음
	// 배열의 총 길이 자체가 LIS의 값이 됨
	// i번째 위치에서 dp테이블에 채워진 원소의 총길이는 전체수열에서 해당 위치에서 가질수 있는 LIS의 길이를 의미
	// 원소의 값들은 i번째 원소가 부분수열의 길이를 늘릴 가능성이 있는지 파악할 원소로 구성됨
	// 즉, 해당 위치에서 기록된 dp배열의 길이 그 자체가 최종LIS값의 최적해가 됨
	// 단, LIS의 수는 구할 수 있지만 배열에 저장된 값이 실제 LIS와 일치하는것은 아님

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] numbers = new int[n];
		int[] dp = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		int lis = 0;
		for (int i = 0; i < n; i++) {
			int start = 0, end = lis;
			int idx = -1;
			while (start <= end) {
				int mid = (start + end) / 2;
				// 나보다 크거나 같은 값들중 가장 작은값 찾음
				if (dp[mid] >= numbers[i]) {
					idx = mid;
					end = mid - 1;
				} else {
					start = mid + 1;
				}
			}
			if (idx == -1)
				dp[lis++] = numbers[i];
			else
				dp[idx] = numbers[i];
		}

		System.out.println(lis);

	}

}
