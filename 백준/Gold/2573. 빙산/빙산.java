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
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < map[0].length; j++) {
                int cur = Integer.parseInt(st.nextToken());
                map[i][j] = cur;
                if (cur != 0)
                    queue.add(new int[] {i, j});
            }
        }

        int time = 0;
        boolean possible = true;
        while (true) {
            time++;
            int size = queue.size();
            int[][] tempMap = new int[n][m];
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curX = cur[0];
                int curY = cur[1];
                int cnt = 0;
                for (int j = 0; j < 4; j++) {
                    int nextX = curX + dx[j];
                    int nextY = curY + dy[j];

                    if (map[nextX][nextY] == 0)
                        cnt--;
                }
                tempMap[curX][curY] = cnt;
                if (map[curX][curY] + tempMap[curX][curY] > 0)
                    queue.add(new int[] {curX, curY});
            }

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    map[i][j] += tempMap[i][j];
                    if (map[i][j] < 0)
                        map[i][j] = 0;
                }
            }

//            for (int i = 0; i < map.length; i++) {
//                for (int j = 0; j < map[0].length; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            if (queue.size() > 0) {
                int[] start = queue.peek();
                int startX = start[0];
                int startY = start[1];

                Queue<int[]> q = new LinkedList<>();
                q.add(new int[] {startX, startY});
                int cnt = 0;
                boolean[][] visited = new boolean[n][m];
                visited[startX][startY] = true;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int curX = cur[0];
                    int curY = cur[1];
                    cnt++;

                    for (int j = 0; j < 4; j++) {
                        int nextX = curX + dx[j];
                        int nextY = curY + dy[j];

                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextX][nextY] || map[nextX][nextY] == 0)
                            continue;

                        visited[nextX][nextY] = true;
                        q.add(new int[] {nextX, nextY});
                    }
                }

                if (cnt != queue.size()) {
                    break;
                }
            } else {
                possible = false;
                break;
            }
        }

        if (possible)
            System.out.println(time);
        else
            System.out.println(0);
    }
}
