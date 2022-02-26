import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public static void main(String[] args) {

		/*
		 * 최장증가 부분수열 길이계산
		 * 
		 * 첫줄 tc
		 * 각 tc의 첫줄에는 수열의 길이N
		 * 둘째줄에는 공백구분으로 순서대로주어짐
		 * 1<=원소<-2^31-1
		 * 
		 * 각원소별 본인보다 작은것중 젤큰거찾기
		 * */
		
		Scanner sc = new Scanner(System.in);
		
		int tc = sc.nextInt();
		int n, max;
		
		for(int i=1;i<=tc;i++) {
			n = sc.nextInt();
			int[] caseArr = new int[n];
			int[] lis = new int[n];
			lis[0] = max = 1;
			for(int j = 0;j<n;j++) {
				caseArr[j] = sc.nextInt();
				lis[j]=1;
				for(int k = 0;k<j;k++) {
					if(caseArr[k]<caseArr[j]) {
						if(lis[k]>=lis[j]) lis[j]=lis[k]+1;
					}
				}
				if(max<lis[j]) max = lis[j];
			}
			//System.out.println(Arrays.toString(lis));
			System.out.println("#"+i+" "+max);
		}
		
		
	}	//end of main
}	//end of Solution
