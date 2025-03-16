import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<>();
        int l = 0;
        int r = 0;
        map.put(arr[l], 1);
        int max = 1;

        while (true) {
            r++;
            if (r == n) {
                if (max == 1)
                    max = Math.max(max, r - l);
                break;
            }
            int add = arr[r];
            if (!map.containsKey(add)) {
                map.put(add, 1);
            } else if (map.get(add) == k) {
                max = Math.max(max, r - l);
                while (arr[l] != add) {
                    map.replace(arr[l], map.get(arr[l]), map.get(arr[l]) - 1);
                    l++;
                }
                map.replace(arr[l], map.get(arr[l]), map.get(arr[l]) - 1);
                map.replace(add, map.get(add), map.get(add) + 1);
                l++;
            } else {
                map.replace(add, map.get(add), map.get(add) + 1);
            }
            max = Math.max(max, r - l + 1);
        }

        System.out.println(max);
    }
}