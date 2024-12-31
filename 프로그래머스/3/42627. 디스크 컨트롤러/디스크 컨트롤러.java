import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
    public static int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1])
                    return o1[1] - o2[1];
                else {
                    if (o1[0] != o2[0])
                        return o1[0] - o2[0];
                    else
                        return o1[2] - o2[2];
                }
            }
        });

        int time = 0;
        int size = jobs.length;

        if (size == 1) {
            return jobs[0][1] - jobs[0][0];
        }

        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int idx = 0;
        while (true) {
            int[] cur = jobs[idx];
            if (cur[0] == 0) {
                pq.add(new int[] {cur[0], cur[1], idx});
                idx++;
            } else
                break;
        }

        while (idx < size || !pq.isEmpty()) {
            while (pq.isEmpty()) {
                time++;
                while (idx < size && jobs[idx][0] == time) {
                    pq.add(new int[] {jobs[idx][0], jobs[idx][1], idx});
                    idx++;
                }
            }

            int[] cur = pq.poll();
            time += cur[1];
            while (idx < size) {
                if (jobs[idx][0] <= time) {
                    pq.add(new int[] {jobs[idx][0], jobs[idx][1], idx});
                    idx++;
                } else
                    break;
            }
            answer += (time - cur[0]);
        }

        return answer / size;
    }
}