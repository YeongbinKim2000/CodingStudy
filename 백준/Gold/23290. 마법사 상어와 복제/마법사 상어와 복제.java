import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int[] sharkDx = {0, -1, 0, 1, 0};
    static int[] sharkDy = {0, 0, -1, 0, 1};
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static ArrayList<int[]>[][] fishMap;
    static ArrayList<int[]>[][] tempFishMap;
    static ArrayList<int[]>[][] newFishMap;
    static ArrayList<int[]> possibleMoves;
    static int sharkX;
    static int sharkY;
    static int[][] smellMap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        fishMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                fishMap[i][j] = new ArrayList<>();
                fishMap[i][j].add(new int[9]);
            }
        }
        smellMap = new int[4][4];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            fishMap[x][y].get(0)[d]++;
        }

        st = new StringTokenizer(br.readLine(), " ");
        sharkX = Integer.parseInt(st.nextToken()) - 1;
        sharkY = Integer.parseInt(st.nextToken()) - 1;

        int round = 0;
        while (round < s) {
            round++;
            copy();
            fishMove();
            sharkMove();
            removeSmell();
            getCopy();

//            System.out.println("round: " + round);
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    System.out.print((i + 1) + " " + (j + 1) + ": ");
//                    for (int k = 1; k <= 8; k++)
//                        System.out.print(newFishMap[i][j].get(0)[k] + " ");
//                    System.out.println();
//                }
//            }
//            System.out.println("shark: " + (sharkX + 1)+ " " + (sharkY + 1));
//            System.out.println();
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    System.out.print(smellMap[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    if (fishMap[i][j].get(0)[k] > 0)
                        cnt += fishMap[i][j].get(0)[k];
                }
            }
        }

        System.out.println(cnt);
    }

    private static void copy() {
        tempFishMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempFishMap[i][j] = new ArrayList<>();
                tempFishMap[i][j].add(new int[9]);
                for (int k = 1; k <= 8; k++) {
                    if (fishMap[i][j].get(0)[k] > 0)
                        tempFishMap[i][j].get(0)[k] += fishMap[i][j].get(0)[k];
                }
            }
        }
    }

    private static void fishMove() {
        newFishMap = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newFishMap[i][j] = new ArrayList<>();
                newFishMap[i][j].add(new int[9]);
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    if (fishMap[i][j].get(0)[k] > 0) {
                        boolean canMove = false;
                        for (int cnt = 1, curDir = k; cnt <= 8; cnt++, curDir--) {
                            if (curDir <= 0)
                                curDir = 8;
                            int nextX = i + dx[curDir];
                            int nextY = j + dy[curDir];

                            if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4 || smellMap[nextX][nextY] > 0
                                    || (nextX == sharkX && nextY == sharkY))
                                continue;

                            canMove = true;
                            newFishMap[nextX][nextY].get(0)[curDir] += fishMap[i][j].get(0)[k];
                            break;
                        }
                        if (!canMove)
                            newFishMap[i][j].get(0)[k] += fishMap[i][j].get(0)[k];
                    }
                }
            }
        }

        fishMap = newFishMap;
    }

    private static void sharkMove() {
        possibleMoves = new ArrayList<>();

        int[] arr = new int[4];
        boolean[][] visited = new boolean[4][4];
        checkMove(arr, sharkX, sharkY, visited, 0, 0);

        Collections.sort(possibleMoves, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[3] != o2[3])
                    return o2[3] - o1[3];
                else {
                    if (o1[0] != o2[0])
                        return o1[0] - o2[0];
                    else {
                        if (o1[1] != o2[1])
                            return o1[1] - o2[1];
                        else
                            return o1[2] - o2[2];
                    }
                }
            }
        });

        int firstNextX = sharkX + sharkDx[possibleMoves.get(0)[0]];
        int firstNextY = sharkY + sharkDy[possibleMoves.get(0)[0]];
        for (int i = 1; i <= 8; i++) {
            if (fishMap[firstNextX][firstNextY].get(0)[i] > 0) {
                fishMap[firstNextX][firstNextY].clear();
                fishMap[firstNextX][firstNextY].add(new int[9]);
                smellMap[firstNextX][firstNextY] = 3;
                break;
            }
        }
        int secondNextX = firstNextX+ sharkDx[possibleMoves.get(0)[1]];
        int secondNextY = firstNextY + sharkDy[possibleMoves.get(0)[1]];
        for (int i = 1; i <= 8; i++) {
            if (fishMap[secondNextX][secondNextY].get(0)[i] > 0) {
                fishMap[secondNextX][secondNextY].clear();
                fishMap[secondNextX][secondNextY].add(new int[9]);
                smellMap[secondNextX][secondNextY] = 3;
                break;
            }
        }
        int thirdNextX = secondNextX + sharkDx[possibleMoves.get(0)[2]];
        int thirdNextY = secondNextY  + sharkDy[possibleMoves.get(0)[2]];
        for (int i = 1; i <= 8; i++) {
            if (fishMap[thirdNextX][thirdNextY].get(0)[i] > 0) {
                fishMap[thirdNextX][thirdNextY].clear();
                fishMap[thirdNextX][thirdNextY].add(new int[9]);
                smellMap[thirdNextX][thirdNextY] = 3;
                break;
            }
        }

        sharkX = thirdNextX;
        sharkY = thirdNextY;
    }

    private static void checkMove(int[] arr, int curX, int curY, boolean[][] visited, int idx, int cnt) {
        if (idx == 3) {
            arr[idx] = cnt;
            possibleMoves.add(arr);
            return;
        }

        for (int i = 1; i <= 4; i++) {
            int nextX = curX + sharkDx[i];
            int nextY = curY + sharkDy[i];

            if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4)
                continue;

            int[] tempArr = new int[4];
            for (int j = 0; j < idx; j++)
                tempArr[j] = arr[j];
            tempArr[idx] = i;
            if (!visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                int curCnt = 0;
                for (int j = 1; j <= 8; j++)
                    curCnt += fishMap[nextX][nextY].get(0)[j];
                checkMove(tempArr, nextX, nextY, visited, idx + 1, cnt + curCnt);
                visited[nextX][nextY] = false;
            } else
                checkMove(tempArr, nextX, nextY, visited, idx + 1, cnt);
        }
    }

    private static void removeSmell() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smellMap[i][j] > 0)
                    smellMap[i][j]--;
            }
        }
    }

    private static void getCopy() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= 8; k++) {
                    if (tempFishMap[i][j].get(0)[k] > 0)
                        fishMap[i][j].get(0)[k] += tempFishMap[i][j].get(0)[k];
                }
            }
        }
    }
}
