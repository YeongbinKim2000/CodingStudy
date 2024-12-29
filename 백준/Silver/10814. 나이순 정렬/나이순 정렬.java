import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        String[][] people = new String[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String curAge = st.nextToken();
            String curName = st.nextToken();
            String curSeq = String.valueOf(i + 1);

            people[i][0] = curAge;
            people[i][1] = curName;
            people[i][2] = curSeq;
        }

        Comparator<String[]> comparator = new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (Integer.parseInt(o1[0]) != Integer.parseInt(o2[0]))
                    return Integer.parseInt(o1[0]) - Integer.parseInt(o2[0]);
                else
                    return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
            }
        };

        Arrays.sort(people, comparator);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < people.length; i++) {
            sb.append(people[i][0]).append(" ").append(people[i][1]).append('\n');
        }

        System.out.println(sb);
    }
}
