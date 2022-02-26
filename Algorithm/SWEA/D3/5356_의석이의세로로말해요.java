import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_SWEA_D3_5356_의석이의세로로말해요 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());

		char[][] board = new char[5][15];
		for (int tc = 1; tc <= t; tc++) {
			//문자가 들어있지 않다는것을 알리는 문자
			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < 5; i++) {
				Arrays.fill(board[i], '!');
			}
			//채우기
			for (int i = 0; i < 5; i++) {
				String str = br.readLine();
				for (int j = 0; j < str.length(); j++) {
					board[i][j] = str.charAt(j);
				}
			}
			//출력
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 5; j++) {
					if(board[j][i]!='!') sb.append(board[j][i]);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
