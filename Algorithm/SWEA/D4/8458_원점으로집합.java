import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SWEA_8458_D4_원점으로집합 {

	// 음.. 각 점이 모두 홀수이거나 모두 짝수여야 가능하다고 한다.
	// 또한 점에서 각 값들을 뺀값이 0보다 작거나 같으며 짝수가 되면 원점에 도달할 수 있다고 한다
	// 이유는 모르겠다......

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());

			st = new StringTokenizer(br.readLine(), " ");
			int distance = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
			int isEven = distance % 2;
			int max = distance, answer = 0;
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				distance = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				if (distance > max)
					max = distance;
				if (distance % 2 != isEven) {
					answer = -1;
					break;
				}
			}

			if (answer != -1) {
				boolean flag = false;
				while (true) {
					if (max <= 0 && max % 2 == 0) {
						break;
					}
					max -= ++answer;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

//	public static int setPoints() {
//		int max = 0;
//		for (int[] p : points) {
//			if (!(p[0] == 0 && p[1] == 0)) {
//				min = 987654321;
//				toZero(p[0], p[1], 1, Math.max(Math.abs(p[0]), Math.abs(p[1])));
//				if (min == 987654321) {
//					max = -1;
//					break;
//				}
//			} else {
//				min = 0;
//			}
//			System.out.println(min);
//			if (min > max) {
//				max = min;
//				System.out.println("M" + max);
//			}
//		}
//		return max;
//	}
//
//	public static void toZero(int x, int y, int num, int m) {
//		if (num > m * 2) {
//			return;
//		}
//		if (x == 0 && y == 0) {
//			if (num < min) {
//				min = num - 1;
//				System.out.println(min);
//			}
//			return;
//		}
//		if (x == num) {
//			toZero(0, y, num + 1, m);
//		} else if (y == num) {
//			toZero(x, 0, num + 1, m);
//		} else {
//			if (x != 0) {
//				toZero(x - num, y, num + 1, m);
//				toZero(x + num, y, num + 1, m);
//			}
//			if (y != 0) {
//				toZero(x, y + num, num + 1, m);
//				toZero(x, y - num, num + 1, m);
//			}
//		}
//	}

}
