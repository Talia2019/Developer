package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SWEA_1238_D4 {

	//D4 1238 Contact
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		List<Integer>[] people = new ArrayList[101];
		boolean[] isVisited = new boolean[101];
		Queue<Integer> queue = new LinkedList<>();
		int max = 0;

		for (int i = 0; i < 101; i++) {
			people[i] = new ArrayList<>();
		}

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n / 2; i++) {
				people[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			queue.add(s);
			int size = 0;
			while (!queue.isEmpty()) {
				max = queue.peek();	//현단계의 첫번째꺼
				size = queue.size();
				while (size-- > 0) {//현단계 애들만 뽑음
					int cur = queue.poll();
					max = max > cur ? max : cur;
					isVisited[cur] = true;
					for (Integer p : people[cur]) {
						if (!isVisited[p]) {
							queue.add(p);
							isVisited[p] = true;
						}
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");
			
			for (int i = 0; i < 101; i++) {
				people[i].clear();
			}
			Arrays.fill(isVisited, false);
			max = 0;
		}
		System.out.println(sb);
	}

}

