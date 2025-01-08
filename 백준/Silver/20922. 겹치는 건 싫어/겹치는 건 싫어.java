import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int maxLength = Integer.MIN_VALUE;

        HashMap<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 1;
        int curLength = 1;
        map.put(arr[i], 1);
        while (j < arr.length) {
            if (!map.containsKey(arr[j]) || map.get(arr[j]) < k) {
                map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
                j++;
                curLength++;
                if (j == arr.length)
                    maxLength = Math.max(maxLength, curLength);
                continue;
            }

            maxLength = Math.max(maxLength, curLength);
            while (map.get(arr[j]) >= k) {
                map.replace(arr[i], map.get(arr[i]), map.get(arr[i]) - 1);
                i++;
                curLength--;
            }
            map.put(arr[j], map.getOrDefault(arr[j], 0) + 1);
            j++;
            curLength++;
        }

        System.out.println(maxLength);
    }
}
