import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static int m;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max = Integer.MIN_VALUE;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                findMax(i, j, 0, 0);
                findTet1(i, j);
                findTet2(i, j);
                findTet3(i, j);
                findTet4(i, j);
            }
        }

        System.out.println(max);
    }

    private static void findMax(int curX, int curY, int cnt, int sum) {
        int curCnt = cnt + 1;
        int curSum = sum + map[curX][curY];

        if (curCnt == 4) {
            max = Math.max(max, curSum);
            return;
        }

        if (!visited[curX][curY]) {
            visited[curX][curY] = true;
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextX][nextY])
                    continue;

                findMax(nextX, nextY, curCnt, curSum);
            }
            visited[curX][curY] = false;
        }
    }

    private static void findTet1(int curX, int curY) {
        boolean possible = true;
        int curSum = map[curX][curY];

        for (int i = 1; i < 4; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                possible = false;
                break;
            }

            curSum += map[nextX][nextY];
        }

        if (possible)
            max = Math.max(max, curSum);
    }

    private static void findTet2(int curX, int curY) {
        boolean possible = true;
        int curSum = map[curX][curY];

        for (int i = 0; i < 4; i++) {
            if (i == 1)
                continue;

            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                possible = false;
                break;
            }

            curSum += map[nextX][nextY];
        }

        if (possible)
            max = Math.max(max, curSum);
    }

    private static void findTet3(int curX, int curY) {
        boolean possible = true;
        int curSum = map[curX][curY];

        for (int i = 0; i < 4; i++) {
            if (i == 2)
                continue;

            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                possible = false;
                break;
            }

            curSum += map[nextX][nextY];
        }

        if (possible)
            max = Math.max(max, curSum);
    }

    private static void findTet4(int curX, int curY) {
        boolean possible = true;
        int curSum = map[curX][curY];

        for (int i = 0; i < 3; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];

            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                possible = false;
                break;
            }

            curSum += map[nextX][nextY];
        }

        if (possible) {
            max = Math.max(max, curSum);
        }
    }
}
