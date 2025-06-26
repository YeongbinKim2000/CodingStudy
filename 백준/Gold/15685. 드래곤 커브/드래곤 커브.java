import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        map = new boolean[101][101];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            draw(x, y, d, g);
        }

        int cnt = 0;
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map.length - 1; j++) {
                if (map[i][j]) {
                    if (map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1])
                        cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    private static void draw(int x, int y, int d, int target) {
        int cur = 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(d);
        map[x][y] = true;

        while (cur < target) {
            cur++;
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                int dir = (list.get(i) + 1) % 4;
                list.add(dir);
            }
        }

        for (int i = 0; i < list.size(); i++) {
            int dir = list.get(i);

            if (dir == 0) {
                x++;
            } else if (dir == 1) {
                y--;
            } else if (dir == 2) {
                x--;
            } else {
                y++;
            }
            map[x][y] = true;
        }
    }
}
