package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_SWEA_1225_D3 {
//	다음 주어진 조건에 따라 n개의 수를 처리하면 8자리의 암호를 생성할 수 있다.
//
//	- 8개의 숫자를 입력 받는다.
//
//	- 첫 번째 숫자를 1 감소한 뒤, 맨 뒤로 보낸다. 
//
//	다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 다음 첫 번째 수는 3을 감소하고 맨 뒤로, 그 다음 수는 4, 그 다음 수는 5를 감소한다.
//
//	이와 같은 작업을 한 사이클이라 한다.
//
//	- 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며, 프로그램은 종료된다. 이 때의 8자리의 숫자 값이 암호가 된다.
//	 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[] numbers = new int[8];
		LinkedList<Integer> queue = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= 10; i++) {
			String t = br.readLine();
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 8; j++) {
				queue.add(Integer.parseInt(st.nextToken()));
			}
			int sub = 1;
			while (true) {
//				System.out.println(queue.toString());
				int f = queue.poll();
				if (f - sub <= 0) {
					queue.add(0);
					break;
				}
				queue.add(f - sub);
				sub++;
				if (sub > 5)
					sub %= 5;
			}
			sb.append("#").append(i).append(" ");
			for (int j = 0; j < 8; j++) {
				sb.append(queue.poll()).append(" ");
			}
			sb.append("\n");
//			System.out.println(sb);
		}
		System.out.println(sb);
	}
}
