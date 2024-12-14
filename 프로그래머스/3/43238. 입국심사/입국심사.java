import java.util.Arrays;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long from = 1;
        long to = (long)times[0] * n;

        long answer = to;

        while (from <= to) {
            long mid = (from + to) / 2;
            long cnt = 0;
            for (int i = 0; i < times.length; i++) {
                cnt += mid / times[i];
            }

            if (cnt < n)
                from = mid + 1;
            else {
                answer = mid;
                to = mid - 1;
            }
        }

        return answer;
    }
}