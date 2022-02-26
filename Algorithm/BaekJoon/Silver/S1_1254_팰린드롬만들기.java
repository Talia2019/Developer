package jiyoung.week9;

import java.util.Scanner;

public class Palindrome {

	// S1 1254 팰린드롬 만들기

	// 문자열 뒤에만 추가해야한대
	// 중앙에서부터 좌우 확인해나가며?? 해보기?
	// 다른지점이 나타났을때
	// 오른쪽 끝까지 갔으면 왼쪽 인덱스만큼 더해주면됨
	// 오른쪽 끝까지 못갔으면 해당 인덱스부터 뒤에 또 대칭인지확인
	// 뒤에 대칭이면 앞만 추가해주면 되고, 대칭 아니면 전체 추가해줘야됨

	// 이런식으로 해보려했는데 aaaabb와 abab처럼 좌/우가 달라야 하는 경우 조금 답이없어서 방법바꿈
	//

	public static String str;
	public static int len;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		str = sc.next();
		len = str.length();

		System.out.println(getLen());
	}

//	public static int isPalin(int start) {
//		if (start == str.length() - 1)
//			return 0;
//
//		StringBuilder sb = new StringBuilder();
//		int result = 0;
//
//		int left, right;
//		int len = str.length() - start;
//		if (len % 2 == 0) {
//			right = start + len / 2;
//			left = right - 1;
//		} else {
//			right = start + len / 2 + 1;
//			left = right - 2;
//		}
//
//		while (right < str.length()) {
//			if (str.charAt(left) != str.charAt(right)) {
//				result += isPalin(right);
//				break;
//			}
//			left--;
//			right++;
//		}
//
//		result += (left - start + 1);
//		return result;
//	}

	public static boolean isPalin(int start) {
		for (int i = start, j = 0; i < len; i++, j++) {
			if (str.charAt(i) != str.charAt(len - 1 - j)) {
				return false;
			}
		}
		return true;
	}

	public static int getLen() {
		for (int i = 0; i < len; i++) {
			if (isPalin(i))
				return len + i;
		}
		return len * 2;
	}
}

//동호는 규완이가 적어놓고 간 문자열 S에 0개 이상의 문자를 문자열 뒤에 추가해서 팰린드롬을 만들려고 한다. 동호는 가능하면 가장 짧은 문자열을 만들려고 한다.

//입력
//첫째 줄에 문자열 S가 주어진다. S의 길이는 최대 1000이다.

//출력
//첫째 줄에 동호가 만들 수 있는 가장 짧은 팰린드롬의 길이를 출력한다.
