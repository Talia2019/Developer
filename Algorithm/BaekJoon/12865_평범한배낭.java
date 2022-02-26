package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_12865_G5 {
//	12865 평범한 배낭
//	N개의 물건이 있다. 각 물건은 무게 W와 가치 V를 가지는데, 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있다. 아직 행군을 해본 적이 없는 준서는 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있다. 
//	준서가 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자.
//	첫 줄에 물품의 수 N(1 ≤ N ≤ 100)과 준서가 버틸 수 있는 무게 K(1 ≤ K ≤ 100,000)가 주어진다. 두 번째 줄부터 N개의 줄에 거쳐 각 물건의 무게 W(1 ≤ W ≤ 100,000)와 해당 물건의 가치 V(0 ≤ V ≤ 1,000)가 주어진다.
//	입력으로 주어지는 모든 수는 정수이다.

	static Integer[][] bag;
	static int[][] items;
	static int n, k;

	// 이후의 무게 + 내 무게가 k넘으면
	// bag[index][weight] items[i][0] = W, items[i][1] = V
	// 만약 weight < W 라면 넣을수 있는게 없으므로 이전 bag[i-1][weight] 가져옴
	// 만약 weight >= W 라면 i번째 물건을 담을 수 있음
	// --> V + bag[i-1][weight-W] (현재물건담은거) VS bag[i-1][weight] (이전 담겨져있던거)
	// 어려웡..
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		bag = new Integer[n][100001];
		items = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			items[i][0] = Integer.parseInt(st.nextToken());
			items[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(getMaxValue(n-1, k));
	}

	public static int getMaxValue(int i, int weight) {
		if (i < 0)
			return 0;
		if (bag[i][weight] == null) {
			if (weight < items[i][0])
				bag[i][weight] = getMaxValue(i - 1, weight);
			else {
				int now = items[i][1] + getMaxValue(i - 1, weight - items[i][0]);
				int prev = getMaxValue(i - 1, weight);
				bag[i][weight] = now > prev ? now : prev;
			}
		}
		return bag[i][weight];
	}

}
