import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 초밥 접시 수
        int d = Integer.parseInt(st.nextToken()); // 초밥 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속 접시 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] belt = new int[n];
        for (int i = 0; i < belt.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cur = Integer.parseInt(st.nextToken());
            belt[i] = cur;
        }

        int[] sushiCount = new int[d + 1];
        int uniqueSushi = 0;

        for (int i = 0; i < k; i++) {
            if (sushiCount[belt[i]] == 0)
                uniqueSushi++;
            sushiCount[belt[i]]++;
        }

        int max = uniqueSushi;
        if (sushiCount[c] == 0)
            max++;

        for (int i = 1; i < n; i++) {
            int removeSushi = belt[i - 1];
            sushiCount[removeSushi]--;
            if (sushiCount[removeSushi] == 0)
                uniqueSushi--;

            int addSushi = belt[(i + k - 1) % n];
            if (sushiCount[addSushi] == 0)
                uniqueSushi++;
            sushiCount[addSushi]++;

            if (sushiCount[c] == 0)
                max = Math.max(max, uniqueSushi + 1);
            else
                max = Math.max(max, uniqueSushi);
        }

        System.out.println(max);
    }
}
