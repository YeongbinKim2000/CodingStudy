import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int on;
    static int off;
    static int cnt;
    static Queue<Integer> queue;
    static int n;
    static int k;
    static int[] belt;
    static boolean[] isOn;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        on = 1;
        off = n;
        cnt = 0;
        queue = new LinkedList<>();

        st = new StringTokenizer(br.readLine(), " ");
        belt = new int[2 * n + 1];
        isOn = new boolean[2 * n + 1];
        for (int i = 1; i <= 2 * n; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int level = 0;
        while (true) {
            level++;
            level1();
            level2();
            level3();
            if (cnt >= k) {
                break;
            }
        }

        System.out.println(level);
    }

    private static void level1() {
        on--;
        if (on == 0)
            on = 2 * n;

        off--;
        if (off == 0)
            off = 2 * n;

        if (isOn[off]) {
            isOn[off] = false;
        }
    }

    private static void level2() {
        int idx = off + 1;
        int c = 1;
        while (c <= n) {
            c++;
            idx--;
            if (idx == 0)
                idx = 2 * n;
            if (!isOn[idx]) {
                continue;
            }

            int next = idx + 1;
            if (next == 2 * n + 1)
                next = 1;

            if (belt[next] > 0 && !isOn[next]) {
                isOn[idx] = false;
                belt[next]--;
                if (belt[next] == 0)
                    cnt++;
                if (next != off)
                    isOn[next] = true;
            }

        }
//        if (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 1; i <= size; i++) {
//                int cur = queue.poll();
//                int next = cur + 1;
//                if (next == 2 * n + 1)
//                    next = 1;
//
//                if (belt[next] > 0 && !isOn[next]) {
//                    isOn[cur] = false;
//                    belt[next]--;
//                    if (belt[next] == 0)
//                        cnt++;
//                    if (next != off) {
//                        queue.add(next);
//                        isOn[next] = true;
//                    }
//                } else {
//                    queue.add(cur);
//                }
//            }
//        }
    }

    private static void level3() {
        if (belt[on] > 0 && !isOn[on]) {
            queue.add(on);
            isOn[on] = true;
            belt[on]--;
            if (belt[on] == 0)
                cnt++;
        }
    }
}
