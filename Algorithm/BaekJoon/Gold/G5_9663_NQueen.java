package boj;

import java.util.Scanner;

public class Main_G5_9663_NQueen {

	public static int n, total;
	public static boolean[] col, plus, minus;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();
		col = new boolean[n];
		plus = new boolean[2 * n];
		minus = new boolean[2 * n];

		chess(0);
		System.out.println(total);
	}

	public static void chess(int row) {
		if (row == n) {
			total++;
			return;
		}

		// 열
		for (int i = 0; i < n; i++) {
			// row행 i열
			if (col[i] || plus[row + i] || minus[row - i + n])
				continue;
			col[i] = plus[row + i] = minus[row - i + n] = true;
			chess(row + 1);
			col[i] = plus[row + i] = minus[row - i + n] = false;
		}

	}

}

//N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.
//
//N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

// 가로, 세로, 대각선
