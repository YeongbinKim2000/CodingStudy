import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < arr.length; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int i = 0;
        int j = 0;
        int sum = arr[0];
        int length = Integer.MAX_VALUE;

        while (i <= j) {
            if (sum >= s) {
                length = Math.min(length, j - i + 1);
                sum -= arr[i];
                i++;
            } else {
                j++;
                if (j >= n)
                    break;
                sum += arr[j];
            }
        }

        if (length == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(length);
    }
}
