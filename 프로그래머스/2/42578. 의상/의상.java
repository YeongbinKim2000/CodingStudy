import java.util.HashMap;
class Solution {
    public static int solution(String[][] clothes) {
        int answer = 1;

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            String curKind = clothes[i][1];
            map.put(curKind, map.getOrDefault(curKind, 0) + 1);
        }

        if (map.size() == 1)
            return clothes.length;
        else {
            for (Integer value: map.values()) {
                answer *= (value + 1);
            }
        }

        return answer - 1;
    }
}