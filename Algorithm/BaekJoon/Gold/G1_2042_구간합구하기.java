package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_G1_2042_구간합구하기 {

	// 세그먼트 트리 연습해보기

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		long[] numbers = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			numbers[i] = Long.parseLong(br.readLine());
		}

		SegmentTree tree = new SegmentTree(n + 1);
		tree.init(numbers, 1, 1, n);

		for (int i = 0; i < m + k; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String op = st.nextToken();
			int b = Integer.parseInt(st.nextToken());
			Long c = Long.parseLong(st.nextToken());

			switch (op) {
			case "1":
				long diff = c - numbers[b];
				numbers[b] = c;
				tree.update(1, 1, n, b, diff);
				break;
			case "2":
				sb.append(tree.sum(1, 1, n, b, c.intValue())).append("\n");
				break;
			}
		}
		System.out.println(sb);
	}

	static class SegmentTree {
		private long[] tree;

		// 생성자에서 세그먼트 트리의 전체노드 수 계산
		public SegmentTree(int n) {
			// 트리 높이
			// 자바 log는 밑이 자연로그라 log2로 나눠 밑을2로 만들어줌
			double height = Math.ceil(Math.log(n) / Math.log(2)) + 1;
			// 트리 노드 수
			long nodeCnt = Math.round(Math.pow(2, height));
			// 트리 길이
			tree = new long[Math.toIntExact(nodeCnt)];
		}

		// 세그먼트 트리의 노드 값 초기화
		// arr : 세그먼트 트리로 나타낼 배열
		// node : 메서드 시작할 노드인덱스. 항상1부터
		// start : 세그먼트 트리의 노드들이 갖는 값의 시작 인덱스
		// end : 세그먼트 트리의 노드들이 갖는 값의 종료 인덱스
		long init(long[] arr, int node, int start, int end) {
			// 세그먼트 트리의 리프노드인 경우
			if (start == end) {
				// 리프노드에 배열의 값 저장 후 리턴
				return tree[node] = arr[start];
			} else {
				// 리프노드가 아닌 경우 자식 노드의 값을 더해서 노드의 값 초기화 후 리턴
				// 왼자식 + 오른자식
				return tree[node] = init(arr, node * 2, start, (start + end) / 2)
						+ init(arr, node * 2 + 1, (start + end) / 2 + 1, end);
			}
		}

		// 특정 구간의 합 구하기
		// node : 세그먼트 트리의 노드 번호
		// start : 노드가 갖는 합의 시작 인덱스
		// end : 노드가 갖는 합의 끝 인덱스
		// left : 구하려 하는 배열의 구간 합 중 구간의 시작 인덱스
		// right : 구하려 하는 배열의 구간 합 중 구간의 끝 인덱스
		long sum(int node, int start, int end, int left, int right) {
			// 노드가 갖는 값의 구간이 구하려 하는 합의 구간에 속하지 않으면 0 리턴
			// end - left 순서거나, right - start 순서 : 아예 속하지 않음
			if (left > end || right < start)
				return 0;
			// left - start - end - right 순서
			// 노드가 갖는 값의 구간이 구하려 하는 합의 구간에 속하는 경우 노드 값 리턴
			else if (left <= start && end <= right)
				return tree[node];
			else {
				// 그외
				// 1. 노드가 갖는 값의 구간이 구하려 하는 합의 구간에 일부 속하고 일부는 속하지 않는 경우
				// 2. 노드가 갖는 값의 구간이 구하려 하는 합의 구간을 모두 포함하는 경우
				// 자식 노드를 탐색해서 값을 리턴
				return sum(node * 2, start, (start + end) / 2, left, right)
						+ sum(node * 2 + 1, (start + end) / 2 + 1, end, left, right);
			}
		}

		// 배열의 특정 값이 변경되면 세그먼트 트리의 노드 값 변경
		// 차이값을 구해 각 노드가 갖는 구간에 변경될 배열의 인덱스가 포함되면 차이값을 더해줌
		// 이 과정을 리프노드에 도달할때까지 재귀형태
		// node : 메서드가 실행죄는 노드의 번호
		// start : 노드가 갖는 합의 시작 인덱스
		// end : 노드가 갖는 합의 끝 인덱스
		// index : 값이 변경될 배열의 인덱스
		// diff : 변경될 값 - 배열의 기존 값 = 차이값
		void update(int node, int start, int end, int index, long diff) {
			// 노드가 갖는 값의 구간에 배열의 인덱스(값이 변경될애)가 포함되지 않을 경우
			// 아무것도안함
			if (start > index || end < index)
				return;
			else {
				// 노드가 갖는 값의 구간에 배열의 인덱스(값이 변경될애)가 포함될 경우
				// 노드의 값 + 차이값 (변경할값 - 기존값)
				tree[node] = tree[node] + diff;

				// 노드가 리프노드가 아닌 경우
				if (start != end) {
					// 리프노드까지 계속 자식노드를 탐색
					update(node * 2, start, (start + end) / 2, index, diff);
					update(node * 2 + 1, (start + end) / 2 + 1, end, index, diff);
				}
			}
		}
	}

}
