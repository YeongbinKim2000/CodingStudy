import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[k];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        long min = 1;
        long max = arr[k - 1];
        while (min <= max) {
            long mid = min + (max - min) / 2;
            long cnt = 0;
            for (int i = 0; i < arr.length; i++) {
                cnt += arr[i] / mid;
            }
            // System.out.println(min + " " + max + " " + mid + " " + cnt);
            if (cnt < n)
                max = mid - 1;
            else
                min = mid + 1;
        }

        System.out.println(max);
    }
}
