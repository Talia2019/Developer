package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_S4_2164_카드2 {

	// S4 2164 카드2

	// 그냥 큐로 계속 넣고빼면됨
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}

		int idx = 0;
		while (q.size() > 1) {
			int num = q.poll();
			if (idx % 2 != 0) {
				q.offer(num);
			}
			idx++;
		}
		System.out.println(q.poll());
	}

}
