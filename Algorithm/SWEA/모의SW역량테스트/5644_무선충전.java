package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

//	5644 무선충전 모의SW역량테스트

//	배터리 객체를 만들어서 사용인원/배터리등 넣어둠
//	사용자는 중첩된 칸에 들어간경우 사용총합의 최댓값을 구하는거라 미사용중인 배터리를 사용하는게 제일 효율적
//
	private static List<Integer>[][] map;
	private static Battery[] battery;
	private static int[] firstUser, secondUser;
//	private static int[][] dir = { { 0, 0 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };// 상우하좌
	private static int[][] dir = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };// 상우하좌
	private static int totalCharge;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		int m, a;
		map = new ArrayList[10][10]; // 리스트의 배열

		for (int tc = 1; tc <= t; tc++) {

			totalCharge = 0;

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					map[i][j] = new ArrayList<>();
				}
			}

			st = new StringTokenizer(br.readLine(), " ");
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			firstUser = new int[m];
			secondUser = new int[m];

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < m; i++)
				firstUser[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < m; i++)
				secondUser[i] = Integer.parseInt(st.nextToken());

			battery = new Battery[a];
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				makeMap(i, x - 1, y - 1, c);
				battery[i] = new Battery(p);
			}

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					Collections.sort(map[i][j], new Comparator<Integer>() {

						@Override
						public int compare(Integer o1, Integer o2) {
							return battery[o2].power - battery[o1].power;
						}
					});
				}
			}

			play(m);
			sb.append("#").append(tc).append(" ").append(totalCharge).append("\n");
		}

		System.out.println(sb);
	}

	public static void printMap(int x, int y, int x2, int y2) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == x && y == i)
					System.out.print("-1");
				else if (j == x2 && i == y2)
					System.out.print("-2");
				else
					System.out.print(map[j][i].size() + " ");
			}
			System.out.println();
		}
		System.out.println("+++++++++++++++++++++++");
	}

	public static void play(int m) {
		int fx = 0, fy = 0;
		int sx = 9, sy = 9;
		int fd, sd;
		int fbi = -1, sbi = -1;
		int fb1 = 0, fb2 = 0, sb1 = 0, sb2 = 0;
		for (int i = 0; i <= m; i++) {
			fb1 = fb2 = sb1 = sb2 = 0;
			// 둘다 첫번째 배터리 주고
			// 두번째 배터리 있는애가 두번째 배터리 씀

			List<Integer> batteryList = map[fx][fy];
			fbi = sbi = -1;
			if (batteryList.size() > 0) { // 배터리가 있는 상태
				// 어떤 배터리 쓸지 고름
				fbi = batteryList.get(0);
				fb1 = battery[batteryList.get(0)].power;
				if (batteryList.size() > 1) { // 두번째 배터리 있음
					fb2 = battery[batteryList.get(1)].power;
				}
				battery[fbi].userNum++;
			}
			batteryList = map[sx][sy];
			if (batteryList.size() > 0) { // 배터리가 있는 상태
				// 어떤 배터리 쓸지 고름
				sbi = batteryList.get(0);
				sb1 = battery[batteryList.get(0)].power;
				if (batteryList.size() > 1) { // 두번째 배터리 있음
					sb2 = battery[batteryList.get(1)].power;
				}
			}
			if (fbi != -1 && fbi == sbi) { // 둘다 같은 배터리를 고른경우
				totalCharge += fb1;
				totalCharge += (fb2 > sb2 ? fb2 : sb2);
			} else {
				totalCharge += (fb1 + sb1);
			}

//			if (fbi != -1)
//				System.out.println("1번이 배터리씀: " + battery[fbi].power);
//			if (sbi != -1)
//				System.out.println("2번이 배터리씀: " + battery[sbi].power);
//			printMap(fx, fy, sx, sy);

			if (i == m)
				break;
			fd = firstUser[i];
			sd = secondUser[i];
			fx += dir[fd][0];
			fy += dir[fd][1];
			sx += dir[sd][0];
			sy += dir[sd][1];
		}
	}

	public static void makeMap(int bi, int x, int y, int c) {
		for (int i = -c; i <= c; i++) {
			for (int j = -c; j <= c; j++) {
				if (Math.abs(i) + Math.abs(j) <= c && !isOut(x + i, y + j)) {
					map[x + i][y + j].add(bi);
				}
			}
		}
	}

	public static boolean isOut(int x, int y) {
		if (x < 0 || x >= 10 || y < 0 || y >= 10)
			return true;
		return false;
	}

	static class Battery {
		public int power;
		public int userNum;

		public Battery(int power) {
			this.power = power;
			userNum = 0;
		}
	}

}
