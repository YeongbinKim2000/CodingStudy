import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int cnt;
    static boolean[] counted;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        counted = new boolean[n + 1];

        ArrayList<Integer>[] light = new ArrayList[n + 1];
        ArrayList<Integer>[] heavy = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            light[i] = new ArrayList<>();
            heavy[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            light[b].add(a);
            heavy[a].add(b);
        }

        cnt = 0;
        int limit = (n + 1) / 2;
        for (int i = 1; i <= n; i++) {
            dfs(light, i, limit);
            dfs(heavy, i, limit);
        }

        System.out.println(cnt);
    }

    private static void dfs(ArrayList<Integer>[] graph, int start, int limit) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        boolean[] visited = new boolean[n + 1];
        visited[start] = true;
        int ct = 0;

        while (!stack.isEmpty()) {
            ct++;
            int cur = stack.pop();

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);

                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }

        if (!counted[start] && ct - 1 >= limit) {
            cnt++;
            counted[start] = true;
        }
    }
}