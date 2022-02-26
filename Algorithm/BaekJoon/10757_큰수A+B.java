import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String num1, num2;
		Scanner sc = new Scanner(System.in);
		
		num1 = sc.next();
		num2 = sc.next();
		
		Queue q1 = new LinkedList();
		Queue q2 = new LinkedList();
		
		int len1, len2;
		len1 = num1.length();
		len2 = num2.length();
		int len = len1>len2? len1:len2;
		
		for(int i=len1-1;i>=0;i--) {
			q1.offer(num1.charAt(i)-'0');
		}
		for(int i=0;i<len-len1;i++) {
			q1.offer(0);
		}
		for(int i=len2-1;i>=0;i--) {
			q2.offer(num2.charAt(i)-'0');
		}
		for(int i=0;i<len-len2;i++) {
			q2.offer(0);
		}
		
		int up = 0;
		String ans = "";
		Stack st = new Stack();
		for(int i=0;i<len;i++) {
			int a = ((int)q1.poll() + (int)q2.poll());
			a+=up;
			if(a>=10) {
				st.push(a%10);
				up = 1;
			}
			else {
				st.push(a);
				up = 0;
			}
		}
		if(up==1) ans+=1;
		for(int i=0;i<len;i++) {
			ans += st.pop();
		}
		System.out.println(ans);
		
	}

}
