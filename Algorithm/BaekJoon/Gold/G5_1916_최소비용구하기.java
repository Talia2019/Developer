package jiyoung.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class MinPrice {

	// G5 1916 최소비용구하기

	// 최소비용이니까 다익스트라 이용해보면 될듯

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		final int INFINITY = Integer.MAX_VALUE;

		int n = Integer.parseInt(br.readLine());
		n++;
		int m = Integer.parseInt(br.readLine());
//		int[][] bus = new int[n][n];
		List<Integer[]>[] bus = new ArrayList[n];
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			bus[i] = new ArrayList<>();
		}

		// 출발도시번호, 도착도시번호, 버스비용
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			bus[s].add(new Integer[] { e, w });
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		Arrays.fill(distance, INFINITY);
		distance[start] = 0;

		int min = 0, cur = 0;
		for (int i = 0; i < n; i++) {
			// 방문하지 않은 정점들 중 최소가중치 정점 선택
			min = INFINITY;
			for (int j = 0; j < n; j++) {
				if (!visited[j] && distance[j] < min) {
					min = distance[j];
					cur = j;
				}
			}
			visited[cur] = true;
			if (cur == end)
				break;

			// cur정점을 경유지로 하여 가는 다른 방문안한 정점처리
			for (Integer[] b : bus[cur]) {
				if (!visited[b[0]] && distance[b[0]] > min + b[1]) {
					distance[b[0]] = min + b[1];
				}
			}
		}
		System.out.println(distance[end]);

	}

}

//N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 M개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 
//A번째 도시에서 B번째 도시까지 가는데 드는 최소비용을 출력하여라. 도시의 번호는 1부터 N까지이다.
//첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 
//그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 
//버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
//그리고 M+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다. 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
//첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다
