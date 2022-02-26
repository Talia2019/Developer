package jiyoung.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FastWay {

//	S1 1446 지름길

//	모든 지름길은 일방통행이고, 고속도로를 역주행할 수는 없다.
//	세준이가 운전해야 하는 거리의 최솟값을 출력하시오.

//	첫째 줄에 지름길의 개수 N과 고속도로의 길이 D가 주어진다. N은 12 이하이고, D는 10,000보다 작거나 같은 자연수이다. 
//	둘째 줄부터 N개의 줄에 지름길의 시작 위치, 도착 위치, 지름길의 길이가 주어진다. 모든 위치와 길이는 10,000보다 작거나 같은 음이 아닌 정수이다. 지름길의 시작 위치는 도착 위치보다 작다.

	
	
	
	
	
	
//	풀이
//	길이 1차원이라 그래프로 어떻게 풀어야 할지모르겠어요 ㅠ

//	지름길들을 시작위치 기준 정렬
//	현재 길의 도착위치보다 뒤에 시작위치를 가진 애들부터 탐색 -> 해당 시작위치가 탐색하는애들중 가장 작은 도착위치보다 뒤에있다면 탈출
//	지름길 노드들을 이어서 갈 수 있는 노드들에 연결

	static class Road implements Comparable<Road> {
		int start;
		int end;
		int distance;

		public Road(int start, int end, int distance) {
			this.start = start;
			this.end = end;
			this.distance = distance;
		}

		@Override
		public int compareTo(Road o) {
			return Integer.compare(this.start, o.start);
		}
	}

	public static Road[] roads;
	public static int n, d, realN;
	public static int min;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		roads = new Road[n];
		min = d;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			if (e - s <= d) // 진짜 지름길인 경우에만 저장
				continue;
			roads[realN++] = new Road(s, e, d);
		}

		roads = Arrays.copyOf(roads, realN);
		Arrays.sort(roads);
		findMin(0, 0, 0);
		System.out.println(min);

	}

	// 이동거리 : cur.start-start + cur.distance
	static void findMin(int idx, int start, int distance) {
		int minEnd = d;

		Road cur;
		for (int i = idx; i < realN; i++) {
			cur = roads[i];
			if (cur.start < start) // 내 시작점보다 뒤에있는 지름길
				continue;
			if (cur.end < minEnd)
				minEnd = cur.end;
			if (cur.start > minEnd) // 내 시작점이 앞서있던 마지막 종료지점보다 크면 탈출 (지름길을 건너는게 무조건 빠르니까)
				return;
			findMin(i + 1, cur.end, distance + cur.start - start + cur.distance);
		}

		if (start < d) // 도착못함
			distance += (d - start);
		if (start > d) // 역주행불가능
			return;
		if (min > distance)
			min = distance;
	}

}
