import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int limit = Integer.parseInt(br.readLine());

        // upperbound
        int l = 0;
        int r = arr[n - 1];
        while (l <= r) {
            int mid = (l + r) / 2;
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (arr[i] > mid)
                    sum += mid;
                else
                    sum += arr[i];
            }

            if (sum > limit)
                r = mid - 1;
            else
                l = mid + 1;
        }

        System.out.println(l - 1);
    }
}