import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int from = Integer.parseInt(st.nextToken());
		int to = Integer.parseInt(st.nextToken());
		int[] arr = new int[to+1];
		
		//Fill the array
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		
		arr[1] = 0;
		
		//erase
		for (int i = 2; i < arr.length; i++) {
			int temp = i;
			while (temp <= to) {
				if (temp != i && arr[temp] != 0) {
					arr[temp] = 0;
				}
				temp += i;
			}
		}
		
		for (int i = from; i <= to; i++) {
			if (arr[i] != 0) 
				System.out.println(arr[i]);
		}
	}
}
