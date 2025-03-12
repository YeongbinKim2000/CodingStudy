import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[v + 1];
        visited = new boolean[v + 1];
        dist = new int[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }
        
        int start = Integer.parseInt(br.readLine());
        dist[start] = 0;
        
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph[from].add(new Node(to, cost));
        }
        
        dijkstra(start);
        
        for (int i = 1; i <= v; i++) {
            if (dist[i] != Integer.MAX_VALUE)
                System.out.println(dist[i]);
            else
                System.out.println("INF");
        }
    }
    
    private static void dijkstra(int cur) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            public int compare (Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
            
        pq.add(new Node(cur, 0));
        
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (visited[now.v])
                continue;
            
            visited[now.v] = true;
            
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