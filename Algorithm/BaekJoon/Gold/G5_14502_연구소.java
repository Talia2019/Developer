package jiyoung.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Laboratory {

//	G5 14502 연구소

//	연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.

//	첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
//	둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.
//	빈 칸의 개수는 3개 이상이다.
//	첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

//	고립된 공간이 있는가?
//	걍 완탐으로 모든 빈곳에 벽 세워보고 바이러스 퍼트려보기

//	벽이 아닌 공간의 수 - 최소 바이러스 수 를 출력할것
	
	public static int n, m;
	public static int[][] lab, copyLab;
	public static int virusNum, minVirusNum = 100, notWall;
	public static List<Integer[]> virus = new ArrayList<>();
	public static List<Integer[]> empty = new ArrayList<>();
	public static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };// 상하좌우

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) {	//바이러스
					virus.add(new Integer[] { i, j });
					notWall++;
				}
				else if (lab[i][j] == 0) {	//바이러스가 퍼질 수 있는 공간
					empty.add(new Integer[] { i, j });
					notWall++;
				}
			}
		}
		makeWall();
		System.out.println(notWall - minVirusNum - 3);	//벽3개 추가해줬으니 3빼줌
	}

	// 벽/바이러스가 있거나 나가는 위치를 제외하고 모든 곳에 벽을 설치해보고 바이러스 퍼트림
	public static void makeWall() {
		Integer[] firstWall, secondWall, thirdWall;
		for (int i = 0; i < empty.size(); i++) {
			firstWall = empty.get(i);
			lab[firstWall[0]][firstWall[1]] = 1;
			for (int j = i + 1; j < empty.size(); j++) {
				secondWall = empty.get(j);
				lab[secondWall[0]][secondWall[1]] = 1;
				for (int k = j + 1; k < empty.size(); k++) {
					thirdWall = empty.get(k);
					lab[thirdWall[0]][thirdWall[1]] = 1;
					
					spreadVirus();

					lab[thirdWall[0]][thirdWall[1]] = 0;
				}
				lab[secondWall[0]][secondWall[1]] = 0;
			}
			lab[firstWall[0]][firstWall[1]] = 0;
		}
	}

	public static boolean isRestricted(int x, int y) {
		if (x < 0 || x >= n || y < 0 || y >= m || copyLab[x][y] != 0)
			return true;
		return false;
	}

	public static void spreadVirus() {
		virusNum = virus.size();
		copyLab = new int[n][m];
		for (int i = 0; i < copyLab.length; i++) {	//맵복사
			System.arraycopy(lab[i], 0, copyLab[i], 0, lab[i].length);
		}
		for (Integer[] v : virus) {	//모든 바이러스를 퍼트려봄
			spreadOneVirus(v[0], v[1]);
		}
		minVirusNum = minVirusNum < virusNum ? minVirusNum : virusNum;
	}

	public static void spreadOneVirus(int x, int y) {
		int nx = 0, ny = 0;
		for (int i = 0; i < dir.length; i++) {
			nx = x + dir[i][0];
			ny = y + dir[i][1];
			if (!isRestricted(nx, ny)) {
				virusNum++;
				copyLab[nx][ny] = 2;
				spreadOneVirus(nx, ny);
			}
		}
	}

	public static void printMap() {
		for (int i = 0; i < copyLab.length; i++) {
			for (int j = 0; j < copyLab[i].length; j++) {
				System.out.print(copyLab[i][j] + " ");
			}
			System.out.println();
		}
	}
}
