import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(o1) == Math.abs(o2))
                    return o1 - o2;
                else
                    return Math.abs(o1) - Math.abs(o2);
            }
        });

        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(br.readLine());
            if (cur == 0) {
                if (pq.isEmpty())
                    System.out.println(0);
                else
                    System.out.println(pq.poll());
            } else
                pq.add(cur);
        }
    }
}