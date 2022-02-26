package jiyoung.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Tree {

	// G4 1967 트리의지름

	// 예전에 풀었던거같기도하고..
	// 내려온길, 자식들중 값들 가장큰두개랑 최대값 비교
	// 좀 복잡한듯 -> dfs사용하는 방법이 정석
	
	// 트리 특성상 한 정점에서 다른 정점으로 가는 경로는 유일 = 가장 멀리있는 두 정점의 경로는 유일
	// 정점간 cost를 기준으로 경로를 결정하기 때문에 가장 긴 정점의 경로는 결국 어느 정점에서의 가장 먼 거리에 있는 정점의 경로와 겹칠 수밖에 없음?
	// (각 정점마다 본인한테 가장 먼 정점이 존재하는데, 여기까지 가능 경로랑 겹친다고?)
	
	// 1. dfs를 통해 임의의 정점 하나에서 가장 먼 정점을 구함
	// 2. dfs를 통해 구한 정점으로부터 가장 먼 정점까지의 거리 = 이게답  
	

	static class Node {
		int child, weight;

		public Node(int child, int weight) {
			this.child = child;
			this.weight = weight;
		}
	}

	public static int max;
	public static List<Node>[] tree;
	public static int[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		nodes = new int[n + 1];
		tree = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			tree[p].add(new Node(c, w));
		}

		findMax(1, 0, 0);
		System.out.println(max);
	}

	public static int findMax(int n, int weight, int parentValue) { // 현재노드, 위에서부터 온 값들의 합 -> 위랑 연결된값 + 최대자식값을 리턴
		List<Integer> childValues = new ArrayList<>();
		childValues.add(0);
		childValues.add(0);

		for (Node node : tree[n])
			childValues.add(findMax(node.child, node.weight, parentValue + node.weight));

		Collections.sort(childValues, Collections.reverseOrder());
		int curMax;
		if (parentValue > childValues.get(0) || parentValue > childValues.get(1))
			curMax = parentValue + childValues.get(0);
		else
			curMax = childValues.get(0) + childValues.get(1);
		if (max < curMax)
			max = curMax;

		return childValues.get(0) + weight;
	}

}

//입력으로 루트가 있는 트리를 가중치가 있는 간선들로 줄 때, 트리의 지름을 구해서 출력하는 프로그램을 작성하시오. 
