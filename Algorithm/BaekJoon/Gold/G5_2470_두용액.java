package jiyoung.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class TwoWater {

	// G5 2470 두 용액

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int[] bottles = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			bottles[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(bottles);

		int start = 0, end = bottles.length - 1;
		int min = Integer.MAX_VALUE;
		int small = start, big = end;
		while (start < end) {
			int value = bottles[start] + bottles[end];
			if (min > Math.abs(value)) {
				min = Math.abs(value);
				small = start;
				big = end;

				if (min == 0)
					break;
			}
			if (value > 0)
				end--;
			else
				start++;
		}
		
		System.out.println(bottles[small] + " " + bottles[big]);
	}

}

//두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다
