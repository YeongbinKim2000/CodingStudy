import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0}; // Up, Right, Down, Left
    static int[] dy = {0, 1, 0, -1};
    static int time = 0;
    static int direction = 1; // Initially facing Right (index 1)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine()); // Board size
        int k = Integer.parseInt(br.readLine()); // Number of apples

        // Initialize the board
        int[][] map = new int[n][n];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int appleX = Integer.parseInt(st.nextToken()) - 1;
            int appleY = Integer.parseInt(st.nextToken()) - 1;
            map[appleX][appleY] = 1; // Place an apple
        }

        // Read direction changes
        HashMap<Integer, String> directionChanges = new HashMap<>();
        int l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            directionChanges.put(t, dir);
        }

        // Initialize snake
        Deque<int[]> snake = new LinkedList<>();
        snake.add(new int[]{0, 0}); // Snake starts at (0, 0)

        // Simulation
        while (true) {
            time++;
            int[] head = snake.peekFirst();
            int nextX = head[0] + dx[direction];
            int nextY = head[1] + dy[direction];

            // Check for collisions (wall or body)
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || collidesWithBody(nextX, nextY, snake)) {
                break;
            }

            // Move the snake's head
            snake.addFirst(new int[]{nextX, nextY});

            // Check if the snake eats an apple
            if (map[nextX][nextY] == 1) {
                map[nextX][nextY] = 0; // Remove the apple
            } else {
                // Move the tail if no apple is eaten
                snake.pollLast();
            }

            // Handle direction change
            if (directionChanges.containsKey(time)) {
                changeDirection(directionChanges.get(time));
            }
        }

        System.out.println(time);
    }

    // Check if the snake's head collides with its body
    private static boolean collidesWithBody(int x, int y, Deque<int[]> snake) {
        for (int[] segment : snake) {
            if (segment[0] == x && segment[1] == y) {
                return true;
            }
        }
        return false;
    }

    // Change the snake's direction
    private static void changeDirection(String dir) {
        if (dir.equals("L")) {
            direction = (direction + 3) % 4; // Turn left
        } else if (dir.equals("D")) {
            direction = (direction + 1) % 4; // Turn right
        }
    }
}
