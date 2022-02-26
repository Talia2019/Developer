package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_S3_14889_스타트와링크 {

	// n개중 n/2개를 뽑고 능력치 차를 계산

	public static int n, half, min;
	public static int[][] score;
	public static boolean[] selected;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		half = n / 2;
		score = new int[n][n];
		selected = new boolean[n];
		min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				score[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		comb(0, 0);
		System.out.println(min);

	}

	public static void comb(int cnt, int s) {
		if (cnt == half) {
			int start, link;
			start = link = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < i; j++) {
					if (selected[i] == selected[j]) {
						// 같은팀
						if (selected[i])
							start = start + score[i][j] + score[j][i];
						else
							link = link + score[i][j] + score[j][i];
					}

				}
			}

			if (Math.abs(start - link) < min)
				min = Math.abs(start - link);
			return;
		}
		for (int i = s; i < n; i++) {
			selected[i] = true;
			comb(cnt + 1, i + 1);
			selected[i] = false;
		}
	}

}
