package com.lile.股票;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 */
public class _122_买卖股票的最佳时机II {

    public static void main(String[] args) {
        _122_买卖股票的最佳时机II obj = new _122_买卖股票的最佳时机II();

        int[] prices = new int[] {7, 1, 5, 3, 6, 4};
        Times.test("暴力法【超时】", () -> {
            Asserts.test(obj.maxProfit1(prices) == 7);
        });
        Times.test("贪心法", () -> {
            Asserts.test(obj.maxProfit2(prices) == 7);
        });
        Times.test("动态规划：二维数组", () -> {
            Asserts.test(obj.maxProfit3(prices) == 7);
        });
        Times.test("动态规划：使用二维滚动數組", () -> {
            Asserts.test(obj.maxProfit4(prices) == 7);
        });
        Times.test("动态规划：使用一维滚动數組", () -> {
            Asserts.test(obj.maxProfit5(prices) == 7);
        });
    }

    private int res;

    /**
     *  暴力法【超时】
     *  时间复杂度：O(n^2)
     *  空间复杂度：O(1)
     */
    public int maxProfit1(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        this.res = 0;
        dfs(prices, 0, len, 0, res);
        return this.res;
    }

    /**
     * 使用深度优先搜索（DFS）来解决最佳买卖股票问题
     * 该方法探索所有可能的交易方案，以找到最大利润
     *
     * @param prices 股价数组，包含了每一天的股票价格
     * @param index  当前是第几天，从 0 开始计数
     * @param len    股价数组的长度，表示总天数
     * @param status 当前是否持有股票，0 表示不持有，1表示持有
     * @param profit 当前累计的收益
     */
    private void dfs(int[] prices, int index, int len, int status, int profit) {
        // 达到最后一天时，更新最大收益并返回
        if (index == len) {
            this.res = Math.max(this.res, profit);
            return;
        }

        // 选择不做任何操作，即跳过当前天
        dfs(prices, index + 1, len, status, profit);

        // 根据当前是否持有股票的状态，决定是否买入或卖出
        if (status == 0) {
            // 当前不持有股票，可以尝试买入股票
            dfs(prices, index + 1, len, 1, profit - prices[index]);

        } else {
            // 当前持有股票，可以尝试卖出股票
            dfs(prices, index + 1, len, 0, profit + prices[index]);
        }
    }

    /**
     * 贪心法
     * 贪心算法的直觉：由于不限制交易次数，只要今天股价比昨天高，就交易。
     * 贪心算法 在每一步总是做出在当前看来最好的选择。
     * 「贪心算法」 和 「动态规划」、「回溯搜索」 算法一样，完成一件事情，是 分步决策 的；
     * 「贪心算法」 在每一步总是做出在当前看来最好的选择
     * 贪心算法和动态规划相比，它既不看前面（也就是说它不需要从前面的状态转移过来），也不看后面（无后效性，后面的选择不会对前面的选择有影响），因此贪心算法时间复杂度一般是线性的，空间复杂度是常数级别的；
     * 这道题 「贪心」 的地方在于，对于 「今天的股价 - 昨天的股价」，得到的结果有 3 种可能：① 正数，② 0，③负数。贪心算法的决策是： 只加正数
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }

        int res = 0;
        for (int i = 1; i < len; i++) {
            // 只要上涨就购买在，不上涨就不购买
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 二维dp动规五部曲：
     * （1）dp[i][j]含义：dp[i][0] 表示第i天持有股票所得最多现金，dp[i][1] 表示第i天不持有股票所得最多现金
     * （2）递推公式：dp[i][0] = max(dp[i - 1][0], dp[i - 1][1]-prices[i])
     *              dp[i][1] = max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
     * （3）dp数组初始化：dp[0][0] -= prices[0]; dp[0][1] = 0
     * （4）遍历顺序：从递推公式可以看出dp[i]都是由dp[i - 1]推导出来的，那么一定是从前向后遍历
     * （5）举例推导dp数组，以示例1，输入：[7,1,5,3,6,4]为例，dp数组状态如下：
     *                   dp[i][0]   dp[i][1]
     *                0    -7          0
     *                1    -1          0
     *                2    -1          4
     *                3     1          4
     *                4    -1          7
     *                5     3          7
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
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);
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
            dp[i % 2][0] = Math.max(dp[(i - 1) % 2][0], dp[(i - 1) % 2][1] - prices[i]);
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
            dp[0] = Math.max(dp[0], dp[1] - prices[i - 1]);
            // 如果 dp[0] 被更新，那么 dp[1] 肯定会被更新为正数的 dp[1]
            // 而不是 dp[0]+prices[i-1]==0 的0，
            // 所以这里使用会改变的dp[0]也是可以的
            // 当然 dp[1] 初始值为 0 ，被更新成 0 也没影响
            // 前一天卖出；或当天卖出, 当天要卖出，得前一天持有才行
            dp[1] = Math.max(dp[1], dp[0] + prices[i - 1]);
        }
        return dp[1];
    }
}
