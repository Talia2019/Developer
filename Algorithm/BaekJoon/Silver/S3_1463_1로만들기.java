package boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_S3_1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int x = sc.nextInt();

		int answer = bfs(x);
		answer = bfs_prunning(x);
		answer = dp(x);
	}

	// 재귀함수 - bfs
	// 300ms
	public static int bfs(int x) {
		// bfs
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x);

		int cnt = 0; // 몇단계까지 왔는지.. 연산의 회수
		ex: while (q.size() > 0) {
			for (int i = q.size(); i > 0; i--) { // 같은 형제 레벨만큼 반복
				x = q.poll();
				if (x <= 1)
					break ex;

				if (x % 3 == 0)
					q.offer(x / 3);
				if (x % 2 == 0)
					q.offer(x / 2);
				q.offer(x - 1);

			}
			cnt++; // 연산회수 증가
		}
		System.out.println(cnt);
		return cnt;
	}

	// 가지치기?
	// 이미 확인했던숫자면 넘어감 -> 같은 단계면 앞에서계산한거고, 다른단계면 앞에서 계산한값이 단계까 더 적은거니까
	// 허나 가지치기의 효과가 있을지 잘 모름
	// 120ms
	public static int bfs_prunning(int x) {
		boolean[] check = new boolean[x + 1];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(x);
		check[x] = true;
		int cnt = 0; // 몇단계까지 왔는지.. 연산의 회수
		ex: while (q.size() > 0) {
			for (int i = q.size(); i > 0; i--) { // 같은 형제 레벨만큼 반복
				x = q.poll();
				if (x <= 1)
					break ex;
				System.out.println(x);
				if (x % 3 == 0) {
					if (!check[x / 3]) {
						check[x / 3] = true;
						q.offer(x / 3);
					}
				}
				if (x % 2 == 0)
					if (!check[x / 2]) {
						check[x / 2] = true;
						q.offer(x / 2);
					}
				if (!check[x - 1]) {
					check[x - 1] = true;
					q.offer(x - 1);
				}

			}
			cnt++; // 연산회수 증가
		}
		System.out.println(cnt);
		return cnt;
	}

	// dp
	public static int dp(int x) {
		int[] memo = new int[x + 1]; // memo[i] : i 숫자가 1이 되기 위해 필요한 최소 연산 수
//		memo[0] = memo[1] = 0; //어차피 0임 그래서 안써도 되긴함
//		memo[2] = memo[3] = 1;
		//주의할점! 만약 x에 1이나2가 들어오면 위 값을 초기화해줄때 바운더리 에러발생!!
		//그래서 89번째 줄 주석하고 밑에 반복문을 4부터가 아니라 2부터 하게해줬음
		//반드시 초기화가 필요하다면 ? if문으로 넣어줘야함

		for (int i = 2; i <= x; i++) {
			memo[i] = memo[i - 1] + 1;
			if (i % 2 == 0) 
				memo[i] = Math.min(memo[i / 2] + 1, memo[i]);
			if(i%3==0)
				memo[i] = Math.min(memo[i / 3] + 1, memo[i]);
		}
		System.out.println(memo[x]);
		return memo[x];
	}

}
