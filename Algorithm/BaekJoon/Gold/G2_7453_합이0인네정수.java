package jiyoung.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FourInteger {

	// G2 7453 합이 0인 네 정수

	// 흠.. 배열 두개씩 각 합들을 전부 구해놓고.. 그담에 결과끼리 투포인터로 비교해보면 될듯?
	// 4000*4000 * 2
	// 각각 정렬 후 투포인터로

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		long[][] numbers = new long[4][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {
				numbers[j][i] = Long.parseLong(st.nextToken());
			}
		}

		long[][] sums = new long[2][n * n];
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sums[0][cnt] = numbers[0][i] + numbers[1][j];
				sums[1][cnt++] = numbers[2][i] + numbers[3][j];
			}
		}

		Arrays.sort(sums[0]);
		Arrays.sort(sums[1]);

		int len = n * n;
		int first = 0, second = len - 1;
		long totalCnt = 0;
		while (first < len && second >= 0) {
//			System.out.println(first + ", "+second + ": "+sums[0][first]+", "+sums[1][second]);
			if (sums[0][first] > 0) {// 하나 부호 바뀌었을때 다른쪽도 부호바뀔때까지
				while (sums[1][second] > 0) {
					second--;
				}
			}
			if (sums[1][second] < 0) {
				while (sums[0][first] < 0) {
					first++;
				}
			}

			long value = sums[0][first] + sums[1][second];
			if (value == 0) {
				long ftmp = sums[0][first];
				long stmp = sums[1][second];
				//여기 중간 cnt마저 long으로 해줘야됨! 이부분 생각못해서 좀틀렸음
				long cnt1 = 1, cnt2 = 1;
				while (++first < len && sums[0][first] == ftmp)
					cnt1++;
				while (--second >= 0 && sums[1][second] == stmp)
					cnt2++;
				totalCnt += cnt1 * cnt2;
			} else if (value < 0) {
				first++;
			} else {
				second--;
			}
		}

		System.out.println(totalCnt);

	}

}
