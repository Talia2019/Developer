package boj;

import java.util.Scanner;

public class Main_boj_2579_실버3 {
//	계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
//	연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
//	마지막 도착 계단은 반드시 밟아야 한다.

	// 재귀 - 파라미터 : 몇번연속, 인덱스
	//

	public static int[] stairs;
	public static int[][] sum;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int stairNum = sc.nextInt();
		stairs = new int[stairNum];
		sum = new int[stairNum][2];
		for (int i = 0; i < stairNum; i++) {
			stairs[i] = sc.nextInt();
		}
		int one = getSum(0, 0);
		int two = getSum(1, 0);

		System.out.println(one > two ? one : two);
		//시작점을 0으로 잡았기에 생긴 문제! 
//		System.out.println(getSum(0, 0));
	}

	public static int getSum(int i, int contn) {
		int result = 0;
		if (i >= stairs.length) // 도착지점 넘음
			return -30000000;
		if (i == stairs.length - 1) { // 도착지점
			return stairs[i];
		}

		int one, two;
		if (contn < 1) { // 한칸도뛸수있음
			if (sum[i][0] == 0)
				sum[i][0] = stairs[i] + getSum(i + 1, 1);
			if (sum[i][1] == 0)
				sum[i][1] = stairs[i] + getSum(i + 2, 0);
			one = sum[i][0];
			two = sum[i][1];
			result = one > two ? one : two;
		} else { // 두칸만 뛸수있음
			if (sum[i][1] == 0)
				sum[i][1] = stairs[i] + getSum(i + 2, 0);
			result = sum[i][1];
		}
//		System.out.println(i + " " + contn + " " + result);
		return result;
	}

}
