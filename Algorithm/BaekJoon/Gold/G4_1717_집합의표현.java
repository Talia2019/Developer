package jiyoung.week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SetExpression {

	// G4 1717 집합의표현
	// 서로소의 정석..인듯한 문제 같음
	public static int n, m;
	public static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		make();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
			case "0":
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				break;
			case "1":
				int a = find(Integer.parseInt(st.nextToken()));
				int b = find(Integer.parseInt(st.nextToken()));
				if (a == b)
					sb.append("YES\n");
				else
					sb.append("NO\n");
				break;
			}
		}
		System.out.println(sb);
	}

	public static void make() {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
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

		if (rootA != rootB)
			parents[rootB] = rootA;
	}

}
