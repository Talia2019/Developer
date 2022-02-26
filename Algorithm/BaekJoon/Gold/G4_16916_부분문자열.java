package jiyoung.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubString {

	// G4 16916 부분문자열

	// String.contains -> 시간초과 ㅎ이게 되면 골드일리가 없지~!
	// 보이어 무어 알고리즘 -> 시간초과
	// KMP 알고리즘 -> 통과
	
	// 교안에서 보이어무어가 평균적으로 가장 빠른 속도를 갖는다고 설명했으나
	// 테스트케이스는 극악의 케이스?들이 있어서 kmp가 더 효율적으로 먹히는듯싶다
	// 그냥 kmp만 외우고있어도될듯..

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] text = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();

//		KMP
		int length = pattern.length;
		int[] pi = new int[length];

		for (int i = 1, j = 0; i < length; i++) {
			while (j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (pattern[i] == pattern[j]) {
				pi[i] = ++j;
			}
		}
//		System.out.println(Arrays.toString(pi));
		boolean flag = false;
		for (int i = 0, j = 0; i < text.length; i++) {
			if (j > 0 && text[i] != pattern[j]) {
				j = pi[j - 1];
			}
			if (text[i] == pattern[j]) {
				if (j == length - 1) {
					flag = true;
					break;
				}
				j++;
			}
		}

//		보이어무어
//		int[] skip = new int[26];
//
//		int pLength = pattern.length;
//		int tLength = text.length;
//		Arrays.fill(skip, pLength);
//
//		int index = 0;
//		for (; index < pLength - 1; index++) {
//			skip[pattern[index] - 'a'] = pLength - index - 1;
//		}
//
//		int indexP;
//		boolean flag = false;
//		w: while (index < tLength) {
//			indexP = pLength - 1;
//
//			while (text[index] == pattern[indexP]) {
//				if (indexP == 0) {
//					flag = true;
//					break w;
//				}
//				index--;
//				indexP--;
//			}
//			index += (skip[text[index] - 'a'] > pLength - indexP) ? skip[text[index] - 'a'] : pLength - indexP;
//		}
//
		if (flag)
			System.out.println(1);
		else
			System.out.println(0);
	}

}
