import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_S1_9205_맥주마시면서걸어가기 {

	// S1 9205 맥주 마시면서 걸어가기

	// 모든 노드(좌표, 집/편의점/페스티벌)들을 방문하며 해당 노드가 페스티벌인지 확인
	// 단, 이동할 수 있는 노드는 거리가 50이하인곳

	static class Node {
		int x;
		int y;
		int type; // 0: 집, 1: 편의점, 2: 페스티벌

		public Node(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		int n;
		boolean[] visited;
		Node[] nodeList;
		Queue<Node> q = new LinkedList<>();

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());

			visited = new boolean[n + 2];
			nodeList = new Node[n + 2];
		
			for (int i = 0; i < n + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				nodeList[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
			}
			nodeList[n+1].type = 2;
			q.offer(nodeList[0]);
			visited[0] = true;
			
			Node node;
			boolean isHappy = false;
			while(q.size()>0) {
				node = q.poll();
				if(node.type==2) {
					isHappy = true;
					break;
				}
				for (int i = 1; i < n+2; i++) {
					if(!visited[i] && (Math.abs(node.x-nodeList[i].x)+Math.abs(node.y-nodeList[i].y))<=1000) { //갈수있는곳
						q.offer(nodeList[i]);
						visited[i] = true;
					}	
				}
			}
			if(isHappy) sb.append("happy").append("\n");
			else sb.append("sad").append("\n");

		}
		System.out.println(sb);
	}

}

//입력
//첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)
//각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).
//다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)
//송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)
//출력
//각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다. 
