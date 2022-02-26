package boj;

import java.util.Scanner;

public class Main_boj_9251_G5 {

//	9251 LCS 
//	LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.

//	첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
//	첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를 출력한다.

// 	x == y : LCS(xi yj) = LCS(xi-1, yj-1) + 1
//	나를 누적
//	x != y : LCS(xi yj) = max(LCS(xi-1, yj), LCS(xi, yj-1))
//	더 큰거로
	static Integer[][] LCS;
	private static char[] str1;
	private static char[] str2;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		str1 = sc.next().toCharArray();
		str2 = sc.next().toCharArray();

		int len1 = str1.length;
		int len2 = str2.length;
		LCS = new Integer[len1--][len2--];

		System.out.println(getLCS(len1, len2));
	}

	public static int getLCS(int x, int y) {
		if (x < 0 || y < 0)
			return 0;
		if (LCS[x][y] == null) {
			LCS[x][y] = 0;
			if (str1[x] == str2[y])
				LCS[x][y] = getLCS(x - 1, y - 1) + 1;
			else {
				int get1 = getLCS(x - 1, y);
				int get2 = getLCS(x, y - 1);
				LCS[x][y] = get1 > get2 ? get1 : get2;
			}

		}
		return LCS[x][y];
	}

}
