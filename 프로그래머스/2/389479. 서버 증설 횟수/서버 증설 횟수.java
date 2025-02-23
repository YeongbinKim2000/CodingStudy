import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static int solution(int[] players, int m, int k) {
        int answer = 0;
        int time = 0;
        Queue<Integer> servers = new LinkedList<>();

        while (time < 24) {
            int size = servers.size();
            if (size > 0) {
                for (int i = 0; i < size; i++) {
                    int cur = servers.poll();
                    if (cur != k) {
                        cur++;
                        servers.add(cur);
                    }
                }
            }

            int nowPlayer = players[time];
            if (nowPlayer >= m && nowPlayer >= (servers.size() * m)) {
                int need = (nowPlayer / m) - servers.size();

                if (need != 0) {
                    for (int i = 0; i < need; i++) {
                        servers.add(1);
                        answer++;
                    }
                }
            }

            System.out.println(time + " " + nowPlayer + " " + servers.size() + " " + answer);

            time++;
        }

        return answer;
    }
}