import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int k;
    static int max;
    static ArrayList<Character>[] words;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        max = 0;

        if (k < 5)
            System.out.println(0);
        else {
            boolean[] used = new boolean[26];
            used[(int)'a' - 97] = true;
            used[(int)'n' - 97] = true;
            used[(int)'t' - 97] = true;
            used[(int)'i' - 97] = true;
            used[(int)'c' - 97] = true;
            int cnt = 5;
            words = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                words[i] = new ArrayList<>();
                String curWord = br.readLine();
                for (int j = 4; j < curWord.length() - 4; j++) {
                    char curChar = curWord.charAt(j);
                    if (!used[(int)curChar - 97] && !words[i].contains(curChar))
                        words[i].add(curChar);
                }
            }

            check(cnt, 0, used);

            System.out.println(max);
        }
    }

    private static void check(int cnt, int idx, boolean[] used) {
        if (cnt == k) {
            int ans = 0;
            for (int i = 0; i < words.length; i++) {
                boolean possible = true;
                for (int j = 0; j < words[i].size(); j++) {
                    if (!used[(int)words[i].get(j) - 97]) {
                        possible = false;
                        break;
                    }
                }
                if (possible)
                    ans++;
            }

            max = Math.max(max, ans);
        }

        for (int i = idx; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                check(cnt + 1, i, used);
                used[i] = false;
            }
        }
    }
}