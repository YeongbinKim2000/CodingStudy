import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			int cur = Integer.parseInt(st.nextToken());
			map.put(cur, map.getOrDefault(cur, 0) + 1);
		}
		
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int target = Integer.parseInt(st.nextToken());
			sb.append(map.getOrDefault(target, 0));
			sb.append(" ");
		}
		
		System.out.println(sb);
	}
}