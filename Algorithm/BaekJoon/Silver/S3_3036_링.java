package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_3036_링 {

//	S3 3036 링

//	첫번째꺼/i번째꺼
//	약분은 두 수의 최대공약수로 나눈수

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		int firstCircle = Integer.parseInt(st.nextToken());
		for (int i = 1; i < n; i++) {
			int cur = Integer.parseInt(st.nextToken());
			int gcd = gcd(firstCircle, cur);
			sb.append(firstCircle / gcd).append("/").append(cur/gcd).append("\n");
		}
		System.out.println(sb);
	}

	public static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}
