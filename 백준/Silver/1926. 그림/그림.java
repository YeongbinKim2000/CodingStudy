import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        ArrayList<Integer> pictures = new ArrayList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    int cnt = 1;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[] {i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        int curX = cur[0];
                        int curY = cur[1];

                        for (int k = 0; k < 4; k++) {
                            int nextX = curX + dx[k];
                            int nextY = curY + dy[k];

                            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || map[nextX][nextY] == 0 || visited[nextX][nextY])
                                continue;

                            queue.add(new int[]{nextX, nextY});
                            visited[nextX][nextY] = true;
                            cnt++;
                        }
                    }

                    pictures.add(cnt);
                }
            }
        }

        Collections.sort(pictures);

        System.out.println(pictures.size());

        if (pictures.isEmpty())
            System.out.println(0);
        else
            System.out.println(pictures.get(pictures.size() - 1));
    }
}
