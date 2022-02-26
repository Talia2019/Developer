package boj;

import java.util.Scanner;

public class Main_S5_1913_달팽이 {

//	S5 1913 달팽이
//	홀수 자연수 N이 주어졌을 때, 1부터 N^2까지  자연수를 달팽이모양으로 N*N에 채우기
//	또한 N2 이하의 자연수가 하나 주어졌을 때, 그 좌표도 함께 출력하시오. 예를 들어 N=5인 경우 6의 좌표는 (4,3)이다.
//	첫째 줄에 홀수인 자연수 N(3 ≤ N ≤ 999)이 주어진다. 둘째 줄에는 위치를 찾고자 하는 N2 이하의 자연수가 하나 주어진다.
//	N개의 줄에 걸쳐 표를 출력한다. 각 줄에 N개의 자연수를 한 칸씩 띄어서 출력하면 되며, 자릿수를 맞출 필요가 없다. N+1번째 줄에는 입력받은 자연수의 좌표를 나타내는 두 정수를 한 칸 띄어서 출력한다.

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int n = sc.nextInt();
		int object = sc.nextInt();

		int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };// 상 우 하 좌
		int[][] map = new int[n][n];

		int cnt = 1;
		int x = n / 2, y = n / 2;
		int d = 0;
		int block = 0;
		int b = 1, num = 0; // n칸씩 두번
		int ix=0, iy=0;
		
		while (cnt <= n * n) {
			if(cnt==object) {
				ix = x + 1;
				iy = y + 1;
			}
			map[x][y] = cnt++;
			x += dir[d][0];
			y += dir[d][1];
			block++;
			if (block == b) {
				d++;
				block = 0;
				if (d >= 4)
					d %= 4;
				if(++num == 2) {
					num=0;
					b++;
				}
			}
			
		}

		for (int[] is : map) {
			for (int i : is) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
		}
		sb.append(ix).append(" ").append(iy);
		System.out.println(sb);
	}

}
