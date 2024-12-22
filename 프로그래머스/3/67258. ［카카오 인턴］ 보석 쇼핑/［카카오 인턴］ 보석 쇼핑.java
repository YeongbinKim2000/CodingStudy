import java.util.HashMap;
import java.util.HashSet;
class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }

        int size = set.size();

        HashMap<String, Integer> map = new HashMap<>();
        int l = 0;
        int length = Integer.MAX_VALUE;
        for (int r = 0; r < gems.length; r++) {
            map.put(gems[r], map.getOrDefault(gems[r], 0) + 1);

            while(map.get(gems[l]) > 1){
                map.put(gems[l], map.get(gems[l])-1);
                l++;
            }

            if(map.size() == size && length > r - l){
                length = r - l;
                answer[0] = l + 1;
                answer[1] = r + 1;
            }
        }

        return answer;
    }
}