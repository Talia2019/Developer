package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_S3_1874_스택수열 {

	// S3 1874 스택수열

	// peek이 원하는 수보다 작으면 원하는 수가 나타날때까지 집어넣고 나타나면 뽑음
	// peek이 내가 원하는 수보다 크면 NO
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		Queue<Integer> que = new LinkedList<Integer>();
		for (int i = 1; i <= n; i++) {
			que.offer(i);
		}
		stack.add(0);

		int number, cur;
		for (int i = 0; i < n; i++) {
			number = Integer.parseInt(br.readLine());
			cur = stack.peek();
			if (cur == number) {
				stack.pop();
				sb.append("-").append("\n");
			} else if (cur < number) {
				while (que.size() > 0 && que.peek() <= number) {
					stack.push(que.poll());
					sb.append("+").append("\n");
				}
				stack.pop();
				sb.append("-").append("\n");
			} else {
				sb.setLength(0);
				sb.append("NO");
				break;
			}
		}
		System.out.println(sb);

	}

}

//1부터 n까지의 수를 스택에 넣었다가 뽑아 늘어놓음으로써, 하나의 수열을 만들 수 있다. 이때, 스택에 push하는 순서는 반드시 오름차순을 지키도록 한다고 하자. 임의의 수열이 주어졌을 때 스택을 이용해 그 수열을 만들 수 있는지 없는지, 있다면 어떤 순서로 push와 pop 연산을 수행해야 하는지를 알아낼 수 있다. 이를 계산하는 프로그램을 작성하라.
//입력
//첫 줄에 n (1 ≤ n ≤ 100,000)이 주어진다. 둘째 줄부터 n개의 줄에는 수열을 이루는 1이상 n이하의 정수가 하나씩 순서대로 주어진다. 물론 같은 정수가 두 번 나오는 일은 없다.
//출력
//입력된 수열을 만들기 위해 필요한 연산을 한 줄에 한 개씩 출력한다. push연산은 +로, pop 연산은 -로 표현하도록 한다. 불가능한 경우 NO를 출력한다.

// 1234
// 43687521

// 1234 ++++
// --43
// 12 56 ++
// -6
// 125 78 ++
// --87
// ---521
// 문제 이해하는데 한참걸렸다..
