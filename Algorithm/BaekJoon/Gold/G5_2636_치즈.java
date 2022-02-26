package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// G5 2636 치즈

//	입력으로 사각형 모양의 판의 크기와 한 조각의 치즈가 판 위에 주어졌을 때, 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간과 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 구하는 프로그램을 작성하시오.
//
//	입력
//	첫째 줄에는 사각형 모양 판의 세로와 가로의 길이가 양의 정수로 주어진다. 세로와 가로의 길이는 최대 100이다. 판의 각 가로줄의 모양이 윗 줄부터 차례로 둘째 줄부터 마지막 줄까지 주어진다. 치즈가 없는 칸은 0, 치즈가 있는 칸은 1로 주어지며 각 숫자 사이에는 빈칸이 하나씩 있다.
//
//	출력
//	첫째 줄에는 치즈가 모두 녹아서 없어지는 데 걸리는 시간을 출력하고, 둘째 줄에는 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수를 출력한다.

	// 가장자리를 다 죽여야됨
	// 가장자리를 어떻게구하지?? -> 사방에 0이 있는 칸은 가장자리..가 아니라
	// -> 연결된 빈공간(공기)을 모두 탐색후, 공기랑 인접해있으면 가장자리

	public static int[][] map;
	public static int h, w;
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };// 상하좌우
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new int[h + 2][w + 2];
		visited = new boolean[h + 2][w + 2];

		int cheeseNum = 0;
		for (int i = 1; i <= h; i++) {
			String str = br.readLine();
			for (int j = 1; j <= w; j++) {
				map[i][j] = str.charAt(2 * (j - 1)) - '0';
				if (map[i][j] == 1)
					cheeseNum++;
			}
		}

		boolean isCheese = true;
		int si = 1, sj = 1;
		int mx = h, my = w;
		int hour = 0, lastCheese = 0;
		List<int[]> deleteList = new ArrayList<int[]>();
		while (isCheese) {
			deleteList.clear();
			isCheese = false;
			hour++;
			lastCheese = cheeseNum;
			visited = new boolean[h + 2][w + 2];
			bfs(1, 1);

			for (int i = si; i <= h; i++) {
				for (int j = sj; j <= w; j++) {
					if (map[i][j] == 1) {
						if (mx > i)
							mx = i;
						if (my > j)
							my = j;
						if (isEdge(i, j)) {
							cheeseNum--;
							deleteList.add(new int[] { i, j });
						} else
							isCheese = true;
					}
				}
			}
			for (int[] is : deleteList) {
				map[is[0]][is[1]] = 0;
			}
			si = mx;
			sj = my;
			mx = h;
			my = w;
//			System.out.println("----------");
//			for (int[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}
//			System.out.println("----------");
		}
		System.out.println(hour);
		System.out.println(lastCheese);
	}

	public static boolean isEdge(int x, int y) {
		int dx, dy;
		for (int i = 0; i < 4; i++) {
			dx = x + dir[i][0];
			dy = y + dir[i][1];
			if (visited[dx][dy])
				return true;
		}
		return false;
	}

	// 공기를 모두 탐색
	public static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();

		q.offer(new int[] { x, y });
		visited[x][y] = true;
		int dx, dy;
		while (q.size() > 0) {
			int[] arr = q.poll();
			x = arr[0];
			y = arr[1];
			for (int i = 0; i < 4; i++) {
				dx = x + dir[i][0];
				dy = y + dir[i][1];
				if (isIn(dx, dy) && !visited[dx][dy] && map[dx][dy] == 0) {
					q.offer(new int[] { dx, dy });
					visited[dx][dy] = true;
				}

			}
		}
	}

	public static boolean isIn(int x, int y) {
		if (x >= 0 && x <= h+1 && y >= 0 && y <= w+1) return true;
		return false;
	}
}
