package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_SWEA_3234_준환이의양팔저울 {

//	D4 3234 준환이의 양팔저울

//	오른쪽 위에 올라가 있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다. (왼쪽이 크거나 같음)

//	첫 번째 줄에 테스트 케이스의 수 T가 주어진다.
//	각 테스트 케이스마다 첫 번째 줄에 N(1 ≤ N ≤ 9)가 주어진다.
//	두 번째 줄에는 각 무게추의 무게를 의미하는 N개의 자연수가 공백으로 구분되어 주어진다. 무게는 1이상 999이하이다.
//	각 테스트 케이스마다 무게추를 올리는 과정에서 오른쪽 위에 올라가있는 무게의 총합이 왼쪽에 올라가 있는 무게의 총합보다 커지지 않는 경우의 수를 출력한다.

//	무게를정렬해서..
//	넥퍼써서 순서대로 구해서 하나씩 올려보는게 빠를까
//	그냥 하나씩 올려서 가지치기 해보는게 빠를까? - 일단 하나씩 올려보기 --> 시간초과....

//	하나씩 올려보기 --> 시간초과...

// 	순열을 구한 후 해당 순열에서 저울에 올릴수 있는경우를 구함
	public static int[] weight;
	public static int[] perm;
	public static boolean[] isVisited;
	public static int n, totalCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			weight = new int[n];
			perm = new int[n];
			isVisited = new boolean[n];

			for (int i = 0; i < n; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
//			setScale(0, 0, 0);
			permutation(0);
			sb.append("#").append(tc).append(" ").append(totalCnt).append("\n");
			totalCnt = 0;
		}
		System.out.println(sb);

	}

	// 다 올렸으면 cnt++
	// 왼>=오 일 경우만 올림
//	public static void setScale(int left, int right, int cnt) {
//		if (cnt == n) { // 다올린거
//			totalCnt++;
//			return;
//		}
//
//		int l = 0, r = 0;
//		for (int i = 0; i < n; i++) {
//			if (isVisited[i])
//				continue;
//			isVisited[i] = true;
//			l = left + weight[i]; // 왼쪽에 추 올림
//			if (l >= right)
//				setScale(l, right, cnt + 1);
//			r = right + weight[i]; // 오른쪽에 추올림
//			if (left >= r)
//				setScale(left, r, cnt + 1);
//			isVisited[i] = false;
//		}
//
//	}

	public static void setScale(int left, int right, int cnt) {
		if (cnt == n) { // 다올린거
			totalCnt++;
			return;
		}

		setScale(left + perm[cnt], right, cnt + 1);
		if (left >= right + perm[cnt])
			setScale(left, right + perm[cnt], cnt + 1);
	}

	public static void permutation(int cnt) {
		if (cnt == n) { // 다올린거
			setScale(0, 0, 0);
			return;
		}

		for (int i = 0; i < n; i++) {
			if (isVisited[i])
				continue;
			isVisited[i] = true;
			perm[cnt] = weight[i];
			permutation(cnt + 1);
			isVisited[i] = false;
		}
	}
}
