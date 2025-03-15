import java.util.*;
import java.io.*;

public class Main {
    static int[][] table;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int t = 1; t <= tc; t++) {
            table = new int[11][11];
            max = Integer.MIN_VALUE;
            for (int i = 0; i < 11; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 11; j++) {
                    table[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            boolean[] visited = new boolean[11];
            select(0, 0, visited, 0);

            System.out.println(max);
        }
    }

    private static void select(int idx, int cnt, boolean[] visited, int score) {
        if (idx == 11) {
            if (cnt == 11)
                max = Math.max(max, score);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (table[idx][i] > 0 && !visited[i]) {
                visited[i] = true;
                select(idx + 1, cnt + 1, visited, score + table[idx][i]);
                visited[i] = false;
            }
        }
    }
}