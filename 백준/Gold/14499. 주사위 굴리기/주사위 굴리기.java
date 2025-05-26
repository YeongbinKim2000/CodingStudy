import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int diceX;
    static int diceY;
    static int topX;
    static int topY;
    static int bottomX;
    static int bottomY;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        diceX = Integer.parseInt(st.nextToken());
        diceY = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        int[][] diceMap = {{-1, 0, 1}, {0, 0, 0}, {-1, 0, -1}, {-1, 0, -1}};
        topX = 1;
        topY = 1;
        bottomX = 3;
        bottomY = 1;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            int command = Integer.parseInt(st.nextToken()) - 1;
            moveDice(command, diceMap);
        }
    }

    private static void moveDice(int command, int[][] diceMap) {
        int nextX = diceX + dx[command];
        int nextY = diceY + dy[command];

        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m)
            return;

        turnDice(command, diceMap);

        diceX = nextX;
        diceY = nextY;

        if (map[diceX][diceY] == 0)
            map[diceX][diceY] = diceMap[bottomX][bottomY];
        else {
            diceMap[bottomX][bottomY] = map[diceX][diceY];
            map[diceX][diceY] = 0;
        }

        System.out.println(diceMap[topX][topY]);
    }

    private static void turnDice(int command, int[][] diceMap) {
        if (command == 0) {
            int temp = diceMap[1][2];
            diceMap[1][2] = diceMap[1][1];
            diceMap[1][1] = diceMap[1][0];
            diceMap[1][0] = diceMap[3][1];
            diceMap[3][1] = temp;
        } else if (command == 1) {
            int temp = diceMap[1][0];
            diceMap[1][0] = diceMap[1][1];
            diceMap[1][1] = diceMap[1][2];
            diceMap[1][2] = diceMap[3][1];
            diceMap[3][1] = temp;
        } else if (command == 2) {
            int temp = diceMap[0][1];
            for (int i = 0; i <= 2; i++) {
                diceMap[i][1] = diceMap[i + 1][1];
            }
            diceMap[3][1] = temp;
        } else {
            int temp = diceMap[3][1];
            for (int i = 3; i >= 1; i--) {
                diceMap[i][1] = diceMap[i - 1][1];
            }
            diceMap[0][1] = temp;
        }
    }
}
