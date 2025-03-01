import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] di = {0, 0, 0, 0, 1, -1};
    static int[] dj = {0, 1, 0, -1, 0, 0};
    static int[] dk = {1, 0, -1, 0, 0, 0};
    static char[][][] map;
    static int l;
    static int r;
    static int c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (l == 0 && r == 0 && c == 0)
                break;

            map = new char[l][r][c];
            int startI = 0;
            int startJ = 0;
            int startK = 0;
            int endI = 0;
            int endJ = 0;
            int endK = 0;
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < r; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < c; k++) {
                        char cur = line.charAt(k);
                        if (cur == 'S') {
                            startI = i;
                            startJ = j;
                            startK = k;
                        } else if (cur == 'E') {
                            endI = i;
                            endJ = j;
                            endK = k;
                        }
                        map[i][j][k] = cur;
                    }
                }
                br.readLine();
            }

            bfs(startI, startJ, startK, endI, endJ, endK);
        }
    }

    private static void bfs(int startI, int startJ, int startK, int endI, int endJ, int endK) {
        boolean[][][] visited = new boolean[l][r][c];
        int time = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startI, startJ, startK});

        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int curI = cur[0];
                int curJ = cur[1];
                int curK = cur[2];

                if (curI == endI && curJ == endJ && curK == endK) {
                    time--;
                    System.out.println("Escaped in " + time + " minute(s).");
                    return;
                }

                for (int j = 0; j < 6; j++) {
                    int nextI = curI + di[j];
                    int nextJ = curJ + dj[j];
                    int nextK = curK + dk[j];

                    if (nextI < 0 || nextI >= l || nextJ < 0 || nextJ >= r || nextK < 0 || nextK >= c || visited[nextI][nextJ][nextK] || map[nextI][nextJ][nextK] == '#')
                        continue;

                    visited[nextI][nextJ][nextK] = true;
                    queue.add(new int[] {nextI, nextJ, nextK});
                }
            }
        }

        System.out.println("Trapped!");
        return;
    }
}
