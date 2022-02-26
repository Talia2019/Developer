package jiyoung.week5;

import java.util.Arrays;
import java.util.Scanner;

public class MooGame {

//	S1 5904 Moo게임

//	0 : 3
//	1 : 3 + (1+3) + 3 = 10
//	2 : 10 + (2+3) + 10 = 25
//	3 : 25 + (3+3) + 25 = 56

//	Sk : 2 * S(k-1) + k+3
//	타겟이 몇번 돌려야 나오는지 확인하고, 해당 수만큼 재귀 돌면서 찾기
//	S(k-1)+1이면 m
	
	public static int[] sizeS = new int[30]; // 현재 타겟이 몇번 돌려야 나오는지
	public static int target;
	public static String answer = "o";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		target = sc.nextInt();
		
		int k = 0;
		sizeS[0] = 3;
		for (int i = 1; i < sizeS.length; i++) {
			sizeS[i] = sizeS[i - 1] * 2 + i + 3;
			if (target <= sizeS[i]) {
				k = i;
				break;
			}
		}

		getMoo(k, target);
		System.out.println(answer);

	}

	public static void getMoo(int k, int index) {
		if (k == 0) {
			if (index == 1)
				answer = "m";
			return;
		}

		if (index == sizeS[k - 1] + 1) {
			answer = "m";
			return;
		} else if (index <= sizeS[k - 1]) {
			getMoo(k - 1, index);
		} else if (index > sizeS[k - 1] + k + 3) {
			getMoo(k - 1, index - sizeS[k - 1] - k - 3);
		}
	}
}
