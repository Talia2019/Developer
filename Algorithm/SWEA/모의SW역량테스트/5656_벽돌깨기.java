package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_5656_벽돌깨기 {

	// 교수님설명

	// 완탐(중복순열)
	// 1. 구슬을0열~w-1열까지 시도
	// 2. 구슬이 떨어졌을때 처음만나는 벽돌 찾기(위에서부터)
	// 3. 함께 제거될 벽돌을 식별 - bfs
	// 4. 부서진 벽돌 정리(벽돌 내리기)
	// 5. 다음 구슬 시도

	// 두번째 아이디어
	// 부숴지지 않은 벽돌만 리스트에 넣기

	public static int n, w, h, min;
	public static int[][] map;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	public static Queue<Point> q = new LinkedList<>();

	public static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new int[h][w];
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					int input = Integer.parseInt(st.nextToken());
					map[i][j] = input;
//					if (input > 0)
//						bricks[i].add(input);
				}
			}

			min = 987654321;
			go(0, map);
			sb.append("#").append(tc).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	// 중복순열로 구슬을 던짐
	public static void go(int cnt, int[][] map) {
		if (cnt == n) {// 구슬 다 던짐
			// 남은 벽돌 수 세서 최소값 갱신
			int res = getRemain(map);
			min = Math.min(res, min);
			return;
		}

		int[][] copyMap = new int[h][w];
		for (int c = 0; c < w; c++) { // 0열부터 마지막 열까지 시도
			// c열에 구슬이 던져졌을때 위에서 첨만나는 벽돌 찾기
			int r = 0;
			while (r < h && map[r][c] == 0)
				r++;
//			int r = bricks[c].size();
			if (r == h) {// 열이 비었음
				go(cnt + 1, map); // 다음구슬 던짐
			} else {// 맞은벽돌 있음
				// 이전 cnt까지의 map상태를 복사해서 사용
				for (int i = 0; i < h; i++)
					System.arraycopy(map[i], 0, copyMap[i], 0, w);
				// 맞은 벽돌 및 주변 벽돌 함께 제거 처리(연쇄적 처리)
				boom_bfs(copyMap, r, c);
//				boom_dfs(copyMap, r, c, copyMap[r][c]);
				// 제거된 벽돌들 내리기
				down(copyMap);
				// 다음 구슬 던지기
				go(cnt + 1, copyMap);
			}
		}
	}

	private static int getRemain(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static List<Integer> list = new ArrayList<Integer>();

	private static void down(int[][] map) {
		for (int c = 0; c < w; c++) {
			int r = h - 1; // 밑에서부터
			while (r > 0) {
				if (map[r][c] == 0) {// 빈칸이면 벽돌내리기
					int nr = r - 1;// 자신 행부터 탐색
					while (nr > 0 && map[nr][c] == 0)
						nr--;
					map[r][c] = map[nr][c];// 현재 빈칸엔 자신의 위쪽으로 첨만나는 벽돌로 내리기
					map[nr][c] = 0;
				}
				r--;
			}
		}

		// 이런식으로 하면됨
//		for (int c = 0; c < w; c++) {
//			int r;
//			for (r = h - 1; r >= 0; r--) {
//				if (map[r][c] > 0) {
//					list.add(map[r][c]);
//					map[r][c] = 0;// 벽돌이 있던자리 빈칸으로
//				}
//			} // 부서지지 않은 벽돌만 리스트에 담기
//
//			// 리스트에 있는 벽돌 제일 아래 행부터 채우기
//			r = h - 1;
//			for (Integer b : list)
//				map[r--][c] = b;
//			list.clear();
//		}
	}

	private static void boom_bfs(int[][] map, int r, int c) {
		// bfs로 함께 부숴질 벽돌 처리
		q.clear();
		if (map[r][c] > 1)
			q.offer(new Point(r, c, map[r][c]));
		// 0으로 마킹하는게 방문처리해주는거 - visited배열안써도됨
		map[r][c] = 0;

		while (q.size() > 0) {
			Point p = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				for (int k = 1; k < p.cnt; k++) {
					nr += dir[d][0];
					nc += dir[d][1];
					if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == 0)
						continue;
					if (map[nr][nc] > 1)
						q.offer(new Point(nr, nc, map[nr][nc]));
					map[nr][nc] = 0;
				}
			}
		}
	}

	private static void boom_dfs(int[][] map, int r, int c, int cnt) { // cnt:r,c위치의 벽돌의 숫자
		map[r][c] = 0;
		if (cnt == 1)
			return;

		for (int d = 0; d < 4; d++) {
			int nr = r;
			int nc = c;
			for (int k = 1; k < cnt; k++) {
				nr += dir[d][0];
				nc += dir[d][1];
				if (nr < 0 || nr >= h || nc < 0 || nc >= w || map[nr][nc] == 0)
					continue;
				boom_dfs(map, nr, nc, map[nr][nc]);
			}
		}
	}
}
