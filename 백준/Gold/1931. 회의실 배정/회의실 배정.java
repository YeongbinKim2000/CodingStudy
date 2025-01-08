import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[i][0] = from;
            arr[i][1] = to;
        }

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        };

        Arrays.sort(arr, comparator);

        int cnt = 1;
        int from = arr[0][0];
        int to = arr[0][1];
        for (int i = 1; i < arr.length; i++) {
            if (!overlap(from, to, arr[i][0], arr[i][1])) {
                from = arr[i][0];
                to = arr[i][1];
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean overlap(int from, int to, int curFrom, int curTo) {
        if (from > curFrom)
            return true;

        if (to <= curFrom)
            return false;

        return true;
    }
}
