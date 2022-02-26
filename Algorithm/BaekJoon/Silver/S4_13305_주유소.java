package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S4_13305_주유소 {

//	S4 13305 주유소

//	도시 개수, 도로길이, 리터당가격

//	다음 최소가 나올때까지 주유함

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] distance = new int[n];
		int[] gasStation = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n - 1; i++) {
			distance[i] = Integer.parseInt(st.nextToken());
		}
		distance[n - 1] = 0;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			gasStation[i] = Integer.parseInt(st.nextToken());
		}

		int cur = gasStation[0];
		long dis = 0;
		long pay = 0;
		for (int i = 1; i < n; i++) {
			dis = distance[i - 1];
			while (i < n && gasStation[i] > cur) {
				dis += distance[i];
				i++;
			}
			pay += cur * dis;
			if (i != n)
				cur = gasStation[i];
		}

		System.out.println(pay);
	}

}
