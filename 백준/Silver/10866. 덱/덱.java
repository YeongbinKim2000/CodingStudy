import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        Stack<Integer> deque = new Stack<>();
        int n = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String cur = st.nextToken();
            if (cur.equals("push_front")) {
                int value = Integer.parseInt(st.nextToken());
                Stack<Integer> tempStack = new Stack<>();
                while (!deque.isEmpty())
                    tempStack.push(deque.pop());
                deque.push(value);
                while(!tempStack.isEmpty())
                    deque.push(tempStack.pop());
            } else if (cur.equals("push_back")) {
                int value = Integer.parseInt(st.nextToken());
                deque.push(value);
            } else if (cur.equals("pop_front")) {
                if (deque.isEmpty())
                    System.out.println(-1);
                else {
                    Stack<Integer> tempStack = new Stack<>();
                    while (!deque.isEmpty())
                        tempStack.push(deque.pop());
                    System.out.println(tempStack.pop());
                    while(!tempStack.isEmpty())
                        deque.push(tempStack.pop());
                }
            } else if (cur.equals("pop_back")) {
                if (deque.isEmpty())
                    System.out.println(-1);
                else
                    System.out.println(deque.pop());
            } else if (cur.equals("size"))
                System.out.println(deque.size());
            else if (cur.equals("empty")) {
                if (deque.isEmpty())
                    System.out.println(1);
                else
                    System.out.println(0);
            } else if (cur.equals("front")) {
                if (deque.isEmpty())
                    System.out.println(-1);
                else {
                    Stack<Integer> tempStack = new Stack<>();
                    while (!deque.isEmpty())
                        tempStack.push(deque.pop());
                    System.out.println(tempStack.peek());
                    while(!tempStack.isEmpty())
                        deque.push(tempStack.pop());
                }
            } else if (cur.equals("back")) {
                if (deque.isEmpty())
                    System.out.println(-1);
                else {
                    System.out.println(deque.peek());
                }
            }
        }
    }
}