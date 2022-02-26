import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	static int[] score, calorie;
	static int max = 0;
	static int n, l;
	
	public static void main(String[] args) {
		/* 
		 * 재료를 선택하면 준비해놓은 재료를 사용해 조합
		 * 재료에 대한 맛의 점수를 매겨놓음
		 * 재료의 칼로리가 주어졌을때 칼로리 이하의 조합중 선호하는 햄버거를 조합해주기
		 * 선호도는 재료들의 맛에 대한 점수의 합으로 결정, 같은재료중복X, 조합의 제한은 칼로리뿐
		 * 
		 * 첫줄 T
		 * 각 tc첫줄에는 재료의수 N, 제한 칼로리를 나타내는 L. 공백구분
		 * 1<=N<=20, 1<=L<=10^4
		 * 다음N개 줄에는 민기의 점수 Ti와 칼로리Ki. 공백구분
		 * 1<=T, K <=10^3
		 * 
		 * tc입력
		 * for(tc) 	n, l입력
		 * 	for(n)	 
		 * 		재귀로???
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		
		for(int i=1;i<=tc;i++) {
			
			n = sc.nextInt();
			l = sc.nextInt();
			
			score = new int[n];
			calorie = new int[n];

			max = 0;
			
			for(int j = 0;j<n;j++) {
				score[j] = sc.nextInt();
				calorie[j] = sc.nextInt();				
			}
			//System.out.println(n+" "+l+" "+Arrays.toString(score)+" "+Arrays.toString(calorie));
			sumScore(0,0,0);
			//System.out.println(Arrays.toString(lis));
			System.out.println("#"+i+" "+max);
		}
	}	//end of main
	static void sumScore(int sc, int cal, int i) {
		if(cal>l) return;
		if(i==n) {
			if(sc>max) max = sc;
			return;
		}
		sumScore(sc, cal, i+1);
		sumScore(sc+score[i], cal+calorie[i], i+1);
	}
}	//end of Solution
