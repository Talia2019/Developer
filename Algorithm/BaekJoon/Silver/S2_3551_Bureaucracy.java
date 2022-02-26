package jiyoung.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Bureaucracy {

	// S2 3551 Bureaucracy

	// 또 영어문제!!!! 뜻은 관료??

	// 왕이 왕국의 모든 법을 기록하기로 했다
	// 새로운 법이 통과되면 일치하는 기록이 법 아카이브에 추가됨? 머가일치하지
	// 수세기후 변호사가 법에 두 종류만 있다는것을 알아냄
	// - direct : 새로운 표준을 정의?
	// - canceling : 이전법을 취소
	// 만약 그 법을 취소하는 법이 없으면 그 법은 active라 함
	// active상태인 법을 찾아내기

	// 첫번째 줄은 통과된 법안들의 수n
	// 그다음 n개만큼 각 법을 명시? declare : 통과됨, cancel i : i법이 취소됨
	// 법은 1번부터

	// active법 개수
	// 살아있는애들 오름차순출력

	// 각 들어오는 법마다 노드 추가
	// 취소하는경우 상태 바꿔주고 해당 법에 취소하는애 연결해줌 - 연결된애들 다 상태 바꿔줌
	// 어차피 연결된애들은 취소하는애들밖에 없어서 연결된애들 싹 상태바꿔줘도 될듯
	// 이렇게 하면 안됨 한법을 두개가 취소한다거나..하는 예외들 발생

	// 법에 취소시키는 애들을 자식으로 둠
	// 깊이가 짝수면 살아있는법, 아니면 죽음
	public static List<Integer>[] laws;
	public static int[] mem; // 깊이 저장

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		laws = new ArrayList[n + 1];
		mem = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			laws[i] = new ArrayList<>();
			mem[i] = -1;
			st = new StringTokenizer(br.readLine(), " ");
			if ("cancel".equals(st.nextToken())) {
				int num = Integer.parseInt(st.nextToken()); // 취소당하는애
				laws[num].add(i);
			}
		}

		List<Integer> activeList = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			dfs(i);
			if (mem[i] == 0)
				activeList.add(i);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(activeList.size()).append("\n");
		for (Integer integer : activeList) {
			sb.append(integer).append(" ");
		}
		System.out.println(sb);

	}

	public static int dfs(int node) {
		if (mem[node] == -1) {
			mem[node] = 0;
			for (int i = 0; i < laws[node].size(); i++) {
				int cur = laws[node].get(i);
				if (mem[cur] == -1) {
					mem[cur] = dfs(cur);
				}
				if (mem[cur] % 2 == 0) {// 자식 깊이가 짝수면 내깊이는 홀수가됨
					mem[node] = 1;
					break;
				}
			}
		}
		return mem[node];
	}

}
