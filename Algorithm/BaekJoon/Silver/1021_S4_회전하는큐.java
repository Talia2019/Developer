package jiyoung.week2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RotatingQueue {
//	큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.
//	입력
//	첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다. N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다. 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다. 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
//	10 3
//	1 2 3 -> 0
//	10 3
//	2 9 5 -> 8
//	2/ 3, 3, 3/ 4번 -> 1 + 3 + 4 = 8? 
//	32 6
//	27 16 30 11 6 23 -> 59
//	10 10
//	1 6 3 2 7 9 8 4 10 5 -> 14

	public static LinkedList<Integer> que;
	public static int cnt;

	public static void main(String[] args) {
		// 삽입삭제가 많아서 링크드리스트로 푸는게 유리한듯?
		que = new LinkedList<>();
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean flag = false; // true : 앞, false : 뒤

		for (int i = 0; i < n; i++) {
			que.add(i + 1);
		}
		for (int i = 0; i < m; i++) {
			int get = sc.nextInt();
			for (int j = 0; j < (que.size() + 1) / 2; j++) {
				if (que.get(j) == get) {
					flag = true;
				}
			}
			if (flag)
				front(get);
			else
				back(get);
			flag = false;
		}
		System.out.println(cnt);
	}

	public static void back(int n) {
//		System.out.println("B");
		while (que.get(0) != n) {
			cnt++;
			int q = que.removeLast();
			que.addFirst(q);
		}
		que.remove(0);
	}

	public static void front(int n) {
//		System.out.println("F");
		while (que.get(0) != n) {
			cnt++;
			int q = que.removeFirst();
			que.addLast(q);
		}
		que.remove(0);
	}
}
