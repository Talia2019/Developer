package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_11053_S2 {

	private static int[] recordLIS;
	private static int maxNum = 1, maxIndex;

//	수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오
//	첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
//	둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

	// 최대인덱스들어왔으면 최대값+1
	// 1차원배열 크기1000 - 나보다 앞에있는 최대값+1
	// 값이 0이면 1넣어주고 찾음
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		recordLIS = new int[1001];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		
		//최초값을 maxIndex로 정해주려고
		maxIndex = Integer.parseInt(st.nextToken());
		recordLIS[maxIndex] = 1; 
		for (int i = 0; i < n-1; i++) {
			getLIS(Integer.parseInt(st.nextToken()));
		}
		System.out.println(maxNum);
	}

	public static void getLIS(int n) {
		if (n > maxIndex) {	//최대인덱스가 들어온경우 최대값을 가질수밖에 없음
			recordLIS[n] = ++maxNum;
			maxIndex = n;
			return;
		}
		
		//나자신 - 1로 초기화
		recordLIS[n] = 1;
		int max = 1;
		for (int i = n - 1; i >= 0; i--) {
			if (recordLIS[i] >= max) {
				recordLIS[n] = recordLIS[i] + 1;
				max = recordLIS[i];
			}
		}
		maxNum = recordLIS[n] > maxNum ? recordLIS[n] : maxNum;
	}

}
