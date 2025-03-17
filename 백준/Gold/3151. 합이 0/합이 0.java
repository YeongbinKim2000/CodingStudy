import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        long cnt = 0;
        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int sum = arr[i] + arr[l] + arr[r];
                if (sum == 0) {
                    if (arr[l] == arr[r]) {
                        // l과 r이 같은 값을 가리키면 조합의 개수는 (r - l + 1)C2
                        long num = r - l + 1;
                        cnt += (num * (num - 1)) / 2;
                        break;  // 같은 값이므로 더 이상 진행할 필요 없음
                    }

                    long lCnt = 1;
                    while (l < r && arr[l] == arr[l + 1] ) {
                        lCnt++;
                        l++;
                    }
                    l++;
                    long rCnt = 1;
                    while (l < r && arr[r] == arr[r - 1]) {
                        rCnt++;
                        r--;
                    }
                    r--;
                    cnt += lCnt * rCnt;
                } else if (sum > 0)
                    r--;
                else
                    l++;
            }
        }

        System.out.println(cnt);
    }
}