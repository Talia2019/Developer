import java.util.*;

// 기본 알고리즘

public class algorithm {
    public static int CNT = 4;
    public static boolean[] isSelected;
    public static int[] numbers;

    public static void main(String[] args) {
        isSelected = new boolean[CNT];
        numbers = new int[CNT];

        // 순열
        permutation(0);
        permutationBit(0, 0);

        //중복순열
        permutationOverlap(0);

        //조합
        combination(0, 0);

        //중복조합
        combinationOverlap(0, 0);

        //next permutation
        int[] numberList = {4, 2, 6, 7, 1};
        //순열
        Arrays.sort(numberList);
        do {
            System.out.println(Arrays.toString(numberList));
        } while (nextPermutation(numberList));
        //조합
        int[] selected = {0, 0, 0, 1, 1}; //5C2
        do {
            for (int i = 0; i < 5; i++) {
                if (selected[i] == 1)
                    System.out.print(numberList[i] + " ");
            }
            System.out.println();
        } while (nextPermutation(selected));

        //부분집합
        powerSet();

        //bfs
        bfs(new int[][]{{0, 0}}, new int[]{0, 0});
        //dfs
        dfs(new int[]{0, 0});

        //MST - Prim/Kruskal
        //간선이 적으면 kruskal, 간선이 많으면 prim이 유리함
        //Prim
        prim();

        //Kruskal
        kruskal();

        //최단경로 - Dijkstra, 벨만포드, 플로이드워샬
        //Dijkstra : 양수가중치
        dijkstra();
        //벨만포드 : 음수가중치도 가능
        bellmanFordMain();
        //플로이드워샬
        floydWarshall();

        //이진탐색
        System.out.println(binarySearch());

        //dp knapsack01
        knapsack01();

        //lis
        lis();



    }

    public static void permutation(int cnt) {
        if (cnt == CNT) {
            System.out.println(Arrays.toString(numbers));
            return;
        }
        for (int i = 0; i < CNT; i++) {
            if (isSelected[i])
                continue;
            isSelected[i] = true;
            numbers[cnt] = i + 1;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    private static void permutationOverlap(int cnt) {
        if (cnt == CNT) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = 0; i < CNT; i++) {
            numbers[cnt] = i + 1;
            permutationOverlap(cnt + 1);
        }
    }

    private static void permutationBit(int cnt, int flag) {
        if (cnt == CNT) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = 0; i < CNT; i++) {
            if ((flag & 1 << i) != 0)
                continue;
            numbers[cnt] = i + 1;
            permutationBit(cnt + 1, flag | (1 << i));
        }
    }

    private static void combination(int cnt, int start) {
        if (cnt == CNT - 1) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i <= CNT - 1; i++) {
            numbers[cnt] = i + 1;
            combination(cnt + 1, i + 1);
        }
    }

    private static void combinationOverlap(int cnt, int start) {
        if (cnt == CNT - 1) {
            System.out.println(Arrays.toString(numbers));
            return;
        }

        for (int i = start; i < CNT - 1; i++) {
            numbers[cnt] = i + 1;
            combinationOverlap(cnt + 1, i);
        }
    }

    private static boolean nextPermutation(int[] numbers) {
        int n = numbers.length;
        int i = n - 1;
        //꼭대기 찾기
        while (i > 0 && numbers[i - 1] >= numbers[i]) i--;
        if (i == 0) return false;

        //swap 위치 찾기
        int j = n - 1;
        while (numbers[i - 1] >= numbers[j]) j--;

        //swap
        swap(numbers, i - 1, j);

        //i부터 맨뒤까지 오름차순 (원래 내림차순이었음)
        int k = n - 1;
        while (i < k) {
            swap(numbers, i++, k--);
        }
        return true;
    }

    public static void swap(int[] numbers, int i, int j) {
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;
    }

    public static void powerSet() {
        int[] numbers = {1, 2, 3, 4, 5};
        int n = 4;
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    System.out.print(numbers[j] + " ");
                }
            }
            System.out.println();
        }
    }

    public static boolean[][] isVisited = new boolean[5][5];
    public static int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static int n = 0; // 그래프 크기

    public static void bfs(int[][] graph, int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        isVisited[start[0]][start[1]] = true;
        while (queue.size() > 0) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dir[i][0];
                int ny = cur[1] + dir[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && !isVisited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    isVisited[nx][ny] = true;
                }
            }
        }
    }

    public static void dfs(int[] start) {
        isVisited[start[0]][start[1]] = true;
        for (int i = 0; i < 4; i++) {
            int nx = start[0] + dir[i][0];
            int ny = start[1] + dir[i][1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n && !isVisited[nx][ny])
                dfs(new int[]{nx, ny});
        }
    }

    public static class Edge implements Comparator<Edge> {
        int node;
        int distance;

        public Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compare(Edge o1, Edge o2) {
            return Integer.compare(o1.distance, o2.distance);
        }
    }

    public static void prim() {
        //하나의 정점에서 연결된 간선들중 하나씩 선택하며 MST를 찾는 알고리즘
        //정점중심 -> 인접행렬, 인접리스트

        //1. 임의 정점을 하나 선택해서 시작
        //2. 선택한 정점에 연결된 간선들을 우선순위 큐에 넣음
        //3. poll해서 나온 정점이 방문한 정점이 아니면 선택
        //4. n-1개의 간선이 선택되거나, 모든 정점을 볼 때까지 2~3반복

        //노드를 방문했는지 기록하는 visited배열
        //처음에 선택한 정점의 minEdge는 0
        //한정점에서 다른정점으로 연결할 수 있는 정점들을 확인하고, 해당 가중치 비용이 minEdge에 저장된 값보다 최소라면 저장
        //최소간선에 연결되어 있던 정점을 택해 그를 기준으로 탐색을 반복

        //크루스칼은 정렬에서 시간을 많이 잡아먹음 -> 정점크기에 비해 간선이 많을수록 크루스칼이 불리할 확률이 높음

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        int v = 10; //정점 수
        int e = 10; //간선 수
        boolean[] visited = new boolean[v];
        int answer = 0; //거리 합
        int cnt = 0; //확인한 정점 수

        List<List<Edge>> edgeList = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < e - 1; i++) {
            int a = i;
            int b = i + 1;
            int dis = i; //원래 입력받는 연결간선정보들!!
            edgeList.get(a).add(new Edge(b, dis));
            edgeList.get(b).add(new Edge(a, dis));
        }

        pq.add(new Edge(0, 0)); //시작점

        while (pq.size() > 0) {
            Edge edge = pq.poll();
            if (visited[edge.node]) continue;
            visited[edge.node] = true;
            answer += edge.distance;
            for (Edge next : edgeList.get(edge.node)) {
                if (!visited[next.node])
                    pq.add(next);
            }
            if (++cnt == v) break;
        }
        System.out.println(answer);
    }

    public static class Edge2 implements Comparable<Edge2> {
        int from, to;
        int distance;

        public Edge2(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge2 o) {
            return Integer.compare(this.distance, o.distance);
        }
    }

    public static int[] parents;

    public static void kruskal() {
        //간선을 하나씩 선택해서 MST를 찾는 알고리즘
        //간선을 하나씩 선택하며 트리를 완성
        //간선중심 -> 간선리스트

        //1. 최초 모든 간선을 가중치에 따라 오름차순으로 정렬
        //2. 가중치가 낮은 간선부터 선택하며 트리를 증가시킴
        //3. n-1개의 간선이 선택될때까지 2번 반복

        //모든 노드들이 떨어진 상태 (make set) 상태에서 시작
        //각 간선마다 union을 시도하는데, 사이클이 발생할 경우 union안함함
        int v = 10;
        int e = 10;
        int answer = 0; //거리 합
        int cnt = 0; //확인한 정점 수
        //pq로 해도되고 아니면 그냥 array에 넣어서 정렬시킨후 해도됨
        PriorityQueue<Edge2> pq = new PriorityQueue<Edge2>();

        makeSet(v);
        for (int i = 0; i < e - 1; i++) {
            pq.add(new Edge2(i, i + 1, i)); //원래 정점 입력받아서 넣는거
        }

        while (pq.size() > 0) {
            Edge2 cur = pq.poll();
            if (union(cur.from, cur.to)) {
                answer += cur.distance;
                if (++cnt == v) break;
            }
        }

        System.out.println(answer);
    }

    public static void makeSet(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if (parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB)
            return false;
        parents[b] = a;
        return true;
    }

    public static void dijkstra() {
        //양의 간선들을 가져야함
        int e = 10;
        int v = 10;
        List<List<Edge>> edgeList = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i < v; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < e - 1; i++) {
            edgeList.get(i).add(new Edge(i + 1, i));
        }

        int[] distance = new int[v];
        Arrays.fill(distance, Integer.MAX_VALUE);

        int startNode = 0;
        distance[startNode] = 0;
        pq.add(new Edge(startNode, distance[startNode]));

        while (pq.size() > 0) {
            Edge cur = pq.poll();

            if (distance[cur.node] < cur.distance) continue;
            for (Edge next : edgeList.get(cur.node)) {
                if ((distance[next.node] > distance[cur.node] + next.distance)) {
                    distance[next.node] = distance[cur.node] + next.distance;
                    pq.add(new Edge(next.node, distance[next.node]));
                }
            }
        }
        System.out.println(Arrays.toString(distance));
    }

    public static void bellmanFordMain() {
        int v = 10;
        int e = 10;
        List<Edge2> edgeList = new ArrayList<>();
        int[] distance = new int[v + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < e; i++) {
            edgeList.add(new Edge2(i, i + 1, i));
        }

        if (bellmanFord(0, v, e, distance, edgeList))
            System.out.println("-1");
        else {
            for (int i = 1; i < v; i++) {
                if (distance[i] == Integer.MAX_VALUE)
                    System.out.println("-1");
                else
                    System.out.println(distance[i]);
            }
        }

    }

    public static boolean bellmanFord(int start, int v, int e, int[] distance, List<Edge2> edgeList) {
        distance[start] = 0;
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < e; j++) {
                Edge2 cur = edgeList.get(j);
                if (distance[cur.from] == Integer.MAX_VALUE) continue;

                if (distance[cur.to] > distance[cur.from] + cur.distance) {
                    distance[cur.to] = distance[cur.from] + cur.distance;
                    if (i == v - 1)
                        return true;
                }
            }
        }
        return false;
    }

    public static void floydWarshall() {
        int N = 10;
        int[][] distances = new int[10][10];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    continue;
                }
                distances[i][j] = 1000000;
            }
        }
        //입력받는 과정
//        for (int i = 0; i < M; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            int start = Integer.parseInt(st.nextToken());
//            int end = Integer.parseInt(st.nextToken());
//            int time = Integer.parseInt(st.nextToken());
//            distances[start][end] = Math.min(distances[start][end], time);
//        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }
    }

    public static int binarySearch() {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int key = 8;
        int start = 0;
        int end = 10;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] == key)
                return mid;
            if (numbers[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    public static void knapsack01() {
        int n = 5; //물건 수
        int w = 20; //최대 무게
        int[] profits = {0, 10, 3, 4, 2, 5};
        int[] weights = {0, 5, 5, 5, 5, 5};
        //한칸비우는건 안고르는경우 고려

        //dp 배열 전체크기 만들기
        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int wi = 1; wi <= w; wi++) {
                if (weights[i] <= wi)
                    dp[i][wi] = Math.max(dp[i - 1][wi], profits[i] + dp[i - 1][wi - weights[i]]);
                else
                    dp[i][wi] = dp[i - 1][wi];
            }
        }

        //dp 배열 한행만 만들기
        //가치만 따지는것은 가능, 어떤물건이 포함됐는지 확인불가
        int[] DP = new int[w + 1];
        for (int i = 1; i <= n; i++) {
            for (int wi = w; wi >= weights[i]; wi--) {
                //뒤부터 자신까지만
                DP[wi] = Math.max(DP[wi], profits[i] + DP[wi - weights[i]]);
            }
        }

        //dp[n][w], DP[n][w] 가 답!
    }

    public static void lis() {
        int n = 10;
        int[] arr = new int[n];
        int[] lis = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        //ver 1 (n^2)
        int max = 0; //전체중 최장길이
        for (int i = 0; i < n; i++) {
            lis[i] = 1;

            for (int j = 0; j < i; j++) { // j: i의 앞쪽원소
                if (arr[j] < arr[i] && lis[i] < lis[j] + 1)
                    lis[i] = lis[j] + 1;
            } // i를 끝으로 하는 최장길이 값 계산 완료
            if (max < lis[i])
                max = lis[i];
        }
        System.out.println(max);

        //ver 2 (nlogn)
        //LIS[i] : i길이를 LIS로 하는 가장 작은 값(끝값)
        int lastIdx = 0;
        lis[0] = arr[0];
        // binarysearch : 해당 key를 찾으면 그 위치를 리턴하고, 그렇지 않으면 -insertion point-1를 리턴
        // -1까지 해주는이유는 0과 -0을 구분할 수 없기때문에
        for (int i = 0; i < n; i++) {
            if (arr[i] > lis[lastIdx])
                lis[++lastIdx] = arr[i];
            else {
                int idx = Arrays.binarySearch(lis, 0, lastIdx + 1, arr[i]);
                if (idx < 0)
                    idx = idx * -1 - 1;
                lis[idx] = arr[i];
            }
        }
    }
}