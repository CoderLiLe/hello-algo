package com.lile.backpack.zero_one;

import com.lile.tools.Asserts;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/last-stone-weight-ii/description/
 * 题目难度：中等
 *
 * 有一堆石头，每块石头的重量都是正整数。
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 * 示例：
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 *
 * 组合 2 和 4，得到 2，所以数组转化为 [2,7,1,8,1]，
 * 组合 7 和 8，得到 1，所以数组转化为 [2,1,1,1]，
 * 组合 2 和 1，得到 1，所以数组转化为 [1,1,1]，
 * 组合 1 和 1，得到 0，所以数组转化为 [1]，这就是最优值。
 *
 * 提示：
 * 1 <= stones.length <= 30
 * 1 <= stones[i] <= 1000
 */
public class _1049最后一块石头的重量II {
    /**
     * 思路：
     * 416. 分割等和子集 是求背包是否正好装满，而本题是求背包最多能装多少。
     * 物品就是石头，物品的重量为stones[i]，物品的价值也为stones[i]。
     *
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[j]表示容量（这里说容量更形象，其实就是重量）为j的背包，最多可以背最大重量为dp[j]。
     *      相对于 01背包，本题中，石头的重量是 stones[i]，石头的价值也是 stones[i] 。
     *      “最多可以装的价值为 dp[j]” 等同于 “最多可以背的重量为dp[j]”
     * （2）确定递推公式
     *      01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *      本题，相当于背包里放入石头，那么石头i的重量是stones[i]，其价值也是stones[i]。
     *      所以递推公式：dp[j] = max(dp[j], dp[j - stones[i]] + stones[i]);
     * （3）dp数组如何初始化
     *      既然 dp[j]中的j表示容量，那么最大容量（重量）是多少呢，就是所有石头的重量和。
     *      因为提示中给出1 <= stones.length <= 30，1 <= stones[i] <= 1000，所以最大重量就是30 * 1000 。
     *      而我们要求的target其实只是最大重量的一半，所以dp数组开到15000大小就可以了。
     *      当然也可以把石头遍历一遍，计算出石头总重量 然后除2，得到dp数组的大小。
     *      因为重量都不会是负数，所以dp[j]都初始化为0就可以了
     * （4）确定遍历顺序
     *      如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
     * （5）举例推导dp数组
     *      dp[j]的数值一定是小于等于j的。
     *      dp[target]里是容量为target的背包所能背的最大重量。
     *      那么分成两堆石头，一堆石头的总重量是dp[target]，另一堆就是sum - dp[target]。
     *      在计算target的时候，target = sum / 2 因为是向下取整，所以sum - dp[target] 一定是大于等于dp[target]的。
     *      那么相撞之后剩下的最小石头重量就是 (sum - dp[target]) - dp[target]。
     */


    /**
     * 动态规划：二维数组实现
     * 时间复杂度：O(m × n) , m是石头总重量（准确的说是总重量的一半），n为石头块数
     * 空间复杂度：O(m × n)
     * @param stones
     * @return
     */
    public int lastStoneWeightII(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum >> 1;
        int[][] dp = new int[stones.length][target + 1];
        for (int i = stones[0]; i < target + 1; i++) {
            dp[0][i] = stones[0];
        }

        for (int i = 1; i < stones.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j >= stones[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return sum - dp[stones.length - 1][target] * 2;
    }

     /**
     * 动态规划：一维数组实现
     * 时间复杂度：O(m × n) , m是石头总重量（准确的说是总重量的一半），n为石头块数
     * 空间复杂度：O(m)
     */
    public int lastStoneWeightII2(int[] stones) {
        int sum = Arrays.stream(stones).sum();
        int target = sum >> 1;
        int[] dp = new int[target + 1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = target; j >= stones[i]; j--) {
                // 两种情况，要么放，要么不放
                dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
            }
        }
        return sum - dp[target] * 2;
    }

    public static void main(String[] args) {
        _1049最后一块石头的重量II obj = new _1049最后一块石头的重量II();
        int[] stones = {2, 7, 4, 1, 8, 1};
        Asserts.test(obj.lastStoneWeightII(stones) == 1);
        Asserts.test(obj.lastStoneWeightII2(stones) == 1);
    }
}
