package com.lile.backpack.完全背包;

import com.lile.tools.Asserts;

/**
 * 377. 组合总和IV
 * https://leetcode.cn/problems/combination-sum-iv/description/
 * 难度：中等
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 * <p>
 * <p>
 * 示例 1：
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * <p>
 * 示例 2：
 * 输入：nums = [9], target = 3
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 1000
 * nums 中的所有元素 互不相同
 * 1 <= target <= 1000
 * <p>
 * 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
 */
public class _377组合总和 {
    /**
     * 零钱兑换解决的是组合问题（元素在不同位置都代表同一个含义），而该题解决的是排列问题（元素在不同位置代表不同的含义）
     * <p>
     * 组合问题的遍历顺序是：先物品，再背包，从小到大遍历。
     * 排列问题的遍历顺序时：先背包，再物品，从小到大遍历，内层要判定物品容量在规定范围内。可以保证，在某一重量条件下，物品的各种排列方式都会被记录下来。
     * <p>
     * 二维dp动规五部曲：
     * （1）dp[i][j]含义：表示从下标为[0,i]的整数中选择，每个整数可以取无限次，凑成目标整数有dp[i][j]种排列方法。
     *          在 { 不以任何数做结尾(一个数都不选)，以nums[0]结尾， ... ,nums[i]结尾 } 的容量为j的全排列数之和
     * （2）递推公式：dp[i][j] = dp[i−1][j] + dp[nums.length−1][j−nums[i]]
     *          不以nums[i]做结尾，这种情况的全排列数为dp[i−1][j]。
     *          以nums[i]做结尾，这种情况的全排列数为dp[nums.length−1][j−nums[i]]。背包容量还剩 j−nums[i]，而排列剩下的数以nums数组任何一个数结尾都可以
     * （3）dp数组初始化：dp[i][0] = 1，便于计算
     * （4）遍历顺序：由于是排列问题，遍历先背包，再物品，最内层没遍历到一个物品i，都从0-i再遍历更新一遍。
     * （5）举例推导dp数组
     */
    public int combinationSum4(int[] nums, int target) {
        int[][] dp = new int[nums.length][target + 1];

        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = 1;
        }


        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (i >= 1) {
                    dp[i][j] = dp[i][j] + dp[i - 1][j];
                }
                if (j >= nums[i]) {
                    dp[i][j] = dp[i][j] + dp[nums.length - 1][j - nums[i]];
                }
                System.out.println("[" + i + ", " + j + "] = " + dp[i][j]);
            }
        }

        return dp[nums.length - 1][target];
    }

    public int combinationSum4_1(int[] nums, int target) {
        int[] dp = new int[target + 1];

        dp[0] = 1;

        for (int j = 1; j <= target; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (j >= nums[i]) {
                    dp[j] = dp[j] + dp[j - nums[i]];
                }
            }
        }

        return dp[target];
    }

    public static void main(String[] args) {
        _377组合总和 obj = new _377组合总和();

        int[] nums = {1, 2, 3};
        int target = 4;

        Asserts.test(obj.combinationSum4(nums, target) == 7);
        Asserts.test(obj.combinationSum4_1(nums, target) == 7);
    }

}
