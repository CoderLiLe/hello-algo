package 高频题;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _659_分割数组为连续子序列 {
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> need = new HashMap<>();

        // 统计 nums 中元素的频率
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (0 == freq.get(num)) {
                // 已经被用到其他子序列中
                continue;
            }

            // 先判断 num 是否能接到其他子序列后面
            if (need.containsKey(num) && need.get(num) > 0) {
                // num 可以接到之前的某个序列后面
                freq.put(num, freq.get(num) - 1);
                // 对 num 的需求减一
                need.put(num, need.get(num) - 1);
                // 对 num + 1 的需求加一
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);
            } else if (freq.getOrDefault(num, 0) > 0
                    && freq.getOrDefault(num + 1, 0) > 0
                    && freq.getOrDefault(num + 2, 0) > 0) {
                // 将 num 作为开头，新建一个长度为 3 的子序列 [num, num+1, num+2]
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                // 对 num + 3 的需求加一
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);
            } else {
                // 两种情况都不符合，则无法分配
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        _659_分割数组为连续子序列 obj = new _659_分割数组为连续子序列();
        int[] nums = {1,2,3,3,4,5};
        Asserts.test(true == obj.isPossible(nums));

        int[] nums2 = {1,2,3,3,4,4,5,5};
        Asserts.test(true == obj.isPossible(nums2));

        int[] nums3 = {1,2,3,4,4,5};
        Asserts.test(false == obj.isPossible(nums3));
    }
}
