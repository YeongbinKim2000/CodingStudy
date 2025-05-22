import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int n;
	static int[][] map;
	static int time;
	static Shark shark;
	static boolean canHunt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n][n];
		time = 0;
		shark = new Shark(-1, -1, 2, 0);
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (num == 9) {
					shark.posX = i;
					shark.posY = j;
				} else
					map[i][j] = num;
			}
		}
		
		canHunt = true;
		while (canHunt) {
			hunt();
		}
		
		System.out.println(time);
	}
	
	private static void hunt() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {shark.posX, shark.posY});
		boolean[][] visited = new boolean[n][n];
		visited[shark.posX][shark.posY] = true;
		ArrayList<int[]> possiblePrey = new ArrayList<>();
		int cnt = 0;
		boolean can = false;
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int[] cur = queue.poll();
				int curX = cur[0];
				int curY = cur[1];
				
				for (int j = 0; j < 4; j++) {
					int nextX = curX + dx[j];
					int nextY = curY + dy[j];
					
					if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || 
							visited[nextX][nextY] || map[nextX][nextY] > shark.size)
						continue;
					
					if (map[nextX][nextY] != 0 && map[nextX][nextY] < shark.size)
						possiblePrey.add(new int[] {nextX, nextY});
					
					visited[nextX][nextY] = true;
					queue.add(new int[] {nextX, nextY});
				}
			}
			cnt++;
			if (possiblePrey.size() >= 1) {
				can = true;
				break;
			}
		}
		if (can) {
			int[] curFish = possiblePrey.get(0);
			int curX = curFish[0];
			int curY = curFish[1];
			if (possiblePrey.size() > 1) {
				for (int i = 1; i < possiblePrey.size(); i++) {
					int[] nowFish = possiblePrey.get(i);
					int nowX = nowFish[0];
					int nowY = nowFish[1];
					
					if (curX > nowX) {
						curX = nowX;
						curY = nowY;
					} else if (curX == nowX && curY > nowY) {
						curX = nowX;
						curY = nowY;
					}
				}
			}
			
			time += cnt;
			map[curX][curY] = 0;
			shark.posX = curX;
			shark.posY = curY;
			shark.preyCnt++;
			if (shark.preyCnt == shark.size) {
				shark.size += 1;
				shark.preyCnt = 0;
			}
		} else {
			canHunt = false;
			return;
		}
	}
	
	private static class Shark {
		int posX;
		int posY;
		int size;
		int preyCnt;
		
		private Shark(int posX, int posY, int size, int preyCnt) {
			this.posX = posX;
			this.posY = posY;
			this.size = size;
			this.preyCnt = preyCnt;
		}
	}
}
