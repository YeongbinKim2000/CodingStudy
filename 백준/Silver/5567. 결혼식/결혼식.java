import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer>[] lists;
    static HashSet<Integer> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        lists = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            lists[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lists[a].add(b);
            lists[b].add(a);
        }

        set = new HashSet<>();
        boolean[] checked = new boolean[n + 1];
        call(1, 2, checked);

        if (set.size() == 0)
            System.out.println(set.size());
        else
            System.out.println(set.size() - 1);
    }

    private static void call(int person, int recursive, boolean[] checked) {
        if (recursive == 0)
            return;

        for (int i = 0; i < lists[person].size(); i++) {
            if (!checked[lists[person].get(i)]) {
                checked[lists[person].get(i)] = true;
                set.add(lists[person].get(i));
                call(lists[person].get(i), recursive - 1, checked);
                checked[lists[person].get(i)] = false;
            }
        }
    }
}
