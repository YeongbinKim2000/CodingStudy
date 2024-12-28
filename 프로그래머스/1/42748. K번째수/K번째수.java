import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < answer.length; i++) {
            int[] curCommand = commands[i];
            int from = curCommand[0] - 1;
            int to = curCommand[1] - 1;
            int k = curCommand[2] - 1;
            int[] arr = new int[to - from + 1];
            for (int j = from, idx = 0; j <= to; j++, idx++) {
                arr[idx] = array[j];
            }
            Arrays.sort(arr);
            answer[i] = arr[k];
        }

        return answer;
    }
}