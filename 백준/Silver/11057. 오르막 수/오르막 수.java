import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int MOD = 10007;

        // dp[i][j] represents the number of increasing numbers of length i ending with digit j
        int[][] dp = new int[n + 1][10];

        // Base case: For length 1, there is exactly 1 number ending with each digit
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        // Fill the dp array
        for (int i = 2; i <= n; i++) { // Length
            for (int j = 0; j < 10; j++) { // Ending digit
                for (int k = 0; k <= j; k++) { // Previous digits (0 to j)
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= MOD;
                }
            }
        }

        // Sum up all numbers of length n
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result += dp[n][i];
            result %= MOD;
        }

        System.out.println(result);
    }
}
