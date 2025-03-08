import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());

            int l = 0;
            int r = n - 1;
            boolean find = false;
            while (l <= r) {
                int mid = (l + r) / 2;

                if (nums[mid] == target) {
                    find = true;
                    break;
                } else if (nums[mid] > target) {
                    r = mid - 1;
                } else
                    l = mid + 1;
            }
            if (find)
                sb.append(1).append(" ");
            else
                sb.append(0).append(" ");
        }

        System.out.println(sb);
    }
}
