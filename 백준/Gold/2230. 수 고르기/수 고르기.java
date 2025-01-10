import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int i = 0;
        int j = 1;
        int minDiff = Integer.MAX_VALUE;
        while (j < arr.length && i <= j) {
            int curDiff = arr[j] - arr[i];
            if (curDiff < m) {
                j++;
            } else {
                minDiff = Math.min(minDiff, curDiff);
                i++;
            }
        }

        if (minDiff != Integer.MAX_VALUE)
            System.out.println(minDiff);
        else
            System.out.println(0);
    }
}
