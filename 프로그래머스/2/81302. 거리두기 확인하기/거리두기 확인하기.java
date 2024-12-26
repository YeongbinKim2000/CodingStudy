import java.util.LinkedList;
import java.util.Queue;
class Solution {
    static int[] manDx = {0, 0, 1, 2, 1, 1, 0, 0, -1, -1, -2, -1};
    static int[] manDy = {1, 2, 1, 0, 0, -1, -2, -1, -1, 0, 0, 1};

    public static int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < places.length; i++) {
            String[] curString = places[i];
            char[][] curRoom = new char[5][5];
            for (int j = 0; j < curString.length; j++) {
                String curLine = curString[j];
                for (int k = 0; k < curLine.length(); k++) {
                    curRoom[j][k] = curLine.charAt(k);
                }
            }

            int possible = 1;
            boolean[][] peopleChecked = new boolean[5][5];
            for (int m = 0; m < 5; m++) {
                for (int n = 0; n < 5; n++) {
                    if (curRoom[m][n] == 'P') {
                        peopleChecked[m][n] = true;
                        Queue<int[]> queue = new LinkedList<>();
                        queue.add(new int[] {m, n});
                        boolean[][] visited = new boolean[5][5];
                        while (!queue.isEmpty()) {
                            int[] cur = queue.poll();
                            int curX = cur[0];
                            int curY = cur[1];

                            for (int l = 0; l < 12; l++) {
                                int nextX = curX + manDx[l];
                                int nextY = curY + manDy[l];

                                if (nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5
                                        || visited[nextX][nextY] || peopleChecked[nextX][nextY] || curRoom[nextX][nextY] != 'P')
                                    continue;

                                if (possibleCheck(curRoom, curX, curY, nextX, nextY)) {
                                    visited[nextX][nextY] = true;
                                    queue.add(new int[] {nextX, nextY});
                                } else {
                                    possible = 0;
                                    break;
                                }
                            }
                            if (possible == 0)
                                break;
                        }
                        if (possible == 0)
                            break;
                    }
                }
                if (possible == 0)
                    break;
            }
            answer[i] = possible;
        }

        return answer;
    }

    private static boolean possibleCheck(char[][] curRoom, int curX, int curY, int nextX, int nextY) {
        if (Math.abs(curX - nextX) + Math.abs(curY - nextY) == 1)
            return false;

        if (curX == nextX && curY != nextY) {
            int checkPlaceY = (curY + nextY) / 2;
            if (curRoom[curX][checkPlaceY] != 'X')
                return false;
            else
                return true;
        }

        if (curX != nextX && curY == nextY) {
            int checkPlaceX = (curX + nextX) / 2;
            if (curRoom[checkPlaceX][curY] != 'X')
                return false;
            else
                return true;
        }

        if (curRoom[curX][nextY] == 'X' && curRoom[nextX][curY] == 'X')
            return true;

        return false;
    }
}