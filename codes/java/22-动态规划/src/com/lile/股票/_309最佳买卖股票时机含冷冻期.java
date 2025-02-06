package com.lile.股票;

import com.lile.tools.Asserts;

/**
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 */
public class _309最佳买卖股票时机含冷冻期 {
    /**
     * 1、dp[i][j]：表示第i天，第j个状态，最大利润，一共有四种状态：
     *   持有股票状态dp[i][0]（今天买入股票，或者是之前就买入了股票然后没有操作，一直持有）
     *   保持不持有股票状态dp[i][1]（之前就卖出了股票然后没有操作）
     *   今天卖出股票dp[i][2]
     *   冷冻期状态dp[i][3]，但冷冻期状态不可持续，只有一天！
     * 2、递推公式：
     *  今天持有股票dp[i][0]：前一天持有股票dp[i - 1][0]；今天买入股票：前一天不持有股票dp[i - 1][1]；者前一天为冷冻期dp[i][3]；
     *  今天保持不持有股票dp[i][1]：前一天卖出了股票dp[i - 1][1]；前一天为冷冻期dp[i - 1][3]
     *  今天卖出股票dp[i][2]：前一天持有股票dp[i - 1][0]
     *  今天为冷冻期dp[i][3]：前一天卖出了股票dp[i - 1][2]
     *  综上分析，递推代码如下：
     *  dp[i][0] = max(dp[i - 1][0], max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
     *  dp[i][1] = max(dp[i - 1][1], dp[i - 1][3]);
     *  dp[i][2] = dp[i - 1][0] + prices[i];
     *  dp[i][3] = dp[i - 1][2];
     *  3、初始化：
     *  dp[0][0] = -prices[0]
     *  dp[0][1] = dp[0][2] = dp[0][3] = 0;
     *  4、遍历顺序：dp[i]依赖dp[i - 1]，所以从前往后遍历
     *  5、举例推导dp数组
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len][4];
        dp[0][0] = -prices[0];
        dp[0][1] = dp[0][2] = dp[0][3] = 0;
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], Math.max(dp[i - 1][3], dp[i - 1][1]) - prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(dp[len - 1][1], Math.max(dp[len - 1][2], dp[len - 1][3]));
    }

    /**
     * 滚动数组优化
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[2][4];
        dp[0][0] = -prices[0];
        dp[0][1] = dp[0][2] = dp[0][3] = 0;
        for (int i = 1; i < len; i++) {
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], Math.max(dp[(i - 1) % 2][3], dp[(i - 1) % 2][1]) - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], dp[(i - 1) % 2][3]);
            dp[i % 2][2] = dp[(i - 1) % 2][0] + prices[i];
            dp[i % 2][3] = dp[(i - 1) % 2][2];
        }
        return Math.max(dp[(len - 1) % 2][1], Math.max(dp[(len - 1) % 2][2], dp[(len - 1) % 2][3]));
    }

    /**
     * 一维数组优化
     */
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        int[] dp = new int[4];
        dp[0] = -prices[0];
        dp[1] = dp[2] = dp[3] = 0;
        for (int i = 1; i < len; i++) {
            int pre0 = dp[0];
            int pre2 = dp[2];
            dp[0] = Math.max(dp[0], Math.max(dp[3], dp[1]) - prices[i]);
            dp[1] = Math.max(dp[1], dp[3]);
            dp[2] = pre0 + prices[i];
            dp[3] = pre2;
        }
        return Math.max(dp[1], Math.max(dp[2], dp[3]));
    }

    /**
     * 1、dp[i][j]：表示第i天，第j个状态，最大利润，一共有两种状态：
     *   第i天持有股票收益dp[i][0]
     *   第i天不持有股票收益[i][1]
     * 2、递推公式：
     *  第i天持有股票收益dp[i][0]：前一天持有股票dp[i - 1][0]；前一天为冷冻期，则第i-2天不持有股票dp[i - 2][1]，今天可以买入
     *  第i天不持有股票收益[i][1]：前一天不持有股票dp[i - 1][1]；前一天持有股票dp[i - 1][0]，今天卖出
     *  综上分析，递推代码如下：
     *  dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i - 1]);
     *  dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
     *  3、初始化：
     *  dp[1][1] = 0;
     *  4、遍历顺序：dp[i]依赖dp[i - 1] 和 dp[i - 2]，所以从前往后遍历，i从2开始，防止数组越界
     *  5、举例推导dp数组
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        int[][] dp = new int[len + 1][2];
        dp[1][0] = -prices[0];

        for (int i = 2; i <= len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 2][1] - prices[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i - 1]);
        }
        return dp[len][1];
    }

    public static void main(String[] args) {
        _309最佳买卖股票时机含冷冻期 obj = new _309最佳买卖股票时机含冷冻期();

        int[] prices = new int[]{1, 2, 3, 0, 2};
        Asserts.test(obj.maxProfit(prices) == 3);
        Asserts.test(obj.maxProfit2(prices) == 3);
        Asserts.test(obj.maxProfit3(prices) == 3);
        Asserts.test(obj.maxProfit4(prices) == 3);
    }
}
