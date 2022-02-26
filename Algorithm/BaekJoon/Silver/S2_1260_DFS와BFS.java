import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

//	S2 1260 DFS와BFS
	
//	그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 
//	단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 
//	정점 번호는 1번부터 N번까지이다.
	
//	첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 
//	다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 
//	입력으로 주어지는 간선은 양방향이다.
//	첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
	
	static boolean[][] graph;
	static boolean[] isVisited;
	static StringBuilder bfsSb, dfsSb;
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		bfsSb = new StringBuilder();
		dfsSb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		
		graph = new boolean[n+1][n+1];
		isVisited = new boolean[n+1];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from][to] = true;
			graph[to][from] = true;
		}
		
		dfs(v);
		Arrays.fill(isVisited, false);
		bfs(v);
		
		System.out.println(dfsSb);
		System.out.println(bfsSb);
	}
	
	public static void dfs(int node) {
		isVisited[node] = true;
		dfsSb.append(node).append(" ");
		for (int i = 0; i < graph.length; i++) {
			if(graph[node][i] && !isVisited[i]) {
				dfs(i);
			}
		}
	}

	public static void bfs(int startNode) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(startNode);
		isVisited[startNode] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			bfsSb.append(cur).append(" ");
			for (int i = 0; i < graph.length; i++) {
				if(graph[cur][i] && !isVisited[i]) {
					isVisited[i] = true;
					queue.offer(i);
				}
			}
		}
	}

}
