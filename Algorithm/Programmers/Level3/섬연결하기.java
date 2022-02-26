package jiyoung.week6;

import java.util.Arrays;

public class ConnectingIsland {

//	섬연결하기
//	n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 
//	최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

//	간선리스트가 주어지니까 kruskal쓰면 될듯?

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(
				s.solution(4, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } }));
		System.out.println(s.solution(6, new int[][] { { 0, 1, 5 }, { 0, 3, 2 }, { 0, 4, 3 }, { 1, 4, 1 }, { 3, 4, 10 },
				{ 1, 2, 2 }, { 2, 5, 3 }, { 4, 5, 4 } }));
		System.out.println(
				s.solution(5, new int[][] { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 2, 3, 8 }, { 3, 4, 1 } }));
	}

	static class Solution {

		int e, v;
		Edge[] edgeList;
		int[] parents;

		class Edge implements Comparable<Edge> {
			int start, end, weight;

			public Edge(int start, int end, int weight) {
				this.start = start;
				this.end = end;
				this.weight = weight;
			}

			@Override
			public int compareTo(Edge o) {
				return Integer.compare(this.weight, o.weight);
			}
		}

		public int solution(int n, int[][] costs) {
			int answer = 0;

			// 오름차순 정렬을 위해 따로 담음
			v = costs.length;
			e = n;
			edgeList = new Edge[v];
			for (int i = 0; i < v; i++) {
				Edge e = new Edge(costs[i][0], costs[i][1], costs[i][2]);
				edgeList[i] = e;
			}

			// 오름차순정렬
			Arrays.sort(edgeList);

			make();

			int cnt = 0;
			for (Edge edge : edgeList) {
				if (union(edge.start, edge.end)) {
					//첨에 e-1이 아니라 v-1을 해서 틀렸음!!
					answer += edge.weight;
					if (++cnt == e - 1)
						break;
				}
			}

			return answer;
		}

		public void make() {
			parents = new int[e];
			for (int i = 0; i < e; i++) {
				parents[i] = i;
			}
		}

		public int find(int a) {
			if (parents[a] == a)
				return a;
			return parents[a] = find(parents[a]);
		}

		public boolean union(int a, int b) {
			int parentA = find(a);
			int parentB = find(b);
			if (parentA == parentB)
				return false;
			parents[parentB] = parentA;
			return true;
		}

	}

}
