package jiyoung.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Friends {

	// G2 4195 친구 네트워크

	// 연결된 사람의 수를 저장해놓기

	public static int f;
	public static int[] parents, size;
	public static Map<String, Integer> map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		map = new HashMap<String, Integer>();

		int cnt = 1;
		for (int tc = 0; tc < t; tc++) {
			f = Integer.parseInt(br.readLine());
			make();
			for (int i = 0; i < f; i++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				map.putIfAbsent(name1, cnt++);
				map.putIfAbsent(name2, cnt++);
				union(map.get(name1), map.get(name2));

				sb.append(size[find(map.get(name1))]).append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void make() {
		parents = new int[f * 2 + 1];
		size = new int[f * 2 + 1];
		for (int i = 1; i < parents.length; i++) {
			parents[i] = i;
			size[i] = 1;
		}
	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;
		parents[rootB] = rootA;
		size[rootA] += size[rootB];
	}
}

//어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
//
//친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.
