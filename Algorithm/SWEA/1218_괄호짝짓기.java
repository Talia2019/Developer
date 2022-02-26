package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SWEA_1218_D4 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Stack<Character> stack = new Stack<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 10; i++) {
			int length = Integer.parseInt(br.readLine());
			String str = br.readLine();
			boolean flag = true;
			for (int j = 0; j < length; j++) {
				char c = str.charAt(j);
				if(c == '{' || c == '[' || c == '(' || c == '<') {
					stack.push(c);
				}
				else {
					if(stack.isEmpty()) {
//						System.out.println(i + " " + j + " : " + c);
//						System.out.println(stack.toString());
						flag = false;
					}
					char c2 = stack.pop();
					switch(c2) {
					case '{':
						if(c!='}') flag = false;
						break;
					case '[':
						if(c!=']') flag = false;
						break;
					case '(':
						if(c!=')') flag = false;
						break;
					case '<':
						if(c!='>') flag = false;
						break;
					}
					if(flag == false) break;
				}
			}
//			System.out.println(i + " " + stack.toString());
			sb.append("#").append(i).append(" ");
			if(!stack.isEmpty() || !flag)  sb.append(0);
			else sb.append(1);
			sb.append("\n");
			
			stack.clear();
		}
		System.out.println(sb);
	}

}
