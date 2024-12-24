class Solution {
    public static int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        int total = brown + yellow;
        for (int i = 3; i <= Math.sqrt(total); i++) {
            if (total % i == 0) {
                int j = total / i;
                int[][] rectArr = new int[i][j];
                int cnt = 0;
                for (int k = 0; k < rectArr.length; k++) {
                    for (int t = 0; t < rectArr[0].length; t++) {
                        if (k == 0 || t == 0 || k == rectArr.length - 1 || t == rectArr[0].length - 1)
                            continue;
                        cnt++;
                    }
                }
                if (cnt == yellow) {
                    answer[0] = j;
                    answer[1] = i;
                    break;
                }
            }
        }

        return answer;
    }
}