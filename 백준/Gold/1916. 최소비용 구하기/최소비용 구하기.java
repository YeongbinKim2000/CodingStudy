import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int m;
    static ArrayList<Node1>[] graph;
    static int[] dist;
    static boolean[] visited;
    static int end;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node1(b, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(dist[end]);
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node1> pq = new PriorityQueue<>(new Comparator<Node1>(){
            public int compare(Node1 o1, Node1 o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.add(new Node1(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node1 curNode = pq.poll();
            int curValue = curNode.v;

            if (!visited[curValue])
                visited[curValue] = true;
            else
                continue;

            for (int i = 0; i < graph[curValue].size(); i++) {
                Node1 nextNode = graph[curValue].get(i);

                if (!visited[nextNode.v] && dist[nextNode.v] > dist[curNode.v] + nextNode.cost) {
                    dist[nextNode.v] = curNode.cost + nextNode.cost;
                    pq.add(new Node1(nextNode.v, dist[nextNode.v]));
                }
            }
        }
    }

    private static class Node1 {
        int v;
        int cost;

        private Node1 (int v, int cost) {
            this.v = v;
            this.cost = cost;
        }
    }
}

