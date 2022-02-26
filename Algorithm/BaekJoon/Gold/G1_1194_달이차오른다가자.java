package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_G1_1194_달이차오른다가자 {

	// G1 1194 달이차오른다 가자

	// 열쇠를 먹으면 방문 초기화?
	// 1. bfs -> 열쇠 처리를 어떻게 해야할지 모르겠어서 dfs로 풀어봤음
	// 2. dfs -> 방문배열과 열쇠를 함수에서 들고다니도록.. 시간초과
	// 3. bfs -> 열쇠랑 좌표를 묶어주는 클래스 생성 + 비트연산자

	public static int n, m, min = 987654321;
	public static boolean flag;
	public static boolean[][][] visited;
	public static char[][] maze;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Node {
		int r;
		int c;
		int keys;

		public Node(int r, int c, int keys) {
			this.r = r;
			this.c = c;
			this.keys = keys;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visited = new boolean[1 << 6][n][m];
		maze = new char[n][m];
		int[] start = new int[2];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				maze[i][j] = input.charAt(j);
				if (maze[i][j] == '0') {
					start[0] = i;
					start[1] = j;
				}
			}
		}

		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(start[0], start[1], 0));

		int r, c, nr, nc, k;
		int cnt = 0;

		q: while (q.size() > 0) {
			cnt++;
			int size = q.size();

			while (size-- > 0) {
				Node cur = q.poll();
				r = cur.r;
				c = cur.c;
				k = cur.keys;

//				System.out.println("-----------------");
//				for (int i = 0; i < n; i++) {
//					for (int j = 0; j < m; j++) {
//						if (i == r && j == c)
//							System.out.print("*");
//						else
//							System.out.print(maze[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println(cnt);

				for (int i = 0; i < 4; i++) {
					nr = r + dir[i][0];
					nc = c + dir[i][1];

					if (isOut(nr, nc) || visited[k][nr][nc])
						continue;

					char curMaze = maze[nr][nc];
					if (curMaze == '0' || curMaze == '.') {
						visited[k][nr][nc] = true;
						q.add(new Node(nr, nc, k));
					} else if (curMaze >= 'a' && curMaze <= 'z') { // 열쇠
						int keys = (k | (1 << (curMaze - 'a')));
//						if ((k & (1 << (curMaze - 'a'))) == 0) { // 열쇠 없었음
//							for (boolean[] bs : visited[keys]) {
//								Arrays.fill(bs, false);
//							}
//						}
						visited[keys][nr][nc] = true;
						q.add(new Node(nr, nc, keys));
					} else if (curMaze >= 'A' && curMaze <= 'Z') { // 문
						if ((k & (1 << (curMaze - 'A'))) != 0) { // 열쇠있음
							visited[k][nr][nc] = true;
							q.add(new Node(nr, nc, k));
						}
					} else if (curMaze == '1') {
						flag = true;
						min = cnt;
						break q;
					}
				}
			}

		}

		if (flag)
			System.out.println(min);
		else
			System.out.println(-1);
	}

	public static boolean isOut(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= m || maze[r][c] == '#')
			return true;
		return false;
	}

//	public static void dfs(Set<Integer> key, int r, int c, int cnt) {
//		int nr, nc;
//		if (cnt > min)
//			return;
////
////		System.out.println("-----------------");
////		for (int a = 0; a < n; a++) {
////			for (int j = 0; j < m; j++) {
////				if (a == r && j == c)
////					System.out.print("*");
////				else
////					System.out.print(maze[a][j]);
////			}
////			System.out.println();
////		}
////		System.out.println(cnt);
//
//		for (int i = 0; i < 4; i++) {
//			nr = r + dir[i][0];
//			nc = c + dir[i][1];
//
//			if (isOut(nr, nc) || visited[nr][nc])
//				continue;
//
//			visited[nr][nc] = true;
//			char curMaze = maze[nr][nc];
//
//			if (curMaze == '.' || curMaze == '0') {
//				dfs(key, nr, nc, cnt + 1);
//				visited[nr][nc] = false;
//			} else if (curMaze >= 'a' && curMaze <= 'z') { // 열쇠
//				if (!key.contains(curMaze - 'a')) {
//					for (boolean[] bs : visited) {
//						Arrays.fill(bs, false);
//					}
//					key.add(curMaze - 'a');
//					visited[nr][nc] = true;
//					dfs(key, nr, nc, cnt + 1);
//					visited[nr][nc] = false;
//					key.remove(curMaze - 'a');
//				} else {
//					dfs(key, nr, nc, cnt + 1);
//					visited[nr][nc] = false;
//				}
//			} else if (curMaze >= 'A' && curMaze <= 'Z') { // 문
//				if (key.contains(curMaze - 'A')) {
//					dfs(key, nr, nc, cnt + 1);
//					visited[nr][nc] = false;
//				}
//			} else if (curMaze == '1') {
//				visited[nr][nc] = false;
//				flag = true;
//				if (min > cnt)
//					min = cnt;
//				continue;
//			}
//		}
//	}

}
