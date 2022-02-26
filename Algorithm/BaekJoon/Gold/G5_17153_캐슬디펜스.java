import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

//	게임이 진행되는 곳은 크기가 N×M인 격자판으로 나타낼 수 있다. 격자판은 1×1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대 하나이다. 격자판의 N번행의 바로 아래(N+1번 행)의 모든 칸에는 성이 있다.
//	성을 적에게서 지키기 위해 궁수 3명을 배치하려고 한다. 궁수는 성이 있는 칸에 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 각각의 턴마다 궁수는 적 하나를 공격할 수 있고, 
//	모든 궁수는 동시에 공격한다. 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 같은 적이 여러 궁수에게 공격당할 수 있다. 
//	공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면, 적이 이동한다. 적은 아래로 한 칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다. 모든 적이 격자판에서 제외되면 게임이 끝난다
//	궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산해보자.
//	격자판의 두 위치 (r1, c1), (r2, c2)의 거리는 |r1-r2| + |c1-c2|이다.

//	첫째 줄에 격자판 행의 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다. 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈 칸, 1은 적이 있는 칸이다.
//	첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.

//	NC3 조합 - 궁수위치
//	궁수가 커버할수있는 열이 중요.. 해당 열에 있으면 언젠간 죽임
//	궁수가 커버못하는 열을 구하고 해당 열에 위치하는 적들의 수 가 가장 적은것 구하기
//	궁수(x y) 거리 d  -> y-(d-1) y+(d-1) 까지 커버됨

	private static int[] archers = new int[3];
	private static int n, m, d;
	private static int max;
	private static int[][] map;
	private static List<LinkedList<Integer>> enemy = new ArrayList<LinkedList<Integer>>();
	private static int[][] range;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		map = new int[n][m];

		for (int i = 0; i < m; i++) { // 열개수만큼
			enemy.add(new LinkedList<Integer>());
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					enemy.get(j).add(i); // j를 기준으로 i를 집어넣음
			}
		}

		comb(0, 0);
		System.out.println(max);
	}

	// 궁수 셋뽑고 뽑을때마다 처리가능한 적 수 구하기
	public static void comb(int cnt, int start) {
		if (cnt == 3) { // 궁수 셋뽑음
			int kill = playGame();
			max = max > kill ? max : kill;
			return;
		}
		for (int i = start; i < m; i++) {
			archers[cnt] = i;
			comb(cnt + 1, i + 1);
		}
		return;
	}

	public static int playGame() {
		boolean[][] flag = new boolean[n][m];
		getRange(); // 궁수들이 처리할수있는 index는 true
		int kill = 0;

		List<LinkedList<Integer>> enemyCopy = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < m; i++) {
			LinkedList<Integer> list = new LinkedList<>();
			list.addAll(enemy.get(i));
			enemyCopy.add(list);
		}

		while (true) {
//			System.out.println("궁수:" + archers[0] + " " + archers[1] + " " + archers[2]);
			for (int i = n - 1; i >= 0; i--) {
//				System.out.println(i + "행!");
				List<Integer> yy = new ArrayList<>();
				for (int arch = 0; arch < 3; arch++) { // 궁수 셋
					int x = -1, y = -1;
					int minDistance = Integer.MAX_VALUE;
//					System.out.println("궁수: " + arch);
					int start = range[arch][0];
					int end = range[arch][1];
					int index = 0;
//					System.out.println("궁수의 범위가" + d + "라서 " + start + "부터" + end + "까지");
					findMin: for (int j = start; j <= end; j++) { // 궁수가 처리할수있는 적중 최소거리이면서 왼쪽아이 찾기
						if (!enemyCopy.get(j).isEmpty()) {
							int e = enemyCopy.get(j).peekLast();
							while (e > i) {
								enemyCopy.get(j).removeLast();
								if (enemyCopy.get(j).isEmpty()) {
									continue findMin;
								}
								e = enemyCopy.get(j).peekLast();
							}

//							System.out.println(j + "열의 적이있음 : " + e+"행에");
							int dis = Math.abs(i - e) + Math.abs(archers[arch] - j);
							if (dis <= d - 1) { // 궁수가 처리할수 있는 애중에서
								if (dis < minDistance) { // 현거리가 최소일때 적의 위치
									x = index; // 적 리스트에서 인덱스
									y = j;
									minDistance = dis;
//									System.out.println(e + "행" + j + "열 " + " 거리는" + dis);
								}
							}
							index++;
						}
					}
					if (x != -1) {
//						System.out.println("킬");
//						System.out.println(enemyCopy.get(y).removeLast()+"행" +y+"열  제거");
						int x2 = enemyCopy.get(y).peekLast();
						if (!flag[x2][y]) {
							flag[x2][y] = true;
							yy.add(y);
							kill++;
						}
					}
				}
				for (Integer integer : yy) {
					enemyCopy.get(integer).removeLast();
//					System.out.println(enemyCopy.get(integer).removeLast() + "행" + integer + "제거");
				}
			}
			break;
		}
//		System.out.println(kill);

		return kill;
	}

//	 해당 궁수가 처리할수 있는 열을 구하고 그걸 바탕으로 처리가능한 적 구함
//	궁수(x y) 거리 d  -> y-(d-1) y+(d-1) 까지 커버됨
//	궁수들이 눈앞의 한줄에서 처리할수있는 index의 시작점, 끝점
	public static void getRange() {
		range = new int[3][2];
		for (int i = 0; i < archers.length; i++) {
			int start = archers[i] - (d - 1);
			int end = archers[i] + d - 1;
			if (start < 0)
				range[i][0] = 0;
			else
				range[i][0] = archers[i] - (d - 1);
			if (end >= m)
				range[i][1] = m - 1;
			else
				range[i][1] = archers[i] + d - 1;
		}
		return;
	}

}
