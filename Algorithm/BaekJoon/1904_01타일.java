import java.util.Scanner;

public class Main {

//	각각의 타일들은 0 또는 1이 쓰여 있는 낱장의 타일들이다.
//	0이 쓰여진 낱장의 타일들을 붙여서 한 쌍으로 이루어진 00 타일들을 만들었다. 결국 현재 1 하나만으로 이루어진 타일 또는 0타일을 두 개 붙인 한 쌍의 00타일들만이 남게 되었다.
//	그러므로 지원이는 타일로 더 이상 크기가 N인 모든 2진 수열을 만들 수 없게 되었다. 
//	예를 들어, N=1일 때 1만 만들 수 있고, N=2일 때는 00, 11을 만들 수 있다. (01, 10은 만들 수 없게 되었다.) 또한 N=4일 때는 0011, 0000, 1001, 1100, 1111 등 총 5개의 2진 수열을 만들 수 있다.
//	우리의 목표는 N이 주어졌을 때 지원이가 만들 수 있는 모든 가짓수를 세는 것이다. 단 타일들은 무한히 많은 것으로 가정하자.
//	첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
//	첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로 나눈 나머지를 출력한다.

	static int[] cntZero = new int[1000001]; // 00으로 시작
	static int[] cntOne = new int[1000001]; // 1로 시작

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		cntZero[1] = 0;
		cntOne[1] = cntZero[2] = cntOne[2] = 1;
		
		int n = sc.nextInt();
		System.out.println(getCnt(n));

	}

	public static int getCntZero(int n) {	//0으로 시작 : cntZero[n-1]뒤에 1붙인거 + cntZero[n-2]뒤에 00붙인거
		if (n < 1)
			return 0;

		if (cntZero[n] == 0) {
			cntZero[n] = (getCntZero(n-1) + getCntZero(n-2))%15746;
		}
		
		return cntZero[n];
	}
	public static int getCntOne(int n) {	//1로 시작 : cnt[n-1] 앞에 1
		if (n < 1)
			return 0;

		if (cntOne[n] == 0) {
			cntOne[n] = (getCntOne(n-1) + getCntZero(n-1))%15746;
		}
		return cntOne[n];
	}
	public static int getCnt(int n) {
		return (getCntZero(n) + getCntOne(n))%15746;
	}
}
