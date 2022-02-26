package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_D3_4047_영준이의카드카운팅 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		boolean[][] deck;
fo:		for (int tc = 1; tc <= t; tc++) {
			deck = new boolean[4][14];
			sb.append("#").append(tc).append(" ");
			String str = br.readLine();
			int stri = 0, i = 0;
			while (stri < str.length()) {
				switch (str.charAt(stri++)) {
				case 'S':
					i = 0;
					break;
				case 'D':
					i = 1;
					break;
				case 'H':
					i = 2;
					break;
				case 'C':
					i = 3;
					break;
				}
				int num = Integer.parseInt(str.substring(stri++, ++stri));
				if(deck[i][num]==true) {
					sb.append("ERROR").append("\n");
					continue fo;
				}
				deck[i][num]=true;
			}
			for (int j = 0; j < deck.length; j++) {
				int cnt = 0;
				for (int j2 = 1; j2 < deck[i].length; j2++) {
					if(deck[j][j2]==false) {
						cnt++;
					}
				}
				sb.append(cnt).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
