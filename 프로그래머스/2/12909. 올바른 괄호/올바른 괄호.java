import java.util.Stack;
class Solution {
    boolean solution(String s) {
        boolean answer = true;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(')
                stack.push('(');
            else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                } else
                    stack.pop();
            }
        }

        if (!stack.isEmpty())
            answer = false;

        return answer;
    }
}