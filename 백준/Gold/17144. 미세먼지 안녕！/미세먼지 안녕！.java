import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r;
	static int c;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int upperX;
	static int upperY = 0;
	static int lowerX;
	static int lowerY = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		int cleaner = 0;
		int sum = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != -1)
					continue;
				else if (map[i][j] == -1 && cleaner == 0) {
					upperX = i;
					cleaner++;
				} else  {
					lowerX = i;
				}
			}
		}
		
		int time = 0;
		while (time < t) {
			diffuse();
			upperRotate();
			lowerRotate();
			time++;
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] != -1)
					sum += map[i][j];
			}
		}
		
		System.out.println(sum);
	}
	
	private static void diffuse() {
		int[][] tempMap = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					int nextX = i + dx[k];
					int nextY = j + dy[k];
					
					if (nextX >= 0 && nextX < r && nextY >= 0 && nextY < c && map[nextX][nextY] != -1) {
						tempMap[nextX][nextY] += (map[i][j] / 5);
						cnt++;
					}
				}
				if (cnt != 0) {
					int divide = map[i][j] / 5;
					map[i][j] = map[i][j] - (divide * cnt);
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				map[i][j] += tempMap[i][j];
			}
		}
	}
	
	private static void upperRotate() {
		int curX = upperX - 1;
		int curY = 0;
		int dir = 3;
		
		while (true) {
			int nextX = curX + dx[dir];
			int nextY = curY + dy[dir];
			
			if (nextX < 0 || nextX >= r || nextY < 0 || nextY >= c || nextX > upperX)
				dir = (dir + 1) % 4;
			else if (map[nextX][nextY] == -1) {
				map[curX][curY] = 0;
				break;
			} else {
				map[curX][curY] = map[nextX][nextY];
				curX = nextX;
				curY = nextY;
			}
		}
	}
	
	private static void lowerRotate() {
		int curX = lowerX + 1;
		int curY = 0;
		int dir = 1;
		
		while (true) {
			int nextX = curX + dx[dir];
			int nextY = curY + dy[dir];
			
			if (nextX < 0 || nextX >= r || nextY < 0 || nextY >= c || nextX < lowerX) {
				dir -= 1;
				if (dir < 0)
					dir = 3;
			} else if (map[nextX][nextY] == -1) {
				map[curX][curY] = 0;
				break;
			} else {
				map[curX][curY] = map[nextX][nextY];
				curX = nextX;
				curY = nextY;
			}
		}
	}
}
