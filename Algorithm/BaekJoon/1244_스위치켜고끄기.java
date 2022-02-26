package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_1244_실버4 {
//	첫째 줄에는 스위치 개수가 주어진다. 스위치 개수는 100 이하인 양의 정수이다. 둘째 줄에는 각 스위치의 상태가 주어진다. 
//	켜져 있으면 1, 꺼져있으면 0이라고 표시하고 사이에 빈칸이 하나씩 있다. 셋째 줄에는 학생수가 주어진다. 
//	학생수는 100 이하인 양의 정수이다. 넷째 줄부터 마지막 줄까지 한 줄에 한 학생의 성별, 학생이 받은 수가 주어진다. 
//	남학생은 1로, 여학생은 2로 표시하고, 학생이 받은 수는 스위치 개수 이하인 양의 정수이다. 학생의 성별과 받은 수 사이에 빈칸이 하나씩 있다.
//	스위치의 상태를 1번 스위치에서 시작하여 마지막 스위치까지 한 줄에 20개씩 출력한다. 예를 들어 21번 스위치가 있다면 이 스위치의 상태는 둘째 줄 맨 앞에 출력한다. 
//	켜진 스위치는 1, 꺼진 스위치는 0으로 표시하고, 스위치 상태 사이에 빈칸을 하나씩 둔다.

	static int[] light;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int sn = Integer.parseInt(st.nextToken());
//		System.out.println(sn);

		light = new int[sn];

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < sn; i++) {
			light[i] = Integer.parseInt(st.nextToken());
		}
//		System.out.println(Arrays.toString(light));
		int pn = Integer.parseInt(in.readLine());
//		System.out.println(pn);
		int a, b;
		for (int i = 0; i < pn; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			switch (a) {
			case 1:
				forMan(b);
//				System.out.println(Arrays.toString(light));
				break;
			case 2:
				forWoman(b);
//				System.out.println(Arrays.toString(light));
				break;
			}
			printLight();
		}
//		printLight();
	}

	public static void printLight() {

		int l = light.length;

		int i = 0;
		while (l > 0) {
			int s;
			s = l > 20 ? 20 : l;
			for (int j = 0; j < s; j++) {
				System.out.print(light[i++] + " ");
			}
			System.out.println();
			l -= 20;
		}
	}
	public static void togle(int n) {
		if (light[n] == 0)
			light[n] = 1;
		else
			light[n] = 0;
	}

	public static void forMan(int n) {
		int index = n;
		while (index <= light.length) {
			togle(index - 1);
			index+=n;
		}
	}

	public static boolean isOut(int n, int i) {
		if (n - i < 0 || n + i > light.length - 1)
			return true;
		return false;
	}

	public static void forWoman(int n) {
		int i = 1;
		togle(n - 1);
		while (true) {
			if (isOut(n - 1, i))
				return;
			if (light[n - 1 + i] == light[n - 1 - i]) {
				togle(n - 1 - i);
				togle(n - 1 + i);
			}
			else return;
			i++;
		}
	}
}
