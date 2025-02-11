import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        System.out.println(divide(0, 0, n));
    }

    private static String divide(int x, int y, int size) {
        boolean allSame = true;
        char first = arr[x][y];

        // Check if all elements in this submatrix are the same
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (arr[i][j] != first) {
                    allSame = false;
                    break;
                }
            }
            if (!allSame) break;
        }

        if (allSame) {
            return String.valueOf(first);
        }

        // Divide into four quadrants
        int half = size / 2;
        StringBuilder sb = new StringBuilder("(");
        sb.append(divide(x, y, half));              // Top-left
        sb.append(divide(x, y + half, half));       // Top-right
        sb.append(divide(x + half, y, half));       // Bottom-left
        sb.append(divide(x + half, y + half, half));// Bottom-right
        sb.append(")");

        return sb.toString();
    }
}
