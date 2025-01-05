import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
class Solution {
    static ArrayList<char[]> orderList;
    static ArrayList<String> result;
    public static String[] solution(String[] orders, int[] course) {
        orderList = new ArrayList<>();
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            char[] curOrder = order.toCharArray();
            Arrays.sort(curOrder);
            orderList.add(curOrder);
        }

        result = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {
            combination(course[i]);
        }

        String[] answer = new String[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.get(i);
        }

        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() < o2.length()) {
                    for (int i = 0; i < o1.length(); i++) {
                        if (o1.charAt(i) != o2.charAt(i))
                            return o1.charAt(i) - o2.charAt(i);
                    }
                    return -1;
                } else {
                    for (int i = 0; i < o2.length(); i++) {
                        if (o1.charAt(i) != o2.charAt(i))
                            return o1.charAt(i) - o2.charAt(i);
                    }
                    return 1;
                }
            }
        };

        Arrays.sort(answer, comparator);

        return answer;
    }

    private static void combination(int n) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < orderList.size(); i++) {
            char[] cur = orderList.get(i);
            if (cur.length < n) continue;

            boolean[] visited = new boolean[cur.length];
            pick("", cur, visited, 0, n, map);
        }

        int maxCount = 2; // 최소 2번 이상 주문된 조합만 선택
        ArrayList<String> candidates = new ArrayList<>();
        for (String key : map.keySet()) {
            int count = map.get(key);
            if (count > maxCount) {
                maxCount = count;
                candidates.clear();
                candidates.add(key);
            } else if (count == maxCount) {
                candidates.add(key);
            }
        }

        result.addAll(candidates);
    }

    private static void pick(String cur, char[] order, boolean[] visited, int idx, int n, HashMap<String, Integer> map) {
        if (n == 0) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pick(cur + order[i], order, visited, i + 1, n - 1, map);
                visited[i] = false;
            }
        }
    }
}