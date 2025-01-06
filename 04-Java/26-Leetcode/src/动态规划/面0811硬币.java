package 动态规划;

import tools.Asserts;
import tools.Times;

/**
 * https://leetcode.cn/problems/coin-lcci/description/
 * <p>
 * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
 */
public class 面0811硬币 {
    final static int MOD = 1000000007;

    /**
     * 方法 1 ： 二维 dp 比较直观的解法
     */
    private static int waysToChange1(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        // dp[i][j] 表示 i 种硬币组成面值为 j 时的方法数
        int[][] dp = new int[5][n + 1];
        // base case
        // dp[0][j] 0 种硬币组成面值 j，不可能有方案，因此是 0， java 初始化时数组是 0，不用特殊处理
        // dp[i][0] 多种硬币组成面值 0，只有一种方案，就是一枚也不选
        for (int i = 1; i <= 4; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= n; j++) {
                // 下面👇这部分代码是可以进一步改写的，因为从状态转移方程里面可以看到都有 dp[i-1][j],
                // 因此可以直接不用判断就赋值给 dp[i][j]，判断后再加上『 选择当前硬币时 』的补偿值就可以了

                if (j - coins[i - 1] < 0) {                   // 要组成的面值比当前硬币金额小，该硬币不可以选择
                    dp[i][j] = dp[i - 1][j] % MOD;  // 只能由 i - 1 中硬币来组成面值 j
                } else {
                    // 当前硬币可以不选，也可以选择
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i - 1]]) % MOD;
                }
            }
        }
        return dp[4][n];
    }

    /**
     * 方法 2 ： 进一步一维 dp ，从状态转移方程可以看出，dp[i][j] 仅仅和 dp[i-1]的状态有关，所以可以压缩为 1 维
     */
    private static int waysToChange2(int n) {
        int[] coins = new int[]{1, 5, 10, 25};
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = 1; i <= n; i++) {
                if (i - coin >= 0) {
                    dp[i] = (dp[i] + dp[i - coin]) % MOD;
                }
            }
        }
        return dp[n];
    }

    /**
     * 方法 3 ： 暴力数学，等差数列求和
     */
    private static int waysToChange3(int n) {
        int res = 0;
        // 一个一个试每一种可能的n25数
        for (int n25 = 0; n25 <= n / 25; n25++) {
            // 除去25分硬币之后还需要的硬币数值
            int temp1 = n - n25 * 25;
            for (int n10 = 0; n10 <= temp1 / 10; n10++) {
                int temp2 = temp1 - n10 * 10;
                for (int n5 = 0; n5 <= temp2 / 5; n5++) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 方法 4 ： 暴力数学，等差数列求和优化
     */
    private static int waysToChange4(int n) {
        int res = 0;
        // 一个一个试每一种可能的n25数
        for (int n25 = 0; n25 <= n / 25; n25++) {
            int temp1 = n - n25 * 25;
            for (int n10 = 0; n10 <= temp1 / 10; n10++) {
                res += (temp1 - n10 * 10) / 5 + 1;
            }
        }
        return res;
    }

    /**
     * 方法 5 ： 暴力数学，等差数列求和进一步优化
     */
    private static int waysToChange5(int n) {
        int res = 0;
        for (int n25 = 0; n25 <= n / 25; n25++) {
            int rest = n - n25 * 25;
            int n10 = rest / 10;
            int n5 = rest % 10 / 5;
            res = (res + (int) ((long) (n10 + 1) * (n10 + n5 + 1) % MOD)) % MOD;
        }
        return res;
    }

    public static void main(String[] args) {
        Times.test("方法 1 ： 二维 dp", () -> {
            Asserts.test(waysToChange1(1) == 1);
            Asserts.test(waysToChange1(5) == 2);
            Asserts.test(waysToChange1(10) == 4);
            Asserts.test(waysToChange1(900750) == 504188296);
        });
        Times.test("方法 2 ： 一维 dp ", () -> {
            Asserts.test(waysToChange2(1) == 1);
            Asserts.test(waysToChange2(5) == 2);
            Asserts.test(waysToChange2(10) == 4);
            Asserts.test(waysToChange2(900750) == 504188296);
        });
        Times.test("方法 3 ： 暴力数学，等差数列求和", () -> {
            Asserts.test(waysToChange3(1) == 1);
            Asserts.test(waysToChange3(5) == 2);
            Asserts.test(waysToChange3(10) == 4);
            Asserts.test(waysToChange3(5335) == 20503233);
        });
        Times.test("方法 4 ： 暴力数学，等差数列求和优化", () -> {
            Asserts.test(waysToChange4(1) == 1);
            Asserts.test(waysToChange4(5) == 2);
            Asserts.test(waysToChange4(10) == 4);
            Asserts.test(waysToChange4(5335) == 20503233);
        });
        Times.test("方法 5 ： 暴力数学，等差数列求和进一步优化", () -> {
            Asserts.test(waysToChange5(1) == 1);
            Asserts.test(waysToChange5(5) == 2);
            Asserts.test(waysToChange5(10) == 4);
            Asserts.test(waysToChange5(900750) == 504188296);
        });
    }
}
