package 动态规划;

import tools.Asserts;
import tools.Times;

public class _279_完全平方数 {
    public static void main(String[] args) {
        Times.test("动态规划", () -> {
            Asserts.test(numSquares(12) == 3);
            Asserts.test(numSquares(13) == 2);
            Asserts.test(numSquares(55) == 4);
            Asserts.test(numSquares(735577) == 3);
        });
    }

    /**
     * 动态规划：
     * T = O(n∗sqrt(n))
     */
    public static int numSquares(int n) {
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
}
