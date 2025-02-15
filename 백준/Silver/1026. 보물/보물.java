import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        Integer[] a = new Integer[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        Integer[] b = new Integer[n];
        for (int i = 0; i < n; i++)
            b[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(a);
        Arrays.sort(b, Collections.reverseOrder());

        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += (a[i] * b[i]);

        System.out.println(sum);
    }
}
