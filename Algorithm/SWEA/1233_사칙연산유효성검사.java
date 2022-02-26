import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= 10; tc++) {
			
			int n = Integer.parseInt(br.readLine());
			boolean flag = true;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int index = Integer.parseInt(st.nextToken());
				String op = st.nextToken();
				if(op.equals("-") || op.equals("+") || op.equals("*") || op.equals("/")) {
					//연산자인데 중간노드가 아닌경우 잘못됨
					if(!st.hasMoreTokens()) {
						flag = false;
						while(++i<n) {
							br.readLine();
						}
						break;
					} 
				}
				else {
					//숫자인데 리프노드가 아닌경우 잘못됨
					if(st.hasMoreTokens()) {
						while(++i<n) {
							br.readLine();
						}
						flag = false;
						break;
					}
				}
			}
			sb.append("#").append(tc).append(" ");
			if(!flag) sb.append(0);
			else sb.append(1);
			sb.append("\n");
			flag = true;
		}
		System.out.println(sb);
	}

}
