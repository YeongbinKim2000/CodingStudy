import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

class Solution {
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        int[] peopleArr = new int[N + 2];
        for (int i = 0; i < stages.length; i++) {
            peopleArr[stages[i]]++;
        }

//        for (int i = 0; i < peopleArr.length; i++) {
//            System.out.print(peopleArr[i] + " ");
//        }
//        System.out.println();

        int[] sumArr = new int[N + 1];
        sumArr[N] = peopleArr[N] + peopleArr[N + 1];
        for (int i = N - 1; i >= 1; i--) {
            sumArr[i] = peopleArr[i] + sumArr[i + 1];
        }

//        for (int i = 0; i < sumArr.length; i++) {
//            System.out.print(sumArr[i] + " ");
//        }
//        System.out.println();

        HashMap<Integer, Double> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            Double rate = 0.0;
            if (sumArr[i] == 0)
                rate = 0.0;
            else
                rate = ((double)peopleArr[i]) / ((double)sumArr[i]);
            map.put(i, rate);
        }

        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Double difference = map.get(o2) - map.get(o1);
                if (difference > 0)
                    return 1;
                else if (difference < 0)
                    return -1;
                else
                    return o1 - o2;
            }
        });

        for (int i = 0; i < answer.length; i++) {
            answer[i] = keySet.get(i);
        }

        return answer;
    }
}