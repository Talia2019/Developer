package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
//	 재료가 N개 있다. 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다. 
//	여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 사용한 재료의 신맛의 곱이고, 쓴맛은 합이다.
//	재료는 적어도 하나 사용해야 한다.
//	재료의 신맛과 쓴맛이 주어졌을 때, 신맛과 쓴맛의 차이가 가장 작은 요리를 만드는 프로그램을 작성하시오.

//	첫째 줄에 재료의 개수 N(1 ≤ N ≤ 10)이 주어진다. 
//	다음 N개 줄에는 그 재료의 신맛과 쓴맛이 공백으로 구분되어 주어진다. 
//	모든 재료를 사용해서 요리를 만들었을 때, 그 요리의 신맛과 쓴맛은 모두 1,000,000,000보다 작은 양의 정수이다.

//	재료의 부분집합마다 신맛, 쓴맛을 구하고 그 최소값을 구함
//	바이너리카운팅 써볼까??

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[][] ingredients = new int[n][2];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ingredients[i][0] = Integer.parseInt(st.nextToken());
			ingredients[i][1] = Integer.parseInt(st.nextToken());
		}

		int min = Integer.MAX_VALUE;
		for (int i = 1; i < (1 << n); i++) { // 1<<n: 부분집합 개수
			int[] food = new int[2];
			food[0] = 1;
			for (int j = 0; j < n; j++) { // 원소의 수만큼 비트를 비교
				if ((i & (1 << j)) != 0) { // j재료 선택
					food[0] *= ingredients[j][0];
					food[1] += ingredients[j][1];
//					System.out.println(j + " 선택");
//					System.out.println(Arrays.toString(food));
				}
			}
			int sub = Math.abs(food[0] - food[1]);
			min = min < sub ? min : sub;
//			System.out.println(sub + " " + min);
		}
		
		System.out.println(min);
	}

}
