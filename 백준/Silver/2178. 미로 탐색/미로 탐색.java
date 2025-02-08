import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {0, 0});
        int targetX = n - 1;
        int targetY = m - 1;
        int length = 0;
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        boolean arrive = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            length++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];

                if (curX == targetX && curY == targetY) {
                    arrive = true;
                    break;
                }

                for (int k = 0; k < 4; k++) {
                    int nextX = curX + dx[k];
                    int nextY = curY + dy[k];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || map[nextX][nextY] == '0' || visited[nextX][nextY])
                        continue;

                    queue.add(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            if (arrive)
                break;
        }

        System.out.println(length);
    }
}
