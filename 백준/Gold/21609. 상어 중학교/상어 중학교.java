import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int score;
    static ArrayList<Group> groups;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        score = 0;
        while (true) {
            groups = new ArrayList<>();
            findGroups();
            if (groups.isEmpty())
                break;
            Collections.sort(groups, new Comparator<Group>() {
                @Override
                public int compare(Group o1, Group o2) {
                    if (o1.size != o2.size)
                        return o2.size - o1.size;
                    else {
                        if (o1.rainbowCnt != o2.rainbowCnt)
                            return o2.rainbowCnt - o1.rainbowCnt;
                        else {
                            if (o1.standardX != o2.standardX)
                                return o2.standardX - o1.standardX;
                            else
                                return o2.standardY - o1.standardY;
                        }
                    }
                }
            });
            removeGroup(groups.get(0));
            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }

    private static void findGroups() {
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    int num = map[i][j];
                    visited[i][j] = true;
                    boolean[][] tempVisited = new boolean[n][n];
                    tempVisited[i][j] = true;
                    int size = 0;
                    int standardX = i;
                    int standardY = j;
                    int rainbowCnt = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j});

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];
                        size++;

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || tempVisited[nextX][nextY]
                                || map[nextX][nextY] == -1 || (map[nextX][nextY] != 0 && map[nextX][nextY] != num))
                                continue;

                            if (map[nextX][nextY] == 0)
                                rainbowCnt++;
                            else
                                visited[nextX][nextY] = true;

                            tempVisited[nextX][nextY] = true;
                            queue.add(new int[] {nextX, nextY});
                        }
                    }

                    if (size >= 2)
                        groups.add(new Group(num, size, rainbowCnt, standardX, standardY));
                }
            }
        }
    }

    private static void removeGroup(Group group) {
        score += (group.size * group.size);
        int startX = group.standardX;
        int startY = group.standardY;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY});
        boolean[][] visited = new boolean[n][n];
        visited[startX][startY] = true;
        map[startX][startY] = -2;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int k = 0; k < 4; k++) {
                int nextX = curX + dx[k];
                int nextY = curY + dy[k];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || visited[nextX][nextY]
                        || map[nextX][nextY] == -1 || (map[nextX][nextY] != 0 && map[nextX][nextY] != group.num))
                    continue;

                map[nextX][nextY] = -2;
                visited[nextX][nextY] = true;
                queue.add(new int[] {nextX, nextY});
            }
        }
    }

    private static void gravity() {
        for (int col = 0; col < n; col++) {
            int curBottom = n;
            for (int row = n - 1; row >= 0; row--) {
                if (map[row][col] == -1)
                    curBottom = row;
                else if (map[row][col] >= 0) {
                    map[--curBottom][col] = map[row][col];
                    if (curBottom != row)
                        map[row][col] = -2;
                }
            }
        }
    }

    private static void rotate() {
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[n - j - 1][i] = map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                map[i][j] = rotated[i][j];
        }
    }

    private static class Group {
        int num;
        int size;
        int rainbowCnt;
        int standardX;
        int standardY;

        private Group(int num, int size, int rainbowCnt, int standardX, int standardY) {
            this.num = num;
            this.size = size;
            this.rainbowCnt = rainbowCnt;
            this.standardX = standardX;
            this.standardY = standardY;
        }
    }
}
