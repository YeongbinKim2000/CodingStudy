import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 학생 수
        int k = Integer.parseInt(st.nextToken()); // 졸고 있는 수
        int q = Integer.parseInt(st.nextToken()); // 출석코드 보내는 수
        int m = Integer.parseInt(st.nextToken()); // 구간 수

        boolean[] isSleep = new boolean[n + 3];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++)
            isSleep[Integer.parseInt(st.nextToken())] = true;

        boolean[] checked = new boolean[n + 3];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < q; i++) {
            int send = Integer.parseInt(st.nextToken());
            if (!isSleep[send]) {
                checked[send] = true;
                for (int j = send + send; j < n + 3; j += send) {
                    if (!isSleep[j])
                        checked[j] = true;
                }
            }
        }

        int[] sumArr = new int[n + 3];
        for (int i = 3; i < n + 3; i++) {
            if (!checked[i])
                sumArr[i] = sumArr[i - 1] + 1;
            else
                sumArr[i] = sumArr[i - 1];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            System.out.println(sumArr[to] - sumArr[from - 1]);
        }
    }
}