import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                fishes[num] = new Fish(dir, true);
                map[i][j] = num;
            }
        }

        int sum = map[0][0];
        int sharkDir = fishes[map[0][0]].dir;
        fishes[map[0][0]].isAlive = false;
        map[0][0] = 0;
        play(0, 0, sharkDir, map, fishes, sum);

        System.out.println(max);
    }

    private static void play(int sharkX, int sharkY, int sharkDir, int[][] map, Fish[] fishes, int sum) {
        int[][] curMap = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                curMap[i][j] = map[i][j];
            }
        }
        Fish[] curFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            curFishes[i] = new Fish(fishes[i].dir, fishes[i].isAlive);
        }

        for (int i = 1; i <= 16; i++) {
            if (curFishes[i].isAlive) {
                int curX = 0;
                int curY = 0;
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (curMap[j][k] == i) {
                            curX = j;
                            curY = k;
                        }
                    }
                }
                int curDir = curFishes[i].dir;
                int cnt = 0;

                while (cnt < 8) {
                    int nextX = curX + dx[curDir];
                    int nextY = curY + dy[curDir];

                    if (nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4 || (nextX == sharkX && nextY == sharkY)) {
                        curDir = curDir + 1;
                        if (curDir > 8)
                            curDir = 1;
                        curFishes[i].dir = curDir;
                        cnt++;
                        continue;
                    }

                    if (curMap[nextX][nextY] != 0) {
                        int temp = curMap[curX][curY];
                        curMap[curX][curY] = curMap[nextX][nextY];
                        curMap[nextX][nextY] = temp;
                    } else {
                        curMap[nextX][nextY] = curMap[curX][curY];
                        curMap[curX][curY] = 0;
                    }

                    break;
                }
            }
        }

    	/*
    	for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			System.out.print(map[i][j] + " ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    	*/

        Queue<int[]> possibleMove = new LinkedList<>();
        boolean canSharkMove = false;
        for (int i = 1; ; i++) {
            int nextSharkX = sharkX + i * dx[sharkDir];
            int nextSharkY = sharkY + i * dy[sharkDir];

            if (nextSharkX < 0 || nextSharkX >= 4 || nextSharkY < 0 || nextSharkY >= 4)
                break;

            if (curMap[nextSharkX][nextSharkY] != 0) {
                canSharkMove = true;
                /*
                System.out.println(nextSharkX + " " + nextSharkY + "!!!");
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        System.out.print(map[j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
                */
                possibleMove.add(new int[] {nextSharkX, nextSharkY});
            }
        }

        if (!canSharkMove) {
            max = Math.max(max, sum);
            return;
        }

        int size = possibleMove.size();
        for (int i = 0; i < size; i++) {
            int[] next = possibleMove.poll();
            int nextX = next[0];
            int nextY = next[1];
            int nextTarget = curMap[nextX][nextY];
            /*
            System.out.println(nextX + " " + nextY + " " + nextTarget + "!!!");
            for (int j = 0; j < 4; j++) {
            	for (int k = 0; k < 4; k++) {
            		System.out.print(curMap[j][k] + " ");
            	}
            	System.out.println();
            }
            System.out.println();
            */
            curMap[nextX][nextY] = 0;
            curFishes[nextTarget].isAlive = false;
            play(nextX, nextY, curFishes[nextTarget].dir, curMap, curFishes, sum + nextTarget);
            curFishes[nextTarget].isAlive = true;
            curMap[nextX][nextY] = nextTarget;
        }
    }

    private static class Fish {
        int dir;
        boolean isAlive;

        private Fish(int dir, boolean isAlive) {
            this.dir = dir;
            this.isAlive = isAlive;
        }
    }
}
