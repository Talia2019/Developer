package jiyoung.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CityDivision {

	// G4 1647 도시 분할 계획

	// 크루스칼, 마지막거 빼면될듯
	// 정렬보다 우선순위큐쓰는게 빠른듯

	static class Edge implements Comparable<Edge> {
		int start, end;
		int pay;

		public Edge(int start, int end, int pay) {
			this.start = start;
			this.end = end;
			this.pay = pay;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.pay, o.pay);
		}
	}

	public static int n, m;
	public static int[] parents;
	public static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n + 1];
		edgeList = new Edge[m];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(edgeList);

		make();
		int cnt = 0, pay = 0;
		for (Edge edge : edgeList) {
			if (union(edge.start, edge.end)) {
				if(++cnt==n-1)
					break;
				pay += edge.pay;
			}
		}
		
		System.out.println(pay);
	}

	public static void make() {
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		else
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
