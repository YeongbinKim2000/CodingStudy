import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
class Solution {
    static String[] answer;
    public static String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (o1, o2) -> {
            if (o1[0].equals(o2[0])) return o1[1].compareTo(o2[1]);
            return o1[0].compareTo(o2[0]);
        });

        answer = new String[tickets.length + 1];
        List<String> list = new ArrayList<>();
        boolean[] visited = new boolean[tickets.length];

        list.add("ICN");
        dfs(tickets.length + 1, "ICN", tickets, visited, list);

        return answer;
    }

    private static boolean dfs(int length, String from, String[][] tickets, boolean[] visited, List<String> list) {
        if (list.size() == length) {
            answer = list.toArray(new String[0]);
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (from.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                list.add(tickets[i][1]);
                if (dfs(length, tickets[i][1], tickets, visited, list)) return true;
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }

        return false;
    }
}