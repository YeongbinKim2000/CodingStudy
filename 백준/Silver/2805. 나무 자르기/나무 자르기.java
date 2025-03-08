import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());	//나무 수
		int m = Integer.parseInt(st.nextToken());	//필요 길이
		
		long max = 0;
		
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (max < arr[i]) {
				max = arr[i];
			}
		}
		
		long min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		
		min = 0;
		while (min < max) {
			long mid = (min + max) / 2;
			long total = 0;
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > mid) {
					total = total + (arr[i] - mid);
				} else {
					continue;
				}
			}
			
			if (total < m) {
				max = mid;
			} else {
				min = mid + 1;
			}
		}
		
		System.out.println(max - 1);
	}
}
