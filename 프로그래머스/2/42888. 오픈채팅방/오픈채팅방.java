import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public static String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> results = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String curLine = record[i];
            String[] values = curLine.split(" ");
            String order = values[0];
            if (order.equals("Enter")) {
                if (!map.containsKey(values[1]))
                    map.put(values[1], values[2]);
                else
                    map.replace(values[1], map.get(values[1]), values[2]);
                results.add(values[1] + "님이 들어왔습니다.");
            } else if (order.equals("Leave")) {
                results.add(values[1] + "님이 나갔습니다.");
            } else {
                map.replace(values[1], map.get(values[1]), values[2]);
            }
        }

        String[] answer = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            String curOrder = results.get(i);
            String[] cur = curOrder.split(" ");
            cur[0] = map.get(cur[0].substring(0, cur[0].length() - 2)) + cur[0].substring(cur[0].length() - 2);
            answer[i] = cur[0] + " " + cur[1];
        }

        return answer;
    }
}