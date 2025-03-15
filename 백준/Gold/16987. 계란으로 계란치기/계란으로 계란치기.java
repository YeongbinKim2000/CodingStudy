import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int max;
    static int[] strengths;
    static int[] weights;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        strengths = new int[n];
        weights = new int[n];
        max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            strengths[i] = Integer.parseInt(st.nextToken());
            weights[i] = Integer.parseInt(st.nextToken());
        }

        play(0);
        System.out.println(max);
    }

    private static void play(int idx) {
        if (idx == n) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (strengths[i] <= 0) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        // 현재 계란이 깨진 경우 다음 계란으로 이동
        if (strengths[idx] <= 0) {
            play(idx + 1);
            return;
        }

        boolean hit = false;
        for (int i = 0; i < n; i++) {
            if (i == idx || strengths[i] <= 0) continue; // 자기 자신을 때릴 수 없고, 이미 깨진 계란은 무시

            hit = true;
            int originalIdxStrength = strengths[idx];
            int originalIStrength = strengths[i];

            strengths[idx] -= weights[i];
            strengths[i] -= weights[idx];

            play(idx + 1);

            // 원래 값 복원 (백트래킹)
            strengths[idx] = originalIdxStrength;
            strengths[i] = originalIStrength;
        }

        // 단 한 번도 다른 계란을 치지 못한 경우 다음 계란으로 이동
        if (!hit) play(idx + 1);
    }
}
