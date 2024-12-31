import java.util.HashSet;
import java.util.Arrays;
class Solution {
    public static int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);
        boolean[] losted = new boolean[n + 1];
        for (int i = 0; i < lost.length; i++) {
            losted[lost[i]] = true;
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < reserve.length; i++) {
            if (losted[reserve[i]]) {
                set.add(i);
                losted[reserve[i]] = false;
            }
        }

        for (int i = 0; i < reserve.length; i++) {
            if (set.contains(i))
                continue;
            
            if (reserve[i] - 1 >= 0 && losted[reserve[i] - 1]) {
                losted[reserve[i] - 1] = false;
                continue;
            }

            if (reserve[i] + 1 < losted.length && losted[reserve[i] + 1]) {
                losted[reserve[i] + 1] = false;
            }
        }

        int answer = n;
        for (int i = 1; i < losted.length; i++) {
            if (losted[i])
                answer--;
        }

        return answer;
    }
}