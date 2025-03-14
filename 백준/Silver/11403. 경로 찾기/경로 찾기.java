import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[][] map;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int cur = Integer.parseInt(st.nextToken());
                if (j == i)
                    continue;
                if (cur == 1) {
                    graph[i].add(j);
                }
            }
        }

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                track(i, j);
            }
        }


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void track(int from, int to) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    map[from][next] = 1;
                }
            }
        }
    }
}