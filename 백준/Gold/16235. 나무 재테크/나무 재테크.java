import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int k;
    static int[][] nutrient;
    static int[][] curNutrient;
    static ArrayList<Tree> trees;
    static Queue<Integer> deadTrees;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        nutrient = new int[n][n];
        curNutrient = new int[n][n];
        trees = new ArrayList<>();
        deadTrees = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
                curNutrient[i][j] = 5;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int treeX = Integer.parseInt(st.nextToken()) - 1;
            int treeY = Integer.parseInt(st.nextToken()) - 1;
            int treeAge = Integer.parseInt(st.nextToken());

            trees.add(new Tree(treeX, treeY, treeAge, false));
        }

        Collections.sort(trees);

        while (k > 0) {
            spring();
            summer();
            fall();
            winter();
            k--;
        }

        System.out.println(trees.size());
    }

    private static void spring() {
        for (int i = 0; i < trees.size(); i++) {
            Tree curTree = trees.get(i);

            if (curNutrient[curTree.x][curTree.y] >= curTree.age) {
                curNutrient[curTree.x][curTree.y] -= curTree.age;
                trees.get(i).age += 1;
                continue;
            }

            deadTrees.add(i);
        }
    }

    private static void summer() {
        while (!deadTrees.isEmpty()) {
            int curIdx = deadTrees.poll();
            Tree curTree = trees.get(curIdx);
            int age = curTree.age;
            curNutrient[curTree.x][curTree.y] += age / 2;
            trees.get(curIdx).isDead = true;
        }
    }

    private static void fall() {
        ArrayList<Tree> newTrees = new ArrayList<>();

        for (int i = 0; i < trees.size(); i++) {
            Tree curTree = trees.get(i);
            if (!curTree.isDead && curTree.age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int newTreeX = curTree.x + dx[j];
                    int newTreeY = curTree.y + dy[j];
                    if (newTreeX >= 0 && newTreeX < n && newTreeY >= 0 && newTreeY < n)
                        newTrees.add(new Tree(newTreeX, newTreeY, 1, false));
                }
            }
        }

        for (int i = 0; i < trees.size(); i++) {
            Tree curTree = trees.get(i);
            if (!curTree.isDead)
                newTrees.add(curTree);
        }

        trees = newTrees;
    }

    private static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                curNutrient[i][j] += nutrient[i][j];
            }
        }
    }

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;
        boolean isDead;

        public Tree(int x, int y, int age, boolean isDead) {
            this.x = x;
            this.y = y;
            this.age = age;
            this.isDead = isDead;
        }

        @Override
        public int compareTo(Tree t) {
            return this.age - t.age;
        }
    }
}
