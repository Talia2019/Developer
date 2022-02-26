package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SWEA_1228_암호문1_D3 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		List<Integer> code;

		for (int tc = 1; tc <= 10; tc++) {
			code = new LinkedList<Integer>();
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				code.add(Integer.parseInt(st.nextToken()));
			}

			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				String op = st.nextToken();
				while(!op.equals("I")) {
					op = st.nextToken();
				}
				int insertIndex = Integer.parseInt(st.nextToken());
				if (insertIndex > 10)
					continue;
				
				int insertNum = Integer.parseInt(st.nextToken());
				int num = insertIndex + insertNum;
				if(insertIndex + insertNum >= 10) num = 10;
				for (int j = insertIndex; j < num; j++) {
					code.add(j, Integer.parseInt(st.nextToken()));
				}
			}
			sb.append("#").append(tc).append(" ");
			int i = 0;
			for (Integer integer : code) {
				sb.append(integer).append(" ");
				if(i++>=9) break;
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
