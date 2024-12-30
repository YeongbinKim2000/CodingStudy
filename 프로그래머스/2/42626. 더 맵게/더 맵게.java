import java.util.PriorityQueue;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }

        int cnt = 0;
        while (!pq.isEmpty() && pq.peek() < K) {
            cnt++;
            int min = pq.poll();
            if (pq.isEmpty())
                return -1;
            int secMin = pq.poll();
            int newValue = min + secMin * 2;
            pq.add(newValue);
        }

        return cnt;
    }
}