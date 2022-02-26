package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_P5_6549_히스토그램에서가장큰직사각형 {

	// 특정 시작점과 끝점 => 그중 최소높이 : 길이 * 최소높이 = 히스토그램넓이
	// 시작점과 끝점(nC2) 사이에서 최소높이를 어떻게 구하느냐가 관건

	// 특정 구간의 구간합, 구간곱, 최소값, 최대값을 최소 시간으로 구하려면 세그먼트트리를 활용하면 좋다함
	// nC2 * nlogn = nlogn * n(n-1)/2

	// 세그먼트 트리만 하면 될줄 알았는데 분할정복도 해야됨
	// 최소 높이를 기준으로 분할정복해가며 최대 넓이를 갖는 사각형을 찾아냄 -> 인덱스저장..

	static class SegmentTreeMinValue {
		int[] tree;
		int[] arr;
		int INFINITY = Integer.MAX_VALUE;

		public SegmentTreeMinValue(int n, int[] arr) {
			this.arr = arr;
			int height = (int) Math.ceil(Math.log(n) / Math.log(2)) + 1;
			long nodeCnt = Math.round(Math.pow(2, height));
			tree = new int[Math.toIntExact(nodeCnt)];
		}

		int init(int node, int start, int end) {
			if (start == end)
//				return tree[node] = arr[start];
				return tree[node] = start;
			else {
				int mid = (start + end) / 2;
//				return tree[node] = Math.min(init(arr, node * 2, start, mid), init(arr, node * 2 + 1, mid + 1, end));
				int leftIdx = init(node * 2, start, mid);
				int rightIdx = init(node * 2 + 1, mid + 1, end);
				return tree[node] = arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
			}
		}

		int getMinIndex(int node, int start, int end, int left, int right) {
			if (end < left || right < start)
				return INFINITY;
			if (left <= start && end <= right)
				return tree[node];
			int mid = (start + end) / 2;
			int leftIdx = getMinIndex(node * 2, start, mid, left, right);
			int rightIdx = getMinIndex(node * 2 + 1, mid + 1, end, left, right);
			if (leftIdx == INFINITY)
				return rightIdx;
			if (rightIdx == INFINITY)
				return leftIdx;
			return arr[leftIdx] < arr[rightIdx] ? leftIdx : rightIdx;
		}
	}

	public static SegmentTreeMinValue tree;
	public static int n;
	public static int[] squares;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");

			n = Integer.parseInt(st.nextToken());
			if (n == 0)
				break;

			squares = new int[n + 1];
			for (int i = 1; i <= n; i++) {
				squares[i] = Integer.parseInt(st.nextToken());
			}

			tree = new SegmentTreeMinValue(n + 1, squares);
			tree.init(1, 1, n);

//			long max = 0;
//			int volumn = 0;
//			for (int i = 1; i < n; i++) {
//				for (int j = i + 1; j <= n; j++) {
//					volumn = tree.getMinValue(1, 1, n, i, j) * (j - i + 1);
//					if (volumn > max)
//						max = volumn;
//				}
//			}
//			sb.append(max).append("\n");

			sb.append(getMaxVolumn(1, n)).append("\n");
		}

		System.out.println(sb);

	}

	public static long getMaxVolumn(int i, int j) {
		int minIdx = tree.getMinIndex(1, 1, n, i, j);
		long maxVolumn = (long) squares[minIdx] * (long) (j - i + 1);

		// 왼쪽있으면
		if (i < minIdx)
			maxVolumn = Math.max(maxVolumn, getMaxVolumn(i, minIdx - 1));

		// 오른쪽있으면
		if (minIdx < j)
			maxVolumn = Math.max(maxVolumn, getMaxVolumn(minIdx + 1, j));

		return maxVolumn;
	}

}
