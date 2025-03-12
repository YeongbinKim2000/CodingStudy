import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n * 2 - 1];
        for (int i = 0; i < n; i++) {
            int startIdx = (n * 2 - 1) / 2 - i;
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = startIdx, cnt = 0; cnt <= i; j += 2, cnt++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < arr[0].length; j++) {
        //         System.out.print(arr[i][j] + " ");
        //     }
        //     System.out.println();
        // }
        
        int[][] sumArr = new int[n][n * 2 - 1];
        sumArr[0][(n * 2 - 1) / 2] = arr[0][(n * 2 - 1) / 2];
        for (int i = 1; i < n; i++) {
            int startIdx = (n * 2 - 1) / 2 - i;
            for (int j = startIdx, cnt = 0; cnt <= i; j += 2, cnt++) {
                if (j == startIdx) {
                    sumArr[i][j] = sumArr[i - 1][j + 1] + arr[i][j];
                } else if (cnt == i) {
                    sumArr[i][j] = sumArr[i - 1][j - 1] + arr[i][j];
                } else {
                    sumArr[i][j] = Math.max(sumArr[i - 1][j - 1], sumArr[i - 1][j + 1]) + arr[i][j];
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n * 2 - 1; i += 2) {
            int cur = sumArr[n - 1][i];
            if (max < cur)
                max = cur;
        }
        
        System.out.println(max);
    }
}