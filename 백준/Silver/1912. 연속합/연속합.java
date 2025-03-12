import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n + 1];
        int[] sumArr = new int[n + 1];
        int maxSum = Integer.MIN_VALUE;
        int minIdx = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sumArr[i] = sumArr[i - 1] + arr[i];
            int possibleMax = sumArr[i] - sumArr[minIdx];
            if (possibleMax > maxSum)
                maxSum = possibleMax;
            if (sumArr[i] < sumArr[minIdx])
                minIdx = i;
        }
        
        System.out.println(maxSum);
    }
}