package jiyoung.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeFindingParent {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 1,4 2,3 2,4 이렇게 들어올 경우 24중 누가 부모인지 구분해야됨
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
//		int[] record = new int[n + 1]; // 자식인덱스에 부모값을 저장
//		record[1] = 1;
		List<ArrayList<Integer>> tree = new ArrayList<>();
		for (int i = 0; i < n + 1; i++) {
			tree.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			tree.get(node1).add(node2);	//부모 자식 구분없이 넣음
			tree.get(node2).add(node1);
//			if (record[node1] != 0) { // 부모가 이미 있으면 내가 부모인거
//				record[node2] = node1;
//			} else { // 부모가 없으면 다른쪽이 부모
//				record[node1] = node2;
//			}
//			tree.addNode(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1); // 루트

		int[] parent = new int[n+1];	// 인덱스-자식 값-부모
		int cur = 0;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			for (int i : tree.get(cur)) {
				if(parent[i] != 0) continue;	//0이 아닌경우 상위노드
				parent[i] = cur;
				queue.add(i);
			}
		}
		for (int i = 2; i < parent.length; i++) {
			sb.append(parent[i]).append("\n");
		}
		System.out.println(sb);
		
		//Tree_ver
//		Tree tree = new Tree(n);
//		for (int i = 0; i < n - 1; i++) {
//			st = new StringTokenizer(br.readLine(), " ");
//			tree.addNode(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
//		}
//		int[] parents = tree.saveParent;
//		for (int i = 2; i < parents.length; i++) {
//			sb.append(parents[i]).append("\n");
//		}
//		System.out.println(sb);
	}

}


//class Tree {
//	public Node root;
//	public boolean[] isIn;
//	public int[] saveParent;
//
//	public Tree(int n) {
//		root = new Node(1);
//		isIn = new boolean[n + 1];
//		isIn[1] = true;
//		saveParent = new int[n + 1];
//	}
//
//	public void addNode(int node1, int node2) {
//		int parent, child;
//		if (isIn[node1]) { // 이미 들어가있는게 부모
//			parent = node1;
//			child = node2;
//		} else {
//			parent = node2;
//			child = node1;
//		}
//
//		Queue<Node> queue = new LinkedList<Node>();
//		queue.offer(root); // 루트 인덱스 저장
//
//		Node cur = null;
//		while (!queue.isEmpty()) {
//			cur = queue.poll();
//			if (cur.data == parent) {
//				cur.child.add(new Node(child));
//				isIn[child] = true;
//				saveParent[child] = cur.data;
//				return;
//			}
//			for (Node node : cur.child) {
//				queue.add(node);
//			}
//		}
//	}
//}
//
//class Node {
//	public int data;
//	public List<Node> child = new ArrayList<>();
//
//	public Node(int d) {
//		data = d;
//	}
//}
