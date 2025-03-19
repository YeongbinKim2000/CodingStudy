import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());
            set.add(cur);
        }

        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int[] coins = new int[list.size()];
        for (int i = 0; i < coins.length; i++)
            coins[i] = list.get(i);

        int[] dp = new int[k + 1];
        for (int i = 1; i <= k; i++)
            dp[i] = Integer.MAX_VALUE;

        if (coins[0] > k) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= k)
                dp[coins[i]] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] > 0 && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        if (dp[k] == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
    }
}