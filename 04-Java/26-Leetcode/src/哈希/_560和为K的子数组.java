package 哈希;

import tools.Asserts;

import java.util.HashMap;
import java.util.Map;

public class _560和为K的子数组 {
    /**
     * 方法一：暴力解法（超时）
     */
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法二：暴力解法的优化
     * 固定了起点，即先固定左边界，然后枚举右边界哈，时间复杂度降了一维
     */
    public int subarraySum2(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            int sum = 0;
            // 区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; right++) {
                sum += nums[right];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法三：前缀和
     * 构建前缀和数组，以快速计算区间和；
     * 注意在计算区间和的时候，下标有偏移。
     *
     * @param nums 原始整数数组
     * @param k    目标和
     * @return 和为k的子数组数量
     */
    public int subarraySum3(int[] nums, int k) {
        int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                // 区间和 [left..right]，注意下标偏移
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 方法四：前缀和 + 哈希表优化
     * 由于只关心次数，不关心具体的解，我们可以使用哈希表加速运算；
     * 由于保存了之前相同前缀和的个数，计算区间总数的时候不是一个一个地加，时间复杂度降到了 O(N)。
     *
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum4(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0, 1);

        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;

            // 先获得前缀和为 preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)) {
                count += preSumFreq.get(preSum - k);
            }

            // 然后维护 preSumFreq 的定义
            preSumFreq.put(preSum, preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /**
     * 暴力枚举
     * 计算数组中和为k的连续子数组的数量
     * 该方法通过双重循环遍历所有可能的子数组，并计算它们的和，以找到和为k的子数组
     *
     * @param nums 输入的整数数组
     * @param k    目标和
     * @return 和为k的连续子数组的数量
     */
    public int subarraySum5(int[] nums, int k) {
        // 初始化计数器为0，用于记录和为k的子数组数量
        int count = 0;
        // 外层循环遍历数组的每个元素，作为子数组的起始位置
        for (int start = 0; start < nums.length; ++start) {
            // 初始化当前子数组的和为0
            int sum = 0;
            // 内层循环从当前起始位置向前遍历，构建子数组并计算和
            for (int end = start; end >= 0; --end) {
                // 将当前元素加到子数组的和中
                sum += nums[end];
                // 如果当前子数组的和等于目标和k，则计数器加一
                if (sum == k) {
                    count++;
                }
            }
        }
        // 返回和为k的子数组数量
        return count;
    }

    /**
     * 计算一个数组中所有和为k的连续子数组的数量
     * 使用前缀和和哈希表来优化查找过程，减少时间复杂度
     *
     * @param nums 输入的整数数组
     * @param k    目标和
     * @return 返回所有和为k的连续子数组的数量
     */
    public int subarraySum6(int[] nums, int k) {
        // 初始化计数器和前缀和变量
        int count = 0, pre = 0;
        // 使用哈希表记录前缀和及其出现的次数
        HashMap<Integer, Integer> mp = new HashMap<>();
        // 为了处理前缀和正好等于k的情况，需要初始化哈希表包含一个键值对(0, 1)
        mp.put(0, 1);
        // 遍历数组，计算前缀和，并更新哈希表
        for (int i = 0; i < nums.length; i++) {
            // 更新前缀和
            pre += nums[i];
            // 如果当前前缀和减去k存在于哈希表中，说明找到了一个或多个符合条件的子数组
            if (mp.containsKey(pre - k)) {
                // 将这些子数组的数量加到计数器上
                count += mp.get(pre - k);
            }
            // 更新哈希表，记录当前前缀和出现的次数
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        // 返回找到的符合条件的子数组数量
        return count;
    }

    public static void main(String[] args) {
        _560和为K的子数组 obj = new _560和为K的子数组();
        int[] nums = new int[] {1, 1, 1};
        Asserts.test(obj.subarraySum(nums, 2) == 2);
        Asserts.test(obj.subarraySum2(nums, 2) == 2);
        Asserts.test(obj.subarraySum3(nums, 2) == 2);
        Asserts.test(obj.subarraySum4(nums, 2) == 2);
        Asserts.test(obj.subarraySum5(nums, 2) == 2);
    }
}
