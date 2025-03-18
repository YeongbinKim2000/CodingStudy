import java.util.*;
import java.io.*;

public class Main {
    static int m;
    static int[] boxes;
    static boolean[] moved;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] cranes = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            cranes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cranes);

        m = Integer.parseInt(br.readLine());
        boxes = new int[m];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < m; i++)
            boxes[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(boxes);
        moved = new boolean[m];

        if (cranes[cranes.length - 1] < boxes[boxes.length - 1])
            System.out.println(-1);
        else {
            int time = 0;
            int movedCnt = 0;
            while (movedCnt < m) {
                time++;
                for (int i = cranes.length - 1; i >= 0; i--) {
                    int cur = select(cranes[i]);
                    if (cur == -1)
                        continue;
                    moved[cur] = true;
                    movedCnt++;
                    if (movedCnt == m)
                        break;
                }
            }

            System.out.println(time);
        }
    }

    private static int select(int limit) {
        int l = 0;
        int r = m;

        while (l < r) {
            int mid = (l + r) / 2;

            if (boxes[mid] <= limit)
                l = mid + 1;
            else
                r = mid;
        }

        for (int i = r - 1; i >= 0; i--) {
            if (!moved[i])
                return i;
        }

        return -1;
    }
}