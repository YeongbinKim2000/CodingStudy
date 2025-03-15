import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 백트래킹을 위한 배열
        boolean[] added = new boolean[n]; // 0부터 사용
        divide(0, added, 0); // 팀 나누기 시작

        System.out.println(min);
    }

    private static void divide(int idx, boolean[] added, int count) {
        if (count == n / 2) {
            calculate(added);
            return;
        }

        for (int i = idx; i < n; i++) {
            if (!added[i]) {
                added[i] = true;
                divide(i + 1, added, count + 1);
                added[i] = false;
            }
        }
    }

    private static void calculate(boolean[] added) {
        ArrayList<Integer> teamA = new ArrayList<>();
        ArrayList<Integer> teamB = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (added[i]) teamA.add(i);
            else teamB.add(i);
        }

        int scoreA = 0, scoreB = 0;
        
        for (int i = 0; i < n / 2; i++) {
            for (int j = i + 1; j < n / 2; j++) {
                scoreA += map[teamA.get(i)][teamA.get(j)] + map[teamA.get(j)][teamA.get(i)];
                scoreB += map[teamB.get(i)][teamB.get(j)] + map[teamB.get(j)][teamB.get(i)];
            }
        }

        min = Math.min(min, Math.abs(scoreA - scoreB));
    }
}
