import java.util.*;
class Solution {
    public static int solution(int n, int[][] edge) {
        ArrayList<Integer>[] list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for (int i = 0; i < edge.length; i++) {
            int a = edge[i][0];
            int b = edge[i][1];
            list[a].add(b);
            list[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        boolean[] visited = new boolean[n + 1];
        int[] distances = new int[n + 1];

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int i = 0; i < list[cur].size(); i++) {
                int next = list[cur].get(i);

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    distances[next] = (distances[cur] + 1);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 2; i <= n; i++) {
            if (distances[i] > max)
                max = distances[i];
        }

        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (distances[i] == max)
                cnt++;
        }

        return cnt;
    }
}