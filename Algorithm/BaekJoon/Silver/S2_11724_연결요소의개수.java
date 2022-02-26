package jiyoung.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ConnectedComponent {

	// S2 11724 연결요소의 개수
	// 실버2문제가 제일예뻐요ㅋㅋ S2

	// union find쓰면 금방할듯?

	public static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		make(n);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		int cnt = 0;
		boolean[] visited = new boolean[n+1];
		for (int i = 1; i <= n; i++) {
			int root = find(i);
			if (!visited[root]) {
				cnt++;
				visited[root] = true;
			}
		}
		System.out.println(cnt);
	}

	public static void make(int n) {
		parents = new int[n+1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return false;
		parents[rootB] = rootA;
		return true;
	}
}
//문제
//방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
//입력
//첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
//출력
//첫째 줄에 연결 요소의 개수를 출력한다.
