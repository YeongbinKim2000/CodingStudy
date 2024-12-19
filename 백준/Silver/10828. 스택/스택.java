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
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            if (command.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                stack.push(num);
            } else if (command.equals("pop")) {
                if (!stack.isEmpty())
                    System.out.println(stack.pop());
                else
                    System.out.println(-1);
            } else if (command.equals("size"))
                System.out.println(stack.size());
            else if (command.equals("empty")) {
                if (stack.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            } else if (command.equals("top")) {
                if (stack.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(stack.peek());
            }
        }
    }
}
