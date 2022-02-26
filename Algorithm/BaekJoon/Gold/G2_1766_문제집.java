package jiyoung.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Workbook {

	// G2 1766 문제집

	// 이전문제랑 코드가 똑같은거같은건.. 기분탓인감....

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] indegree = new int[n + 1];
		List<Integer>[] graph = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			indegree[b]++;
		}

		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0)
				q.add(i);
		}

		StringBuilder sb = new StringBuilder();
		while (q.size() > 0) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for (int i : graph[cur]) {
				if(--indegree[i] == 0) {
					q.add(i);
				}
			}
		}
		
		System.out.println(sb);

	}

}
