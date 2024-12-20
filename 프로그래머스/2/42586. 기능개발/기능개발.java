import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            queue.add(progresses[i]);
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                cur += speeds[idx + i];
                queue.add(cur);
            }

            if (!queue.isEmpty() && queue.peek() >= 100) {
                int cnt = 0;
                while (!queue.isEmpty() && queue.peek() >= 100) {
                    queue.poll();
                    cnt++;
                    idx++;
                }
                list.add(cnt);
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}