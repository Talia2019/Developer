package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_S4_10866_덱 {

	// S4 10866 덱

	// 덱 구현
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Deque<Integer> deque = new LinkedList<Integer>();

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			switch (st.nextToken()) {
			case "push_front":
				deque.addFirst(Integer.parseInt(st.nextToken()));
				break;
			case "push_back":
				deque.addLast(Integer.parseInt(st.nextToken()));
				break;
			case "pop_front":
				if (deque.size() > 0)
					System.out.println(deque.pollFirst());
				else
					System.out.println(-1);
				break;
			case "pop_back":
				if (deque.size() > 0)
					System.out.println(deque.pollLast());
				else
					System.out.println(-1);
				break;
			case "size":
				System.out.println(deque.size());
				break;
			case "empty":
				if (deque.size() > 0)
					System.out.println(0);
				else
					System.out.println(1);
				break;
			case "front":
				if (deque.size() > 0)
					System.out.println(deque.peekFirst());
				else
					System.out.println(-1);
				break;
			case "back":
				if (deque.size() > 0)
					System.out.println(deque.peekLast());
				else
					System.out.println(-1);
				break;
			}
		}
	}

}
