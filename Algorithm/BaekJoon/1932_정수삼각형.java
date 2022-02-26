package boj;

import java.util.Scanner;

/**
 * 정수삼각형
 */
public class Main_boj_1932_실버1 {
	public static int[][] triangle;
	public static int[][] storeSum;

	// 바로밑, 오른쪽밑
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n;
		n = sc.nextInt();
		triangle = new int[n][];
		storeSum = new int[n][];

		for (int i = 0; i < n; i++) {
			triangle[i] = new int[i + 1];
			storeSum[i] = new int[i + 1];
			for (int j = 0; j <= i; j++) {
				triangle[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(getSum(n-1, 0, 0));
	}

	public static int getSum(int depth, int i, int j) {
		if (depth == 0)
			return triangle[i][j];
		int left = 0, right = 0;
		if(storeSum[i][j] == 0) {
			left = getSum(depth-1, i+1, j);
			right = getSum(depth-1, i+1, j+1);
			int result = left > right ? left : right;
			storeSum[i][j] = triangle[i][j] + result;
		}
		return storeSum[i][j];
	}
}
