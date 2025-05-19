import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int l;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (rowCheck(i))
                cnt++;

            if (colCheck(i))
                cnt++;
        }

        System.out.println(cnt);
    }

    private static boolean rowCheck(int row) {
        boolean allSame = true;
        int start = map[row][0];
        for (int i = 1; i < n; i++) {
            if (map[row][i] != start) {
                allSame = false;
                break;
            }
        }

        if (allSame) {
//            System.out.println("row: " + row);
            return true;
        }

        int curIdx = 0;
        boolean[] slopeExist = new boolean[n];
        while (curIdx < n - 1) {
            int cur = map[row][curIdx];
            int nextIdx = curIdx + 1;
            int next = map[row][nextIdx];
            if (Math.abs(cur - next) > 1)
                return false;
            else if (Math.abs(cur - next) == 0) {
                curIdx++;
            } else {
                if (cur > next) {
                    for (int i = nextIdx, c = 0; c < l; i++, c++) {
                        if (i >= n || map[row][i] != next || slopeExist[i])
                            return false;
                    }
                    for (int i = curIdx + 1, c = 0; c < l; i++, c++)
                        slopeExist[i] = true;
                    curIdx += l;
                } else {
                    for (int i = curIdx, c = 0; c < l; i--, c++) {
                        if (i < 0 || map[row][i] != cur || slopeExist[i])
                            return false;
                    }
                    for (int i = curIdx, c = 0; c < l; i--, c++)
                        slopeExist[i] = true;
                    curIdx++;
                }
            }
        }

//        System.out.println("row: " + row);
        return true;
    }

    private static boolean colCheck(int col) {
        boolean allSame = true;
        int start = map[0][col];
        for (int i = 1; i < n; i++) {
            if (map[i][col] != start) {
                allSame = false;
                break;
            }
        }

        if (allSame) {
//            System.out.println("col: " + col);
            return true;
        }

        int curIdx = 0;
        boolean[] slopeExist = new boolean[n];
        while (curIdx < n - 1) {
            int cur = map[curIdx][col];
            int nextIdx = curIdx + 1;
            int next = map[nextIdx][col];
            if (Math.abs(cur - next) > 1)
                return false;
            else if (Math.abs(cur - next) == 0) {
                curIdx++;
            } else {
                if (cur > next) {
                    for (int i = nextIdx, c = 0; c < l; i++, c++) {
                        if (i >= n || map[i][col] != next || slopeExist[i])
                            return false;
                    }
                    for (int i = curIdx + 1, c = 0; c < l; i++, c++)
                        slopeExist[i] = true;
                    curIdx += l;
                } else {
                    for (int i = curIdx, c = 0; c < l; i--, c++) {
                        if (i < 0 || map[i][col] != cur || slopeExist[i])
                            return false;
                    }
                    for (int i = curIdx, c = 0; c < l; i--, c++)
                        slopeExist[i] = true;
                    curIdx++;
                }
            }
        }

//        System.out.println("col: " + col);
        return true;
    }
}
