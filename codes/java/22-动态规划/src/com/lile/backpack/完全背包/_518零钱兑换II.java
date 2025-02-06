package com.lile.backpack.完全背包;

import com.lile.tools.Asserts;

/**
 * 518. 零钱兑换II
 * https://leetcode.cn/problems/coin-change-ii/description/
 * 难度：中等
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class _518零钱兑换II {
    /**
     * 二维dp
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[i][j] 表示从下标为[0,i]的硬币中，每个硬币可以取无限次，能够凑满容量为j的背包，有dp[i][j]种组合方法。
     * （2）确定递推公式
     *      本题中，硬币i的容量是coins[i]，价值也是coins[i]：
     *          不选硬币i：即背包容量为j，不选硬币i，装满有dp[i - 1][j]中方法
     *          选硬币i： 即先空出硬币i的容量，背包容量为（j - 硬币i容量），放满背包有 dp[i][j - 硬币i容量] 种方法
     *      所以递推公式：if (coins[i] > j) dp[i][j] = dp[i - 1][j];
     *                  else dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
     * （3）dp数组如何初始化
     *      dp[i][0] = 1, 表示容量为0，有1种方法，即不装
     *      dp[0][j]的含义：用「物品0」（即coins[0]） 装满 背包容量为j的背包，有几种组合方法
     *          如果 j 可以整除 物品0，那么装满背包就有1种组合方法
     *
     * （4）确定遍历顺序
     *      二维DP数组的完全背包的两个for循环先后顺序是无所谓的
     *
     * （5）举例推导dp数组
     *      以amount为5，coins为：[2,3,5] 为例：
     *      dp数组应该是这样的：
     *      1 0 1 0 1 0
     *      1 0 1 1 1 1
     *      1 0 1 1 1 2
     * 时间复杂度: O(mn)，其中 m 是amount，n 是 coins 的长度
     * 空间复杂度: O(mn)
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];

        // 初始化最左列
        for (int i = 0; i < coins.length; i++) {
            // dp[i][0] = 1, 表示容量为0，有1种方法，即不装
            dp[i][0] = 1;
        }

        // 初始化最上行
        for (int j = 1; j < amount + 1; j++) {
            dp[0][j] = j % coins[0] == 0 ? 1 : 0;
        }

        // 以下遍历顺序行列可以颠倒
        for (int i = 1; i < coins.length; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (coins[i] <= j) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[coins.length - 1][amount];
    }

    /**
     * 一维dp
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[j] 凑成总金额j的货币组合数为dp[j]
     * （2）确定递推公式
     *      dp[j] = dp[j] + dp[j - coins[i]]
     * （3）dp数组如何初始化
     *      装满背包容量为0 的方法是1，即不放任何物品，dp[0] = 1
     *
     * （4）确定遍历顺序
     *      外层for循环遍历物品（钱币），内层for遍历背包（金钱总额）,dp[j]里计算的是组合数
     *      外层for循环遍历背包（金钱总额）， 内层for遍历物品（钱币）,dp[j]里计算的是排列数
     *
     * （5）举例推导dp数组
     *      以amount为5，coins为：[2,3,5] 为例：
     *      dp数组应该是这样的：
     *      1 0 1 0 1 0
     *      1 0 1 1 1 1
     *      1 0 1 1 1 2
     *
     * 时间复杂度: O(mn)，其中 m 是amount，n 是 coins 的长度
     * 空间复杂度: O(m)
     */
    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        _518零钱兑换II obj = new _518零钱兑换II();

        int[] coins = {2, 3, 5};
        int amount = 5;

        Asserts.test(obj.change(amount, coins) == 2);
        Asserts.test(obj.change2(amount, coins) == 2);
    }
}
