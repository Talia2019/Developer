package jiyoung.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class TreeDiameter {

//	1167 트리의 지름 G3

//	트리의 지름이란, 트리에서 임의의 두 점 사이의 거리 중 가장 긴 것을 말한다. 트리의 지름을 구하는 프로그램을 작성하시오.
//	트리가 입력으로 주어진다. 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.
//	먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다. 
//	각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.
//	첫째 줄에 트리의 지름을 출력한다.

	
//	****************
//	Sol.ver1
//	리프노드를 찾음
//	리프노드에서 순회하며 max를 찾음
//	->시간초과
	
//	Sol.ver2
//	루트노드를 제외한 노드에는 2개 이상의 간선이 붙어있음 -> 붙어있는 간선중 가장 큰값(자식들의것도 포함) 두개를 더하면됨
//	위에서부터 가져온값, 자식들에게서 가져온값중 큰값2개 찾고 더해서 max와 비교
//	(부모와 연결된 엣지 + 자식중 가장큰값) 을 리턴으로 넘김 
//	****************
	
	
	public static boolean isVisit[];
	public static List<ArrayList<Node>> tree;
	public static List<Integer> leaf;
	public static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		tree = new ArrayList<>();

		int v = Integer.parseInt(br.readLine());

		leaf = new ArrayList<>();
		isVisit = new boolean[v + 1];
		for (int i = 0; i < v + 1; i++) {
			tree.add(new ArrayList<Node>());
		}

		// 노드추가
		for (int i = 0; i < v; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cur = Integer.parseInt(st.nextToken());
			int num, edge;

			while (true) {
				num = Integer.parseInt(st.nextToken());
				if (num == -1)
					break;
				edge = Integer.parseInt(st.nextToken());
				tree.get(cur).add(new Node(num, edge));
			}
		}

		// 리프노드 찾기
//		findLeaf(1, 0);

//		for (Integer integer : leaf) {
//			Arrays.fill(isVisit, false);
//			findLength(integer, 0);
//		}

		findMax(1,0);
		
		System.out.println(max);
	}

//	(위에서부터 가져온값, 자식들에게서 가져온값들)중 큰값2개 찾고 더해서 max와 비교 -> 다 리스트에 넣고 정렬
//	(부모와 연결된 엣지 + 자식중 가장큰값) 을 리턴으로 넘김 
	public static int findMax(int n, int parentLength) { // 현재노드와, 위에연결된 엣지들 더한거

		int parentEdge = 0; // 부모와 연결된 엣지값
		
		List<Integer> childValues = new ArrayList<>();
		childValues.add(parentLength);

		List<Node> subTree = tree.get(n); // 노드 n에 연결된 노드들

		isVisit[n] = true;

		for (int i = 0; i < subTree.size(); i++) {
			int nextNode = subTree.get(i).nextNode; // 연결된 노드번호
			int edge = subTree.get(i).edge; // 해당 노드와 연결된 엣지 값
			if (!isVisit[nextNode]) { // 방문한적 없으면 자식노드
				isVisit[nextNode] = true;
				childValues.add(findMax(nextNode, parentLength + edge));
				isVisit[nextNode] = false;
			}
			else { // 방문한적 있으면 부모노드
				parentEdge = edge;
			}
		}

		//자식이 없을경우 대비해서 추가
		childValues.add(0);
		childValues.add(0);
		
		// parentLength, 자식들 중 1,2위 찾아서 max와 비교
		Collections.sort(childValues, Collections.reverseOrder()); // 내림차순 정렬
		int curMax = childValues.get(0) + childValues.get(1);
		max = max > curMax ? max : curMax;
		
		//자식들중에서만 1,2위를 찾기 위해 parentLength 제거
		for (int i = 0; i < childValues.size(); i++) {
			if(childValues.get(i)==parentLength) {
				childValues.remove(i);
				break;
			}
		}
		
		//자식들중 1,2위 찾기
		int firstMax, secondMax;
		firstMax = childValues.get(0);	
		secondMax = childValues.get(1);
		
		return parentEdge + (firstMax > secondMax ? firstMax : secondMax);
	}

//	Sol.ver1 -> 시간초과
	
////	리프노드들 찾기
//	public static void findLeaf(int n, int length) {
//
////		tree.get(n) : 노드 n이 갖고있는 연결노드들
//		boolean flag = true; // 리프노드인가?
//		List<Node> subTree = tree.get(n);
//
//		isVisit[n] = true;
//
//		for (int i = 0; i < subTree.size(); i++) {
//			int nextNode = subTree.get(i).nextNode;
//			int edge = subTree.get(i).edge;
//			if (!isVisit[nextNode]) { // 방문한적 없으면 들어가야됨
//				flag = false;
////				System.out.println("현재노드 : " + n + "방문할노드 : " + nextNode + "방문할것");
//				isVisit[nextNode] = true;
//				findLeaf(nextNode, edge);
//				isVisit[nextNode] = false;
//			}
//		}
//
//		if (flag) {
//			leaf.add(n);
//			max = max > length ? max : length;
////			System.out.println(n+"노드는 리프노드");
//		}
//	}
//
////	노드들에서 각각 max찾기
//	public static void findLength(int n, int length) {
//
//		boolean flag = true; // 리프노드인가?
//		List<Node> subTree = tree.get(n);
//
//		isVisit[n] = true;
//
//		for (int i = 0; i < subTree.size(); i++) {
//			int nextNode = subTree.get(i).nextNode;
//			int edge = subTree.get(i).edge;
//			if (!isVisit[nextNode]) { // 방문한적 없으면 들어가야됨
//				flag = false;
//				isVisit[nextNode] = true;
//				findLength(nextNode, length + edge);
//				isVisit[nextNode] = false;
//			}
//		}
//
//		if (flag) {
//			max = max > length ? max : length;
//		}
//	}

}

class Node {
	int nextNode;
	int edge;

	public Node(int nextNode, int edge) {
		super();
		this.nextNode = nextNode;
		this.edge = edge;
	}
}
