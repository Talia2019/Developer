package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_S3_9375_패션왕신해빈 {

//	S3 9375 패션왕 신해빈

//	부분조합의 수 라고 생각했는데 -> 시간초과남
//	0개를 고르는 상황을 추가한다면
//	옷의 종류가 ABC가 있을때 (A+1)*(B+1)*(C+1)-1 가 정답이됨. (하나도 선택안한경우 뺀거)
	public static int n, lastIdx = 0, totalSum = 0;
	public static boolean[] selected;
	public static int[] clothes;
	// type, index
	public static Map<String, Integer> mapping;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		int n;
		String type;
		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			selected = new boolean[n];
			clothes = new int[n];
			totalSum = lastIdx = 0;
			mapping = new HashMap<String, Integer>();
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				type = st.nextToken();

				int index = mapping.getOrDefault(type, -1);
				if (index < 0) {
					clothes[lastIdx] = 1;
					mapping.put(type, lastIdx++);
				} else {
					clothes[index]++;
				}
			}
//			subSet(0, 1);
			int sum = 1;
			for (int i = 0; i < clothes.length; i++) {
				sum *= clothes[i] + 1;
			}
			sb.append(--sum).append("\n");
		}
		System.out.println(sb);
	}

	public static void subSet(int cnt, int sum) {
		if (cnt == lastIdx) {
			totalSum += sum;
			return;
		}
		subSet(cnt + 1, sum * clothes[cnt]);
		subSet(cnt + 1, sum);
	}

}
