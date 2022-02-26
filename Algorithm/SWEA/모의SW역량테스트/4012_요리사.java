package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

//	모의SW역량테스트 4012 요리사

//	A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 배분
//	식재료 i는 식재료 j와 같이 요리하게 되면 궁합이 잘 맞아 시너지 Sij가 발생한다. (1 ≤ i ≤ N, 1 ≤ j ≤ N, i ≠ j)
//	각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지 Sij들의 합이다

//	2. 식재료의 수 N은 4이상 16이하의 짝수이다. (4 ≤ N ≤ 16)
//	3. 시너지 Sij는 1이상 20,000이하의 정수이다. (1 ≤ Sij ≤ 20,000, i ≠ j)

//	크기4조합 구하고 걔들의 순열?...

	public static int[][] synergy;
	public static String finalNumber;
	public static int gap = Integer.MAX_VALUE;
	public static int[] combNum1, combNum2;
	public static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			synergy = new int[n][n];
			combNum1 = new int[n / 2];
			combNum2 = new int[n / 2];
			gap = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					synergy[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(gap).append("\n");
		}
		System.out.println(sb);
	}

	public static void comb(int cnt, int start, int num) {
		if (cnt == n / 2) {
			int k1 = 0, k2 = 0;
			for (int i = 0; i < n; i++) {
				if ((num & (1 << i)) != 0) {
					combNum1[k1++] = i;
				} else
					combNum2[k2++] = i;
			}
			int get1 = getSynergy(true);
			int get2 = getSynergy(false);
			int thisGap = Math.abs(get1 - get2);
			gap = gap < thisGap ? gap : thisGap;
			return;
		}
		for (int i = start; i < n; i++) {
			comb(cnt + 1, i + 1, num | (1 << i));
		}
	}

	public static int getSynergy(boolean flag) {
		int syn = 0;

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n / 2; j++) {
				if (j == i)
					continue;
				if(flag)
					syn += synergy[combNum1[i]][combNum1[j]];
				else
					syn += synergy[combNum2[i]][combNum2[j]];
			}
		}

		return syn;
	}

}
