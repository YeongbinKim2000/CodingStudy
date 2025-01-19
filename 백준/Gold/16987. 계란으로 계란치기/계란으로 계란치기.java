import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int max;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] eggStrengths = new int[n];
        int[] eggWeights = new int[n];

        max = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            eggStrengths[i] = Integer.parseInt(st.nextToken());
            eggWeights[i] = Integer.parseInt(st.nextToken());
        }

        play(eggStrengths, eggWeights, 0, n);
        System.out.println(max);
    }

    private static void play(int[] eggStrengths, int[] eggWeights, int idx, int n) {
        if (idx == n) {  // 종료 조건
            int brokenCnt = 0;
            for (int strength : eggStrengths) {
                if (strength <= 0)
                    brokenCnt++;
            }
            max = Math.max(max, brokenCnt);
            return;
        }

        // 현재 계란이 깨졌다면 다음 계란으로
        if (eggStrengths[idx] <= 0) {
            play(eggStrengths, eggWeights, idx + 1, n);
            return;
        }

        boolean isHit = false;  // 계란을 부딪혔는지 확인

        for (int i = 0; i < n; i++) {
            if (i != idx && eggStrengths[i] > 0) { // 다른 깨지지 않은 계란 선택
                isHit = true;

                eggStrengths[i] -= eggWeights[idx];
                eggStrengths[idx] -= eggWeights[i];

                play(eggStrengths, eggWeights, idx + 1, n);

                eggStrengths[i] += eggWeights[idx]; // 상태 복구
                eggStrengths[idx] += eggWeights[i];
            }
        }

        // 부딪힐 계란이 없으면 다음 계란으로
        if (!isHit) {
            play(eggStrengths, eggWeights, idx + 1, n);
        }
    }
}
