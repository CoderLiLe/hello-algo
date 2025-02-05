package com.lile.backpack.完全背包;

import com.lile.tools.Asserts;

public class _322零钱兑换 {
    /**
     * 二维dp动规五部曲：
     * （1）dp[i]：凑成总金额i所需的最少的硬币个数
     * （2）递推公式：dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
     * （3）dp数组初始化：dp[0] = 0
     * （4）遍历顺序：coins（物品）放在外循环，target（背包）在内循环。且内循环正序。
     * （5）举例推导dp数组
     *
     * 时间复杂度: O(n * amount)，其中 n 为 coins 的长度
     * 空间复杂度: O(amount)
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for (int coin : coins) { // 遍历物品
            for (int i = coin; i <= amount; i++) { // 遍历背包
                // 如果dp[j - coins[i]]是初始值则跳过
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        _322零钱兑换 obj = new _322零钱兑换();
        Asserts.test(obj.coinChange(new int[] {1, 2, 5}, 11) == 3);
    }

}
