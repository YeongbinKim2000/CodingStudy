import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] names = new String[n];
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            names[i] = name;
            values[i] = value;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int cur = Integer.parseInt(br.readLine());

            int l = 0;
            int r = n;
            while (l < r) {
                int mid = (l + r) / 2;

                if (values[mid] < cur)
                    l = mid + 1;
                else
                    r = mid;
            }

            sb.append(names[l]).append('\n');
        }
        System.out.println(sb);
    }
}