import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashSet<String> passwords;
    static int l;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        char[] characters = new char[c];
        for (int i = 0; i < characters.length; i++) {
            characters[i] = st.nextToken().charAt(0);
        }

        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');


        Arrays.sort(characters);

        passwords = new HashSet<>();

        makePasswords(characters, vowels, "", 0, 0, 0);

        List<String> tempSet = new ArrayList<>(passwords);
        Collections.sort(tempSet);

        for (int i = 0; i < tempSet.size(); i++)
            System.out.println(tempSet.get(i));
    }

    private static void makePasswords(char[] characters, ArrayList<Character> vowels, String cur, int idx, int vowelNum, int consonantNum) {
        if (cur.length() == l) {
            if (vowelNum >= 1 && consonantNum >= 2)
                passwords.add(cur);
            return;
        }

        for (int i = idx; i < characters.length; i++) {
            if (vowels.contains(characters[i]))
                makePasswords(characters, vowels, cur + characters[i], i + 1, vowelNum + 1, consonantNum);
            else
                makePasswords(characters, vowels, cur + characters[i], i + 1, vowelNum, consonantNum + 1);
//            makePasswords(characters, vowels, cur, i + 1, vowelNum, consonantNum);
        }
    }
}
