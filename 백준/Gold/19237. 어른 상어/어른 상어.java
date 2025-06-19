import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static BigShark[] sharks;
    static ArrayList<Integer>[][] sharkMap;
    static int[][] smellMap;
    static int[][] timeMap;
    static int time;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        sharkMap = new ArrayList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sharkMap[i][j] = new ArrayList<>();
            }
        }
        sharks = new BigShark[m + 1];
        smellMap = new int[n][n];
        timeMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                int curNum = Integer.parseInt(st.nextToken());
                if (curNum != 0) {
                    BigShark curShark = new BigShark(curNum, i, j, 0, true, new int[4][4]);
                    sharks[curNum] = curShark;
                    sharkMap[i][j].add(curNum);
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= m; i++) {
            sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int i = 1; i <= m; i++) {
            int[][] priorityDir = new int[4][4];
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                priorityDir[j][0] = Integer.parseInt(st.nextToken()) - 1;
                priorityDir[j][1] = Integer.parseInt(st.nextToken()) - 1;
                priorityDir[j][2] = Integer.parseInt(st.nextToken()) - 1;
                priorityDir[j][3] = Integer.parseInt(st.nextToken()) - 1;
            }
            sharks[i].priorityDir = priorityDir;
        }

        time = 0;
        while (true) {
            if (time > 1000) {
                time = -1;
                break;
            }
            lessTime();
            makeSmell();
            chooseDir();
            move();
            if (only1Alive())
                break;
            time++;
        }

        System.out.println(time);
    }

    private static void lessTime() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (timeMap[i][j] > 0) {
                    timeMap[i][j]--;
                    if (timeMap[i][j] == 0) {
                        smellMap[i][j] = 0;
                    }
                }

                if (sharkMap[i][j].size() > 1) {
                    Collections.sort(sharkMap[i][j]);
                    int remainNum = sharkMap[i][j].get(0);
                    for (int k = 1; k < sharkMap[i][j].size(); k++) {
                        sharks[sharkMap[i][j].get(k)].isAlive = false;
                    }
                    sharkMap[i][j].clear();
                    sharkMap[i][j].add(remainNum);
                }
            }
        }
    }

    private static void makeSmell() {
        for (int i = 1; i <= m; i++) {
            if (sharks[i].isAlive) {
                smellMap[sharks[i].posX][sharks[i].posY] = i;
                timeMap[sharks[i].posX][sharks[i].posY] = k;
            }
        }
    }

    private static void chooseDir() {
        for (int i = 1; i <= m; i++) {
            if (sharks[i].isAlive) {
                int[] curPriority = sharks[i].priorityDir[sharks[i].dir];
                boolean noSmellMove = false;
                for (int j = 0; j < 4; j++) {
                    int nextX = sharks[i].posX + dx[curPriority[j]];
                    int nextY = sharks[i].posY + dy[curPriority[j]];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                        continue;

                    if (smellMap[nextX][nextY] == 0) {
                        noSmellMove = true;
                        sharks[i].dir = curPriority[j];
                        break;
                    }
                }

                if (!noSmellMove) {
                    for (int j = 0; j < 4; j++) {
                        int nextX = sharks[i].posX + dx[curPriority[j]];
                        int nextY = sharks[i].posY + dy[curPriority[j]];

                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                            continue;

                        if (smellMap[nextX][nextY] == i) {
                            sharks[i].dir = curPriority[j];
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void move() {
        for (int i = 1; i <= m; i++) {
            if (sharks[i].isAlive) {
                for (int j = 0; j < sharkMap[sharks[i].posX][sharks[i].posY].size(); j++) {
                    if (sharkMap[sharks[i].posX][sharks[i].posY].get(j) == i)
                        sharkMap[sharks[i].posX][sharks[i].posY].remove(j);
                }
                sharks[i].posX = sharks[i].posX + dx[sharks[i].dir];
                sharks[i].posY = sharks[i].posY + dy[sharks[i].dir];
                sharkMap[sharks[i].posX][sharks[i].posY].add(i);
            }
        }
    }

    private static boolean only1Alive() {
        if (!sharks[1].isAlive)
            return false;
        else {
            for (int i = 2; i <= m; i++) {
                if (sharks[i].isAlive)
                    return false;
            }
        }

        return true;
    }

    private static class BigShark {
        int sharkNum;
        int posX;
        int posY;
        int dir;
        boolean isAlive;
        int[][] priorityDir;

        private BigShark(int sharkNum, int posX, int posY, int dir, boolean isAlive, int[][] priorityDir) {
            this.sharkNum = sharkNum;
            this.posX = posX;
            this.posY = posY;
            this.dir = dir;
            this.isAlive = isAlive;
            this.priorityDir = priorityDir;
        }
    }
}
