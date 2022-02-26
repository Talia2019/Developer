package jiyoung.week8;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HideNSeek {

	// G5 13549 숨바꼭질3

	// 좌우이동(1초) 혹은 현재좌표*2로 순간이동(2초) 가능
	// -1, 1, *2 셋중 하나를 선택할 수 있음
	// 우선순위큐

	static class Node implements Comparable<Node> {
		int num;
		int cnt;

		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cnt, o.cnt);
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", cnt=" + cnt + "]";
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt(); // 나
		int k = sc.nextInt(); // 동생
		final int INFINITY = 100001;

		int[] minLen = new int[200001];
		Arrays.fill(minLen, 100001);

		int minCnt = 0;

		// 현재위치와 시간을 저장하는 힙
		PriorityQueue<Node> heap = new PriorityQueue<>();
		heap.add(new Node(n, 0));
		while (heap.size() > 0) {
			Node node = heap.poll();
			int num = node.num;
			int cnt = node.cnt;

			if (num < 0 || minLen[num] <= cnt)
				continue;
			minLen[num] = cnt;

			if (num == k) {
				minCnt = cnt;
				break;
			}

			if (num > k) {
				heap.add(new Node(k, cnt + (num - k)));
			} else { // node.num<k
				heap.add(new Node(num * 2, cnt));
				heap.add(new Node(num - 1, cnt + 1));
				heap.add(new Node(num + 1, cnt + 1));
			}
		}

		System.out.println(minCnt);
	}

}

//수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.
//수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
//입력
//첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.
//출력
//수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
