import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long num = Long.parseLong(st.nextToken());
        long cnt = Long.parseLong(st.nextToken());
        long divide = Long.parseLong(st.nextToken());

        System.out.println(calculate(num, cnt, divide));
    }

    private static long calculate(long num, long cnt, long divide) {
        if (cnt == 1)
            return num % divide;

        long temp = calculate(num, cnt / 2, divide) % divide;
        temp = (temp * temp) % divide;
        if (cnt % 2 == 1)
            temp = (temp * num) % divide;

        return temp;
    }
}
