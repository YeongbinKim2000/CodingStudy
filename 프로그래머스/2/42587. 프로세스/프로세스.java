import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
class Solution {
    public static int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < priorities.length; i++) {
            queue.add(priorities[i]);
            pq.add(priorities[i]);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            int highest = pq.poll();
            if (cur == highest && location == 0)
                return ++answer;
            else if (cur == highest) {
                location--;
                answer++;
            } else if (location == 0) {
                queue.add(cur);
                location = queue.size() - 1;
                pq.add(highest);
            } else {
                queue.add(cur);
                location--;
                pq.add(highest);
            }
        }

        return answer;
    }
}