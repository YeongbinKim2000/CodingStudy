import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static int answer;
    static char[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    public static int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();
        answer = n * m;

        map = new char[n][m];
        for (int i = 0; i < storage.length; i++) {
            String cur = storage[i];
            for (int j = 0; j < cur.length(); j++) {
                map[i][j] = cur.charAt(j);
            }
        }

        for (int i = 0; i < requests.length; i++) {
            String cur = requests[i];
            if (cur.length() == 1) {
                lift(cur.charAt(0));
            } else {
                crane(cur.charAt(0));
            }

//            for (int j = 0; j < map.length; j++) {
//                for (int k = 0; k < map[0].length; k++) {
//                    System.out.print(map[j][k] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        return answer;
    }

    private static void crane(char target) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == target) {
                    map[i][j] = ' ';
                    answer--;
                }
            }
        }
    }

    private static void lift(char target) {
        List<int[]> remove = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] == target && possible(i, j) && bfs(i, j))
                    remove.add(new int[] {i, j});
            }
        }

        for (int i = 0; i < remove.size(); i++) {
            int x = remove.get(i)[0];
            int y = remove.get(i)[1];
            map[x][y] = ' ';
            answer--;
        }
    }

    private static boolean possible(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length)
                return true;

            if (map[nextX][nextY] == ' ')
                return true;
        }
        return false;
    }

    private static boolean bfs(int x, int y) {
        boolean[][] visited = new boolean[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {x, y});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length)
                    return true;

                if (visited[nextX][nextY] || map[nextX][nextY] != ' ')
                    continue;

                visited[nextX][nextY] = true;
                queue.add(new int[] {nextX, nextY});
            }
        }

        return false;
    }
}