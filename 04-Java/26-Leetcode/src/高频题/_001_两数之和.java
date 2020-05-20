package 高频题;

import java.util.HashMap;
import java.util.Map;

public class _001_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[] {-1, -1};

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer idx = target - nums[i];
            if (map.containsKey(idx)) {
                return new int[] {map.get(idx), i};
            }

            map.put(nums[i], i);
        }

        return new int[] {-1, -1};
    }
}
