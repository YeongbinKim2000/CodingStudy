import java.util.Stack;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = prices.length - 1; i >= 0; i--) {
            stack.add(prices[i]);
        }

        int idx = 0;
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int day = 0;
            boolean cont = true;
            for (int i = idx + 1; i < prices.length; i++) {
                if (cont && cur <= prices[i])
                    day++;
                else {
                    cont = false;
                    day++;
                    break;
                }
            }
            answer[idx] = day;
            idx++;
        }

        return answer;
    }
}