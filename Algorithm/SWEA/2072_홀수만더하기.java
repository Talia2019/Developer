import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		/* 3개 테스트케이스
		 * 10개의 수를 입력받아 홀수만 더한 값을 출력
		 * 0이상 10000이하의 정수 => 데이터타입, 메모리제한
		 * 
		 * 테스트케이스 입력받기 => Scanner, BufferedReader
		 * 반복문(테스트케이스) for
		 * 	반복문(10개입력) for
		 * 		홀수이면, 합누적 if%2 !=0
		 * */
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int t;
		t = sc.nextInt();
		
		for(int testCase=1;testCase<=t;testCase++) {
			for(int j=0;j<10;j++) {
				int tmp = sc.nextInt();
				if(tmp%2!=0) sum+=tmp;
			}
			System.out.println("#"+(testCase)+" "+sum);
			sum=0;
		}	
		
	}	//end of main
}	//end of Solution
