import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Integer[] arr = new Integer[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int max = arr[0];

        Arrays.sort(arr, Collections.reverseOrder());

        if (n > 1) {
            for (int i = 1; i < n; i++) {
                int curWeight = arr[i] * (i + 1);
                if (curWeight > max)
                    max = curWeight;
            }
        }

        System.out.println(max);
    }
}
