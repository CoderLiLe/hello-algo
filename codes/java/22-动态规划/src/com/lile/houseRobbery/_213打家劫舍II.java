package com.lile.houseRobbery;

import com.lile.tools.Asserts;

public class _213打家劫舍II {
    /**
     * 对于一个数组，成环的话主要有如下三种情况：
     * 情况一：考虑不包含首尾元素
     * 情况二：考虑包含首元素，不包含尾元素
     * 情况三：考虑包含尾元素，不包含首元素
     *
     * 情况二 和 情况三 都包含了情况一
     */
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int result1 = robRange(nums, 0, len - 2); // 情况二
        int result2 = robRange(nums, 1, len - 1); // 情况三
        return Math.max(result1, result2);
    }

    // 198.打家劫舍的逻辑
    int robRange(int[] nums, int start, int end) {
        if (end == start) {
            return nums[start];
        }
        int len = nums.length;
        int[] dp = new int[len];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[end];
    }

    public static void main(String[] args) {
        _213打家劫舍II obj = new _213打家劫舍II();
        int[] nums = {2, 3, 2};
        Asserts.test(obj.rob(nums) == 3);
    }
}
