import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int cntNegative1;
    static int cnt0;
    static int cnt1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        cntNegative1 = 0;
        cnt0 = 0;
        cnt1 = 0;

        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        divide(arr);

        System.out.println(cntNegative1);
        System.out.println(cnt0);
        System.out.println(cnt1);
    }

    private static void divide(int[][] arr) {
        int num = arr[0][0];
        boolean satisfy = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != num) {
                    satisfy = false;
                    break;
                }
            }
            if (!satisfy)
                break;
        }

        if (satisfy) {
            if (num == -1)
                cntNegative1 += 1;
            else if (num == 0)
                cnt0 += 1;
            else
                cnt1 += 1;
            return;
        }

        int newLength = arr.length / 3;
        for (int i = 0; i < arr.length; i += newLength) {
            for (int j = 0; j < arr.length; j += newLength) {
                int[][] newArr = new int[arr.length / 3][arr.length / 3];
                for (int m = i, cntX = 0; cntX < newArr.length; m++, cntX++) {
                    for (int n = j, cntY = 0; cntY < newArr.length; n++, cntY++) {
                        newArr[cntX][cntY] = arr[m][n];
                    }
                }
                divide(newArr);
            }
        }
    }
}
