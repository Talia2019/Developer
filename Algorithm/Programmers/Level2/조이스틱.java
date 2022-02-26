package jiyoung.week5;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class JoyStick {

//	Level2 조이스틱

//	만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.
//	name은 알파벳 대문자로만 이루어져 있습니다.
//	name의 길이는 1 이상 20 이하입니다.

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution("AAA"));
		System.out.println(s.solution("JAZ"));
		System.out.println(s.solution("JEROEN"));
		System.out.println(s.solution("JAN"));
		System.out.println(s.solution("JAIAN"));
		System.out.println(s.solution("JAIIAN"));
		System.out.println(s.solution("AAAAAAAAAAAAKK")); // len 14 12
		System.out.println(s.solution("BBBBAI"));
		System.out.println(s.solution("ABAAAAAAAABB"));
		System.out.println(s.solution("AABAAAAAAAAAAABB"));

	}

	// 한 글자를 만드는법 -> z-글자 > 13 이하의 경우 위

	// ABCDEFGHIJKLMN | OPQRSTUVWXYZ
	// 01234567890123 | 456789012345
	// 					210987654321

	// 값은 A가 아닌것만
	// 오른쪽으로만 이동하기 0 : 마지막 값의 인덱스
	// 왼쪽으로만 이동하기 1 : len - 첫번째 값의 인덱스
	// 오른쪽 이동 후 왼쪽 이동하기 2 : 다음값까지 가는 거리 > 현재인덱스+len-마지막인덱스인 경우가 있을때 얘가 최선이됨
	// 셋중 최선의 선택을 해야함

	// 그리디로 풀진 않은듯?..

	static class Solution {
		public int solution(String name) {
			int answer = 0;
			int len = name.length();

			Deque<Integer> deque = new LinkedList<>();
			List<Integer> notA = new ArrayList<>();

			for (int i = 0; i < len; i++) {
				if (name.charAt(i) != 'A') {
					deque.add(i);
					notA.add(i);
				}
			}

			if (notA.size() == 0) // 바꿀게없음
				return 0;

			//flag 0 : 오른쪽으로 이동하기, 1: 왼쪽으로 이동하기, 2: 오른쪽후 왼쪽이동
			//backPoint는 오른쪽후 왼쪽이동할때 방향전환할 인덱스
			int directionFlag = 0, backPoint = 0;
			for (int i = 1; i < notA.size(); i++) {
				int cur = notA.get(i - 1);
				int next = notA.get(i);
				if (next - cur > cur + len - notA.get(notA.size() - 1)) {
					directionFlag = 2;
					backPoint = cur;
					break;
				}
			}
			if (directionFlag == 0) {
				if (len - notA.get(0) < notA.get(notA.size() - 1)) // 왼쪽으로 도는게 빠름
					directionFlag = 1;
			}

			switch (directionFlag) {
			case 0:
				answer = goRight(name, deque);
				break;
			case 1:
				answer = goLeft(name, deque);
				break;
			case 2:
				answer = goBack(name, deque, backPoint);
				break;
			}

			return answer;
		}

		public int changeWord(char to) {
			if ('Z' - to < 13) { // 커서 아래로
				return 'Z' - to + 1;
			} else { // 커서 위로
				return to - 'A';
			}
		}

		public int goRight(String name, Deque<Integer> deque) {
			int answer = 0;
			int cur, prev = 0;
			while (!deque.isEmpty()) {
				cur = deque.pollFirst();
				answer += ((cur - prev) + changeWord(name.charAt(cur)));
				prev = cur;
			}
			return answer;
		}

		public int goLeft(String name, Deque<Integer> deque) {
			int answer = 0;
			int cur, prev = name.length();
			while (!deque.isEmpty()) {
				cur = deque.pollLast();
				answer += ((prev - cur) + changeWord(name.charAt(cur)));
				prev = cur;
			}
			return answer;
		}

		public int goBack(String name, Deque<Integer> deque, int backPoint) {
			int answer = 0;
			int cur = -1, prev = 0;
			
			//방향전환 전까지 오른쪽으로 가다가
			while (cur != backPoint) {
				cur = deque.pollFirst();
				answer += ((cur - prev) + changeWord(name.charAt(cur)));
				prev = cur;
			}
			answer += cur;
			
			//왼쪽으로감
			prev = name.length();
			while (!deque.isEmpty()) {
				cur = deque.pollLast();
				answer += ((prev - cur) + changeWord(name.charAt(cur)));
				prev = cur;
			}
			return answer;
		}
	}

}
