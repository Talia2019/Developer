package jiyoung.week7;

import java.util.Scanner;

public class GoodSequence {

//	G4 2661 좋은수열

//	숫자 1, 2, 3으로만 이루어지는 수열이 있다. 임의의 길이의 인접한 두 개의 부분 수열이 동일한 것이 있으면, 그 수열을 나쁜 수열이라고 부른다. 그렇지 않은 수열은 좋은 수열이다.
//	길이가 N인 좋은 수열들을 N자리의 정수로 보아 그중 가장 작은 수를 나타내는 수열을 구하는 프로그램을 작성하라. 
//	입력은 숫자 N하나로 이루어진다. N은 1 이상 80 이하이다.
//	첫 번째 줄에 1, 2, 3으로만 이루어져 있는 길이가 N인 좋은 수열들 중에서 가장 작은 수를 나타내는 수열만 출력한다. 수열을 이루는 1, 2, 3들 사이에는 빈칸을 두지 않는다.

//	일단 같은수가 연속으로 안나오도록 생성
//	안좋은수인지 좋은수인지 체크
//	80이니까 문자열로..

	public static int n;
	public static String answer = null;
	public static String[] numbers;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		makeSequence(1, "1", "1");
		System.out.println(answer);
	}

	public static void makeSequence(int depth, String number, String last) {
		if (answer != null)
			return;

		if (depth == n) {
			if (isGood(number))
				answer = number;
			return;
		}

		if (!isGood(number))
			return;

		switch (last) {
		case "1":
			makeSequence(depth + 1, number + "2", "2");
			makeSequence(depth + 1, number + "3", "3");
			break;
		case "2":
			makeSequence(depth + 1, number + "1", "1");
			makeSequence(depth + 1, number + "3", "3");
			break;
		case "3":
			makeSequence(depth + 1, number + "1", "1");
			makeSequence(depth + 1, number + "2", "2");
			break;
		}

	}

	public static boolean isGood2(String number) {
		int length = number.length() / 2;

		for (int i = 1; i <= length; i++) {
			if (number.substring(number.length() - i)
					.equals(number.substring(number.length() - 2 * i, number.length() - i))) {
				return false;
			}
		}

		return true;
	}

	public static boolean isGood(String number) {
		int last = number.length();
		f: for (int i = 0; i < number.length() - 1; i++) {
			for (int j = i + 1; j < number.length(); j++) {
				if (j - i > last - j)	//앞 문자열의 길이가 뒤 문자열 길이보다 크면 좋음
					continue f;
				String curStr = number.substring(i, j);
				String nextStr = number.substring(j, j + j - i); //j-i개 만큼 잘라냄
				if (curStr.equals(nextStr))
					return false;
			}
		}
		return true;
	}

}
