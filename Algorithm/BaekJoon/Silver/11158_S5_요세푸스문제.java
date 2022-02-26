package boj;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		List<Integer> circle = new ArrayList<Integer>();
		int size = n;
		int[] josep = new int[n];

		for (int i = 1; i <= n; i++) {
			circle.add(i);
		}

		int i = -1;
		int ji = 0;
		while (!circle.isEmpty()) {
			i += k;
			if(i>=size) i%=size;
			josep[ji++] = circle.remove(i--);
			size--;
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for (int j = 0; j < josep.length-1; j++) {
			sb.append(josep[j]).append(", ");
		}
		sb.append(josep[josep.length-1]);
		sb.append(">");
		
		System.out.println(sb);
	}

}
