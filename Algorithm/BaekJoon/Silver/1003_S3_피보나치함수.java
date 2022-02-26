import java.util.Scanner;

public class Main {
	static int[] cnt = new int[41];
	static int[][] number = new int[41][2];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int t;
		t = sc.nextInt();
		
		for (int i = 0; i < t; i++) {
			int n = sc.nextInt();
			fibonacci(n);
			System.out.println(number[n][0] + " " +number[n][1]);
		}
		
	}
	public static int[] fibonacci(int n) {
		if(n==0) {
			number[n][0] = 1;
			return number[n];
		}
		if(n==1) {
			number[n][1] = 1;
			return number[n];
		}
		if(number[n][0]!=0  || number[n][1]!=0) {
			return number[n];
		}
		number[n][0] = fibonacci(n-1)[0] + fibonacci(n-2)[0];
		number[n][1] = fibonacci(n-1)[1] + fibonacci(n-2)[1];
		return number[n];
	}

}
