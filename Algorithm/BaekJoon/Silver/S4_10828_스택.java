package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_S4_10828_스택 {

//	S4 10828 스택

//	정수를 저장하는 스택을 구현, 명령 처리 프로그램 작성
//	push X: 정수 X를 스택에 넣는 연산이다.
//	pop: 스택에서 가장 위에 있는 정수를 빼고, 그 수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.
//	size: 스택에 들어있는 정수의 개수를 출력한다.
//	empty: 스택이 비어있으면 1, 아니면 0을 출력한다.
//	top: 스택의 가장 위에 있는 정수를 출력한다. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력한다.

//	첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		Stack<Integer> stack = new Stack<>();

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			switch (st.nextToken()) {
			case "push":
				stack.add(Integer.parseInt(st.nextToken()));
				break;
			case "pop":
				if (stack.size() == 0)
					System.out.println(-1);
				else
					System.out.println(stack.pop());
				break;
			case "size":
				System.out.println(stack.size());
				break;
			case "empty":
				if (stack.size() == 0)
					System.out.println(1);
				else
					System.out.println(0);
				break;
			case "top":
				if (stack.size() == 0)
					System.out.println(-1);
				else
					System.out.println(stack.peek());
				break;
			}
		}
	}

}
