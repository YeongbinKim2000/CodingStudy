import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        int max = 0;
        int[] belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }
        int[] sushi = new int[d + 1];
        int curCnt = 0;
        for (int i = 0; i < k; i++) {
            int curSushi = belt[i];
            if (sushi[curSushi] == 0)
                curCnt++;
            sushi[curSushi]++;
        }
        if (sushi[c] == 0)
            max = Math.max(max, curCnt + 1);
        else
            max = Math.max(max, curCnt);
        
        for (int i = 0; i < n; i++) {
            sushi[belt[i]]--;
            if (sushi[belt[i]] == 0)
                curCnt--;
            int nextIdx = (i + k) % n;
            if (sushi[belt[nextIdx]] == 0)
                curCnt++;
            sushi[belt[nextIdx]]++;
            
            if (sushi[c] == 0)
                max = Math.max(max, curCnt + 1);
            else
                max = Math.max(max, curCnt);
        }
        
        System.out.println(max);
    }
}