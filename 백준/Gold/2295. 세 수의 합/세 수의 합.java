import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        HashSet<Integer> sums = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = arr[i] + arr[j];
                sums.add(sum);
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum1 = arr[i] - arr[j];
                int sum2 = arr[j] - arr[i];
                if (sums.contains(sum1))
                    max = Math.max(max, arr[i]);

                if (sums.contains(sum2))
                    max = Math.max(max, arr[j]);
            }
        }

        System.out.println(max);
    }

}
