package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	//G5 1600 말이되고픈원숭이
	
//	dp인지 아닌지 모르겠다? -> 시뮬로 풀고, 재귀에서 저장할수있는 부분이 있으면 메모이제이션까지..->dp로 가야할때는 재귀가 너무많이쌓여서 스택오버플로우 쌓일때
//	콜스택엔 2000개정도 쌓을수있음
//
//	dfs : 일반적으론 가지치기가 많이되지만, 2차원배열같은 경우는 가지치기가 잘 안됨 bfs가 유리
//	bfs : 2차원배열 그래프.. 등 시뮬레이션 사방/팔방탐색에서는 주로 bfs가 가지치기 많이됨
//
//	행/열/남은k개수로 3차원배열로 방문체크를 해야함

	public static int minMoveCnt;
	public static int[][] dirNear = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };// 상하좌우
	public static int[][] dirHorse = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
			{ -2, -1 } };// 시계방향
	public static char[][] map;
	public static boolean[][][] visited;
	public static int k, w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		k = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken()); // 1<=w,h<=200
		h = Integer.parseInt(st.nextToken());

		map = new char[h][w];
		visited = new boolean[h][w][k + 1];

		for (int i = 0; i < h; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < w; j++, index += 2) {
				map[i][j] = str.charAt(index); // '0':평지 '1':장애물
			}
		}

		minMoveCnt = -1; // bfs에서는 도착하면 끝이니까 이 초기값이 출력되도록..

		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 0, 0, k, 0 }); // 큐에 시작점넣기
		visited[0][0][k] = true;

		while (q.size() > 0) { // 큐가 빌때까지 반복
			int[] arr = q.poll(); // 큐에서 꺼내기, r,c,k,moveCnt
			int r = arr[0];
			int c = arr[1];
			int kk = arr[2];
			int moveCnt = arr[3];

			// 우측하단에 도달하면 종료, min업데이트
			if (r == h - 1 && c == w - 1) {
				minMoveCnt = moveCnt;
				break;
			}

			// 현정점에 이동가능한 좌표 큐에 넣어줌
			
			for (int i = 0; i < 4; i++) { // 상하좌우
				int nr = r + dirNear[i][0];
				int nc = c + dirNear[i][1];

				// 정점에 인접한, 방문하지 않은정점, 평지일 경우만 진입(큐에넣기)
				if (isIn(w, h, nr, nc) && !visited[nr][nc][kk] && map[nr][nc] == '0') {
					q.offer(new int[] { nr, nc, kk, moveCnt + 1 });
					visited[nr][nc][kk] = true;
				}
			}
			if (kk == 0) // 말처럼 이동횟수 남아있으면 진행
				continue;

			for (int i = 0; i < 8; i++) { // 말처럼
				int nr = r + dirHorse[i][0];
				int nc = c + dirHorse[i][1];

				// 정점에 인접한, 방문하지 않은정점, 평지일 경우만 진입(큐에넣기)
				if (isIn(w, h, nr, nc) && !visited[nr][nc][kk - 1] && map[nr][nc] == '0') {
					q.offer(new int[] { nr, nc, kk - 1, moveCnt + 1 });
					visited[nr][nc][kk - 1] = true;
				}
			}

		}
		System.out.println(minMoveCnt);
	}

	public static boolean isIn(int w, int h, int dx, int dy) {
		if (dx >= 0 && dx < h && dy >= 0 && dy < w)
			return true;
		return false;
	}

}
