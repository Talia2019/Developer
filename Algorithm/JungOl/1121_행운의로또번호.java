package jongol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1121_행운의로또번호 {

	// 첫번째 숫자를 a라 할때
	// a는 M/(2^(n-1))보다 작거나 같아야함

	// 메모이제이션필요
	// 각 열마다 현재 내 숫자/2보다 작거나 같은애들의 합을 넣음
	// 예) 현재 2번째 숫자가 4라면(mem[2][4]) 여기엔 첫번째숫자가 1,2인애들의 합을 넣음
	// mem[2][4] = mem[1][1]+mem[1][2]

	public static int n, m;
	public static long totalCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;

			// 첫번째 숫자가 될 수 있는 최대 수
			int firstNum = m / (1 << (n - 1));

			long[][] mem = new long[n + 1][m + 1];

			for (int i = 1; i <= firstNum; i++) // 첫번째숫자들(1행)
				mem[1][i] = 1;
			
			// 행 : 몇번째숫자인지
			// 열 : 어떤숫자인지
			// 값 : 나타날수있는 개수
			for (int i = 2; i <= n; i++) { //2행부터
				for (int j = 1<<(i-1); j <= m; j++) { //반드시 2^(i-1)부터만 가능함
					for (int k = 1; k <= j / 2; k++) {
						mem[i][j] += mem[i - 1][k];
					}
				}
			}
			
			// 마지막 행들 더하면될듯
			totalCnt = 0;
			for (int i = 1; i <= m; i++) {
				totalCnt += mem[n][i];
			}
			
			sb.append(totalCnt).append("\n");
		}

		System.out.println(sb);
	}

// 	public static void combination(int cnt, int start) {
// 		if (cnt == n) {
// 			totalCnt++;
// 			return;
// 		}
// //		if (cnt == n - 1 && start <= m / 2) {
// //			totalCnt += (m - 2 * start) + 1;
// //			return;
// //		}

// 		for (int i = start * 2; i <= m; i++) {
// 			combination(cnt + 1, i);
// 		}
// 	}

}
