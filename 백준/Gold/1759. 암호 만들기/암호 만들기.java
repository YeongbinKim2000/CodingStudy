import java.util.*;
import java.io.*;

public class Main {
    static int l;
    static int c;
    static char[] arr;
    static ArrayList<String> possible;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new char[c];
        possible = new ArrayList<>();
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < c; i++)
            arr[i] = st.nextToken().charAt(0);
        Arrays.sort(arr);
        boolean[] visited = new boolean[c];
        create("", 0, 0, 0, visited);

        Collections.sort(possible);
        for (int i = 0; i < possible.size(); i++) {
            System.out.println(possible.get(i));
        }
    }

    private static void create(String cur, int idx, int cnt1, int cnt2, boolean[] visited) {
        if (cur.length() == l && cnt1 >= 1 && cnt2 >= 2) {
            possible.add(cur);
            return;
        }

        for (int i = idx; i < visited.length; i++) {
            if (!visited[i]) {
                int newCnt1 = cnt1;
                int newCnt2 = cnt2;
                if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u')
                    newCnt1++;
                else
                    newCnt2++;
                String temp = cur + arr[i];
                visited[i] = true;
                create(temp, i + 1, newCnt1, newCnt2, visited);
                visited[i] = false;
            }
        }
    }
}