package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_S4_1021_회전하는큐 {

	// S4 1021 회전하는큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<Integer> list = new LinkedList<Integer>();

		for (int i = 1; i <= n; i++) {
			list.add(i);
		}

		st = new StringTokenizer(br.readLine(), " ");
		int cnt = 0;
		int header = 0;
		int left, right, lidx = 0, ridx = 0;
		for (int i = 0; i < m; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if (header >= list.size())
				header = 0;
			if (cur == list.get(header)) {
				list.remove(header);
			} else {
				left = right = 0;
				lidx = header;
				while (list.get(lidx) != cur) {
					left++;
					lidx--;
					if (lidx < 0)
						lidx += list.size();
				}
				ridx = header;
				while (list.get(ridx) != cur) {
					right++;
					ridx++;
					if (ridx >= list.size())
						ridx = 0;
				}

				if (left < right) {
					cnt += left;
					header = lidx;
				} else {
					cnt += right;
					header = ridx;
				}
				list.remove(header);
			}
		}
		System.out.println(cnt);

	}

}
