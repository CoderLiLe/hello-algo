package com.lile;

import tools.Asserts;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 子数组是数组中的一个连续部分。
 */
public class _053最大子序和 {
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        // base case
        // 第一个元素前面没有子数组
        dp[0] = nums[0];
        // 状态转移方程
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        // 得到 nums 的最大子数组
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

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
        _053最大子序和 obj = new _053最大子序和();
        Asserts.test(obj.maxSubArray(new int[] {1, 2, 3, 4, 5}) == 15);
        Asserts.test(obj.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
        Asserts.test(obj.maxSubArray2(new int[] {1, 2, 3, 4, 5}) == 15);
        Asserts.test(obj.maxSubArray2(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6);
    }
}
