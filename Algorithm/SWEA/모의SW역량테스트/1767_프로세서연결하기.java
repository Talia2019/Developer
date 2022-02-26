
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution_1767_프로세서연결하기{

	// 1767 프로세서에 연결하기

	// 부분집합(어떤코어쓸지)을 이용한 완전탐색
	// 코어수를 n이라할때 5^12보단 많이 작다함
	// 비 가장자리 코어를 리스트에 담고 코어리스트로 부분집합 처리하여 시도해볼 코어 선택
	// 선택된 코어마다 네방향 전선놓기 시도(해당 방향 놓을수있는지 체크)

	public static List<int[]> cores;
	public static int n;
	public static int[][] mexinos;
//	public static boolean[][] visited;
	public static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } }; // 우좌하상
	public static int maxCore, minLine, totalCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		cores = new ArrayList<int[]>();
		String str;

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			mexinos = new int[n][n];
			cores.clear();
			maxCore = 0;
			minLine = n * n;
//			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				str = br.readLine();
				for (int j = 0; j < n; j++) {
					mexinos[i][j] = str.charAt(j * 2);
					if (i == 0 || i == n - 1 || j == 0 || j == n - 1)
						continue;
					if (mexinos[i][j] == '1') {
//						visited[i][j] = true;
						cores.add(new int[] { i, j });
					}
				}
			}
			totalCnt = cores.size(); // 가장자리 아닌 코어 수
//			findCore(0, 0, 0);
			go(0, 0);
			sb.append("#").append(tc).append(" ").append(minLine).append("\n");
		}
		System.out.println(sb);
	}

	// 코어시작 인덱스, 현재 선택한 코어 개수
	public static void go(int index, int cCnt) {

		if (index == totalCnt) {
			int res = getLength();
			// 선택된 코어 개수로 max갱신
			if (maxCore < cCnt) {
				maxCore = cCnt;
				minLine = res;
			} else if (maxCore == cCnt) {
				if (minLine > res)
					minLine = res;
			}
			return;
		}

		int[] cur = cores.get(index);
		int r = cur[0];
		int c = cur[1];
		// index코어를 선택 : 4방향 시도
		for (int d = 0; d < 4; d++) {
			if (isAvailable(r, c, d)) {
				// 전선놓기
				setLines(r, c, d, '2');
				// 다음코어로
				go(index + 1, cCnt + 1);
				// 놓았던 전선지우기
				setLines(r, c, d, '0');
			}
		}

		// index코어 미선택
		go(index + 1, cCnt);

	}

	private static int getLength() {
		int res = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (mexinos[i][j] == '2')
					res++;
			}
		}
		return res;
	}

	private static void setLines(int r, int c, int d, char s) {
		int nr = r, nc = c;
		while (true) {
			nr += dir[d][0];
			nc += dir[d][1];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				break;
			mexinos[nr][nc] = s;
		}
	}

	private static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		while (true) {
			nr += dir[d][0];
			nc += dir[d][1];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n)
				break;
			if (mexinos[nr][nc] != '0')
				return false;
		}
		return true;
	}

//	// 연결된 코어 개수, 코어 번호
//	public static void findCore(int cnt, int num, int lines) {
//		if (num == cores.size()) { // 다 확인한경우
//			if (cnt >= maxCore) {
//				maxCore = cnt;
//				if (minLine > lines)
//					minLine = lines;
//			}
//		}
//		for (int i = num; i < cores.size(); i++) {
//			int[] core = cores.get(i);
//			int x = core[0];
//			int y = core[1];
//
//			if (x == 0 || x == n - 1 || y == 0 || y == n - 1) {
////				System.out.println(cnt + 1 + " 번째 코어는 바로연결");
//				findCore(cnt + 1, num + 1, lines);
//			} else {
//				for (int j = 0; j < 4; j++) {
//					int line = connect(j, x + dir[j][0], y + dir[j][1]);
//					if (line > 0) {
//						findCore(cnt + 1, num + 1, lines + line);
//					} // 연결한경우
//				}
//				findCore(cnt, num + 1, lines); // 연결안한경우
//			}
//		}
//	}

	// 우좌하상
//	public static int connect(int d, int x, int y) {
//		int nx = x;
//		int ny = y;
//		int lines = 0;
//		switch (d) {
//		case 0:
//			while (ny < n) {
//				if (visited[nx][ny]) {
//					for (int i = y; i < ny; i++) {
//						visited[nx][i] = false;
//					}
//					return 1;
//				}
//				visited[nx][ny] = true;
//				lines++;
//				ny++;
//			}
//			break;
//		case 1:
//			while (ny > 0) {
//				if (visited[nx][ny]) {
//					for (int i = y; i > ny; i--) {
//						visited[nx][i] = false;
//					}
//					return 1;
//				}
//				visited[nx][ny] = true;
//				lines++;
//				ny--;
//			}
//			break;
//		case 2:
//			while (nx < n) {
//				if (visited[nx][ny]) {
//					for (int i = x; i < nx; i++) {
//						visited[i][ny] = false;
//					}
//					return 1;
//				}
//				visited[nx][ny] = true;
//				lines++;
//				nx++;
//			}
//			break;
//		case 3:
//			while (nx > 0) {
//				if (visited[nx][ny]) {
//					for (int i = x; i > nx; i--) {
//						visited[i][ny] = false;
//					}
//					return 1;
//				}
//				visited[nx][ny] = true;
//				lines++;
//				nx--;
//			}
//			break;
//		}
//		return lines;
//	}
}
