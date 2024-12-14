import java.util.ArrayList;
class Solution {
    static boolean[] visited;
    static ArrayList<Integer>[] lists;
    public int solution(int n, int[][] computers) {
        int answer = 0;

        lists = new ArrayList[n + 1];
        for (int i = 1; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 0; i < computers.length - 1; i++) {
            for (int j = i + 1; j < computers.length; j++) {
                if (computers[i][j] == 1) {
                    lists[i + 1].add(j + 1);
                    lists[j + 1].add(i + 1);
                }
            }
        }

        visited = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;

            visited[i] = true;
            answer++;
            dfs(i);
        }

        return answer;
    }

    private static void dfs(int idx) {
        if (lists[idx].isEmpty())
            return;

        for (int i = 0; i < lists[idx].size(); i++) {
            if (!visited[lists[idx].get(i)]) {
                visited[lists[idx].get(i)] = true;
                dfs(lists[idx].get(i));
            }
        }
    }
}