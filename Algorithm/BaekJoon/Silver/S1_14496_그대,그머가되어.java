package jiyoung.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class YaMinJungEm {

//	S1 14496 그대,그머가 되어

//	문자 a를 문자 b로 바꾸려하고, N개의 문자와 치환 가능한 문자쌍 M개가 있다. 머호에게 a를 b로 바꾸기 위한 치환의 최소 횟수를 구해서 머호에게 알려주자!
//	첫째 줄에 머호가 바꾸려 하는 문자 a와 b가 주어진다. 둘째 줄에 전체 문자의 수 N과 치환 가능한 문자쌍의 수 M이 주어진다. 
//	(1 ≤ N ≤ 1,000, 1 ≤ M ≤ 10,000) 이후 M개의 줄에 걸쳐 치환 가능한 문자쌍이 주어진다. 모든 문자는 N이하의 자연수로 표현된다.
//	a를 b로 바꾸기 위해 필요한 최소 치환 횟수를 출력한다. 치환이 불가능한 경우는 –1을 출력한다.

//	트리로?
//	각 노드별로 치환가능한애들을 연결시킴
//	가면서 횟수 가장 적은거로
//	dfs로 하려다 엄청난 실패를 겪고... bfs로 변경.....

	
	
	
//	왜 bfs는 안되는거죠??ㅜㅜㅜㅜㅜ알려주세요...여러분...
	
	
	public static List<Integer>[] tree;
	public static int a, b;
	public static int n, m;
	public static int min;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 생성
		tree = new ArrayList[n + 1];
		visited = new boolean[n + 1];
//		min = Integer.MAX_VALUE;
		min = -1;

		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			tree[n1].add(n2);
			tree[n2].add(n1);
		}

//		// 탐색
//		if (a == b)
//			min = 0;
//		else {
//			search(a, 1);
//			if (min == Integer.MAX_VALUE)
//				min = -1;
//		}
//		
//		System.out.println(min);
		
		search2(a);
		System.out.println(min);
	}

	//dfs -> 답안나옴
	public static void search(int node, int cnt) {
		if (cnt >= min)
			return;

		visited[node] = true;

		for (Integer i : tree[node]) {
			if (!visited[i]) {
				if (i == b) {
					min = cnt;
					return;
				}
				search(i, cnt + 1);
			}
		}

	}

	//bfs
	public static void search2(int node) {
		Deque<Integer> q = new LinkedList<Integer>();
		int[] cnt = new int[n + 1];

		q.add(node);
		visited[node] = true;

		while (!q.isEmpty()) {
			int t = q.poll();
			if (t == b) {
				min = cnt[t];
				return;
			}
			for (Integer integer : tree[t]) {
				if (!visited[integer]) {
					visited[integer] = true;
					q.add(integer);
					cnt[integer] = cnt[t] + 1;
				}
			}
		}
	}
}
