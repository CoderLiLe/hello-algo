package 前缀和;

import tools.Asserts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.cn/problems/continuous-subarray-sum/
 *
 */
public class _523连续的子数组和 {
    /**
     * 使用前缀和与哈希表来解决子数组问题
     * 该方法的核心在于通过前缀和对数组元素的累加，并利用哈希表快速检查是否存在符合特定条件的子数组
     * 具体来说，我们关心的是是否存在一个子数组，其元素和是k的整数倍
     * 为了实现这一点，我们不仅需要记录每个位置的前缀和，还需要记录这些前缀和对k取余的结果
     * 这样，我们就可以通过检查两个不同位置的前缀和对k取余是否相同，来判断它们之间的子数组和是否是k的倍数
     *
     * 时间复杂度：O(n)，因为我们只需遍历数组一次
     * 空间复杂度：O(n)，用于存储前缀和与哈希表
     *
     * @param nums 输入的整数数组
     * @param k 指定的整数k，用于检查子数组和是否为其倍数
     * @return 如果存在至少包含两个元素的子数组，其和为k的倍数，则返回true；否则返回false
     */
    public boolean checkSubarraySum(int[] nums, int k) {
        // 初始化数组长度与前缀和数组
        int n = nums.length;
        int[] sum = new int[n + 1];
        // 计算前缀和，便于后续计算任意子数组的和
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 使用HashSet存储前缀和对k取余的结果，以便快速检查是否存在符合要求的子数组
        Set<Integer> set = new HashSet<>();

        // 从索引2开始遍历，确保检查的子数组长度至少为2
        for (int i = 2; i <= n; i++) {
            // 在检查当前位置前，将前一个可能的左端点的前缀和对k取余的结果存入HashSet
            set.add(sum[i - 2] % k);
            // 如果当前位置的前缀和对k取余的结果在HashSet中已存在，说明找到了符合条件的子数组
            if (set.contains(sum[i] % k)) {
                return true;
            }
        }
        // 如果遍历完数组后没有找到符合条件的子数组，则返回false
        return false;
    }

    /**
     * 暴力
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean checkSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        // 构建前缀数组
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        // 遍历寻找结果
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) {
                int part = sum[j] - sum[i];
                if (part % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _523连续的子数组和 obj = new _523连续的子数组和();
        int[] nums = {23, 2, 4, 6, 7};
        Asserts.test(obj.checkSubarraySum(nums, 6));
        Asserts.test(obj.checkSubarraySum2(nums, 6));
    }

}
