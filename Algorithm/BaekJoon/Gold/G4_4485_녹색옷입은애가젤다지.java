package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_G4_4485_녹색옷입은애가젤다지 {

	// G4 4485 녹색옷입은애가젤다지

	// 최소경로찾기
	// 다익스트라?

	static class Coin implements Comparable<Coin> {
		int x, y;
		int value;

		public Coin(int x, int y, int value) {
			super();
			this.x = x;
			this.y = y;
			this.value = value;
		}

		@Override
		public int compareTo(Coin o) {
			return Integer.compare(this.value, o.value);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		final int INFINITY = Integer.MAX_VALUE;

		int n, t;
		int[][] map;
		int[] distance;
		boolean[] visited;
		int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };
		PriorityQueue<Coin> heap = new PriorityQueue<>();

		t = 0;
		while (true) {
			t++;
			n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;

			map = new int[n][n];
			int len = n * n;
			distance = new int[len];
			visited = new boolean[len];
			Arrays.fill(distance, INFINITY);

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 시작점
			distance[0] = map[0][0];
			heap.add(new Coin(0, 0, distance[0]));
			int min, cur = 0;
			int x, y, nx, ny;

			// 우선순위큐 사용해보기
			while (heap.size() > 0) {
				Coin coin = heap.poll();

				int c = coin.x * n + coin.y;
//				if (c == len - 1) ///모든걸 다 돌기전에 나가버려서 최선이 아닐경우가 있을수 있음!!!
//					break;
				if (distance[c] < coin.value)
					continue;
				visited[c] = true;

				for (int j = 0; j < 4; j++) {
					nx = coin.x + dir[j][0];
					ny = coin.y + dir[j][1];

					c = nx * n + ny;
					if (!isOut(nx, ny, n) && !visited[c] && distance[c] > coin.value + map[nx][ny]) {
						distance[c] = coin.value + map[nx][ny];
						heap.add(new Coin(nx, ny, distance[c]));
					}
				}
			}

//			인접행렬 다익스트라
//			for (int i = 0; i < len; i++) {
//				min = INFINITY;
//				for (int j = 0; j < len; j++) {
//					if (!visited[j] && distance[j] < min) {
//						min = distance[j];
//						cur = j;
//					}
//				}
//
//				// 방문처리
//				if (cur == len - 1)
//					break;
//				visited[cur] = true;
//
//				x = cur / n;
//				y = cur % n;
//				for (int j = 0; j < 4; j++) {
//					nx = x + dir[j][0];
//					ny = y + dir[j][1];
//
//					int c = nx * n + ny;
//					if (!isOut(nx, ny, n) && !visited[c] && distance[c] > min + map[nx][ny]) {
//						distance[c] = min + map[nx][ny];
//					}
//				}
//
//			}
			sb.append("Problem ").append(t).append(": ").append(distance[len - 1]).append("\n");
		}
		System.out.println(sb);
	}

	public static boolean isOut(int x, int y, int n) {
		if (x < 0 || x >= n || y < 0 || y >= n)
			return true;
		return false;
	}

}
//링크는 잃는 금액을 최소로 하여 동굴 건너편까지 이동해야 하며, 한 번에 상하좌우 인접한 곳으로 1칸씩 이동할 수 있다.
//링크가 잃을 수밖에 없는 최소 금액은 얼마일까?
//입력
//입력은 여러 개의 테스트 케이스로 이루어져 있다.
//각 테스트 케이스의 첫째 줄에는 동굴의 크기를 나타내는 정수 N이 주어진다. (2 ≤ N ≤ 125) N = 0인 입력이 주어지면 전체 입력이 종료된다.
//이어서 N개의 줄에 걸쳐 동굴의 각 칸에 있는 도둑루피의 크기가 공백으로 구분되어 차례대로 주어진다. 도둑루피의 크기가 k면 이 칸을 지나면 k루피를 잃는다는 뜻이다. 여기서 주어지는 모든 정수는 0 이상 9 이하인 한 자리 수다.
//출력
//각 테스트 케이스마다 한 줄에 걸쳐 정답을 형식에 맞춰서 출력한다. 형식은 예제 출력을 참고하시오.
