import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            char[][] map = new char[h][w];
            boolean[][] visited = new boolean[h][w]; // 플레이어 이동 체크
            Queue<int[]> fireQueue = new LinkedList<>();
            Queue<int[]> queue = new LinkedList<>();

            int startX = -1, startY = -1;

            for (int j = 0; j < h; j++) {
                String line = br.readLine();
                for (int k = 0; k < w; k++) {
                    map[j][k] = line.charAt(k);
                    if (map[j][k] == '@') {
                        startX = j;
                        startY = k;
                        queue.add(new int[] {startX, startY});
                        visited[startX][startY] = true;
                    } else if (map[j][k] == '*') {
                        fireQueue.add(new int[] {j, k});
                    }
                }
            }

            int second = 0;
            boolean canEscape = false;

            while (!queue.isEmpty()) {
                second++;

                // 1. 불 확산
                int fireSize = fireQueue.size();
                for (int l = 0; l < fireSize; l++) {
                    int[] curFire = fireQueue.poll();
                    int curX = curFire[0], curY = curFire[1];

                    for (int m = 0; m < 4; m++) {
                        int nextX = curX + dx[m];
                        int nextY = curY + dy[m];

                        if (nextX >= 0 && nextX < h && nextY >= 0 && nextY < w
                                && (map[nextX][nextY] == '.' || map[nextX][nextY] == '@')) {
                            map[nextX][nextY] = '*';
                            fireQueue.add(new int[] {nextX, nextY});
                        }
                    }
                }

                // 2. 플레이어 이동
                int size = queue.size();
                for (int j = 0; j < size; j++) {
                    int[] cur = queue.poll();
                    int curX = cur[0], curY = cur[1];

                    for (int l = 0; l < 4; l++) {
                        int nextX = curX + dx[l];
                        int nextY = curY + dy[l];

                        if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) {
                            canEscape = true;
                            break;
                        }

                        if (!visited[nextX][nextY] && map[nextX][nextY] == '.') {
                            visited[nextX][nextY] = true;
                            queue.add(new int[] {nextX, nextY});
                        }
                    }

                    if (canEscape) break;
                }

                if (canEscape) break;
            }

            if (canEscape) {
                System.out.println(second);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
