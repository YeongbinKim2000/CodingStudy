import java.util.ArrayList;
class Solution {
    public static long solution(String expression) {
        long answer = 0;

        char[] priority1 = {'+', '-', '*'};
        char[] priority2 = {'+', '*', '-'};
        char[] priority3 = {'-', '+', '*'};
        char[] priority4 = {'-', '*', '+'};
        char[] priority5 = {'*', '+', '-'};
        char[] priority6 = {'*', '-', '+'};

        answer = Math.max(answer, calculation(expression, priority1));
        answer = Math.max(answer, calculation(expression, priority2));
        answer = Math.max(answer, calculation(expression, priority3));
        answer = Math.max(answer, calculation(expression, priority4));
        answer = Math.max(answer, calculation(expression, priority5));
        answer = Math.max(answer, calculation(expression, priority6));

        return answer;
    }

    private static long calculation(String expression, char[] priority) {
        String[] arr = expression.split("[\\-+*]");
        ArrayList<String> arrList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            arrList.add(arr[i]);
        }

        ArrayList<Character> operators = new ArrayList<>();
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '*' || expression.charAt(i) == '+' || expression.charAt(i) == '-')
                operators.add(expression.charAt(i));
        }

        for (int i = 0; i < priority.length; i++) {
            int curOperator = priority[i];
            int size = operators.size();
            int cnt = 0;
            while (cnt < size) {
                boolean erased = false;
                for (int j = 0; j < operators.size(); j++) {
                    if (curOperator == operators.get(j)) {
                        erased = true;
                        long front = Long.parseLong(arrList.get(j));
                        long back = Long.parseLong(arrList.get(j + 1));
                        long newValue = 0;
                        if (curOperator == '+')
                            newValue = front + back;
                        else if (curOperator == '-')
                            newValue = front - back;
                        else if (curOperator == '*')
                            newValue = front * back;
                        arrList.remove(j);
                        arrList.remove(j);
                        operators.remove(j);
                        String addedValue = Long.toString(newValue);
                        arrList.add(j, addedValue);
                    }
                    if (erased)
                        break;
                }
                cnt++;
            }
        }

        long calculated = Long.parseLong(arrList.get(0));
        calculated = Math.abs(calculated);

        return calculated;
    }
}