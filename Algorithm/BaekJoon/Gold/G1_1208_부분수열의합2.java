package jiyoung.week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SubPowerSum {

	// G1 1208 부분수열의합2

	// 모르겠어서 참고함..
	// 2^40은 너무크니까 2^20 + 2^20으로 반으로 나눠서 해결한다함
	// 왼쪽배열과 오른쪽배열의 부분집합의 합들을 미리 구해놓고
	// 투포인터 알고리즘을 이용하여 계산
	// 이런걸 어떻게 생각해내지 ㅜ
	
	// cnt의 개수조차 int범위를 넘어갈수있음..!

	public static int n, s;
	public static long totalNum;
	public static int[] numbers;
	public static List<Long> leftList, rightList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		numbers = new int[n];
		leftList = new ArrayList<Long>();
		rightList = new ArrayList<Long>();

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		getSub(0, n / 2, 0L, leftList);
		getSub(n / 2, n, 0L, rightList);
		Collections.sort(leftList);
		Collections.sort(rightList);
		
		// 투포인터 알고리즘
		int leftIdx = 0, rightIdx = rightList.size() - 1;
		int leftSize = leftList.size();
		while (leftIdx < leftSize && rightIdx >= 0) {
			long sum = leftList.get(leftIdx) + rightList.get(rightIdx);
			if (sum == s) {
				long leftValue = leftList.get(leftIdx++);
				long rightValue = rightList.get(rightIdx--);

				long leftCnt = 1;
				while (leftIdx < leftSize && leftList.get(leftIdx) == leftValue) {
					leftCnt++;
					leftIdx++;
				}

				long rightCnt = 1;
				while (rightIdx >= 0 && rightList.get(rightIdx) == rightValue) {
					rightCnt++;
					rightIdx--;
				}

				totalNum += leftCnt * rightCnt;
			} else if (sum > s)
				rightIdx--;
			else
				leftIdx++;
		}

		// 공집합을 더하는경우 빼줌
		if (s == 0)
			totalNum--;
		
		System.out.println(totalNum);
	}

	public static void getSub(int start, int end, long sum, List<Long> list) {
		if (start == end) {
			list.add(sum);
			return;
		}
		getSub(start + 1, end, sum, list);
		getSub(start + 1, end, sum + numbers[start], list);
	}

}
