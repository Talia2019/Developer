package jiyoung.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Tree {

//	G4 4256 트리
//	440ms

//	BT의 전위, 중위 순회한 결과가 주어졌을 때, 후위 순회했을 때의 결과를 구하는 프로그램을 작성하시오.
//	첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 노드의 개수 n이 주어진다. 
//	(1 ≤ n ≤ 1,000) BT의 모든 노드에는 1부터 n까지 서로 다른 번호가 매겨져 있다. 다음 줄에는 BT를 전위 순회한 결과, 그 다음 줄에는 중위 순회한 결과가 주어진다. 
//	항상 두 순회 결과로 유일한 이진 트리가 만들어지는 경우만 입력으로 주어진다.
//	각 테스트 케이스마다 후위 순회한 결과를 출력 한다.

//	전위 : 나, 왼, 오
//	중위 : 왼, 나, 오
//	후위 : 왼, 오, 나

//	전위를 통해 다음 루트를 찾고, 후위를 통해 해당 루트를 기준으로 왼쪽오른쪽 나눔
//	전위 순서대로 노드를 탐색하기때문에 전위는 그대로 받고, 후위는 이 전위의 값을 기준으로 탐색하기 때문에 후위는 값을 인덱스로 기록
	static class Node {
		int data;
		Node left, right;

		public Node(int data, Node left, Node right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	public static int n, index;
	public static int[] preOrder, inOrderIndex;
	public static Node tree;
	public static StringBuilder sb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			preOrder = new int[n + 1];
			inOrderIndex = new int[n + 1];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				preOrder[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				inOrderIndex[Integer.parseInt(st.nextToken())] = i;
			}

			tree = new Node(preOrder[0], null, null);

			index = 1;
			getTree(tree, inOrderIndex[preOrder[0]], 0, n - 1);
			postOrder(tree);
			
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void getTree(Node parent, int standard, int start, int end) {
		if (start - end == 0) {
			return;
		}

		while (index < n) {	//index가 static변수라 재귀안에 while넣어도 많이 안돌아감. 아마도?
			int root = preOrder[index];
			int rootIdx = inOrderIndex[root];
			Node node = new Node(root, null, null);
			if (rootIdx == standard || rootIdx < start || rootIdx > end)	//리프노드에 도달하거나, 현노드의 인덱스가 시작점보다 작거나, 끝점보다 크면 탈출 
				return;
			index++;
			if (rootIdx < standard) {// 왼쪽
				parent.left = node;
				getTree(node, rootIdx, start, standard - 1);
				continue;
			}
			if (rootIdx > standard) {// 오른쪽
				parent.right = node;
				getTree(node, rootIdx, standard + 1, end);
				continue;
			}
		}
	}

	public static void postOrder(Node node) {
//		System.out.println(node.data);
		if (node.left != null) {
			postOrder(node.left);
		}
		if (node.right != null) {
			postOrder(node.right);
		}
		sb.append(node.data).append(" ");
	}

}
