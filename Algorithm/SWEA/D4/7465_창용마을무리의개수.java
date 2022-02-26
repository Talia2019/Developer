import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution_SWEA_7465_창용마을무리의개수_D4 {

//	D4 7465 창용 마을 무리의 개수

//	서로소집합 사용해보기
//	다 union한다음에 모든 원소들을 돌면서 셋에 저장 -> 셋의 원소개수 출력

	static int[] parents;
	static Set<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(st.nextToken());

		int n, m;
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			parents = new int[n + 1];
			make();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			set.clear();
			for (int i = 1; i <= n; i++) {
				set.add(find(i));
			}
			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}
		System.out.println(sb);
	}

	public static void make() {
		for (int i = 1; i < parents.length; i++) {
			parents[i] = i;
		}
	}

	public static int find(int n) { // 대표자 찾음
		if (parents[n] == n)
			return n;
		return parents[n] = find(parents[n]);
	}

	public static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if (rootA == rootB)
			return;
		parents[rootB] = rootA;
		return;
	}
}
