import java.util.*;
class Solution {
    static Set<Integer> prime;
    public int solution(String numbers) {
        prime = new HashSet<>();
        boolean[] visit = new boolean[numbers.length()];

        for (int i = 0; i < numbers.length(); i++) {
            dfs(numbers, i+1, "", 0, visit);
        }

        return prime.size();
    }

    private static void dfs(String numbers, int range, String now, int idx, boolean[] visit) {
        if (range == idx) {
            int num = Integer.parseInt(now);
            if (checkPrime(num)) prime.add(num);
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if (visit[i]) continue;

            visit[i] = true;
            dfs(numbers, range, now + numbers.charAt(i), idx+1, visit);
            visit[i] = false;
        }

        return;
    }

    private static boolean checkPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }

        return true;
    }
}