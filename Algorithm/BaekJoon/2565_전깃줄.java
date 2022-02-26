package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_boj_2565_S1 {

//	2565 전깃줄
//	첫째 줄에는 두 전봇대 사이의 전깃줄의 개수가 주어진다. 전깃줄의 개수는 100 이하의 자연수이다. 둘째 줄부터 한 줄에 하나씩 전깃줄이 A전봇대와 연결되는 위치의 번호와 B전봇대와 연결되는 위치의 번호가 차례로 주어진다. 위치의 번호는 500 이하의 자연수이고, 같은 위치에 두 개 이상의 전깃줄이 연결될 수 없다.
//	첫째 줄에 남아있는 모든 전깃줄이 서로 교차하지 않게 하기 위해 없애야 하는 전깃줄의 최소 개수를 출력한다.

//	나를 기준으로 다음전깃줄을 설치할수 있나

	static int[][] electricLine;
	static int[] maxEL;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		electricLine = new int[n][2]; // x y
		maxEL = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			electricLine[i][0] = Integer.parseInt(st.nextToken());
			electricLine[i][1] = Integer.parseInt(st.nextToken());
		}

		// 전깃줄을 x를 기준으로 정렬
		Arrays.sort(electricLine, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
//		for (int[] i : electricLine) {
//			System.out.println(Arrays.toString(i));
//		}
		
		int max = 0;
		for (int i = 0; i < n; i++) {
			int get = getMaxEL(i);
			max = max > get ? max : get;
		}
		
		System.out.println(n - max);
	}

	public static int getMaxEL(int d) {
		if (maxEL[d] == 0) {
			maxEL[d] = 1;

			for (int i = d + 1; i < maxEL.length; i++) {
				if (electricLine[d][1] < electricLine[i][1]) { // 설치가능함
					int next = getMaxEL(i) + 1; // 현재 전봇대 포함해서 1더함
					maxEL[d] = maxEL[d] > next ? maxEL[d] : next;
				}
			}
		}
		return maxEL[d];
	}

}
