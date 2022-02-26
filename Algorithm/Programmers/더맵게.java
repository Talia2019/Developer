package jiyoung.week2;

import java.util.PriorityQueue;

public class MoreSpicy {
//	섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
//	Leo가 가진 음식의 스코빌 지수를 담은 배열 scoville과 원하는 스코빌 지수 K가 주어질 때, 모든 음식의 스코빌 지수를 K 이상으로 만들기 위해 섞어야 하는 최소 횟수를 return 하도록 solution 함수를 작성해주세요.
//	scoville의 길이는 2 이상 1,000,000 이하입니다.
//	K는 0 이상 1,000,000,000 이하입니다.
//	scoville의 원소는 각각 0 이상 1,000,000 이하입니다.
//	모든 음식의 스코빌 지수를 K 이상으로 만들 수 없는 경우에는 -1을 return 합니다.

//	[1, 2, 3, 9, 10, 12]	7	2
	
//	정확성  테스트
//	테스트 1 〉	통과 (0.46ms, 56.6MB)
//	테스트 2 〉	통과 (0.41ms, 73.1MB)
//	테스트 3 〉	통과 (0.46ms, 55MB)
//	테스트 4 〉	통과 (0.30ms, 65.8MB)
//	테스트 5 〉	통과 (0.51ms, 57.9MB)
//	테스트 6 〉	통과 (3.78ms, 58MB)
//	테스트 7 〉	통과 (2.11ms, 57.8MB)
//	테스트 8 〉	통과 (1.05ms, 56.2MB)
//	테스트 9 〉	통과 (0.99ms, 71.5MB)
//	테스트 10 〉	통과 (2.22ms, 70.4MB)
//	테스트 11 〉	통과 (2.10ms, 59.1MB)
//	테스트 12 〉	통과 (8.49ms, 59.7MB)
//	테스트 13 〉	통과 (2.31ms, 57.3MB)
//	테스트 14 〉	통과 (0.38ms, 70.9MB)
//	테스트 15 〉	통과 (2.11ms, 57MB)
//	테스트 16 〉	통과 (0.36ms, 67.8MB)
//	효율성  테스트
//	테스트 1 〉	통과 (131.15ms, 66.3MB)
//	테스트 2 〉	통과 (269.39ms, 87.5MB)
//	테스트 3 〉	통과 (1293.14ms, 123MB)
//	테스트 4 〉	통과 (113.65ms, 63.5MB)
//	테스트 5 〉	통과 (1328.89ms, 124MB)
//	채점 결과
//	정확성: 76.2
//	효율성: 23.8
//	합계: 100.0 / 100.0
	
	public static void main(String[] args) {
		// 힙으로 구현해보기
		// 최대랑 최소를 빠르게 구하려면 힙

		Solution s = new Solution();
		int[] scoville = { 12, 10, 9, 3, 2, 1 };
		System.out.println(s.solution(scoville, 100000));
	}

}

class Solution {
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		for (int i = 0; i < scoville.length; i++) {
			heap.add(scoville[i]);
		}
		while(heap.size()>1) {
			if(heap.peek()>=K) break;
			heap.add(heap.poll() + heap.poll()*2);
			answer++;
		}
		if(heap.poll() < K) return -1;
		return answer;
	}
}
