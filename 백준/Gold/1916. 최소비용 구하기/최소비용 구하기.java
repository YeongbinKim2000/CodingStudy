import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] dist;
    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);
    }

    private static void dijkstra(int cur) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.add(new Node(cur, 0));
        dist[cur] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.v])
                visited[now.v] = true;
            else
                continue;

            for (int i = 0; i < graph[now.v].size(); i++) {
                Node next = graph[now.v].get(i);

                if (!visited[next.v] && dist[next.v] > now.cost + next.cost) {
                    dist[next.v] = now.cost + next.cost;
                    pq.add(new Node(next.v, dist[next.v]));
                }
            }
        }
    }

    private static class Node {
        int v;
        int cost;

        private Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}