import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 파리퇴치
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PzOCKAigDFAUq&categoryId=AV5PzOCKAigDFAUq&categoryType=CODE&problemTitle=2001&orderBy=FIRST_REG_DATETIME&selectCodeLang=ALL&select-1=&pageSize=10&pageIndex=1
 */
public class Solution_SWEA_2001_D2 {
//	N x N 배열 안의 숫자는 해당 영역에 존재하는 파리의 개수를 의미한다.
//	M x M 크기의 파리채를 한 번 내리쳐 최대한 많은 파리를 죽이고자 한다.
//	죽은 파리의 개수를 구하라!
//	1. N 은 5 이상 15 이하이다.
//
//	2. M은 2 이상 N 이하이다.
//
//	3. 각 영역의 파리 갯수는 30 이하 이다.
	public static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= t; testCase++) {
			int n, m;
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
//				System.out.println(Arrays.toString(map[i]));
			}
			int max = 0;
			for (int i = 0; i < n - m + 1; i++) {
				for (int j = 0; j < n - m + 1; j++) {
					int s = getSum(i, j, m);
					max = max > s ? max : s;
				}
			}
			sb.append("#").append(testCase).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	public static int getSum(int x, int y, int m) {
		int result = 0;
		for (int i = x; i < x + m; i++) {
			for (int j = y; j < y + m; j++) {
				result += map[i][j];
			}
		}
		System.out.println(x + " " + y + " " + m + " : " + result);
		return result;
	}

}
