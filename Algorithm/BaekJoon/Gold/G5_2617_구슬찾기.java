package jongol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_jungol_1515_구슬찾기 {

	// 부모의 수나 자식의 수가 n/2 보다 큰경우 세기

	public static int n, m, pCnt, cCnt;
	public static List<Integer>[] graphP, graphC;
	public static int[][] mem;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		mem = new int[n + 1][2];

		for (int[] is : mem) {
			Arrays.fill(is, -1);
		}

		graphP = new ArrayList[n + 1];
		graphC = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graphP[i] = new ArrayList<Integer>();
			graphC[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graphP[p].add(c);
			graphC[c].add(p);
		}

		int half = n / 2;
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			pCnt = cCnt = 0;
			dfsP(i, new boolean[n + 1]);
			dfsC(i, new boolean[n + 1]);
			
			if (pCnt > half || cCnt > half)
				cnt++;
		}

		System.out.println(cnt);
	}

	public static void dfsP(int node, boolean[] visited) {
		visited[node] = true;
		for (int p : graphP[node]) {
			if (!visited[p]) {
				pCnt++;
				dfsP(p, visited);
			}
		}
	}

	public static void dfsC(int node, boolean[] visited) {
		visited[node] = true;
		for (int c : graphC[node]) {
			if (!visited[c]) {
				cCnt++;
				dfsC(c, visited);
			}
		}
	}
}
