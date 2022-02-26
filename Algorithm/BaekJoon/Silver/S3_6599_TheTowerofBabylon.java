package jiyoung.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Babylon {

	// S3 6599 The Tower of Babylon
	// 영어문제!!!!!!

	// n 종류의 블럭이 있고, 각 타입별로 무한공급
	// 각 i타입 블럭은 직사각형고체, 선형차원 xi yi zi 차원에있음
	// 블럭은 방향을 다시 잡을수 있음 -> 3차원중 2개는 차원의 기초고 다른건 높이..?..밑면인듯싶다..
	// 블럭으로 쌓을수 있는 가장 높은 탑
	// 탑을 짓는데 한블럭은 다른블럭 위에 있어야함 (위 블럭의 두 기초차원은 아래블럭보다 작아야함)
	// 예를들어, 같은 사이즈의 기초를 둔 방향을 가진 블럭을 쌓을순없음..?....
	// 지을수있는 최대높이..

	// 첫줄 n - 블럭 타입 개수
	// n개 줄만큼 3차원 주어짐
	// n=0일때 종료

	// 출력 Case 1: maximum height =

	// 밑면이 큰 순서대로..? 쌓는게 맞으려나?
	// 5 4 높이 1인게 있고 1 4 높이 4인게 있으면 전자가 밑면이크지만 후자를 쌓는게 더 높아짐
	// 음...그러면...
	// 각 블럭들을 큐에 넣고 쌓는경우 안쌓는경우 고려해서 재귀로 풀어보면 될듯??

	// 스택오버플로우인가??... 메모리초과남 ㅠㅠ 흠다르게 풀어보면..메모이제이션 써보기
	// 밑면크기에 따라 쌓을수있는 최대 높이를 저장해둠.. 한블럭당 최대 3종류로 쌓을수있긴한데
	// 얘네가 겹치는 경우가 있을 수 있으니 그냥 해시맵에? 어차피 n최대 30
	// 해시맵에 해보려했는데 해시코드 잘모르겠어서 그냥 겹치는거 고려안하고 배열에 저장했음!!

	public static Queue<Block> q;
	public static int[] mem;

	static class Block {
		int index;
		int x, y, z;

		public Block(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		q = new LinkedList<>();
		final int INF = Integer.MAX_VALUE;

		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;

			q.clear();
			mem = new int[n * 3];

			int x, y, z;
			int cnt = 0;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());

				q.add(new Block(cnt++, x, y, z));
				q.add(new Block(cnt++, y, z, x));
				q.add(new Block(cnt++, x, z, y));
			}

			mem[0] = build(0, INF, INF, 0, q);
			sb.append("Case ").append(tc++).append(": maximum height = ").append(mem[0]).append("\n");
		}

		System.out.println(sb);
	}

	public static int build(int index, int baseX, int baseY, int h, Queue<Block> q) {
		if (mem[index] == 0) {
			int size = q.size();
			int max = 0;
			for (int i = 0; i < size; i++) {
				Block b = q.poll();
				if ((b.x >= baseX || b.y >= baseY) && (b.y >= baseX || b.x >= baseY)) {
					q.add(b);
					continue;
				}
				if (mem[b.index] == 0) {
					int value = build(b.index, b.x, b.y, b.z, q);
					mem[b.index] = value;
				}
				q.add(b);
				if (mem[b.index] > max)
					max = mem[b.index];
			}

			mem[index] = max;
		}

		return mem[index] + h;
	}

}
