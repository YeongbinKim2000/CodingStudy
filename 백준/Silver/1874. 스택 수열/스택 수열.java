import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        Stack<Integer> stack = new Stack<>();

        int[] arr = new int[n + 1];
        for (int i = 1; i<= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean possible = true;
        int num = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int targetNum = arr[i];
            if (num <= targetNum) {
                while (num <= targetNum) {
                    stack.push(num);
                    sb.append('+').append('\n');
                    num++;
                }
            }
            if (!stack.isEmpty() && stack.peek() == targetNum) {
                stack.pop();
                sb.append('-').append('\n');
            } else {
                possible = false;
                break;
            }
        }

        if (possible)
            System.out.println(sb);
        else
            System.out.println("NO");
    }
}
