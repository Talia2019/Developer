package jiyoung.week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ComonGrand {

	// G4 3584 가장가까운공통조상

	// union find의 find 써서 해봐도될듯?
	// find에서 부모를 내바로 위만 저장
	// 한쪽노드부터 루트까지 부모를 찾아서 저장
	// 다른한쪽노트도 부모까지 찾아가면서 겹치는게 있으면 걔가 답

	public static int n;
	public static int[] parents;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			make();
			for (int i = 1; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				parents[c] = p;
			}

			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			boolean[] parentList = new boolean[n + 1];
			while (parents[a] != a) { // 같으면 루트임
				parentList[a] = true;
				a = parents[a];
			}

			while (parents[b] != b) {
				b = parents[b];
				if (parentList[b]) {
					sb.append(b).append("\n");
					break;
				}
			}

			if (parents[b] == b)
				sb.append(b).append("\n");
		}
		System.out.println(sb);
	}

	public static void make() {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	// 어차피 바로찾는거라 함수도 필요없을듯
//	public static int find(int a) {
//		return parents[a];
//	}

}

//두 노드의 가장 가까운 공통 조상은, 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은(즉 두 노드에 가장 가까운) 노드를 말합니다
//입력은 A가 B의 부모라는 뜻
//테스트 케이스의 마지막 줄에 가장 가까운 공통 조상을 구할 두 노드가 주어집니다.
