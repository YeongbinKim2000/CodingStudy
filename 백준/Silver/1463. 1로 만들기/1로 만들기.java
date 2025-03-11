import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        int[] dp = new int[n + 1];
        if (n >= 2)
            dp[2] = 1;
        if (n >= 3)
            dp[3] = 1;
        
        for (int i = 4; i <= n; i++) {
            if (i % 2 == 0) {
                if (dp[i] == 0)
                    dp[i] = dp[i / 2] + 1;
                else
                    dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            
            if (i % 3 == 0) {
                if (dp[i] == 0)
                    dp[i] = dp[i / 3] + 1;
                else
                    dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
            
            if (dp[i] == 0)
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
        }
        
        System.out.println(dp[n]);
    }
}