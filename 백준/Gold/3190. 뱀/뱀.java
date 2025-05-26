import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static boolean[][] appleMap;
    static Snake snake;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        appleMap = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int appleX = Integer.parseInt(st.nextToken()) - 1;
            int appleY = Integer.parseInt(st.nextToken()) - 1;

            appleMap[appleX][appleY] = true;
        }

        boolean[][] snakeMap = new boolean[n][n];
        snakeMap[0][0] = true;
        Queue<int[]> body = new LinkedList<>();
        snake = new Snake(0, 0, 0, 0, 0, 1, snakeMap, body);

        int l = Integer.parseInt(br.readLine());
        HashMap<Integer, Character> commands = new HashMap<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            commands.put(x, c);
        }

        int time = 0;
        while (true) {
            if (!move())
                break;
            time++;
            if (commands.containsKey(time)) {
                char rotate = commands.get(time);
                if (rotate == 'L') {
                    snake.direction--;
                    if (snake.direction < 0)
                        snake.direction = 3;
                } else {
                    snake.direction++;
                    if (snake.direction > 3)
                        snake.direction = 0;
                }
            }
        }

        System.out.println(++time);
    }

    private static boolean move() {
        int nextX = snake.headX + dx[snake.direction];
        int nextY = snake.headY + dy[snake.direction];

        if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || snake.snakeMap[nextX][nextY])
            return false;

        snake.snakeMap[nextX][nextY] = true;
        snake.headX = nextX;
        snake.headY = nextY;
        snake.body.add(new int[] {snake.headX, snake.headY});

        if (appleMap[snake.headX][snake.headY]) {
            appleMap[snake.headX][snake.headY] = false;
            snake.length++;
        } else {
            int[] newTail = snake.body.poll();
            snake.snakeMap[snake.tailX][snake.tailY] = false;
            snake.tailX = newTail[0];
            snake.tailY = newTail[1];
        }

        return true;
    }

    private static class Snake {
        int headX;
        int headY;
        int tailX;
        int tailY;
        int direction;
        int length;
        boolean[][] snakeMap;
        Queue<int[]> body;

        private Snake(int headX, int headY, int tailX, int tailY, int direction, int length, boolean[][] snakeMap, Queue<int[]> body) {
            this.headX = headX;
            this.headY = headY;
            this.tailX = tailX;
            this.tailY = tailY;
            this.direction = direction;
            this.length = length;
            this.snakeMap = snakeMap;
            this.body = body;
        }
    }
}
