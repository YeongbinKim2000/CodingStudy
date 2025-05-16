import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int curX = Integer.parseInt(st.nextToken());
        int curY = Integer.parseInt(st.nextToken());
        int curDir = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        boolean[][] isCleaned = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        while (true) {
            if (!isCleaned[curX][curY]) {
                isCleaned[curX][curY] = true;
                cnt++;
            }

            boolean notCleaned = false;
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (map[nextX][nextY] == 0 && !isCleaned[nextX][nextY]) {
                    notCleaned = true;
                    break;
                }
            }

            if (notCleaned) {
                curDir--;
                if (curDir < 0)
                    curDir = 3;
                int nextX = curX + dx[curDir];
                int nextY = curY + dy[curDir];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
                    continue;

                if (map[nextX][nextY] == 0 && !isCleaned[nextX][nextY]) {
                    curX = nextX;
                    curY = nextY;
                }
            } else {
                int nextDir = (curDir + 2) % 4;

                int nextX = curX + dx[nextDir];
                int nextY = curY + dy[nextDir];

                if (map[nextX][nextY] == 0) {
                    curX = nextX;
                    curY = nextY;
                } else if (map[nextX][nextY] == 1)
                    break;
            }
        }

        System.out.println(cnt);
    }
}
