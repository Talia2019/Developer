import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_백준_1149_RGB거리{

	// S1 1149 RGB거리

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		int[][] price = new int[n][3]; // RGB
		int[][] house = new int[n][3]; // RGB로 끝나는 가격

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			price[i][0] = Integer.parseInt(st.nextToken());
			price[i][1] = Integer.parseInt(st.nextToken());
			price[i][2] = Integer.parseInt(st.nextToken());
		}

		house[0][0] = price[0][0];
		house[0][1] = price[0][1];
		house[0][2] = price[0][2];

		int min;
		for (int i = 1; i < n; i++) {
			// R로 끝나는애
			min = Math.min(house[i - 1][1], house[i - 1][2]);
			house[i][0] = price[i][0] + min;

			// G로 끝나는애
			min = Math.min(house[i - 1][0], house[i - 1][2]);
			house[i][1] = price[i][1] + min;

			// R로 끝나는애
			min = Math.min(house[i - 1][0], house[i - 1][1]);
			house[i][2] = price[i][2] + min;
		}

		min = Math.min(house[n - 1][0], Math.min(house[n - 1][1], house[n - 1][2]));
		System.out.println(min);
	}

}

//1번 집의 색은 2번 집의 색과 같지 않아야 한다.
//N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
//i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.

//첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
