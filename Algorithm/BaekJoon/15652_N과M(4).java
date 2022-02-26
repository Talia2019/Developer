import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

//1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열

public class Main {
	
	static boolean[] visit;
	static int[] arr;
	static int n, m;
	static int k = 0;
	static BufferedWriter bw;

	public static void dfs(int depth) throws IOException {
		if(depth == m) {
			String s = "";
			for(int elm:arr) {
				s+=elm;
				s+=' ';
			}
			bw.write(s+"\n");
			return;
		}

		if(depth == 0) k = 0;
		else k = arr[depth-1] - 1;
		
		for(int i=k;i<n;i++) {
			if(visit[i]==false) {
				//visit[i] = true;
				arr[depth] = i+1;
				dfs(depth+1);
				//visit[i] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		
		visit = new boolean[n];
		arr = new int[m];
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		dfs(0);
		
		bw.flush();
		bw.close();
	}
}
