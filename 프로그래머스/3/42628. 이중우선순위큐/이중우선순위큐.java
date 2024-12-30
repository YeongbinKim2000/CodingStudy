import java.util.Collections;
import java.util.PriorityQueue;
class Solution {
    public static int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        boolean minExst = false;
        boolean maxExst = false;
        for (int i = 0; i < operations.length; i++) {
            String[] cur = operations[i].split(" ");
            if (cur[0].equals("I")) {
                int newValue = Integer.parseInt(cur[1]);
                if (!minExst && !maxExst || minExst) {
                    minPq.add(newValue);
                    minExst = true;
                } else {
                    maxPq.add(newValue);
                    maxExst = true;
                }
            } else {
                if (cur[1].equals("1")) {
                    if (!minExst && !maxExst)
                        continue;
                    else if (!minExst && maxExst) {
                        maxPq.poll();
                        if (maxPq.isEmpty())
                            maxExst = false;
                    } else if (minExst && !maxExst) {
                        while (!minPq.isEmpty()) {
                            maxPq.add(minPq.poll());
                        }
                        maxExst = true;
                        minExst = false;
                        maxPq.poll();
                    }
                } else if (cur[1].equals("-1")) {
                    if (!minExst && !maxExst)
                        continue;
                    else if (minExst && !maxExst) {
                        minPq.poll();
                        if (minPq.isEmpty())
                            minExst = false;
                    } else if (!minExst && maxExst) {
                        while (!maxPq.isEmpty()) {
                            minPq.add(maxPq.poll());
                        }
                        minExst = true;
                        maxExst = false;
                        minPq.poll();
                    }
                }
            }
        }

        if (maxPq.isEmpty() && minPq.isEmpty())
            return answer;
        else if (maxPq.size() == 1) {
            answer[0] = answer[1] = maxPq.poll();
        } else if (minPq.size() == 1) {
            answer[0] = answer[1] = minPq.poll();
        } else if (minExst) {
            answer[1] = minPq.poll();
            while (!minPq.isEmpty()) {
                maxPq.add(minPq.poll());
            }
            answer[0] = maxPq.poll();
        } else {
            answer[0] = maxPq.poll();
            while (!maxPq.isEmpty()) {
                minPq.add(maxPq.poll());
            }
            answer[1] = minPq.poll();
        }

        return answer;
    }
}