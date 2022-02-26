package jiyoung.week10;

import java.util.Arrays;

public class IntTriangle {

	// Lv.3 정수삼각형
	// 위에서부터 내려오면서 왼쪽오른쪽 큰값을 선택

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		System.out.println(s.solution(new int[][] { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } }));
	}

}

class Solution {
	int[] dir = { -1, 1 };// 죄우

	public int solution(int[][] triangle) {
		int answer = 0;

		for (int i = 1; i < triangle.length; i++) {
			int len = triangle[i].length;
			for (int j = 0; j < len; j++) {
				int lI = j - 1;
				int rI = j;
				int lV = 0, rV = 0;
				if (lI >= 0)
					lV = triangle[i - 1][lI];
				if (rI < len - 1)
					rV = triangle[i - 1][rI];
				triangle[i][j] += Math.max(lV, rV);
			}
		}

		int len = triangle.length - 1;
		for (int i = 0; i < triangle[len].length; i++) {
			if (triangle[len][i] > answer)
				answer = triangle[len][i];
		}

		return answer;
	}
}
