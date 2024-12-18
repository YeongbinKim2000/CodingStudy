import java.util.HashMap;
class Solution {
    public int solution(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                map.replace(nums[i], map.get(nums[i]), map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }

        if (nums.length / 2 <= map.size())
            return nums.length / 2;
        else
            return map.size();
    }
}