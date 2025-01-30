package 哈希;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _219存在重复元素 {
    /**
     * 检查数组中是否存在两个相同的数字，且它们的索引之差的绝对值最大为k
     * 这个方法通过维护一个哈希表来跟踪每个数字最后出现的位置，从而在遍历数组的同时，
     * 检查是否有满足条件的重复数字
     *
     * @param nums 一个整数数组，其中可能包含重复元素
     * @param k 索引之差的绝对值的最大允许值
     * @return 如果找到至少一对索引之差的绝对值不超过k的相同数字，则返回true；否则返回false
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // 使用哈希表记录每个数字最后出现的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字已经在哈希表中存在，表示之前出现过相同的数字
            if (map.containsKey(nums[i])) {
                // 检查当前数字与之前出现的相同数字的索引之差是否不超过k
                if (Math.abs(i - map.get(nums[i])) <= k) {
                    // 如果索引之差满足条件，返回true
                    return true;
                }
            }
            // 更新或添加当前数字的索引到哈希表中
            map.put(nums[i], i);
        }
        // 遍历完成后，如果没有找到满足条件的重复数字，返回false
        return false;
    }

    public static void main(String[] args) {
        _219存在重复元素 obj = new _219存在重复元素();
        Asserts.test(obj.containsNearbyDuplicate(new int[]{1,2,3,1}, 3));
        Asserts.test(obj.containsNearbyDuplicate(new int[]{1,0,1,1}, 1));
        Asserts.test(!obj.containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
