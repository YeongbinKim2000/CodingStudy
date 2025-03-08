import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] days = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++)
                days[j] = Integer.parseInt(st.nextToken());
            long profit = 0;

            int curMax = days[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                if (days[j] > curMax)
                    curMax = days[j];

                profit += (curMax - days[j]);
            }

            System.out.println(profit);
        }
    }
}
