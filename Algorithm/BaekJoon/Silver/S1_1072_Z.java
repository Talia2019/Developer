import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int r = sc.nextInt();
		int c = sc.nextInt();

		// j 에 2^(n-1)보다 크거나 같은 인덱스가 있으면 1번째칸 -> 1*2^(2n-2) 번째부터 시작
		// i 에 2^(n-1)보다 크거나 같은 인덱스가 있으면 2번째칸 -> 2*2^(2n-2)
		// 둘다 있으면 3번째칸 -> 3*2^(2n-2)
		// 없으면 0번째칸
		// 2^n승이면 n-1번 나눠야함

		// 2^(n-1)만큼 빼고 다시 몇번째칸인지 확인
		int pow = (int) Math.pow(2, n - 1);
		int add = 0;

		while (n >= 1) {
			if (r >= pow && c >= pow) { // 3
				add += (int)Math.pow(2, 2*n-2) * 3;
				r -= pow;
				c -= pow;
			} else if (c >= pow) { // 1
				add += (int)Math.pow(2, 2*n-2);
				c -= pow;
			} else if (r >= pow) { // 2
				add += (int)Math.pow(2, 2*n-2) * 2;
				r -= pow;
			} else { // 0

			}
			n--;
			pow /= 2;
		}
		System.out.println(add);
	}

}
