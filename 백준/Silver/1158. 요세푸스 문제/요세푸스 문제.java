import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Node head = new Node(null, 1, null);
        Node cur = head;
        for (int i = 2; i <= n; i++) {
            Node newNode = new Node(cur, i, null);
            cur.next = newNode;
            cur = newNode;
        }
        cur.next = head;
        head.prev = cur;
        cur = head;

        ArrayList<Integer> values = new ArrayList<>();

        while (cur.prev != cur) {
            for (int i = 1; i < k; i++) {
                cur = cur.next;
            }
            values.add(cur.data);
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            cur = cur.next;
        }
        values.add(cur.data);

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        for (int i = 0; i < values.size() - 1; i++) {
            sb.append(values.get(i).toString()).append(", ");
        }
        sb.append(values.get(values.size() - 1));
        sb.append(">");

        System.out.println(sb);
    }
}

class Node {
    Node prev;
    int data;
    Node next;

    public Node(Node prev, int data, Node next) {
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}