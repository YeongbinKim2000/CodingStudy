import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    static int n;
    static int[][] map;
    static int length;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        map = new int[(int)Math.pow(2, n)][(int)Math.pow(2, n)];
        length = map.length;
        for (int i = 0; i < length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < q; i++) {
            int l = Integer.parseInt(st.nextToken());
            int subLength = (int)Math.pow(2, l);

            divide(subLength);
            checkIce();;
        }

        int sum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] > 0)
                    sum += map[i][j];
            }
        }

        int max = 0;
        boolean[][] visited = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int cnt = 1;
                    visited[i][j] = true;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];

                            if (nextX < 0 || nextX >= length || nextY < 0 || nextY >= length
                                    || visited[nextX][nextY] || map[nextX][nextY] == 0)
                                continue;

                            cnt++;
                            visited[nextX][nextY] = true;
                            queue.add(new int[] {nextX, nextY});
                        }
                    }
                    max = Math.max(max, cnt);
                }
            }
        }

        System.out.println(sum);
        System.out.println(max);
    }

    private static void divide(int subLength) {
        for (int i = 0; i < length; i += subLength) {
            for (int j = 0; j < length; j += subLength) {
                rotate(i, j, subLength);
            }
        }
    }

    private static void rotate(int fromX, int fromY, int subLength) {
        int[][] temp = new int[subLength][subLength];
        for (int i = fromX, x = 0; x < subLength; i++, x++) {
            for (int j = fromY, y = 0; y < subLength; j++, y++) {
                temp[x][y] = map[i][j];
            }
        }

        int[][] rotated = new int[subLength][subLength];
        for (int i = 0; i < subLength; i++) {
            for (int j = 0; j < subLength; j++) {
                rotated[j][subLength - i - 1] = temp[i][j];
            }
        }

        for (int i = fromX, x = 0; x < subLength; i++, x++) {
            for (int j = fromY, y = 0; y < subLength; j++, y++) {
                map[i][j] = rotated[x][y];
            }
        }
    }

    private static void checkIce() {
        boolean[][] checked = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (map[i][j] > 0) {
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if (nextX < 0 || nextX >= length || nextY < 0 || nextY >= length)
                            continue;

                        if (map[nextX][nextY] > 0)
                            cnt++;
                    }

                    if (cnt < 3)
                        checked[i][j] = true;
                }
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (checked[i][j])
                    map[i][j]--;
            }
        }
    }
}
