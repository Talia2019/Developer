package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_2805_D3 {
//	   ① 농장은 크기는 항상 홀수이다. (1 X 1, 3 X 3 … 49 X 49)
//	   ② 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능하다.
//	농장의 크기 N와 농작물의 가치가 주어질 때, 규칙에 따라 얻을 수 있는 수익은 얼마인지 구하여라.
//	농장의 크기 N은 1 이상 49 이하의 홀수이다. (1 ≤ N ≤ 49)
//	농작물의 가치는 0~5이다.

//	[입력]
//	가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
//	각 테스트 케이스에는 농장의 크기 N과 농장 내 농작물의 가치가 주어진다.
//
//	[출력]
//	각 줄은 '#t'로 시작하고, 공백으로 농장의 규칙에 따라 얻을 수 있는 수익을 출력한다.
//	(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)

	// n/2행 n/2열부터 n/2번감
	public static int[][] field;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n;
		int cnt = 0;
		for (int testCase = 1; testCase <= t; testCase++) {
			n = Integer.parseInt(br.readLine());
			field = new int[n][n];
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < n; j++) {
					field[i][j] = str.charAt(j) - '0';
				}
				if (i <= n / 2) {
					for (int j = n / 2 - i; j < n / 2 + i + 1; j++) {
//						System.out.println("i j : "+i+","+j);
						cnt += field[i][j];
					}
				} else {
					int k = n - (i - n/2) * 2;
					for (int j = i - n / 2; j < i - n/2 + k; j++) {
//						System.out.println("i j : " + i + "," + j);
						cnt += field[i][j];
					}
				}
			}
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
			cnt = 0;
		}
		System.out.print(sb);
	}

}
