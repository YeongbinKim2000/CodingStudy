import java.util.*;
import java.io.*;

public class Main {
    static long a;
    static long b;
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        cnt = Integer.MIN_VALUE;
        go(a, 0);

        if (cnt == Integer.MIN_VALUE)
            System.out.println(-1);
        else
            System.out.println(cnt);
    }

    private static void go(long cur, int curCnt) {
        if (cur == b) {
            cnt = Math.max(cnt, curCnt + 1);
            return;
        }

        if (cur > b)
            return;

        go (cur * 2, curCnt + 1);
        go (cur * 10 + 1, curCnt + 1);
    }
}
