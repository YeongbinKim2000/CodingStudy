import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            PriorityQueue<Long> pq = new PriorityQueue<>(new Comparator<Long>() {
                public int compare(Long o1, Long o2) {
                    return (int)(o1 - o2);
                }
            });

            for (int j = 0; j < n; j++)
                pq.add(Long.parseLong(st.nextToken()));

            long cnt = 0;
            while (pq.size() >= 2) {
                long a = pq.poll();
                long b = pq.poll();
                long sum = a + b;
                cnt += sum;
                pq.add(sum);
            }

            System.out.println(cnt);
        }
    }
}
