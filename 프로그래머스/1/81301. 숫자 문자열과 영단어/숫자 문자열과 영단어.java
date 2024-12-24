class Solution {
    public static int solution(String s) {
        String num = "";

        String[] numInString = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        char[] nums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

        int curIdx = 0;
        String curString = s;
        while (curIdx < s.length()) {
            boolean find = false;
            for (int i = 0; i < numInString.length; i++) {
                if (curString.startsWith(numInString[i])) {
                    num += nums[i];
                    curString = curString.substring(numInString[i].length());
                    curIdx = curIdx + numInString[i].length();
                    find = true;
                    break;
                }
            }
            if (find)
                continue;
            for (int i = 0; i < nums.length; i++) {
                if (curString.charAt(0) == nums[i]) {
                    num += nums[i];
                    curString = curString.substring(1);
                    curIdx++;
                    break;
                }
            }
        }

        return Integer.parseInt(num);
    }
}