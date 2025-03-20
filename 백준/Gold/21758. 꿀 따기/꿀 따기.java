import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] map;
    static long max;
    static long[] sumArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++)
            map[i] = Integer.parseInt(st.nextToken());

        max = 0;
        sumArr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            sumArr[i] = sumArr[i - 1] + map[i];
        }

        leftHouse();
        middleHouse();
        rightHouse();

        System.out.println(max);
    }

    private static void leftHouse() {
        int houseIdx = 1;
        int rightBee = n;
        for (int i = 2; i <= n - 1; i++) {
            int leftBee = i;
            long rightSum = sumArr[rightBee - 1] - sumArr[houseIdx - 1] - map[leftBee];
            long leftSum = sumArr[leftBee - 1] - sumArr[houseIdx - 1];
            long sum = rightSum + leftSum;
            max = Math.max(max, sum);
        }
    }

    private static void middleHouse() {
        int leftBee = 1;
        int rightBee = n;
        for (int i = 2; i <= n - 1; i++) {
            int houseIdx = i;
            long rightSum = sumArr[rightBee - 1] - sumArr[houseIdx - 1];
            long leftSum = sumArr[houseIdx] - sumArr[leftBee];
            long sum = rightSum + leftSum;
            max = Math.max(max, sum);
        }
    }

    private static void rightHouse() {
        int houseIdx = n;
        int leftBee = 1;
        for (int i = 2; i <= n - 1; i++) {
            int rightBee = i;
            long rightSum = sumArr[houseIdx] - sumArr[rightBee];
            long leftSum = sumArr[houseIdx] - sumArr[leftBee] - map[rightBee];
            long sum = rightSum + leftSum;
            max = Math.max(max, sum);
        }
    }
}