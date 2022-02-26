import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		int n;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		int k = 2;
		while(n>1) {
			if(n%k==0) {
				System.out.println(k);
				n/=k;
			}
			else {
				k++;
			}
		}
		
	}
	
}
