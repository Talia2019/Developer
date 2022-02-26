package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_S4_4949_균형잡힌세상 {

	// S4 4949 균형잡힌 세상

	// 괄호만 큐에 집어넣고 점이 들어오면 큐에 있던 애들을 스택으로
	// 여는거면 스택에 넣고 닫는거면 스택에서 뽑고
	// 닫는게 있을때 스택이 비어있거나, 큐는비었는데 스택이 안비었으면 NO
	// 각 문장이 .으로 끝난다고 가정된건가??

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Queue<Character> que = new LinkedList<>();
		Stack<Character> stack = new Stack<>();

		String str;
		while (true) {
			str = br.readLine();
			if (str.equals(".")) {
				break;
			}

			que.clear();
			stack.clear();

			char ch;
			for (int i = 0; i < str.length(); i++) {
				ch = str.charAt(i);
				if (ch == '(' || ch == ')' || ch == '[' || ch == ']')
					que.offer(ch);
			}

			// [[()]]
			boolean flag = true;
			while (que.size() > 0) {
				ch = que.poll();
				if (ch == '[' || ch == '(') {
					stack.push(ch);
				} else {
					if (ch == ')') {
						if (stack.size() == 0 || stack.pop() != '(') {
							flag = false;
							break;
						}
					} else { // ]
						if (stack.size() == 0 || stack.pop() != '[') {
							flag = false;
							break;
						}
					}
				}
			}

			if (!flag || stack.size() != 0)
				sb.append("no").append("\n");
			else
				sb.append("yes").append("\n");
		}
		System.out.println(sb);
	}

}

//모든 왼쪽 소괄호("(")는 오른쪽 소괄호(")")와만 짝을 이뤄야 한다.
//모든 왼쪽 대괄호("[")는 오른쪽 대괄호("]")와만 짝을 이뤄야 한다.
//모든 오른쪽 괄호들은 자신과 짝을 이룰 수 있는 왼쪽 괄호가 존재한다.
//모든 괄호들의 짝은 1:1 매칭만 가능하다. 즉, 괄호 하나가 둘 이상의 괄호와 짝지어지지 않는다.
//짝을 이루는 두 괄호가 있을 때, 그 사이에 있는 문자열도 균형이 잡혀야 한다.
//정민이를 도와 문자열이 주어졌을 때 균형잡힌 문자열인지 아닌지를 판단해보자.

//입력
//하나 또는 여러줄에 걸쳐서 문자열이 주어진다. 각 문자열은 영문 알파벳, 공백, 소괄호("( )") 대괄호("[ ]")등으로 이루어져 있으며, 길이는 100글자보다 작거나 같다.
//입력의 종료조건으로 맨 마지막에 점 하나(".")가 들어온다.
//출력
//각 줄마다 해당 문자열이 균형을 이루고 있으면 "yes"를, 아니면 "no"를 출력한다.
