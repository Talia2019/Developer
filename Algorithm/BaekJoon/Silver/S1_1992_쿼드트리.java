import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_S1_1992_쿼드트리 {

	// S1 1992 쿼드트리
	// 다른게 있으면 괄호를 붙이고 함수호출 -> 이걸 리턴

	public static char[][] video;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		video = new char[n][n];
		
		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				video[i][j] = input.charAt(j);
			}
		}
		
		System.out.println(quadTree(0, 0, n));

	}

	public static String quadTree(int r, int c, int len) {
		StringBuilder res = new StringBuilder();

		char flag = video[r][c];
		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (video[i][j] != flag) {
					int half = len / 2;
					res.append("(");
					res.append(quadTree(r, c, half));
					res.append(quadTree(r, c + half, half));
					res.append(quadTree(r + half, c, half));
					res.append(quadTree(r + half, c + half, half));
					res.append(")");
					return res.toString();
				}
			}
		}
		res.append(flag);
		return res.toString();
	}

}
