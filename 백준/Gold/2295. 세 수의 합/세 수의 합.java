import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // Store all possible sums of two numbers
        HashSet<Integer> setA = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                setA.add(arr[i] + arr[j]);
            }
        }

        int maxD = 0;
        // Find the maximum d where d = arr[k] and d = x + y exists in setA
        for (int k = n - 1; k >= 0; k--) {
            for (int i = 0; i < k; i++) {
                int diff = arr[k] - arr[i];
                if (setA.contains(diff)) {
                    maxD = arr[k];
                    System.out.println(maxD);
                    return;
                }
            }
        }
    }
}
