package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_P5_1786_찾기 {

	// P5 1786 찾기
	// KMP알고리즘 사용해보기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();

		int length = pattern.length;
		int[] pi = new int[length];

		// 부분테이블 만들기
		for (int i = 1, j = 0; i < length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j])
				pi[i] = ++j;
		}
		
//		System.out.println(Arrays.toString(pi));
		int cnt = 0;
		List<Integer> indexes = new ArrayList<Integer>();

		for (int i = 0, j = 0; i < text.length; i++) {
			if (j > 0 && text[i] != pattern[j]) {
				j = pi[j - 1];
			}

			if (text[i] == pattern[j]) {
				if (j == length - 1) {
					cnt++;
					indexes.add(i - length + 2);
					j = pi[j];
				} else
					j++;
			}
		}

		sb.append(cnt).append("\n");
		for (Integer integer : indexes) {
			sb.append(integer).append("\n");
		}
		System.out.println(sb);
	}

}
