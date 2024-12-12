import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[] moveX = {0, 1, 0, -1};
    static int[] moveY = {1, 0, -1, 0};
    public int solution(int[][] maps) {
        int answer = -1;
        int startX = 0;
        int startY = 0;
        int targetX = maps.length - 1;
        int targetY = maps[0].length - 1;
        boolean[][] visited = new boolean[maps.length][maps[0].length];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {startX, startY, 1});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curX == targetX && curY == targetY) {
                return curDist;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = curX + moveX[i];
                int nextY = curY + moveY[i];

                if (nextX < 0 || nextX >= maps.length || nextY < 0 || nextY >= maps[0].length)
                    continue;

                if (!visited[nextX][nextY] && maps[nextX][nextY] == 1) {
                    queue.add(new int[]{nextX, nextY, curDist + 1});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return answer;
    }
}