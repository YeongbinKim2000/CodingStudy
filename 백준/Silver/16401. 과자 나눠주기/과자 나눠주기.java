import java.util.*;
import java.io.*;

class Main {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        arr = new long[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Long.parseLong(st.nextToken());
        Arrays.sort(arr);

        long l = 0;
        long r = arr[arr.length - 1] + 1;
        long max = 0;
        while (l < r) {
            long mid = (l + r) / 2;

            if (mid == 0)
                break;

            int cnt = count(mid);

            if (cnt >= m)
                max = Math.max(max, mid);

            if (cnt >= m)
                l = mid + 1;
            else
                r = mid;
        }

        System.out.println(max);
    }

    private static int count(long value) {
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            cnt += (int)(arr[i] / value);
        }

        return cnt;
    }
}