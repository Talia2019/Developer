package jiyoung.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class ACMCraft {

	// G3 1005 ACM Craft

	// dfs로 돌면서 자식dp중 큰값으로 저장

	public static int n, k;
	public static int[] time;
	public static int[] mem;
	public static List<Integer>[] edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");

			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			time = new int[n + 1];
			mem = new int[n + 1];
			Arrays.fill(mem, -1);

			edges = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				edges[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= n; i++) {
				time[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				edges[y].add(x);
			}

			sb.append(dfs(Integer.parseInt(br.readLine()))).append("\n");
		}
		System.out.println(sb);
	}

	public static int dfs(int node) {
		if (mem[node] >= 0)
			return mem[node];

		int max = 0;
		for (Integer nod : edges[node]) {
			if (mem[nod] < 0)
				mem[nod] = dfs(nod);
			if (mem[nod] > max)
				max = mem[nod];
		}
		return mem[node] = max + time[node];
	}

}
