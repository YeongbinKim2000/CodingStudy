import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] times = new int[n + 1];
        int[] prices = new int[n + 1];
        long[] dp = new long[n + 2];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());

            times[i] = time;
            prices[i] = price;
        }

        long max = 0;
        for (int i = 1; i <= n + 1; i++) {
            dp[i] = Math.max(dp[i], max);
            if (i <= n && i + times[i] <= n + 1)
                dp[i + times[i]] = Math.max(dp[i + times[i]], dp[i] + prices[i]);
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}