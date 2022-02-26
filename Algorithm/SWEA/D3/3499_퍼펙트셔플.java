package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_SWEA_3499_퍼펙트셔플_D3_정지영 {

	// 절반까지 큐1에 넣고 뒤에는 큐2에 넣음
	// 각큐에서 한개씩빼서 저장
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		LinkedList<String> queF = new LinkedList<>();
		LinkedList<String> queB = new LinkedList<>();

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < (n + 1) / 2; i++) { // 홀수일때 앞에큐에 더많이넣으려고
				queF.add(st.nextToken());
			}
			for (int i = (n + 1) / 2; i < n; i++) {
				queB.add(st.nextToken());
			}
			sb.append("#").append(tc).append(" ");
			while (!queB.isEmpty()) {
				sb.append(queF.poll()).append(" ");
				sb.append(queB.poll()).append(" ");
			}
			if (!queF.isEmpty())
				sb.append(queF.poll());
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
