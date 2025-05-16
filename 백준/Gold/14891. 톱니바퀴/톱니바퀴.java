import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] wheels;
    static int[] tops;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        wheels = new char[5][8];
        for (int i = 1; i <= 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                wheels[i][j] = line.charAt(j);
            }
        }
        tops = new int[5];

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            start(num, dir);
        }

        int score = 0;
        for (int i = 1; i <= 4; i++) {
            if (i == 1 && wheels[i][tops[i]] == '1')
                score += 1;
            else if (i == 2 && wheels[i][tops[i]] == '1')
                score += 2;
            else if (i == 3 && wheels[i][tops[i]] == '1')
                score += 4;
            else if (i == 4 && wheels[i][tops[i]] == '1')
                score += 8;
        }

        System.out.println(score);
    }

    private static void start(int num, int dir) {
        if (checkLeft(num))
            moveLeft(num - 1, dir * -1);

        if (checkRight(num))
            moveRight(num + 1, dir * -1);

        if (dir == -1) {
            tops[num]++;
            if (tops[num] >= 8)
                tops[num] -= 8;
        } else {
            tops[num]--;
            if (tops[num] < 0)
                tops[num] += 8;
        }
    }

    private static boolean checkLeft(int num) {
        if (num == 1)
            return false;
        else {
            int curLeft = tops[num] - 2;
            if (curLeft < 0)
                curLeft += 8;
            int nextRight = tops[num - 1] + 2;
            if (nextRight >= 8)
                nextRight -= 8;

            if (wheels[num][curLeft] == wheels[num - 1][nextRight])
                return false;
            else
                return true;
        }
    }

    private static void moveLeft(int num, int dir) {
        if (checkLeft(num))
            moveLeft(num - 1, dir * -1);

        if (dir == -1) {
            tops[num]++;
            if (tops[num] >= 8)
                tops[num] -= 8;
        } else {
            tops[num]--;
            if (tops[num] < 0)
                tops[num] += 8;
        }
    }

    private static boolean checkRight(int num) {
        if (num == 4)
            return false;
        else {
            int curRight = tops[num] + 2;
            if (curRight >= 8)
                curRight -= 8;
            int nextLeft = tops[num + 1] - 2;
            if (nextLeft < 0)
                nextLeft += 8;

            if (wheels[num][curRight] == wheels[num + 1][nextLeft])
                return false;
            else
                return true;
        }
    }

    private static void moveRight(int num, int dir) {
        if (checkRight(num))
            moveRight(num + 1, dir * -1);

        if (dir == -1) {
            tops[num]++;
            if (tops[num] >= 8)
                tops[num] -= 8;
        } else {
            tops[num]--;
            if (tops[num] < 0)
                tops[num] += 8;
        }
    }
}
