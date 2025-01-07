import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int from = 0;
            int to = arr.length - 1;
            boolean find = false;
            while (from <= to) {
                int mid = (from + to) / 2;
                if (target == arr[mid]) {
                    find = true;
                    System.out.println(1);
                    break;
                } else if (target > arr[mid])
                    from = mid + 1;
                else
                    to = mid - 1;
            }
            if (!find)
                System.out.println(0);
        }
    }
}
