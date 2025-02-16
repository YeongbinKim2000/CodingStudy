import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[i][0] = x;
            arr[i][1] = y;
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1] - o2[1];

                return o1[0] - o2[0];
            }
        });

        int sum = arr[0][1] - arr[0][0];
        int maxY = arr[0][1];
        for (int i = 0; i < n - 1; i++) {
            int[] cur = arr[i];
            int curX = cur[0];
            int curY = cur[1];
            if (maxY < curY)
                maxY = curY;

            int[] next = arr[i + 1];
            int nextX = next[0];
            int nextY = next[1];

            if (maxY <= nextX)
                sum += (nextY - nextX);
            else if (maxY < nextY)
                sum += (nextY - maxY);
        }

        System.out.println(sum);
    }
}
