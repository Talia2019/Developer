import java.util.Arrays;
import java.util.Scanner;

public class Solution_SWEA_1954_달팽이숫자_D2_정지영 {
//	달팽이의 크기 N은 1 이상 10 이하의 정수이다. (1 ≤ N ≤ 10)
//	[입력]
//	가장 첫 줄에는 테스트 케이스의 개수 T가 주어지고, 그 아래로 각 테스트 케이스가 주어진다.
//	각 테스트 케이스에는 N이 주어진다.
//	[출력]
//	각 줄은 '#t'로 시작하고, 다음 줄부터 빈칸을 사이에 두고 달팽이 숫자를 출력한다.
//	(t는 테스트 케이스의 번호를 의미하며 1부터 시작한다.)
	public static int[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[][] dir = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, }; // 우 하 좌 상
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			StringBuilder sb = new StringBuilder();
			map = new int[t][t];
			int d = 0;
			int x = 0, y = 0;
			int cnt = 1;
			while (true) {
				if (cnt > t * t) 
					break;
				map[x][y] = cnt++;
				x = x + dir[d][0];
				y = y + dir[d][1];
//				System.out.println("현xy"+x+" "+y);
				if(isChangeDir(x,y,t)) {
//					System.out.println("바꿈");
//					System.out.println("현xy"+x+" "+y);
					x -= dir[d][0];
					y -= dir[d][1];
					d++;
					if(d>=4) d%=4;
					x += dir[d][0];
					y += dir[d][1];
				}
				
			}
			System.out.print(printMap(i));
		}
	}
	public static StringBuilder printMap(int t) {
		StringBuilder sb = new StringBuilder();
		sb.append('#').append(t).append('\n');
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				sb.append(map[i][j]).append(" "); 
			}
			sb.append('\n');
		}
		return sb;
	}
	public static boolean isChangeDir(int x, int y, int n) {	//벽만나거나, 이미 적혀진곳 만나면 방향 바꿔야됨
		if (x < 0 || x >= n || y < 0 || y >= n) {
			return true;
		} else if (map[x][y] != 0)
			return true;
		return false;
	}

}
