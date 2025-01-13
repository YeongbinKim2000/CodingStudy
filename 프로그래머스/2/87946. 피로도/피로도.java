import java.util.ArrayList;
import java.util.List;

class Solution {
    static int max;
    static List<String> list;
    public static int solution(int k, int[][] dungeons) {
        max = Integer.MIN_VALUE;
        list = new ArrayList<>();

        boolean[] visited = new boolean[dungeons.length];
        int[] num = new int[dungeons.length];
        for (int i = 0; i < num.length; i++)
            num[i] = i;

        combination(num, visited, 0, "");

        for (int i = 0; i < list.size(); i++) {
            String curSequence = list.get(i);
            char[] sequenceArr = curSequence.toCharArray();
            int curNum = k;
            int curCnt = 0;
            for (int j = 0; j < sequenceArr.length; j++) {
                int curIdx = sequenceArr[j] - 48;
                if (curNum >= dungeons[curIdx][0]) {
                    curNum -= dungeons[curIdx][1];
                    curCnt++;
                } else
                    break;
            }
            if (max < curCnt)
                max = curCnt;
        }

        return max;
    }

    private static void combination(int[] num, boolean[] visited, int cnt, String cur) {
        if (cnt == visited.length) {
            list.add(cur);
            return;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(num, visited, cnt + 1, cur + num[i]);
                visited[i] = false;
            }
        }
    }
}