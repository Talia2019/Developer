import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

//1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

public class Main {
	
	static boolean[] visit;
	static int[] arr;
	static int n, m;

	public static void dfs(int depth) {
		if(depth == m) {
			for(int elm:arr) {
				System.out.print(elm+" ");
			}
			System.out.print("\n");
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(visit[i]==false) {
				visit[i] = true;
				arr[depth] = i + 1;
				dfs(depth+1);
				visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		visit = new boolean[n];
		arr = new int[m];
		
		dfs(0);
	}
}
