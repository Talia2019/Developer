package jiyoung.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class DistanceOfNodes {

	// G4 1240 노드사이의거리

	public static int n, m;
	public static List<Node>[] tree;
	public static int distance;
	public static boolean[] visited;

	static class Node {
		int node;
		int weight;

		public Node(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuffer sb = new StringBuffer();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		tree = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}

		int node1, node2, weight;
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			node1 = Integer.parseInt(st.nextToken());
			node2 = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());

			tree[node1].add(new Node(node2, weight));
			tree[node2].add(new Node(node1, weight));
		}

		int start, target;
		for (int i = 0; i < m; i++) {
			visited = new boolean[n + 1];
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			target = Integer.parseInt(st.nextToken());
			distance = 0;
			visited[start] = true;
			dfs(target, start, 0);
			sb.append(distance).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int target, int node, int weight) {
		if (node == target) {
			distance = weight;
			return;
		}
		for (Node nod : tree[node]) {
			if (distance != 0)
				return;
			if (!visited[nod.node]) {
				visited[nod.node] = true;
				dfs(target, nod.node, weight + nod.weight);
			}
		}
	}

}

//간선의 개수는 항상 n-1이래
//그럼 그냥 dfs로 가면되나
//dp이용가능..?잘모르겠음..
