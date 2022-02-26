package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S4_18258_큐2 {

	// S4 18258 큐2

	// 큐 구현하기

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		Deque<Integer> deque = new LinkedList<Integer>();

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
			case "push":
				int x = Integer.parseInt(st.nextToken());
				deque.offer(x);
				break;
			case "pop":
				if (deque.size() > 0)
					sb.append(deque.poll()).append("\n");
				else
					sb.append("-1").append("\n");
				break;
			case "size":
				sb.append(deque.size()).append("\n");
				break;
			case "empty":
				if (deque.size() > 0)
					sb.append("0");
				else
					sb.append("1");
				sb.append("\n");
				break;
			case "front":
				if (deque.size() > 0)
					sb.append(deque.getFirst()).append("\n");
				else
					sb.append("-1").append("\n");
				break;
			case "back":
				if (deque.size() > 0)
					sb.append(deque.getLast()).append("\n");
				else
					sb.append("-1").append("\n");
				break;
			}
		}
		System.out.println(sb);

	}

}
