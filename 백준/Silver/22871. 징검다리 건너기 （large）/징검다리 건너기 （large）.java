import java.util.*;
import java.io.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long l = 0;
        long r = (long)(n - 1) * (long)(1 + Math.abs(arr[n - 1] - arr[0]));
        while (l <= r) {
            long mid = (l + r) / 2;

            if (!check(arr, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(l);
    }

    private static boolean check(int[] arr, long k) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == n - 1)
                return true;

            for (int i = cur + 1; i < n; i++) {
                long power = (long)(i - cur) * (long)(1 + Math.abs(arr[i] - arr[cur]));

                if (!visited[i] && power <= k) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        return false;
    }
}