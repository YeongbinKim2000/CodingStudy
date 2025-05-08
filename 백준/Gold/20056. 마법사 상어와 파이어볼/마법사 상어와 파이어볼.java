import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.List;

public class Main {
    static int n;
    static List<FireBall> list;
    static Queue<FireBall>[][] arr;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int posX = Integer.parseInt(st.nextToken()) - 1;
            int posY = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            list.add(new FireBall(posX, posY, mass, speed, direction));
        }

        arr = new Queue[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = new LinkedList<>();
            }
        }

        while (k > 0) {
            move();
            divideCombine();
            k--;
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).mass;
        }

        System.out.println(sum);
    }

    static private void move() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).posX = (n + list.get(i).posX + dx[list.get(i).direction] * (list.get(i).speed % n)) % n;
            list.get(i).posY = (n + list.get(i).posY + dy[list.get(i).direction] * (list.get(i).speed % n)) % n;

            arr[list.get(i).posX][list.get(i).posY].add(list.get(i));
        }
    }

    static private void divideCombine() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j].size() >= 2) {
                    int massSum = 0;
                    int speedSum = 0;
                    boolean odd = false;
                    boolean even = false;
                    int num = arr[i][j].size();

                    while (!arr[i][j].isEmpty()) {
                        FireBall cur = arr[i][j].poll();
                        list.remove(cur);
                        if (cur.direction % 2 == 0) {
                            even = true;
                        } else {
                            odd = true;
                        }
                        massSum += cur.mass;
                        speedSum += cur.speed;
                    }

                    int newMass = massSum / 5;
                    int newSpeed = speedSum / num;

                    if (newMass == 0)
                        continue;

                    if (!(even && odd)) {
                        for (int k = 0; k <= 6; k += 2) {
                            list.add(new FireBall(i, j, newMass, newSpeed, k));
                        }
                    } else {
                        for (int k = 1; k <= 7; k += 2) {
                            list.add(new FireBall(i, j, newMass, newSpeed, k));
                        }
                    }
                } else {
                    arr[i][j].clear();
                }
            }
        }
    }

    public static class FireBall {
        int posX, posY, mass, speed, direction;

        public FireBall(int posX, int posY, int mass, int speed, int direction) {
            this.posX = posX;
            this.posY = posY;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }
    }
}
