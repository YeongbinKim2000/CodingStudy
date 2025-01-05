class Solution {
    public static String solution(String new_id) {
        String answer = new_id;

        while (true) {
            String cur = answer;
            answer = level1(answer);
            answer = level2(answer);
            answer = level3(answer);
            answer = level4(answer);
            answer = level5(answer);
            answer = level6(answer);
            answer = level7(answer);
            if (cur.equals(answer))
                break;
        }

        return answer;
    }

    private static String level1(String new_id) {
        return new_id.toLowerCase();
    }

    private static String level2(String new_id) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char cur = new_id.charAt(i);
            if ((cur >= 'a' && cur <= 'z') || (cur >= '0' && cur <= '9') || cur == '-'
                    || cur == '_' || cur == '.')
                sb.append(cur);
        }

        return sb.toString();
    }

    private static String level3(String new_id) {
        if (new_id.length() == 1)
            return new_id;

        StringBuilder sb = new StringBuilder();
        char prev = new_id.charAt(0);
        for (int i = 1; i < new_id.length(); i++) {
            char cur = new_id.charAt(i);
            if (!(prev == '.' && cur == '.'))
                sb.append(prev);
            prev = cur;
            if (i == new_id.length() - 1 && cur != '.')
                sb.append(cur);
        }

        return sb.toString();
    }

    private static String level4(String new_id) {
        if (!new_id.isEmpty() && new_id.charAt(0) == '.')
            new_id = new_id.substring(1);

        if (!new_id.isEmpty() && new_id.charAt(new_id.length() - 1) == '.')
            new_id = new_id.substring(0, new_id.length() - 1);

        return new_id;
    }

    private static String level5(String new_id) {
        if (new_id.isEmpty())
            return "a";

        return new_id;
    }

    private static String level6(String new_id) {
        if (new_id.length() >= 16)
            new_id = new_id.substring(0, 15);

        return new_id;
    }

    private static String level7(String new_id) {
        while (new_id.length() < 3) {
            new_id += new_id.charAt(new_id.length() - 1);
        }

        return new_id;
    }
}