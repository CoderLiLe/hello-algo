package com.lile.子序列;


import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 */
public class _053最大子数组和 {
    /**
     * 1、dp[i]：以 nums[i] 结尾的连续子数组的最大和
     * 2、递推公式：dp[i] = Math.max(nums[i], nums[i] + dp[i - 1])
     * 3、初始化：从递推公式可以看出来dp[i]是依赖于dp[i - 1]的状态，dp[0]就是递推公式的基础
     *          dp[0] = nums[0]
     * 4、遍历顺序：递推公式中dp[i]依赖于dp[i - 1]的状态，需要从前向后遍历
     * 5、举例推导dp数组
     */
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < len; i++) {
            // 状态转移公式
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);

            // max 保存dp[i]的最大值
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int dp = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp <= 0) {
                dp = nums[i];
            } else {
                dp = dp + nums[i];
            }
            max = Math.max(max, dp);
        }
        return max;
    }

    public static void main(String[] args) {
        _053最大子数组和 obj = new _053最大子数组和();
        int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = 6;

        Times.test("动态规划", () -> {
            Asserts.test(obj.maxSubArray(nums) == res);
        });
        Times.test("动态规划（优化）", () -> {
            Asserts.test(obj.maxSubArray2(nums) == res);
        });
    }
}
