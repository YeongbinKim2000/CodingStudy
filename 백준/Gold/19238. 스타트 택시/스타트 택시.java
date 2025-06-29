import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int fuel;
    static int taxiX;
    static int taxiY;
    static Passenger[] passengers;
    static int[][] fromMap;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxiY = Integer.parseInt(st.nextToken()) - 1;

        passengers = new Passenger[m + 1];
        fromMap = new int[n][n];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fromX = Integer.parseInt(st.nextToken()) - 1;
            int fromY = Integer.parseInt(st.nextToken()) - 1;
            int toX = Integer.parseInt(st.nextToken()) - 1;
            int toY = Integer.parseInt(st.nextToken()) - 1;

            fromMap[fromX][fromY] = i;
            passengers[i] = new Passenger(i, fromX, fromY, toX, toY, false);
        }

        boolean possible = true;
        int turn = 0;
        while (turn < m) {
            turn++;

            // 1. 태울 승객 찾기
            if (fromMap[taxiX][taxiY] == 0) {
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[n][n];
                queue.add(new int[]{taxiX, taxiY});
                visited[taxiX][taxiY] = true;
                ArrayList<int[]> candidates = new ArrayList<>();
                int distance = 0;

                boolean found = false;
                while (!queue.isEmpty() && !found) {
                    int size = queue.size();
                    distance++;
                    for (int j = 0; j < size; j++) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];

                        for (int i = 0; i < 4; i++) {
                            int nextX = curX + dx[i];
                            int nextY = curY + dy[i];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n ||
                                    map[nextX][nextY] == 1 || visited[nextX][nextY])
                                continue;

                            if (fromMap[nextX][nextY] > 0 && !passengers[fromMap[nextX][nextY]].isDelivered) {
                                candidates.add(new int[]{nextX, nextY, distance});
                                found = true;
                            }

                            visited[nextX][nextY] = true;
                            queue.add(new int[]{nextX, nextY});
                        }
                    }
                }

                if (candidates.isEmpty()) {
                    possible = false;
                    break;
                }

                candidates.sort((o1, o2) -> {
                    if (o1[0] != o2[0]) return o1[0] - o2[0];
                    return o1[1] - o2[1];
                });

                int d = candidates.get(0)[2];
                fuel -= d;
                if (fuel < 0) {
                    possible = false;
                    break;
                }

                taxiX = candidates.get(0)[0];
                taxiY = candidates.get(0)[1];
            }

            int passengerNum = fromMap[taxiX][taxiY];
            Passenger p = passengers[passengerNum];

            // 2. 목적지까지 이동
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            queue.add(new int[]{taxiX, taxiY});
            visited[taxiX][taxiY] = true;
            boolean canMove = false;
            int targetX = -1;
            int targetY = -1;
            int distance = 0;

            while (!queue.isEmpty() && !canMove) {
                int size = queue.size();
                distance++;
                for (int i = 0; i < size; i++) {
                    int[] cur = queue.poll();
                    int curX = cur[0];
                    int curY = cur[1];

                    for (int j = 0; j < 4; j++) {
                        int nextX = curX + dx[j];
                        int nextY = curY + dy[j];

                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n ||
                                map[nextX][nextY] == 1 || visited[nextX][nextY])
                            continue;

                        if (nextX == p.toX && nextY == p.toY) {
                            canMove = true;
                            targetX = nextX;
                            targetY = nextY;
                            break;
                        }

                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                    }

                    if (canMove) break;
                }
            }

            if (!canMove || fuel < distance) {
                possible = false;
                break;
            }

            fuel -= distance;            
            fuel += distance * 2;          
            p.isDelivered = true;
            fromMap[taxiX][taxiY] = 0;  
            taxiX = targetX;
            taxiY = targetY;
        }

        System.out.println(possible ? fuel : -1);
    }

    private static class Passenger {
        int num;
        int fromX;
        int fromY;
        int toX;
        int toY;
        boolean isDelivered;

        Passenger(int num, int fromX, int fromY, int toX, int toY, boolean isDelivered) {
            this.num = num;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.isDelivered = isDelivered;
        }
    }
}
