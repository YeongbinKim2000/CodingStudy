import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] rectangleMap;
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        rectangleMap = new int[102][102];
        for (int i = 0; i < rectangle.length; i++) {
            int[] curRect = rectangle[i];
            int curRectLeftUpY = curRect[0] * 2;
            int curRectLeftUpX = curRect[1] * 2;
            int curRectRightDownY = curRect[2] * 2;
            int curRectRightDownX = curRect[3] * 2;

            // 가로
            for (int j = curRectLeftUpY; j <= curRectRightDownY; j++) {
                if (rectangleMap[curRectLeftUpX][j] != 3)
                    rectangleMap[curRectLeftUpX][j] = 1;
                if (rectangleMap[curRectRightDownX][j] != 3)
                    rectangleMap[curRectRightDownX][j] = 1;
            }
            // 세로
            for (int j = curRectLeftUpX; j <= curRectRightDownX; j++) {
                if (rectangleMap[j][curRectLeftUpY] != 3)
                    rectangleMap[j][curRectLeftUpY] = 1;
                if (rectangleMap[j][curRectRightDownY] != 3)
                    rectangleMap[j][curRectRightDownY] = 1;
            }

            // 내부
            for (int j = curRectLeftUpX + 1; j < curRectRightDownX; j++) {
                for (int k = curRectLeftUpY + 1; k < curRectRightDownY; k++) {
                    rectangleMap[j][k] =  3;
                }
            }
        }

//        for (int i = 0; i < 51; i++) {
//            for (int j = 0; j < 51; j++) {
//                System.out.print(rectangleMap[i][j] + " ");
//            }
//            System.out.println();
//        }

        boolean[][] visited = new boolean[102][102];
        int fromX = characterY * 2;
        int fromY = characterX * 2;
        int toX = itemY * 2;
        int toY = itemX * 2;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {fromX, fromY, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int curDist = cur[2];

            if (curX == toX && curY == toY)
                return curDist / 2;

            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];

                if (nextX < 0 || nextX >= 102 || nextY < 0 || nextY >= 102)
                    continue;

                if (rectangleMap[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    queue.add(new int[] {nextX, nextY, curDist + 1});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return answer;
    }
}