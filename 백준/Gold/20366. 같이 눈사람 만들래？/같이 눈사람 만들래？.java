import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        boolean isSame = false;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sumA = arr[i] + arr[j];
                int l = i + 1;
                int r = j - 1;
                while (l < r) {
                    int sumB = arr[l] + arr[r];
                    int diff = Math.abs(sumA - sumB);
                    min = Math.min(min, diff);

                    if (sumA < sumB) {
                        r--;
                        if (r == j || r == i)
                            r--;
                    } else if (sumA > sumB) {
                        l++;
                        if (l == i || l == j)
                            l++;
                    } else {
                        min = 0;
                        isSame = true;
                        break;
                    }
                }
                if (isSame)
                    break;
            }
            if(isSame)
                break;
        }

        System.out.println(min);
    }
}
