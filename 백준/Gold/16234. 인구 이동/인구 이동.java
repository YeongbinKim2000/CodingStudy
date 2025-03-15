import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static int l;
    static int r;
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
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            if (!move())
                break;
            day++;
        }

        System.out.println(day);
    }

    private static boolean move() {
        boolean possible = false;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    Stack<int[]> stored = new Stack<>();
                    Queue<int[]> unions = new LinkedList<>();
                    int sum = map[i][j];
                    int size = 1;
                    unions.add(new int[] {i, j, map[i][j]});
                    stored.add(new int[] {i, j});
                    visited[i][j] = true;

                    while (!unions.isEmpty()) {
                        int[] cur = unions.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        int curPpl = cur[2];

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY])
                                continue;

                            int nextPpl = map[nextX][nextY];
                            if (Math.abs(curPpl - nextPpl) < l || Math.abs(curPpl - nextPpl) > r)
                                continue;

                            unions.add(new int[] {nextX, nextY, nextPpl});
                            stored.add(new int[] {nextX, nextY});
                            visited[nextX][nextY] = true;
                            size++;
                            sum += nextPpl;
                        }
                    }

                    if (stored.size() > 1)
                        possible = true;

                    while (!stored.isEmpty()) {
                        int[] cur = stored.pop();
                        int curX = cur[0];
                        int curY = cur[1];

                        map[curX][curY] = sum / size;
                    }
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        return possible;
    }
}