import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        HashSet<Integer> set = new HashSet<>();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            nums[i] = cur;
            set.add(cur);
        }
        List<Integer> tempSet = new ArrayList<>(set);
        Collections.sort(tempSet);
        int[] arr = new int[tempSet.size()];
        for (int i = 0; i < arr.length;i ++) {
            arr[i] = tempSet.get(i);
        }
//        ArrayList<Integer> list = new ArrayList<>();
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            int cur = Integer.parseInt(st.nextToken());
//            nums[i] = cur;
//            if (!list.contains(cur))
//                list.add(cur);
//        }
//
//        int[] arr = new int[list.size()];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = list.get(i);
//        }
//        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int target = nums[i];

            int l = 0;
            int r = arr.length - 1;
            int targetIdx = 0;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (arr[mid] < target) {
                    l = mid + 1;
                } else if (arr[mid] > target) {
                    r = mid - 1;
                } else {
                    targetIdx = mid;
                    break;
                }
            }

            sb.append(targetIdx).append(" ");
        }
        System.out.println(sb);
    }
}
