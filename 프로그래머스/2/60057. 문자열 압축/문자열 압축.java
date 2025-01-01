class Solution {
    static int minLength;
    public static int solution(String s) {
        minLength = s.length();

        for (int i = 1; i <= s.length() / 2; i++) {
            slicing(s, i);
        }

        return minLength;
    }

    private static void slicing(String s, int n) {
        StringBuilder sb = new StringBuilder();
        String compare = s.substring(0, n);
        int idx = n;
        int cnt = 1;

        while (idx < s.length()) {
            if (idx + n == s.length()) {
                String cur = s.substring(idx, idx + n);
                if (compare.equals(cur))
                    cnt++;

                if (cnt == 1)
                    sb.append(compare).append(cur);
                else
                    sb.append(cnt).append(compare);

                break;
            }

            if (idx + n > s.length()) {
                if (cnt == 1) {
                    sb.append(compare);
                } else {
                    sb.append(cnt).append(compare);
                }
                sb.append(s.substring(idx));
                break;
            }

            String cur = s.substring(idx, idx + n);
            if (compare.equals(cur)) {
                cnt++;
            } else {
                if (cnt == 1) {
                    sb.append(compare);
                } else {
                    sb.append(cnt).append(compare);
                }
                compare = cur;
                cnt = 1;
            }
            idx += n;
        }

        int length = sb.toString().length();
//        System.out.println(n);
//        System.out.println(sb);

        if (minLength > length)
            minLength = length;
    }
}