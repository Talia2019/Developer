package boj;

import java.util.Scanner;

public class Main_S2_1541_잃어버린괄호 {

//	S2 1541 잃어버린괄호
//	-가 나오면 -가 나올때까지 계속더함
//	숫자-부호-숫자...
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String str = sc.next();
		
		int sign = 1;
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			if (cur == '-') {
				sum += Integer.parseInt(sb.toString()) * sign;
				sign = -1;
				sb.setLength(0);
			} else if (cur == '+') {
				sum += Integer.parseInt(sb.toString()) * sign;
				sb.setLength(0);
			} else {
				sb.append(cur);
			}
		}
		sum += Integer.parseInt(sb.toString()) * sign;
		System.out.println(sum);

	}

}
