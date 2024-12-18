import java.util.HashMap;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> completions = new HashMap<>();
        for (int i = 0; i < completion.length; i++) {
            if (!completions.containsKey(completion[i]))
                completions.put(completion[i], 1);
            else
                completions.replace(completion[i], completions.get(completion[i]), completions.get(completion[i]) + 1);
        }

        for (int i = 0; i < participant.length; i++) {
            if (!completions.containsKey(participant[i])) {
                answer = participant[i];
                break;
            } if (completions.containsKey(participant[i]) && completions.get(participant[i]) == 1) {
                completions.remove(participant[i]);
            } else
                completions.replace(participant[i], completions.get(participant[i]), completions.get(participant[i]) - 1);
        }

        return answer;
    }
}