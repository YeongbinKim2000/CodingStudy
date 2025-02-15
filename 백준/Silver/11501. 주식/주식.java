import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            int[] maxes = new int[n];
            maxes[n - 1] = arr[n - 1];
            int max = arr[n - 1];
            for (int j = n - 2; j >= 0; j--) {
                if (arr[j] > max)
                    max = arr[j];

                maxes[j] = max;
            }
            long sum = 0;
            for (int j = 0; j < n; j++)
                sum += (maxes[j] - arr[j]);

            System.out.println(sum);
        }
    }
}
