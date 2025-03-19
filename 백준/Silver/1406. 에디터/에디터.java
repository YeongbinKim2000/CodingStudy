import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String line = br.readLine();
        Stack<Character> lStack = new Stack<>();
        Stack<Character> rStack = new Stack<>();

        for (int i = 0; i < line.length(); i++)
            lStack.push(line.charAt(i));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            char order = st.nextToken().charAt(0);
            if (order == 'L' && !lStack.isEmpty())
                rStack.push(lStack.pop());
            else if (order == 'D' && !rStack.isEmpty())
                lStack.push(rStack.pop());
            else if (order == 'B' && !lStack.isEmpty())
                lStack.pop();
            else if (order == 'P') {
                char add = st.nextToken().charAt(0);
                lStack.push(add);
            }
        }

        while (!lStack.isEmpty())
            rStack.push(lStack.pop());

        StringBuilder sb = new StringBuilder();
        while (!rStack.isEmpty())
            sb.append(rStack.pop());

        System.out.println(sb);
    }
}
