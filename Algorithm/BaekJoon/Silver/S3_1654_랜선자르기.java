package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_1654_랜선자르기 {

	// 1~가장긴 랜선.. 이분탐색
	// 각 랜선의 이분탐색으로 찾은길이로 나눈 몫을 더해서 그게 k이상
	
	// start+end/2 해주는과정에서 int넘어갈수 있으므로 long으로 해줘야함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

		long start = 1;
		long end = 0;

		int[] lines = new int[k];
		for (int i = 0; i < k; i++) {
			lines[i] = Integer.parseInt(br.readLine());
			if (end < lines[i])
				end = lines[i];
		}

		long mid = end;
		long answer = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			long cnt = 0;
			for (int i = 0; i < k; i++) {
				cnt += lines[i] / mid;
			}

			if (cnt < n)
				end = mid - 1;
			else {
				answer = mid;
				start = mid + 1;
			}
		}

		System.out.println(answer);

	}

}
