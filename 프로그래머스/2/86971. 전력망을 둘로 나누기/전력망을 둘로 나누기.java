import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int answer;
    public int solution(int n, int[][] wires) {
        answer = Integer.MAX_VALUE;

        ArrayList<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < wires.length; i++) {
            int[] curWire = wires[i];
            int from = curWire[0];
            int to = curWire[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < wires.length; i++) {
            int[] curWire = wires[i];
            int from = curWire[0];
            int to = curWire[1];

            for (int j = 0; j < graph[from].size(); j++) {
                if (graph[from].get(j) == to)
                    graph[from].remove(j);
            }
            for (int j = 0; j < graph[to].size(); j++) {
                if (graph[to].get(j) == from)
                    graph[to].remove(j);
            }
            answer = Math.min(answer, Math.abs(bfs(graph, from) - bfs(graph, to)));
            graph[from].add(to);
            graph[to].add(from);
        }

        return answer;
    }

    static private int bfs(ArrayList<Integer>[] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        int cnt = 1;
        visited[start] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (int i = 0; i < graph[curNode].size(); i++) {
                int nextNode = graph[curNode].get(i);
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.add(nextNode);
                    cnt++;
                }
            }
        }

        return cnt;
    }
}