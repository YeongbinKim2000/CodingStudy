import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] lists;
    static boolean possible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                int possible = Integer.parseInt(st.nextToken());
                if (possible == 1) {
                    if (!lists[i].contains(j))
                        lists[i].add(j);
                    if (!lists[j].contains(i))
                        lists[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[] route = new int[m];
        for (int i = 0; i < m; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        possible = true;
        for (int i = 0; i < route.length - 1; i++) {
            boolean[] visited = new boolean[n + 1];
            if (!canGo(lists, visited, route[i], route[i + 1])) {
                possible = false;
                break;
            }
        }

//        for (int i = 1; i <= n; i++) {
//            for (int j = 0; j < lists[i].size(); j++) {
//                System.out.print(lists[i].get(j) + " " );
//            }
//            System.out.println();
//        }

        if (possible)
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static boolean canGo(ArrayList<Integer>[] lists, boolean[] visited, int from, int to) {
        visited[from] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(from);
        // System.out.println();
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == to)
                return true;

            // System.out.println(cur);
            for (int i = 0; i < lists[cur].size(); i++) {
                // System.out.println(lists[cur].size());
                if (!visited[lists[cur].get(i)]) {
                    visited[lists[cur].get(i)] = true;
                    queue.add(lists[cur].get(i));
                }
            }
        }

        return false;
    }
}
