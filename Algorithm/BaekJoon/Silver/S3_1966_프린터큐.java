package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S3_1966_프린터큐 {

	// S3 1966 프린터큐

	static class Paper {
		int priority;
		boolean isTarget;

		public Paper(int priority, boolean isTarget) {
			this.priority = priority;
			this.isTarget = isTarget;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		int n, m, prio;
		Queue<Paper> list = new LinkedList<>();
		PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

		int answer;
		for (int tc = 0; tc < t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			list.clear();
			heap.clear();

			int[] numList = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			int targetPrio = 0;
			for (int i = 0; i < n; i++) {
				prio = Integer.parseInt(st.nextToken());
				numList[i] = prio;
				if (i == m) {
					list.offer(new Paper(prio, true));
					targetPrio = prio;
				} else
					list.offer(new Paper(prio, false));
				heap.add(prio);
			}

			int order = 0;
			while (heap.peek() != targetPrio) {
				Paper cur = list.poll();
				if (heap.peek() == cur.priority) {
					heap.poll();
					order++;
				} else
					list.offer(cur);
			}

			while (heap.size() > 0 && heap.peek() == targetPrio) {
				Paper cur = list.poll();
				if (heap.peek() == cur.priority) {
					heap.poll();
					order++;
				}
				if (cur.isTarget)
					break;
			}

			sb.append(order).append("\n");
		}
		System.out.println(sb);
	}

}

//현재 Queue의 가장 앞에 있는 문서의 ‘중요도’를 확인한다.
//나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를 인쇄하지 않고 Queue의 가장 뒤에 재배치 한다. 그렇지 않다면 바로 인쇄를 한다.
//현재 Queue에 있는 문서의 수와 중요도가 주어졌을 때, 어떤 한 문서가 몇 번째로 인쇄되는지 알아내는 것이다. 예를 들어 위의 예에서 C문서는 1번째로, A문서는 3번째로 인쇄되게 된다.
//
//입력
//첫 줄에 테스트케이스의 수가 주어진다. 각 테스트케이스는 두 줄로 이루어져 있다.
//
//테스트케이스의 첫 번째 줄에는 문서의 개수 N(1 ≤ N ≤ 100)과, 몇 번째로 인쇄되었는지 궁금한 문서가 현재 Queue에서 몇 번째에 놓여 있는지를 나타내는 정수 M(0 ≤ M < N)이 주어진다. 이때 맨 왼쪽은 0번째라고 하자. 두 번째 줄에는 N개 문서의 중요도가 차례대로 주어진다. 중요도는 1 이상 9 이하의 정수이고, 중요도가 같은 문서가 여러 개 있을 수도 있다.
//
//출력
//각 테스트 케이스에 대해 문서가 몇 번째로 인쇄되는지 출력한다.

// 결국 중요도가 가장 높은 문서를 가장 먼저 출력하게됨
// 우선순위 큐 쓰면될듯?
// 
