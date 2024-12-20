import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> list = new ArrayList<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(arr[0]);
        int prevNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == prevNum)
                continue;
            else {
                queue.add(arr[i]);
                prevNum = arr[i];
            }
        }

        while (!queue.isEmpty()) {
            list.add(queue.poll());
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}