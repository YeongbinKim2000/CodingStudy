import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static int[] scores;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        graph = new ArrayList[n + 1];
        scores = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1)
                break;

            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            search(i);
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (scores[i] < min) {
                min = scores[i];
                cnt = 1;
            } else if (scores[i] == min)
                cnt++;
        }

        System.out.println(min + " " + cnt);
        for (int i = 1; i <= n; i++) {
            if (scores[i] == min)
                System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void search(int from) {
        boolean[] visited = new boolean[n + 1];
        int[] relations = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        int relation = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            relation++;
            for (int j = 0; j < size; j++) {
                int cur = queue.poll();

                for (int i = 0; i < graph[cur].size(); i++) {
                    int next = graph[cur].get(i);
                    if (!visited[next] && next != from) {
                        visited[next] = true;
                        relations[next] = relation;
                        queue.add(next);
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            if (i == from)
                continue;
            if (max < relations[i])
                max = relations[i];
        }

        scores[from] = max;
    }
}