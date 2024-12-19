import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int cnt = 0;

        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] sumArr = new int[n + 1];
        sumArr[1] = arr[1];
        for (int i = 2; i <= n; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n + 1; j++) {
                if (sumArr[j] - sumArr[i] == m)
                    cnt++;
            }
        }

        System.out.println(cnt);
    }
}
