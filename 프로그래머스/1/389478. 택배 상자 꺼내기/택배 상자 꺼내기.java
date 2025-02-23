class Solution {
    static int[] dx = {0, 1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    public static int solution(int n, int w, int num) {
        int answer = 0;

        int divide = n / w;
        if (n % w != 0)
            divide++;

        int[][] map = new int[divide][w];

        int direction = 0;
        int cur = 1;
        int curX = 0;
        int curY = 0;
        int targetX = -1;
        int targetY = -1;
        while (cur <= n) {
            map[curX][curY] = cur;
            if (cur == num) {
                targetX = curX;
                targetY = curY;
            }
            cur++;
            if (w == 1)
                direction = 1;

            int nextX = curX + dx[direction];
            int nextY = curY + dy[direction];
            if (w == 1) {
                direction = 1;
            } else {
                if (nextX < 0 || nextX >= map.length || nextY < 0 || nextY >= map[0].length || direction == 1 || direction == 3) {
                    direction++;
                    if (direction >= 4)
                        direction = 0;

                    nextX = curX + dx[direction];
                    nextY = curY + dy[direction];
                }
            }

            curX = nextX;
            curY = nextY;
        }

        curX = targetX + 1;
        while (curX < map.length && map[curX][targetY] != 0) {
            answer++;
            curX++;
        }

        return answer + 1;
    }
}