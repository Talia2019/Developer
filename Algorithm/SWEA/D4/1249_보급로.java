package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_D4_1249_보급로 {

	// 최소경로 찾기 - 다익스트라

	public static int[][] map;
	public static int[][] distance;
	public static final int INFINITY = 987654321;
	public static boolean[][] visited;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node implements Comparable<Node> {
		int r, c;
		int distance;

		public Node(int r, int c, int distance) {
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.distance, o.distance);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());

			map = new int[n][n];
			distance = new int[n][n];
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				String input = br.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = input.charAt(j) - '0';
				}
			}

			PriorityQueue<Node> pq = new PriorityQueue<>();
			for (int[] is : distance) {
				Arrays.fill(is, INFINITY);
			}
			distance[0][0] = 0;
			pq.add(new Node(0, 0, map[0][0]));

			while (pq.size() > 0) {
				Node cur = pq.poll();
				int r = cur.r;
				int c = cur.c;

				if (r == n - 1 && c == n - 1) {
					continue;
				}
				visited[r][c] = true;

				for (int i = 0; i < 4; i++) {
					int nr = r + dir[i][0];
					int nc = c + dir[i][1];
					if (nr < 0 || nr >= n || nc < 0 || nc >= n || visited[nr][nc])
						continue;
					if (distance[nr][nc] > distance[r][c] + map[nr][nc]) {
						distance[nr][nc] = distance[r][c] + map[nr][nc];
						pq.add(new Node(nr, nc, distance[nr][nc]));
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(distance[n - 1][n - 1]).append("\n");
		}
		System.out.println(sb);

	}

}
