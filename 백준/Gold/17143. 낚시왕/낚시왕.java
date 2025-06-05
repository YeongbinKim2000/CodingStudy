import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Shark>[][] sharkMap = new ArrayList[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                sharkMap[i][j] = new ArrayList<>();
        }
        Shark[] sharks = new Shark[m + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int posX = Integer.parseInt(st.nextToken()) - 1;
            int posY = Integer.parseInt(st.nextToken()) - 1;
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (dir == 1)
                dir = 0;
            else if (dir == 2)
                dir = 2;
            else if (dir == 3)
                dir = 1;
            else
                dir = 3;
            if (dir == 0 || dir == 2)
                speed %= (r - 1) * 2;
            else
                speed %= (c - 1) * 2;
            int size = Integer.parseInt(st.nextToken());

            sharks[i] = new Shark(posX, posY, speed, dir, size, true);
            sharkMap[posX][posY].add(sharks[i]);
        }

        int col = -1;
        int sum = 0;
        while (col < c - 1) {
            col++;
            // fishing
            for (int i = 0; i < r; i++) {
                if (!sharkMap[i][col].isEmpty()) {
                    sharkMap[i][col].get(0).isAlive = false;
                    sum += sharkMap[i][col].get(0).size;
                    sharkMap[i][col].remove(0);
                    break;
                }
            }

            // Shark moves
            for (int i = 1; i <= m; i++) {
                if (sharks[i].isAlive) {
                    int curX = sharks[i].posX;
                    int curY = sharks[i].posY;
                    sharkMap[curX][curY].remove(0);

                    for (int j = 0; j < sharks[i].speed; j++) {
                        int nextX = curX + dx[sharks[i].dir];
                        int nextY = curY + dy[sharks[i].dir];

                        if (nextX < 0 || nextX >= r || nextY < 0 || nextY >= c) {
                            sharks[i].dir  = (sharks[i].dir + 2) % 4;
                            nextX = curX + dx[sharks[i].dir];
                            nextY = curY + dy[sharks[i].dir];
                        }

                        curX = nextX;
                        curY = nextY;
                    }

                    sharks[i].posX = curX;
                    sharks[i].posY = curY;
                    sharkMap[curX][curY].add(sharks[i]);
                }
            }

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (sharkMap[i][j].size() > 1) {
                        Collections.sort(sharkMap[i][j], new Comparator<Shark>() {
                            @Override
                            public int compare(Shark o1, Shark o2) {
                                return o2.size - o1.size;
                            }
                        });

                        for (int k = sharkMap[i][j].size() - 1; k > 0; k--) {
                            sharkMap[i][j].get(k).isAlive = false;
                            sharkMap[i][j].remove(k);
                        }
                    }
                }
            }
        }

        System.out.println(sum);
    }

    private static class Shark {
        int posX;
        int posY;
        int speed;
        int dir;
        int size;
        boolean isAlive;

        private Shark(int posX, int posY, int speed, int dir, int size, boolean isAlive) {
            this.posX = posX;
            this.posY = posY;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.isAlive = isAlive;
        }
    }
}
