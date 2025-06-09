import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] diceMap = {{0, 2, 0}, {4, 1, 3}, {0, 5, 0}, {0, 6, 0}};
    static int diceX;
    static int diceY;
    static int diceDir;
    static int score;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        diceX = 0;
        diceY = 0;
        diceDir = 0;
        score = 0;

        int turn = 0;
        while (turn < k) {
            turn++;
            play();
        }

        System.out.println(score);
    }

    private static void play() {
        int moveX = diceX + dx[diceDir];
        int moveY = diceY + dy[diceDir];

        if (moveX < 0 || moveX >= n || moveY < 0 || moveY >= m) {
            diceDir = (diceDir + 2) % 4;
            moveX = diceX + dx[diceDir];
            moveY = diceY + dy[diceDir];
        }

        diceMove();
        diceX = moveX;
        diceY = moveY;

        int a = diceMap[3][1];
        int b = map[diceX][diceY];

        if (a > b) {
            diceDir++;
            if (diceDir == 4)
                diceDir = 0;
        } else if (a < b) {
            diceDir--;
            if (diceDir < 0)
                diceDir = 3;
        }

        int curNum = map[diceX][diceY];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {diceX, diceY});
        boolean[][] visited = new boolean[n][m];
        visited[diceX][diceY] = true;
        int c = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || visited[nextX][nextY] || map[nextX][nextY] != curNum)
                    continue;

                visited[nextX][nextY] = true;
                queue.add(new int[] {nextX, nextY});
                c++;
            }
        }

        score += (b * c);
    }

    private static void diceMove() {
        if (diceDir == 0) {
            int temp = diceMap[1][2];
            diceMap[1][2] = diceMap[1][1];
            diceMap[1][1] = diceMap[1][0];
            diceMap[1][0] = diceMap[3][1];
            diceMap[3][1] = temp;
        } else if (diceDir == 1) {
            int temp = diceMap[3][1];
            for (int i = 2; i >= 0; i--) {
                diceMap[i + 1][1] = diceMap[i][1];
            }
            diceMap[0][1] = temp;
        } else if (diceDir == 2) {
            int temp = diceMap[1][0];
            diceMap[1][0] = diceMap[1][1];
            diceMap[1][1] = diceMap[1][2];
            diceMap[1][2] = diceMap[3][1];
            diceMap[3][1] = temp;
        } else {
            int temp = diceMap[0][1];
            for (int i = 1; i <= 3; i++) {
                diceMap[i - 1][1] = diceMap[i][1];
            }
            diceMap[3][1] = temp;
        }
    }
}
