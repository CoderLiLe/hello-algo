package com.lile.股票;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 */
public class _121_买卖股票的最佳时机 {

    public static void main(String[] args) {
        _121_买卖股票的最佳时机 obj = new _121_买卖股票的最佳时机();

        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        Times.test("暴力法【超时】", () -> {
            Asserts.test(obj.maxProfit1(prices) == 5);
        });
        Times.test("贪心法", () -> {
            Asserts.test(obj.maxProfit2(prices) == 5);
        });
        Times.test("动态规划：二维数组", () -> {
            Asserts.test(obj.maxProfit3(prices) == 5);
        });
        Times.test("动态规划：使用二维滚动數組", () -> {
            Asserts.test(obj.maxProfit4(prices) == 5);
        });
        Times.test("动态规划：使用一维滚动數組", () -> {
            Asserts.test(obj.maxProfit5(prices) == 5);
        });
        Times.test("抽象动态规划", () -> {
            Asserts.test(obj.maxProfit6(prices) == 5);
        });
    }

    /**
     *  暴力法【超时】
     *  时间复杂度：O(n^2)
     *  空间复杂度：O(1)
     */
    public int maxProfit1(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++){
                result = Math.max(result, prices[j] - prices[i]);
            }
        }
        return result;
    }

    /**
     * 贪心法
     * 因为股票就买卖一次，那么贪心的想法很自然就是取最左最小值，取最右最大值，那么得到的差值就是最大利润。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit2(int[] prices) {
        int low = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < prices.length; i++) {
            // 取最左最小价格
            low = Math.min(low, prices[i]);
            // 直接取最大区间利润
            result = Math.max(result, prices[i] - low);
        }
        return result;
    }

    /**
     * 二维dp动规五部曲：
     * （1）dp[i][j]含义：dp[i][0] 表示第i天持有股票所得最多现金，dp[i][1] 表示第i天不持有股票所得最多现金
     * （2）递推公式：dp[i][0] = max(dp[i - 1][0], -prices[i])
     *              dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
     * （3）dp数组初始化：dp[0][0] -= prices[0]; dp[0][1] = 0
     * （4）遍历顺序：从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历
     * （5）举例推导dp数组，以示例1，输入：[7,1,5,3,6,4]为例，dp数组状态如下：
     *                   dp[i][0]   dp[i][1]
     *                0    -7          0
     *                1    -1          0
     *                2    -1          4
     *                3    -1          4
     *                4    -1          5
     *                5    -1          5
     */
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int length = prices.length;

        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[length][2];
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][1];
    }

    /**
     * 动态规划：使用二维滚动數組
     */
    public int maxProfit4(int[] prices) {
        int len = prices.length;
        int dp[][] = new int[2][2];

        dp[0][0] = - prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < len; i++){
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], - prices[i]);
            dp[i % 2][1] = Math.max(dp[(i - 1) % 2][1], prices[i] + dp[(i - 1) % 2][0]);
        }
        return dp[(len - 1) % 2][1];
    }

    /**
     * 动态规划：使用一维滚动數組
     */
    public int maxProfit5(int[] prices) {
        int[] dp = new int[2];
        // 记录一次交易，一次交易有买入卖出两种状态
        // 0代表持有，1代表卖出
        dp[0] = -prices[0];
        dp[1] = 0;
        // 可以参考斐波那契问题的优化方式
        // 我们从 i=1 开始遍历数组，一共有 prices.length 天，
        // 所以是 i<=prices.length
        for (int i = 1; i <= prices.length; i++) {
            // 前一天持有；或当天买入
            dp[0] = Math.max(dp[0], -prices[i - 1]);
            // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
            // 而不是 dp[0]+prices[i-1]==0 的0，
            // 所以这里使用会改变的dp[0]也是可以的
            // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
            // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }

    /**
     * 抽象动态规划
     */
    public int maxProfit6(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        // 前面扫描过的最小价格
        int minPrice = prices[0];
        // 前面扫描过的最大利润
        int maxProfit = 0;
        // 扫描所有价格
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else { // 把第 i 天的股票卖出
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }
}
