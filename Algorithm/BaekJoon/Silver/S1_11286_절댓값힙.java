package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_S1_11286_절댓값힙 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Queue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
			@Override
			public int compare(Long o1, Long o2) {
				if (Math.abs(o1) < Math.abs(o2)) {
					return -1;
				} else if (Math.abs(o1) > Math.abs(o2)) {
					return 1;
				} else {
					return o1.compareTo(o2);
				}
			}
		});

		for (int i = 0; i < n; i++) {
			long input = Long.parseLong(br.readLine());
			if (input == 0) {
				if (pq.size() == 0)
					sb.append("0").append("\n");
				else
					sb.append(pq.poll()).append("\n");
			} else
				pq.add(input);
		}
		System.out.println(sb);
	}

}
