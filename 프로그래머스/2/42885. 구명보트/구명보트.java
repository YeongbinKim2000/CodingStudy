import java.util.Arrays;
import java.util.Collections;
class Solution {
    public static int solution(int[] people, int limit) {
        int answer = 0;

        Integer[] peoples = new Integer[people.length];
        for (int i = 0; i < peoples.length; i++) {
            peoples[i] = people[i];
        }
        Arrays.sort(peoples, Collections.reverseOrder());

        int i = 0;
        int j = peoples.length - 1;
        while (i <= j) {
            int curWeight = peoples[i];
            if (i != j && curWeight + peoples[j] <= limit) {
                j--;
            }
            i++;
            answer++;
        }

        return answer;
    }
}