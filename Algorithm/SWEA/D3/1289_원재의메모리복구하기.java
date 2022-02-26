package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_1289_원재의메모리복구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			String num = br.readLine();
			int cnt = 0;
			char prev = '0';
			for (int i = 0; i < num.length(); i++) {
				char cur = num.charAt(i);
				if(cur!=prev) {
					cnt++;
					prev = cur;
				}
			}
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.println(sb);
				
	}

}
