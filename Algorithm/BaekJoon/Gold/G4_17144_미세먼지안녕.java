package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G4_17144_미세먼지안녕 {

	private static int[][] map, tmpMap;
	private static int r, c, t;
	private static List<int[]> dust = new ArrayList<int[]>();
//	private static List<int[]> dustWind = new ArrayList<int[]>();
	private static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
//	private static int[][] cleaner = new int[2][2];
	private static int topR, topC, botR, botC; // cleaner

	// G4 17144 미세먼지안녕

	// 확산시킨다음에 (공기청정기 있는곳 제외)
	// 바람불게 해서 한칸씩 민다
	
	// 바람이 부는곳이 아닌이상 먼지는 줄어들순 있어도 사라지진 않음(위치가 변하지 않는다)
	// 얘를 구현하면 좀 더 빨라지긴 하겠으나.. 구현하지않았음..
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		map = new int[r + 2][c + 2];
		tmpMap = new int[r + 2][c + 2];
		for (int[] is : map) {
			Arrays.fill(is, -1);
		}

		for (int i = 1; i <= r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					dust.add(new int[] { i, j });
				if (map[i][j] < 0) {
					botR = i;
					botC = j;
				}
			}
		}

		topR = botR - 1;
		topC = botC;

		for (int i = 0; i < t; i++) {
			spread();
			cleaning();
//			System.out.println("----cleaning----");
//			for (int[] is : map) {
//				System.out.println(Arrays.toString(is));
//			}
		}
		int cnt = 0;
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				cnt += map[i][j];
			}
		}
		cnt += 2;
		System.out.println(cnt);
	}

	public static void spread() {
		int nx, ny, x, y;
		int spreadDust;

		for (int[] is : tmpMap) {
			Arrays.fill(is, 0);
		}

		for (int[] d : dust) {
			x = d[0];
			y = d[1];
			spreadDust = map[x][y] / 5;
			if (spreadDust > 0) {
				for (int i = 0; i < 4; i++) {
					nx = x + dir[i][0];
					ny = y + dir[i][1];
					if (map[nx][ny] != -1) {
						tmpMap[nx][ny] += spreadDust;
						map[x][y] -= spreadDust;
					}
				}
			}
		}

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				map[i][j] += tmpMap[i][j];
			}
		}
	}

	// top은 반시계 -> topR행: 우, 0행: 좌, c-1열 : 상, 0열 : 하
	// bot은 시계 -> botR행 : 우, r-1행: 좌, c-1열 : 하, 0열 : 상
	public static void cleaning() {
		// top
		// 왼쪽 열
		for (int i = topR - 1; i > 1; i--) {
			map[i][1] = map[i - 1][1];
		}
		// 위쪽 행
		for (int i = 1; i < c; i++) {
			map[1][i] = map[1][i + 1];
		}
		// 오른쪽 열
		for (int i = 1; i < topR; i++) {
			map[i][c] = map[i + 1][c];
		}
		// topR행
		for (int i = c; i > 2; i--) {
			map[topR][i] = map[topR][i - 1];
		}
		map[topR][2] = 0;

		// bottom
		// 왼쪽 열
		for (int i = botR + 1; i < r; i++) {
			map[i][1] = map[i + 1][1];
		}
		// 아래쪽 행
		for (int i = 1; i < c; i++) {
			map[r][i] = map[r][i + 1];
		}
		// 오른쪽 열
		for (int i = r; i > botR; i--) {
			map[i][c] = map[i - 1][c];
		}
		// botR행
		for (int i = c; i > 2; i--) {
			map[botR][i] = map[botR][i - 1];
		}
		map[botR][2] = 0;
		
		dust.clear();
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if(map[i][j]>0)
					dust.add(new int[] {i,j});
			}
		}
	}

	public static void getWindDust() {
		for (int i = 1; i <= c; i++) {
//			if(map)
		}
	}
}

//공기청정기는 항상 1번 열에 설치되어 있고, 크기는 두 행을 차지한다. 공기청정기가 설치되어 있지 않은 칸에는 미세먼지가 있고, (r, c)에 있는 미세먼지의 양은 Ar,c이다.
//
//1초 동안 아래 적힌 일이 순서대로 일어난다.
//
//미세먼지가 확산된다. 확산은 미세먼지가 있는 모든 칸에서 동시에 일어난다.
//(r, c)에 있는 미세먼지는 인접한 네 방향으로 확산된다.
//인접한 방향에 공기청정기가 있거나, 칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
//확산되는 양은 Ar,c/5이고 소수점은 버린다.
//(r, c)에 남은 미세먼지의 양은 Ar,c - (Ar,c/5)×(확산된 방향의 개수) 이다.
//공기청정기가 작동한다.
//공기청정기에서는 바람이 나온다.
//위쪽 공기청정기의 바람은 반시계방향으로 순환하고, 아래쪽 공기청정기의 바람은 시계방향으로 순환한다.
//바람이 불면 미세먼지가 바람의 방향대로 모두 한 칸씩 이동한다.
//공기청정기에서 부는 바람은 미세먼지가 없는 바람이고, 공기청정기로 들어간 미세먼지는 모두 정화된다.

//입력
//첫째 줄에 R, C, T (6 ≤ R, C ≤ 50, 1 ≤ T ≤ 1,000) 가 주어진다.
//둘째 줄부터 R개의 줄에 Ar,c (-1 ≤ Ar,c ≤ 1,000)가 주어진다. 공기청정기가 설치된 곳은 Ar,c가 -1이고, 나머지 값은 미세먼지의 양이다. -1은 2번 위아래로 붙어져 있고, 가장 윗 행, 아랫 행과 두 칸이상 떨어져 있다.

//출력
//첫째 줄에 T초가 지난 후 구사과 방에 남아있는 미세먼지의 양을 출력한다.
