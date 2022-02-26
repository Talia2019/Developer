package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_G4_17298_오큰수 {

	// G4 17298 오큰수

	// 스택을 어떻게 사용하지??이걸..

	// binary search사용하면 될거같은뎀
	// bs * -1 -1 값이 현재 인덱스인 경우 -1이고 아닌경우 값을 넣으면됨
	// 라고 생각했는데 정렬된값이 아니라서 원하는 값이 안나옴

	// 그냥 내 오른쪽을 돌면서 첫번째로 나오는 큰 수를 찾아야될듯 -> 시간초과 n^2

	// ㅠㅠ모르겠어서 다른 풀이를 참고했다
	// 현재값이 peek보다 작으면 스택에 인덱스들을 삽입 (스택이 비었으면 삽입)
	// 현재값이 peek보다 크면 스택에 있는 현재값보다 작은애들의 인덱스의 값들까지 큰값으로 바꿔줌. 그리고 현재인덱스를 삽입
	// 모든 수들을 봤으면 스택에 남아있는 인덱스들은 얘보다 큰게 없는거니까 해당 인덱스의 값들을 -1로 바꿔줌

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		int n = Integer.parseInt(br.readLine());

		int[] numbers = new int[n];
		Stack<Integer> stack = new Stack<>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		stack.push(0);

		int biggerV;
		for (int i = 1; i < n; i++) {
			if (numbers[stack.peek()] < numbers[i]) { // 스택에 있는 값들보다 큰값이 나타났음
				biggerV = numbers[i];
				while (stack.size() > 0 && numbers[stack.peek()] < biggerV) {
					numbers[stack.pop()] = biggerV;
				}
			}
			stack.push(i);
		}

		while (stack.size() > 0) {
			numbers[stack.pop()] = -1;
		}

		for (int i = 0; i < n; i++) {
			sb.append(numbers[i]).append(" ");
		}
		// ver2
//		int cur;
//		boolean flag;
//		for (int i = 0; i < n; i++) {
//			cur = i;
//			flag = false;
//			while (++cur < n) {
//				if (numbers[cur] > numbers[i]) {
//					flag = true;
//					break;
//				}
//			}
//			if (flag)
//				sb.append(numbers[cur]).append(" ");
//			else
//				sb.append("-1 ");
//			
//			//ver 1
////			int idx = Arrays.binarySearch(numbers, i, n, numbers[i]);
////			System.out.println(i + " " + numbers[i]);
////			System.out.println(idx);
////			if (idx < 0)
////				idx = idx * -1 - 1;
////
////			if (idx == i)
////				sb.append("-1 ");
////			else
////				sb.append(numbers[idx - 1]).append(" ");
//		}

		System.out.println(sb);
	}

}
