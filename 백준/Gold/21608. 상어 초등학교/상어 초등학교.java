import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static Student[] students;
    static int sum;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());

        students = new Student[n * n];
        map = new int[n][n];
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cur = Integer.parseInt(st.nextToken());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int s3 = Integer.parseInt(st.nextToken());
            int s4 = Integer.parseInt(st.nextToken());

            int[] likes = {s1, s2, s3, s4};
            students[i] = new Student(cur, likes);
        }

        position();

        sum = 0;
        calcSum();

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(sum);
    }

    private static void position() {
        for (int idx = 0; idx < n * n; idx++) {
            Student cur = students[idx];
            int bestX = n;
            int bestY = n;
            int touchCntMax = -1;
            int emptyCntMax = -1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0)
                        continue;
                    int touchCnt = 0;
                    int emptyCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextX = i + dx[k];
                        int nextY = j + dy[k];

                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                            continue;

                        if (map[nextX][nextY] == 0)
                            emptyCnt++;

                        for (int l = 0; l < cur.likes.length; l++) {
                            if (cur.likes[l] == map[nextX][nextY])
                                touchCnt++;
                        }
                    }
                    if (touchCntMax < touchCnt) {
                        bestX = i;
                        bestY = j;
                        touchCntMax = touchCnt;
                        emptyCntMax = emptyCnt;
                    } else if (touchCntMax == touchCnt) {
                        if (emptyCnt > emptyCntMax) {
                            bestX = i;
                            bestY = j;
                            emptyCntMax = emptyCnt;
                        }
                    }
                }
            }
            //System.out.println(bestX + " " + bestY);
            map[bestX][bestY] = cur.num;
        }
    }

    private static void calcSum() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int cur = map[i][j];
                int cnt = 0;
                for (int k = 0; k < students.length; k++) {
                    if (students[k].num == cur) {
                        int curX = i;
                        int curY = j;
                        for (int l = 0; l < 4; l++) {
                            int nextX = curX + dx[l];
                            int nextY = curY + dy[l];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n)
                                continue;

                            for (int u = 0; u < students[k].likes.length; u++) {
                                if (map[nextX][nextY] == students[k].likes[u])
                                    cnt++;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 1)
                    sum += 1;
                else if (cnt == 2)
                    sum += 10;
                else if (cnt == 3)
                    sum += 100;
                else if (cnt == 4)
                    sum += 1000;
            }
        }
    }

    private static class Student {
        int num;
        int[] likes;

        private Student(int num, int[] likes) {
            this.num = num;
            this.likes = likes;
        }
    }
}
