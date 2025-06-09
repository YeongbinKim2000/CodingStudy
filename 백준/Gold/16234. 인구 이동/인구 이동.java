import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    static int r;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int day = 0;
        while (true) {
            if(!move())
                break;
            day++;
        }

        System.out.println(day);
    }

    private static boolean move() {
        boolean canMove = false;
        boolean[][] checked = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!checked[i][j]) {
                    checked[i][j] = true;
                    int sum = map[i][j];
                    Queue<int[]> union = new LinkedList<>();
                    union.add(new int[] {i, j});
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j});
                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || checked[nextX][nextY])
                                continue;

                            int diff = Math.abs(map[curX][curY] - map[nextX][nextY]);
                            if (diff >= l && diff <= r) {
                                union.add(new int[] {nextX, nextY});
                                checked[nextX][nextY] = true;
                                queue.add(new int[] {nextX, nextY});
                                sum += map[nextX][nextY];
                            }
                        }
                    }

                    if (union.size() > 1) {
                        canMove = true;
                        int divide = sum / union.size();
                        while (!union.isEmpty()) {
                            int[] cur = union.poll();
                            int curX = cur[0];
                            int curY = cur[1];

                            map[curX][curY] = divide;
                        }
                    }
                }
            }
        }

        return canMove;
    }
}
