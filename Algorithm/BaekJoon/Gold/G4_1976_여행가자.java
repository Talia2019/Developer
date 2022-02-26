package jiyoung.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Trip {

	// G4 1976 여행가자

	// 행렬 절반만 보면서 연결시키고
	// 막줄 부모가 전부 같은지 확인하면 될듯

	public static int n;
	public static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		make();
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < i; j++) {
				if (input.charAt(j << 1) == '1') {
					union(i, j);
				}
			}
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int tmp = find(Integer.parseInt(st.nextToken()) - 1);
		for (int i = 1; i < m; i++) {
			if (find(Integer.parseInt(st.nextToken()) - 1) != tmp) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");

	}

	public static void make() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	public static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;
		parents[rootB] = rootA;
	}

}
