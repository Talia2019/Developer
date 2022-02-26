package jiyoung.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TwoPoint {

	// P3 2261 가장가까운두점

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static Comparator<Point> Xcomp = new Comparator<Point>() {

		@Override
		public int compare(Point o1, Point o2) {
			return Integer.compare(o1.x, o2.x);
		}

	};

	static Comparator<Point> Ycomp = new Comparator<Point>() {

		@Override
		public int compare(Point o1, Point o2) {
			return Integer.compare(o1.y, o2.y);
		}

	};

	public static Point[] points;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		points = new Point[n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(points, Xcomp);

		System.out.println(half(0, n - 1));
	}

	public static int dist(Point p1, Point p2) {
		return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
	}

	public static int brute(int start, int end) {
		int min = Integer.MAX_VALUE;
		for (int i = start; i < end; i++) {
			Point p = points[i];
			for (int j = i + 1; j <= end; j++) {
				min = Math.min(min, dist(p, points[j]));
			}
		}
		return min;
	}

	// start, end, center : index
	// left, right, middle : value
	public static int half(int start, int end) {

		// 3개 이하면 브루트포스로 계산
		if (end - start < 3) {
			return brute(start, end);
		}

		int center = (start + end) / 2;
		int left, right, middle;
		left = half(start, center);
		right = half(center + 1, end);

		int min = left < right ? left : right;

		middle = middle(start, center, end, min);
		return middle < min ? middle : min;
	}

	public static int middle(int start, int center, int end, int min) {
		int xDist;

		List<Point> list = new ArrayList<>();

		int midX = points[center].x;
		for (int i = start; i <= end; i++) {
			xDist = points[i].x - midX;
			if (xDist * xDist < min) {
				list.add(points[i]);
			}
		}

		Collections.sort(list, Ycomp);

		int yDist;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = i + 1; j < list.size(); j++) {
				yDist = list.get(i).y - list.get(j).y;
				if (yDist * yDist < min) {
					min = Math.min(dist(list.get(i), list.get(j)), min);
				} else
					break;
			}
		}

		return min;
	}

	public static int lineSwipping(int n) {
		int min;
		// 후보군들 넣을 set. y축기준 정렬됨
		TreeSet<Point> set = new TreeSet<>(Ycomp);

		min = dist(points[0], points[1]);
		set.add(points[0]);
		set.add(points[1]);

		int leftIdx = 0;
		for (int i = 2; i < n; i++) {
			Point standard = points[i];

			// x좌표에 대해 min보다 크면 후보군 제외, 다음거 확인
			while (leftIdx < i) {
				Point target = points[leftIdx];
				int xDist = standard.x - target.x;
				if (xDist * xDist > min) {
					set.remove(target);
					leftIdx++;
				} else
					break;
			}

			// p[(-100000, 기준점 - root(minDist)) : 100000, 기준점 + root(minDist)] 사이에 있는
			// 원소들에 대해 subTree를 얻고, 해당 원소들에 대해 기준점과의 거리를 측정
			int sqrtMin = (int) Math.sqrt(min) + 1;
			TreeSet<Point> ySub = (TreeSet<Point>) set.subSet(new Point(-100000, standard.y - sqrtMin),
					new Point(-100000, standard.y + sqrtMin));
			for (Point point : ySub) {
				min = Math.min(min, dist(standard, point));
			}

			set.add(standard);

		}

		return min;
	}

	// 점과 점거리,, 단순히 nC2로하면 10,000,000,000 시간초과일듯
  // 답안 참고!!

	// ver 1 분할정복
	// 점두개일때까지 분할
	// 분할선을 기준으로 최소거리만큼에서 다시 탐색

	// ver 2 라인스위핑
	// TreeSet 이용 (중복X, 정렬)
	// 순서대로 탐색하는데, 기준점에 대해 x좌표의 후보군중 x좌표와 기준점의 x좌표 거리가 min보다 크면
	// 해당 후보군 제외, 그다음 y좌표에 대해 min보다 적은 후보군들에 대해 기준점과 거리 측정
	// 이 과정이 끝나면 기준점을 후보군으로 넣고 위 과정을 반복

}
