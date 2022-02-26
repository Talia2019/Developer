package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_S2_1931_회의실배정 {

//	S2 1931 회의실배정
	
//	첫째 줄에 회의의 수 N(1 ≤ N ≤ 100,000)이 주어진다. 
//	둘째 줄부터 N+1 줄까지 각 회의의 정보가 주어지는데 이것은 공백을 사이에 두고 회의의 시작시간과 끝나는 시간이 주어진다. 
//	시작 시간과 끝나는 시간은 231-1보다 작거나 같은 자연수 또는 0이다.
//	첫째 줄에 최대 사용할 수 있는 회의의 최대 개수를 출력한다.
	
//	끝나는 시간 기준 정렬하고 제일 먼저 끝나는거 -> 그 이후에시작하는거부터,, 반복
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[][] schedule = new int[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			schedule[i][0] = Integer.parseInt(st.nextToken());
			schedule[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(schedule, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]==o2[1])
					return o1[0]-o2[0];
				return o1[1]-o2[1];
			}
		});
		
//		for (int[] is : schedule) {
//			System.out.println(Arrays.toString(is));
//		}
		int endTime = schedule[0][1];
		int cnt = 1;
		for (int i = 1; i < n; i++) {
			while(i<n && schedule[i][0]<endTime) {
				i++;
			}
			if(i==n) {
				break;
			} 
			endTime = schedule[i][1];
			cnt++;
		}
		System.out.println(cnt);
	}

}
