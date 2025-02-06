
package com.lile.股票;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class _123_买卖股票的最佳时机III {

    public static void main(String[] args) {
        _123_买卖股票的最佳时机III obj = new _123_买卖股票的最佳时机III();

        int[] prices = new int[]{3, 3, 5, 0, 0, 3, 1, 4};
        int res = 6;

        int[] prices2 = new int[]{1, 2, 3, 4, 5};
        int res2 = 4;

        Times.test("动态规划：二维数组", () -> {
            Asserts.test(obj.maxProfit1(prices) == res);
            Asserts.test(obj.maxProfit1(prices2) == res2);
        });
        Times.test("动态规划：二维数组（空间优化）", () -> {
            Asserts.test(obj.maxProfit2(prices) == res);
            Asserts.test(obj.maxProfit2(prices2) == res2);
        });
        Times.test("动态规划：三维数组", () -> {
            Asserts.test(obj.maxProfit3(prices) == res);
            Asserts.test(obj.maxProfit3(prices2) == res2);
        });
        Times.test("动态规划：使用三维滚动數組", () -> {
            Asserts.test(obj.maxProfit4(prices) == res);
            Asserts.test(obj.maxProfit4(prices2) == res2);
        });
        Times.test("动态规划：使用二维滚动數組（空间优化）", () -> {
            Asserts.test(obj.maxProfit5(prices) == res);
            Asserts.test(obj.maxProfit5(prices2) == res2);
        });
    }

    /**
     * 由于我们最多可以完成两笔交易，因此在任意一天结束之后，我们会处于以下五个状态中的一种：
     * （1）未进行过任何操作；
     * （2）只进行过一次买操作；
     * （3）进行了一次买操作和一次卖操作，即完成了一笔交易；
     * （4）在完成了一笔交易的前提下，进行了第二次买操作；
     * （5）完成了全部两笔交易。
     * <p>
     * 二维dp动规五部曲：
     * （1）dp[i][j]含义：第i天，j为 [0 - 4] 五个状态，dp[i][j]表示第i天状态j所剩最大现金
     * （2）递推公式：dp[i][1] = max(dp[i-1][0] - prices[i], dp[i - 1][1]);
     * dp[i][2] = max(dp[i - 1][1] + prices[i], dp[i - 1][2]);
     * dp[i][3] = max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
     * dp[i][4] = max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
     * （3）dp数组初始化：dp[0][0] = 0; dp[0][1] = -prices[0];  dp[0][2] = 0;   dp[0][2] = 0;   dp[0][2] = 0;
     * （4）遍历顺序：从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历
     * （5）举例推导dp数组
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n × 5)
     *
     * @param prices
     * @return
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        // 边界判断, 题目中 length >= 1, 所以可省去
        if (prices.length == 0) {
            return 0;
        }

        /*
         * 定义 5 种状态:
         * 0: 没有操作, 1: 第一次买入, 2: 第一次卖出, 3: 第二次买入, 4: 第二次卖出
         */
        int[][] dp = new int[len][5];
        dp[0][1] = -prices[0];
        // 初始化第二次买入的状态是确保 最后结果是最多两次买卖的最大利润
        dp[0][3] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] + prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] - prices[i]);
            dp[i][4] = Math.max(dp[i - 1][4], dp[i - 1][3] + prices[i]);
        }

        return dp[len - 1][4];
    }

    /**
     * 空间优化
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        int[] dp = new int[4];
        // 存储两次交易的状态就行了
        // dp[0]代表第一次交易的买入
        dp[0] = -prices[0];
        // dp[1]代表第一次交易的卖出
        dp[1] = 0;
        // dp[2]代表第二次交易的买入
        dp[2] = -prices[0];
        // dp[3]代表第二次交易的卖出
        dp[3] = 0;
        for (int i = 1; i <= prices.length; i++) {
            // 要么保持不变，要么没有就买，有了就卖
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
            // 这已经是第二次交易了，所以得加上前一次交易卖出去的收获
            dp[2] = Math.max(dp[2], dp[1] - prices[i - 1]);
            dp[3] = Math.max(dp[3], dp[2] + prices[i - 1]);
        }
        return dp[3];
    }

    /**
     * 三维dp动规五部曲：
     * （1）dp[i][k][j]含义：dp[i][k][0] 表示第i天第k次交易持有股票的情况下的最大利润
     *                      dp[i][k][1] 表示第i天第k次交易不持有股票的情况下的最大利润
     * 这里，第k次购入股票视为进行第k次交易
     * （2）递推公式：
     * 第i天持有股票：  dp[i][k][0] = max(dp[i - 1][k][0], dp[i - 1][k - 1][1] - prices[i])
     * 第i天不持有股票：dp[i][k][1] = max(dp[i - 1][k][1], dp[i - 1][k][0] + prices[i]);
     * （3）dp数组初始化：dp[0][k][0] = -prices[0];
     * （4）遍历顺序：从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历
     * （5）举例推导dp数组
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int len = prices.length;
        int maxK = 2;

        int[][][] dp = new int[len][maxK + 1][2];
        for (int k = 0; k <= maxK; k++) {
            // 第1天，第k次交易持有股票初始化
            dp[0][k][0] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int k = 1; k <= maxK; k++) {
                // 第i天第k次交易持有股票
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k - 1][1] - prices[i]);
                // 第i天第k次交易不持有股票
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k][0] + prices[i]);
            }
        }
        return dp[len - 1][maxK][1];
    }

    /**
     * 动态规划：使用三维滚动數組
     */
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        int maxK = 2;
        int[][][] dp = new int[2][maxK + 1][2];
        for (int k = 0; k <= maxK; k++) {
            // 第1天，第k次交易持有股票初始化
            dp[0][k][0] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int k = 1; k <= maxK; k++) {
                // 第i天第k次交易持有股票
                dp[i % 2][k][0] = Math.max(dp[(i - 1) % 2][k][0], dp[(i - 1) % 2][k - 1][1] - prices[i]);
                // 第i天第k次交易不持有股票
                dp[i % 2][k][1] = Math.max(dp[(i - 1) % 2][k][1], dp[(i - 1) % 2][k][0] + prices[i]);
            }
        }
        return dp[(len - 1) % 2][maxK][1];
    }

    /**
     * 动态规划：使用二维滚动數組
     */
    public int maxProfit5(int[] prices) {
        int len = prices.length;
        int maxK = 2;

        int[][] dp = new int[maxK + 1][2];
        for (int k = 0; k <= maxK; k++) {
            // 第1天，第k次交易持有股票初始化
            dp[k][0] = -prices[0];
        }

        for (int i = 1; i < len; i++) {
            for (int k = 1; k <= maxK; k++) {
                // 第i天第k次交易持有股票
                dp[k][0] = Math.max(dp[k][0], dp[k - 1][1] - prices[i]);
                // 第i天第k次交易不持有股票
                dp[k][1] = Math.max(dp[k][1], dp[k][0] + prices[i]);
            }
        }
        return dp[maxK][1];
    }
}
