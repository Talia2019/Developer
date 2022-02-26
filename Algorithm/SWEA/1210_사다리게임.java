package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SWEA_1210_D4 {
//			입력 파일의 첫 번째 줄에는 테스트 케이스의 번호가 주어지며, 바로 다음 줄에 테스트 케이스가 주어진다.
//			총 10개의 테스트 케이스가 주어진다.
//			#부호와 함께 테스트 케이스의 번호를 출력하고, 공백 문자 후 도착하게 되는 출발점의 x좌표를 출력한다.

	// 어떤아이디어?
	// 본인 소스코드공유

	// 목적지부터
	// 올라가며 왼쪽,오른쪽을 확인
	// 1이 있으면 이동
	// 좌/우로 이동하다가 0이 나오면 위로
	// 좌우 둘다 1이면 계속 이동하고, 아니면 바꿔
	public static final int SIZE = 100;
	public static int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 } }; // 좌 우 상
	public static int[][] ladder = new int[SIZE][SIZE];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		for (int testCase = 1; testCase <= 10; testCase++) {
			int tc = Integer.parseInt(br.readLine());
			for (int i = 0; i < SIZE; i++) { // 입력
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < SIZE; j++) {
					ladder[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int dest = 0;
			for (int i = 0; i < SIZE; i++) { // 목적지찾기
				if (ladder[SIZE - 1][i] == 2)
					dest = i;
			}

			int x = SIZE - 1, y = dest;
			int d = 2;
			while (x > 0) {
				d = getDir(x, y, d);
				x += dir[d][0];
				y += dir[d][1];
//				System.out.println("xy: " + x + " " + y);
			}
			sb.append("#").append(testCase).append(" ").append(y).append("\n");
		}
		System.out.print(sb);
	}

	public static int getDir(int x, int y, int d) { // 좌우 둘다 1이면 계속 이동하고, 아니면 바꿔
		int nx = x + dir[d][0];
		int ny = y + dir[d][1];

		if (ny < 0 || ny >= SIZE || ladder[nx][ny] == 0) { // 좌우로 이동하다가 위로 가야 하는경우
			return 2;
		}
		if (y - 1 > 0 && y + 1 < SIZE && ladder[x][y - 1] == 1 && ladder[x][y + 1] == 1) // 가던방향 가기
			return d;
		if (y - 1 > 0 && ladder[x][y - 1] == 1) // 좌
			return 0;
		if (y + 1 < SIZE && ladder[x][y + 1] == 1) // 우
			return 1;
		return 2;
	}

}
