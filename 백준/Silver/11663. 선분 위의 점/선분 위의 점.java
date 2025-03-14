import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int lowerbound = -1;
            int upperbound = -1;

            int l = 0;
            int r = n;
            // lowerbound
            while (l < r) {
                int mid = (l + r) / 2;

                if (arr[mid] < s)
                    l = mid + 1;
                else
                    r = mid;
            }
            lowerbound = l;

            // upperbound
            l = 0;
            r = n;
            while (l < r) {
                int mid = (l + r) / 2;

                if (arr[mid] <= e)
                    l = mid + 1;
                else
                    r = mid;
            }
            upperbound = r;

            System.out.println(upperbound - lowerbound);
        }
    }
}