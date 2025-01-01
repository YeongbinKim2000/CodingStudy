import java.util.Stack;
class Solution {
    public String solution(String p) {
        return work(p);
    }

    private static String work(String s) {
        if (s.isEmpty())
            return "";

        int idx = 0;
        while (idx <= s.length()) {
            idx += 2;
            String u = s.substring(0, idx);
            String v = s.substring(idx);
            if (balance(u)) {
                if (right(u)) {
                    v = work(v);
                    u = u + v;

                    return u;
                } else {
                    String value = "(";
                    v = work(v);
                    value += v;
                    value += ")";
                    u = u.substring(1, u.length() - 1);
                    for (int i = 0; i < u.length(); i++) {
                        char cur = u.charAt(i);
                        if (cur == '(')
                            value += ')';
                        else
                            value += '(';
                    }

                    return value;
                }
            }
        }
        
        return "";
    }

    private static boolean balance(String s) {
        int lCnt = 0;
        int rCnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')
                lCnt++;
            else
                rCnt++;
        }

        return lCnt == rCnt;
    }

    private static boolean right(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (cur == '(')
                stack.push('(');
            else {
                if (stack.isEmpty())
                    return false;
                else
                    stack.pop();
            }
        }

        return stack.isEmpty();
    }
}