import java.util.Scanner;

public class Main {
	static int[][][] w = new int[21][21][21];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a, b, c;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		while(!((a==-1 && b==-1) && c==-1)) {
			System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, getW(a,b,c));
			a = sc.nextInt();
			b = sc.nextInt();
			c = sc.nextInt();
		}
/*
 * if a <= 0 or b <= 0 or c <= 0, then w(a, b, c) returns: 1

if a > 20 or b > 20 or c > 20, then w(a, b, c) returns: w(20, 20, 20)

if a < b and b < c, then w(a, b, c) returns: w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)

otherwise it returns:
    w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)
 * */	
	}
	
	public static int getW(int a, int b ,int c) {
		if(a<=0 || b<=0 || c<=0) {
			return 1;
		}
		if(a>20 || b>20 || c>20) {
			if(w[20][20][20]==0) {
				getW(20,20,20);
			}
			return w[20][20][20];
		}
		if(w[a][b][c] == 0) {
			if(a<b && b<c) {
				w[a][b][c] = getW(a, b, c-1) + getW(a, b-1, c-1) - getW(a, b-1, c);	
			}
			else {
				w[a][b][c] = getW(a-1, b, c) + getW(a-1, b-1, c) + getW(a-1, b, c-1) - getW(a-1, b-1, c-1);
			}
		}
		return w[a][b][c];
	}


}
