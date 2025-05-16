import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int n;
    static int[][] map;
    static Queue<int[]> clouds;
    static boolean[][] disappeared;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new LinkedList<>();
        clouds.add(new int[] {n - 1, 0});
        clouds.add(new int[] {n - 1, 1});
        clouds.add(new int[] {n - 2, 0});
        clouds.add(new int[] {n - 2, 1});
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            disappeared = new boolean[n][n];
            moveRain(d, s);
            waterCopy();

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (!disappeared[j][k] && map[j][k] >= 2) {
                        map[j][k] -= 2;
                        clouds.add(new int[] {j, k});
                    }
                }
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0)
                    sum += map[i][j];
            }
        }

        System.out.println(sum);
    }

    private static void moveRain(int dir, int dist) {
        int size = clouds.size();
        dist %= n;
        for (int i = 0; i < size; i++) {
            int[] cur = clouds.poll();
            int curX = cur[0];
            int curY = cur[1];

            int nextX = curX + dist * dx[dir];
            if (nextX < 0)
                nextX += n;
            else if (nextX >= n)
                nextX -= n;
            int nextY = curY + dist * dy[dir];
            if (nextY < 0)
                nextY += n;
            else if (nextY >= n)
                nextY -= n;

            map[nextX][nextY]++;
            disappeared[nextX][nextY] = true;
            clouds.add(new int[] {nextX, nextY});
        }
    }

    private static void waterCopy() {
        int size = clouds.size();
        for (int i = 0; i < size; i++) {
            int cnt = 0;
            int[] cur = clouds.poll();
            int curX = cur[0];
            int curY = cur[1];
            for (int j = 2; j <= 8; j += 2) {
                int nextX = curX + dx[j];
                int nextY = curY + dy[j];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                    continue;

                if (map[nextX][nextY] > 0)
                    cnt++;
            }
            map[curX][curY] += cnt;
        }
    }
}
