import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String curLine = br.readLine();
            Card head = new Card(null, ' ', null);
            Card cur = head;
            for (int j = 0; j < curLine.length(); j++) {
                if (curLine.charAt(j) == '<') {
                    if (cur.curChar != ' ')
                        cur = cur.prev;
                } else if (curLine.charAt(j) == '>') {
                    if (cur.next != null)
                        cur = cur.next;
                } else if (curLine.charAt(j) == '-') {
                    if (cur.curChar != ' ') {
                        cur.prev.next = cur.next;
                        if (cur.next != null)
                            cur.next.prev = cur.prev;
                        cur = cur.prev;
                    }
                } else {
                    char curCh = curLine.charAt(j);
                    if (cur.next == null) {
                        Card newNode = new Card(cur, curCh, null);
                        cur.next = newNode;
                        cur = newNode;
                    } else {
                        Card newNode = new Card(cur, curCh, cur.next);
                        cur.next.prev = newNode;
                        cur.next = newNode;
                        cur = newNode;
                    }
                }
            }
            cur = head.next;
            StringBuilder sb = new StringBuilder();
            while (cur.next != null) {
                sb.append(cur.curChar);
                cur = cur.next;
            }
            sb.append(cur.curChar);
            System.out.println(sb);
        }
    }
}

class Card {
    Card prev;
    char curChar;
    Card next;

    public Card(Card prev, char curChar, Card next) {
        this.prev = prev;
        this.curChar = curChar;
        this.next = next;
    }
}