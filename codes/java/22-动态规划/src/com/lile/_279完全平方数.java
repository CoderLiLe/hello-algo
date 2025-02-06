package com.lile;

import tools.Asserts;
import tools.Times;

import java.util.Arrays;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *
 * 提示：
 * 1 <= n <= 10^4
 */
public class _279完全平方数 {

    /**
     * 定义递归函数 dfs，用于计算使用前 i 个完全平方数可以凑成 j 的最小数量
     * dfs(i,j) 表示从前 i 个完全平方数中选一些数（可以重复选），满足元素和恰好等于 j，最少要选的数字个数
     * T = O(n ∗ sqrt(n))
     * S = O(n ∗ sqrt(n))
     *
     * @param i
     * @param j
     * @return
     */
    private static int dfs(int i, int j, int[][] memo) {
        // 如果没有可用的完全平方数，则只有当目标金额为 0 时才返回 0。
        if (i == 0) {
            return j == 0 ? 0 : Integer.MAX_VALUE;
        }

        // 如果之前已经计算过此状态，则直接返回结果。
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // 如果当前目标金额小于当前完全平方数，则只能不选这个数。
        if (j < i * i) {
            return memo[i][j] = dfs(i - 1, j, memo);
        }

        // 否则，选择或不选择当前的完全平方数，取两者中的较小值，并保存到备忘录中。
        return memo[i][j] = Math.min(dfs(i - 1, j, memo), dfs(i, j - i * i, memo) + 1);
    }

    /**
     * 记忆化搜索
     *
     * @param n
     * @return 返回组成 n 的最少完全平方数的数量
     */
    public int numSquares(int n) {
        int[][] memo = new int[n + 1][n + 1];
        for (int[] row : memo) {
            // -1 表示没有计算过
            Arrays.fill(row, -1);
        }

        // 计算最大的可能完全平方数的索引，即不大于 sqrt(n) 的最大整数。
        return dfs((int) Math.sqrt(n), n, memo);
    }

    /**
     * 翻译成递推：自底向上计算
     * T = O(n ∗ sqrt(n))
     * S = O(n ∗ sqrt(n))
     */
    public int numSquares2(int n) {
        int[][] memo = new int[n + 1][n + 1];
        Arrays.fill(memo[0], Integer.MAX_VALUE);

        memo[0][0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (j < i * i) {
                    // 只能不选
                    memo[i][j] = memo[i - 1][j];
                } else {
                    // 不选 vs 选
                    memo[i][j] = Math.min(memo[i - 1][j], memo[i][j - i * i] + 1);
                }
            }
        }
        return memo[(int) Math.sqrt(n)][n];
    }

    /**
     * 动态规划：
     * T = O(n ∗ sqrt(n))
     * S = O(n)
     */
    public int numSquares3(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 最坏的情况就是每次 +1
            dp[i] = i;
            for (int j = 1; i - j * j >= 0; j++) {
                // 动态转移方程
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return  dp[n];
    }

    public static void main(String[] args) {
        _279完全平方数 obj = new _279完全平方数();
        Times.test("记忆化搜索", () -> {
            Asserts.test(obj.numSquares(1) == 1);
            Asserts.test(obj.numSquares(12) == 3);
            Asserts.test(obj.numSquares(13) == 2);
            Asserts.test(obj.numSquares(55) == 4);
        });

        Times.test("翻译成递推：自底向上计算", () -> {
            Asserts.test(obj.numSquares2(1) == 1);
            Asserts.test(obj.numSquares2(12) == 3);
            Asserts.test(obj.numSquares2(13) == 2);
            Asserts.test(obj.numSquares2(55) == 4);
        });

        Times.test("动态规划", () -> {
            Asserts.test(obj.numSquares(1) == 1);
            Asserts.test(obj.numSquares3(12) == 3);
            Asserts.test(obj.numSquares3(13) == 2);
            Asserts.test(obj.numSquares3(55) == 4);
        });
    }
}
