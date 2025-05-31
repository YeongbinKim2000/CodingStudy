import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        int taxiY = Integer.parseInt(st.nextToken()) - 1;

        Taxi taxi = new Taxi(taxiX, taxiY, k);

        Passenger[] passengers = new Passenger[m + 1];
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int fromX = Integer.parseInt(st.nextToken()) - 1;
            int fromY = Integer.parseInt(st.nextToken()) - 1;
            int toX = Integer.parseInt(st.nextToken()) - 1;
            int toY = Integer.parseInt(st.nextToken()) - 1;

            int distanceToDestination = -1;
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[n][n];
            queue.add(new int[] {fromX, fromY});
            visited[fromX][fromY] = true;
            boolean isArrived = false;

            while (!queue.isEmpty()) {
                int size = queue.size();
                distanceToDestination++;
                for (int j = 0; j < size; j++) {
                    int[] cur = queue.poll();
                    int curX = cur[0];
                    int curY = cur[1];

                    if (curX == toX && curY == toY) {
                        isArrived = true;
                        break;
                    }

                    for (int l = 0; l < 4; l++) {
                        int nextX = curX + dx[l];
                        int nextY = curY + dy[l];

                        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY] || map[nextX][nextY] == 1)
                            continue;

                        visited[nextX][nextY] = true;
                        queue.add(new int[] {nextX, nextY});
                    }
                }
                if (isArrived)
                    break;
            }

            if (isArrived)
                passengers[i] = new Passenger(i, fromX, fromY, toX, toY, 0, distanceToDestination, false);
            else
                passengers[i] = new Passenger(i, fromX, fromY, toX, toY, 0, -1, false);
        }


        int[][] passengerMap = new int[n][n];
        for (int i = 1; i <= m; i++) {
            passengerMap[passengers[i].fromX][passengers[i].fromY] = i;
        }
        int turn = 0;
        while (turn < m) {
            turn++;
            ArrayList<Passenger> passengerList = new ArrayList<>();
            // estimate the distance to taxi
            int distanceToPassenger = -1;
            if (passengerMap[taxi.posX][taxi.posY] > 0) {
                distanceToPassenger = 0;
                passengerList.add(passengers[passengerMap[taxi.posX][taxi.posY]]);
            } else {
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[n][n];
                queue.add(new int[] {taxi.posX, taxi.posY});
                visited[taxi.posX][taxi.posY] = true;
                boolean isArrived = false;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    distanceToPassenger++;
                    for (int j = 0; j < size; j++) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];

                        for (int l = 0; l < 4; l++) {
                            int nextX = curX + dx[l];
                            int nextY = curY + dy[l];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY] || map[nextX][nextY] == 1)
                                continue;

                            if (passengerMap[nextX][nextY] > 0) {
                                distanceToPassenger++;
                                passengerList.add(passengers[passengerMap[nextX][nextY]]);
                                isArrived = true;
                            }

                            visited[nextX][nextY] = true;
                            queue.add(new int[] {nextX, nextY});
                        }
                    }
                    if (isArrived)
                        break;
                }
            }

            if (passengerList.size() > 1) {
                Collections.sort(passengerList, new Comparator<Passenger>() {
                    @Override
                    public int compare(Passenger o1, Passenger o2) {
                        if (o1.fromX != o2.fromX)
                            return o1.fromX - o2.fromX;
                        else
                            return o1.fromY - o2.fromY;
                    }
                });
            } else if (passengerList.size() == 1) {
            } else
                break;
            passengers[passengerList.get(0).num].distanceToTaxi = distanceToPassenger;
            passengers[passengerList.get(0).num].distanceToTaxi -= (passengerList.size() - 1);
            passengerMap[passengerList.get(0).fromX][passengerList.get(0).fromY] = 0;
            int curUsedFuel = passengerList.get(0).distanceToTaxi + passengerList.get(0).distanceToDestination;
            if (curUsedFuel > taxi.fuel || passengerList.get(0).distanceToDestination == -1)
                break;
            else {
                passengers[passengerList.get(0).num].isDelivered = true;
                taxi.posX = passengerList.get(0).toX;
                taxi.posY = passengerList.get(0).toY;
                taxi.fuel -= curUsedFuel;
                taxi.fuel += (passengerList.get(0).distanceToDestination * 2);
            }
        }

        boolean allMoved = true;
        for (int i = 1; i <= m; i++) {
            if (!passengers[i].isDelivered) {
                allMoved = false;
                break;
            }
        }

        if (allMoved)
            System.out.println(taxi.fuel);
        else
            System.out.println(-1);
    }

    private static class Passenger {
        int num;
        int fromX;
        int fromY;
        int toX;
        int toY;
        int distanceToTaxi;
        int distanceToDestination;
        boolean isDelivered;

        private Passenger(int num, int fromX, int fromY, int toX, int toY, int distanceToTaxi, int distanceToDestination, boolean isDelivered) {
            this.num = num;
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.distanceToTaxi = distanceToTaxi;
            this.distanceToDestination = distanceToDestination;
            this.isDelivered = isDelivered;
        }
    }

    private static class Taxi {
        int posX;
        int posY;
        int fuel;

        private Taxi(int posX, int posY, int fuel) {
            this.posX = posX;
            this.posY = posY;
            this.fuel = fuel;
        }
    }
}
