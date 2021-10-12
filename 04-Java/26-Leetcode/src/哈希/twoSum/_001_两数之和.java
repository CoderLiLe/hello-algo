package 哈希.twoSum;

import java.util.HashMap;

public class _001_两数之和 {
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表：元素映射到对应的索引
        for (int i = 0; i < len; i++) {
            index.put(nums[i], i);
        }

        for (int i = 0; i < len; i++) {
            int other = target - nums[i];
            // 如果 other 存在且不是 nums[i] 本身
            if (index.containsKey(other) && index.get(other) != i) {
                return new int[] {i, index.get(other)};
            }
        }

        return new int[] {-1, -1};
    }
}
