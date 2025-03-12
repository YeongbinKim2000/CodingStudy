import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] costs = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            costs[i][0] = Integer.parseInt(st.nextToken()) + Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] = Integer.parseInt(st.nextToken()) + Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] = Integer.parseInt(st.nextToken()) + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }

        System.out.println(Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]));
    }
}