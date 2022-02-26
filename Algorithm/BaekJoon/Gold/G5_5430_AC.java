package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_G5_5430_AC {

	// G5 5430 AC

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		String commands;
		String numberString;
		List<Integer> numbers = new LinkedList<>();
		int oper = 1;
		int n;
		test: for (int tc = 1; tc <= t; tc++) {
			oper = 1;
			commands = br.readLine();
			n = Integer.parseInt(br.readLine());

			numbers.clear();

			numberString = br.readLine();
			numberString = numberString.substring(1, numberString.length() - 1);
			st = new StringTokenizer(numberString, ",");
			for (int i = 0; i < n; i++) {
				numbers.add(Integer.parseInt(st.nextToken()));
			}

			char command;
			for (int i = 0; i < commands.length(); i++) {
				command = commands.charAt(i);
				switch (command) {
				case 'R':
					oper *= -1;
					break;
				case 'D':
					if (numbers.size() == 0) {
						sb.append("error\n");
						continue test;
					}
					if (oper < 0) { // 뒤
						numbers.remove(numbers.size() - 1);
					} else { // 앞
						numbers.remove(0);
					}
				}

			}

			if (oper < 0)
				Collections.reverse(numbers);
			sb.append("[");
			for (Integer integer : numbers) {
				sb.append(integer).append(",");
			}
			if (numbers.size() > 0)
				sb.deleteCharAt(sb.length() - 1);
			sb.append("]\n");
		}
		System.out.println(sb);
	}

}
