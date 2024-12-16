import java.util.LinkedList;
import java.util.Queue;
class Solution {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {truck_weights[0], 1});
        int sum = truck_weights[0];
        int idx = 1;
        while (!queue.isEmpty()) {
            answer++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curTruck = queue.poll();
                int curTruckWeight = curTruck[0];
                int curTruckPos = curTruck[1];

                if (curTruckPos == bridge_length)
                    sum -= curTruckWeight;
                else
                    queue.add(new int[] {curTruckWeight, curTruckPos + 1});

                // System.out.println(answer + ": " + curTruckWeight + " " + curTruckPos + " " + sum);
            }

            // System.out.println();

            if (idx < truck_weights.length && sum + truck_weights[idx] <= weight) {
                queue.add(new int[] {truck_weights[idx], 1});
                sum += truck_weights[idx];
                idx++;
            }
        }

        answer++;

        return answer;
    }
}