class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];
        int[][] puddleMap = new int[n][m];

        for (int i = 0; i < puddles.length; i++) {
            int puddleY = puddles[i][0] - 1;
            int puddleX = puddles[i][1] - 1;

            puddleMap[puddleX][puddleY] = 1;
        }

        // 가로
        for (int i = 1; i < map[0].length; i++) {
            if (puddleMap[0][i] != 1)
                map[0][i] = 1;
            else
                break;
        }

        // 세로
        for (int i = 1; i < map.length; i++) {
            if (puddleMap[i][0] != 1)
                map[i][0] = 1;
            else
                break;
        }

        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                if (puddleMap[i][j] != 1)
                    map[i][j] = map[i - 1][j] + map[i][j - 1];

                if (map[i][j] > 1000000007)
                    map[i][j] %= 1000000007;
            }
        }

        return map[n - 1][m - 1];
    }
}