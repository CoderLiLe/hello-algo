
package com.lile.股票;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/description/
 */
public class _188_买卖股票的最佳时机IV {

    public static void main(String[] args) {
        _188_买卖股票的最佳时机IV obj = new _188_买卖股票的最佳时机IV();

        int[] prices = new int[]{2, 4, 1};
        int k = 2;
        int res = 2;

        int[] prices2 = new int[]{3, 2, 6, 5, 0, 3};
        int k2 = 2;
        int res2 = 7;

        Times.test("动态规划：三维数组", () -> {
            Asserts.test(obj.maxProfit3(k, prices) == res);
            Asserts.test(obj.maxProfit3(k2, prices2) == res2);
        });
        Times.test("动态规划：使用三维滚动數組", () -> {
            Asserts.test(obj.maxProfit4(k, prices) == res);
            Asserts.test(obj.maxProfit4(k2, prices2) == res2);
        });
        Times.test("动态规划：使用二维滚动數組（空间优化）", () -> {
            Asserts.test(obj.maxProfit5(k, prices) == res);
            Asserts.test(obj.maxProfit5(k2, prices2) == res2);
        });
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
    public int maxProfit3(int maxK, int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int len = prices.length;

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
    public int maxProfit4(int maxK, int[] prices) {
        int len = prices.length;
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
    public int maxProfit5(int maxK, int[] prices) {
        int len = prices.length;

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
