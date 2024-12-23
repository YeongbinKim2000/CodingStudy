import java.util.HashSet;
import java.util.Set;
class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> phone = new HashSet<>();
        for (String next : phone_book)
            phone.add(next);

        StringBuilder sb = new StringBuilder();
        for (String next : phone_book) {
            char[] arr = next.toCharArray();
            for (int i = 0; i < arr.length - 1; i++) {
                sb.append(arr[i]);
                if (phone.contains(sb.toString())) return false;
            }
            sb.setLength(0);
        }
        return true;
    }
}