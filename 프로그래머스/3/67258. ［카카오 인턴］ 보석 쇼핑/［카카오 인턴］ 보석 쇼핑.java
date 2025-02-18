import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public static int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < gems.length; i++)
            set.add(gems[i]);

        int variety = set.size();

        int from = 0;
        int length = Integer.MAX_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        for (int to = 0; to < gems.length; to++) {
            map.put(gems[to], map.getOrDefault(gems[to], 0) + 1);

            while (map.get(gems[from]) > 1) {
                map.replace(gems[from], map.get(gems[from]), map.get(gems[from]) - 1);
                from++;
            }

            if (map.size() == variety && length > (to - from + 1)) {
                length = to - from + 1;
                answer[0] = from + 1;
                answer[1] = to + 1;
            }
        }

        return answer;
    }
}