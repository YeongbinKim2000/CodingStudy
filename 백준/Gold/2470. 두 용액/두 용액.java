import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> negatives = new ArrayList<>();
        ArrayList<Integer> positives = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur > 0)
                positives.add(cur);
            else
                negatives.add(cur);
        }

        if (!negatives.isEmpty())
            Collections.sort(negatives);
        if (!positives.isEmpty())
            Collections.sort(positives);

        if (negatives.isEmpty()) {
            System.out.println(positives.get(0) + " " + positives.get(1));
        } else if (positives.isEmpty()) {
            System.out.println(negatives.get(negatives.size() - 2)  + " " + negatives.get(negatives.size() - 1));
        } else {
            int a = -1;
            int b = -1;
            int minSum = Integer.MAX_VALUE;
            int curNegIdx = negatives.size() - 1;
            int curPosIdx = 0;
            while (curNegIdx >= 0 && curPosIdx < positives.size()) {
                int curNeg = negatives.get(curNegIdx);
                int curPos = positives.get(curPosIdx);
                int curSum = Math.abs(curNeg + curPos);
                if (minSum > curSum) {
                    a = curNeg;
                    b = curPos;
                    minSum = curSum;
                }

                if (minSum == 0)
                    break;

                if (Math.abs(curNeg) < Math.abs(curPos))
                    curNegIdx--;
                else
                    curPosIdx++;
            }
            if (positives.size() >= 2) {
                int curSum = positives.get(0) + positives.get(1);
                if (minSum > curSum) {
                    a = positives.get(0);
                    b = positives.get(1);
                    minSum = curSum;
                }
            }

            if (negatives.size() >= 2) {
                int curSum = Math.abs(negatives.get(negatives.size() - 1) + negatives.get(negatives.size() - 2));
                if (minSum > curSum) {
                    a = negatives.get(negatives.size() - 2);
                    b = negatives.get(negatives.size() - 1);
                    minSum = curSum;
                }
            }

            System.out.println(a + " " + b);
        }
    }
}