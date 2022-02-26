package jiyoung.week9;

public class Network {

	public static void main(String[] args) {

	}

	static class Solution {
		int[] parents;

		public int solution(int n, int[][] computers) {
			int answer = 0;
			make(n);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (computers[i][j] == 1) {
						union(i, j);
					}
				}
			}

			boolean[] visited = new boolean[n];
			int parent;
			for (int i = 0; i < n; i++) {
				parent = find(i);
				if (!visited[parent]) {
					answer++;
					visited[parent] = true;
				}
			}
			return answer;
		}

		public void make(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}

		public int find(int a) {
			if (parents[a] == a)
				return a;
			return parents[a] = find(parents[a]);
		}

		public boolean union(int a, int b) {
			int rootA = find(a);
			int rootB = find(b);
			if (rootA == rootB)
				return false;
			parents[rootB] = rootA;
			return true;
		}
	}
}

//union find 쓰면 금방풀릴듯
