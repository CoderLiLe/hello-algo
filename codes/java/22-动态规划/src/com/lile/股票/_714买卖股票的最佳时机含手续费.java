package com.lile.股票;

import com.lile.tools.Asserts;

public class _714买卖股票的最佳时机含手续费 {
    /**
     * ⚠️可以选择买入时支付手续费，也可以选择卖出时支付手续费
     * 此处选择卖出时支付手续费
     * 二维dp动规五部曲：
     * （1）dp[i][j]含义：dp[i][0] 表示第i天持有股票所得最多现金，dp[i][1] 表示第i天不持有股票所得最多现金
     * （2）递推公式：dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]-prices[i])
     *              dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0] - fee);
     * （3）dp数组初始化：dp[0][0] -= prices[0]; dp[0][1] = 0
     * （4）遍历顺序：从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历
     * （5）举例推导dp数组
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int len = prices.length;

        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i] - fee, dp[i - 1][1]);
        }
        return dp[len - 1][1];
    }

    /**
     * 买入时支付手续费
     */
    public int maxProfit2(int[] prices, int fee) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int len = prices.length;

        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[len][2];
        dp[0][0] = -prices[0] - fee;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return Math.max(dp[len - 1][0], dp[len - 1][1]);
    }

    public static void main(String[] args) {
        _714买卖股票的最佳时机含手续费 obj = new _714买卖股票的最佳时机含手续费();

        int[] prices = new int[]{1, 3, 2, 8, 4, 9};
        int fee = 2;
        Asserts.test(obj.maxProfit(prices, fee) == 8);
        Asserts.test(obj.maxProfit2(prices, fee) == 8);
    }
}
