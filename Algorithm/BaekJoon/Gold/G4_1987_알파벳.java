import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G4_백준_1987_알파벳 {

//	G4 1987 알파벳

//	flag배열 만들어서 방문했는지아닌지? 방문했으면 나감

	public static boolean[] isVisited;
	public static int[][] board;
	public static int max;
	public static int r, c;
	public static int[][] dir = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		isVisited = new boolean[26]; // 알파벳수
		board = new int[r][c];

		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++) {
				board[i][j] = str.charAt(j) - 'A';
			}
		}
		isVisited[board[0][0]] = true;
		movingHorse(0, 0, 1);
		System.out.println(max);
	}

	// 내가 더이상 움직일 곳이 없을때 max cnt와 비교
	// 처음에 해당 칸을 방문하는것과 알파벳 방문을 따로 생각했었는데, 잘 생각해보니 둘을 구분할 필요가 없었다!
	public static void movingHorse(int x, int y, int cnt) {

//		int temp = board[x][y];
//		board[x][y] = -1; // 해당 칸 방문 

		int nx = 0, ny = 0;
		int curChar;
		for (int i = 0; i < 4; i++) {
			nx = x + dir[i][0];
			ny = y + dir[i][1];
			if (isOut(nx, ny))
				continue;
			curChar = board[nx][ny];
//			if (curChar == -1)// 이미방문했던칸
//				continue;
			if (isVisited[curChar]) // 방문했던 알파벳
				continue;
			isVisited[curChar] = true; // 방문처리
			movingHorse(nx, ny, cnt + 1);
			isVisited[curChar] = false;
		}

//		board[x][y] = temp;// 칸 방문해제
		max = max > cnt ? max : cnt;
	}

	public static boolean isOut(int x, int y) {
		if (x < 0 || x >= r || y < 0 || y >= c)
			return true;
		return false;
	}
}
