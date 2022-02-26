import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SWEA_D2_2005_파스칼의삼각형 {

	// 0번째나, 마지막이면 1이고
	// 아니면 pascal[i-1][j-1]+pascal[i-1][j]
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		int[][] pascal;

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			pascal = new int[n][n];
			
			sb.append("#").append(tc).append("\n");
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= i; j++) {
					if (j == 0 || j == i) {
						pascal[i][j] = 1;
					} else {
						pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
					}
					sb.append(pascal[i][j]+" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
