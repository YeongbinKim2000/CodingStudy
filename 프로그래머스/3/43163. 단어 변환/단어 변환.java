class Solution {
    static int answer;
    public int solution(String begin, String target, String[] words) {
        answer = 0;

        boolean exist = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                exist = true;
                break;
            }
        }

        if (!exist)
            return answer;

        boolean[] visited = new boolean[words.length];
        dfs(begin, target, visited, 0, words);

        return answer;
    }

    private static void dfs(String curWord, String target, boolean[] visited, int depth, String[] words) {
        if (curWord.equals(target)) {
            answer = depth;
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i]) {
                int cnt = 0;
                for (int j = 0; j < curWord.length(); j++) {
                    if (words[i].charAt(j) == curWord.charAt(j))
                        cnt++;
                }
                if (cnt == curWord.length() - 1) {
                    visited[i] = true;
                    dfs(words[i], target, visited, depth + 1, words);
                    visited[i] = false;
                }
            }
        }
    }
}