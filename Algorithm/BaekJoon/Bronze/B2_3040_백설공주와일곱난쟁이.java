package boj;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

//	3040 백설공주와일곱난쟁이

//	총 아홉개 줄에 1보다 크거나 같고 99보다 작거나 같은 자연수가 주어진다. 
//	모든 숫자는 서로 다르다. 또, 항상 답이 유일한 경우만 입력으로 주어진다.

//	조합 7개 뽑고 더한값이 100이 되는경우 정답
//	comb(cnt, start)
//	if cnt==r
//			조합생성완료
//		else
//			for i from start to n-1
//				numbers[cnt] <- input[i];
//				comb(cnt+1, i+1);
//			end for
//	end comb()

	private static int[] dwarf;
	private static int[] real;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		dwarf = new int[9];
		real = new int[7];

		for (int i = 0; i < 9; i++) {
			dwarf[i] = sc.nextInt();
		}
		comb(0,0,0);
	}

	public static void comb(int cnt, int start, int sum) {
		if (cnt == 7) {
//			System.out.println(Arrays.toString(real) + " " + sum);
			if (sum == 100) {
				for (int i : real) {
					System.out.println(i);
				}
			}
		} else {
			for (int i = start; i < dwarf.length; i++) {
				real[cnt] = dwarf[i];
				comb(cnt+1, i+1, sum + dwarf[i]);
			}
		}
	}

}
