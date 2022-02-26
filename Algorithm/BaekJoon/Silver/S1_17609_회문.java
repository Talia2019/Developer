package jiyoung.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Palindrome {

	// S1 17609 회문

	// 맨앞, 맨뒤를 비교해보면서 다른경우 한개 앞당겨봄
	// 앞당겼을때 회문이면 1, 아니면2
	
	public static String str;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			str = br.readLine();
			sb.append(getPal(0, 0, str.length() - 1)).append("\n");

		}
		System.out.println(sb);

	}

	public static int getPal(int flag, int start, int end) {
		if (flag > 1)
			return 2;

		while (start <= end) {
			if (start == end)
				return flag;
			if (str.charAt(start) == str.charAt(end)) {
				start++;
				end--;
			} else {
				if (flag == 1)
					return 2;
				int left = getPal(flag + 1, start + 1, end);
				int right = getPal(flag + 1, start, end - 1);

				if (left == 1 || right == 1)
					return 1;
				return 2;
			}
		}

		return flag;
	}

}
