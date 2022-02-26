package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

// 내앞에 나보다 큰게 있는가? 나보다 작으면 스택에서 빼고, 나보다 크거나 같으면 걔의 인덱스를 출력하고 나를 집어넣음
public class Main_백준_2493_탑_정지영 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> towers = new Stack<>();
		Map<Integer, Integer> index = new HashMap<>();
//		int[] index = new int[100000001];	--> 메모리초과해서 해시맵썼어요

		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		towers.add(Integer.parseInt(st.nextToken()));
		sb.append(0).append(" ");
		index.put(towers.peek(), 1);
		int top = 1;
		for (int i = 1; i < n; i++) {
			int t = Integer.parseInt(st.nextToken());
//			index[t] = i+1;
			while (!towers.isEmpty() && towers.peek() < t) {
				towers.pop();
			}
			if (towers.isEmpty())
				sb.append(0).append(" ");
			else {
				sb.append(index.get(towers.peek())).append(" ");
			}
			index.put(t, i+1);
			towers.add(t);
		}
		System.out.println(sb);
	}

}
