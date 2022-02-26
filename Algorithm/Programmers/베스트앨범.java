package jiyoung.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BestAlbum {
//	스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 
//	노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.
//	속한 노래가 많이 재생된 장르를 먼저 수록합니다.
//	장르 내에서 많이 재생된 노래를 먼저 수록합니다.
//	장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
//	노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 
//	베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.
//
//	제한사항
//	genres[i]는 고유번호가 i인 노래의 장르입니다.
//	plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
//	genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
//	장르 종류는 100개 미만입니다.
//	장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
//	모든 장르는 재생된 횟수가 다릅니다.
//	["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]

//	해시에 장르-수 로 저장(가장많은걸) 
//	해시를2개
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] g = { "classic", "pop", "classic", "classic", "pop", "pop" };
		int[] p = { 500, 600, 150, 800, 2500, 2500 };
		int[] ans;

		Solution s = new Solution();
		ans = s.solution(g, p);
		for (int i : ans) {
			System.out.println(i);
		}
	}
}

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		int[] answer = new int[200];

		Map<String, Integer> hm1 = new HashMap<>();
		Map<String, Integer> hm2 = new HashMap<>();
		Map<String, Integer> hm3 = new HashMap<>();
		List<String> l = null;

		for (int i = 0; i < genres.length; i++) {
			int cur = hm1.getOrDefault(genres[i], -1);	//cur = 지금 hm1에 들어있는거, i = 새거
			if (cur == -1) { // 들어있던게 없음
				hm1.put(genres[i], i);
			} else if (plays[cur] < plays[i]) { // 새곡이 재생수 큼
				hm1.put(genres[i], i);
				hm2.put(genres[i], cur);
			} else if (plays[cur] == plays[i]) { // 같음
				if (hm2.getOrDefault(genres[i], -1) < plays[cur])	//원래 없었거나 작은거 저장되었을경우	**이조건 없었어서 2,15케이스 통과못함 - 머리로만 하지말고 직접 테케만들어서 돌려보기! 한번에 확인가능**
					hm2.put(genres[i], i);
			} else { // 적음
				cur = hm2.getOrDefault(genres[i], -1);
				if (cur == -1 || plays[cur] < plays[i]) {
					hm2.put(genres[i], i);
				}
			}
			hm3.put(genres[i], hm3.getOrDefault(genres[i], 0) + plays[i]);
		}
//		System.out.println(hm3.toString());
		l = new ArrayList<>(hm1.keySet());
		Collections.sort(l, (o1, o2) -> (hm3.get(o2).compareTo(hm3.get(o1))));

//		System.out.println(l.toString());

		int index = 0;
		for (int i = 0; i < l.size(); i++) {
			String g = l.get(i);
			answer[index++] = hm1.get(g);
			if (hm2.get(g) != null)
				answer[index++] = hm2.get(g);
		}
		return Arrays.copyOfRange(answer, 0, index);
	}
}
