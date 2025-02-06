package com.lile.基础;

import com.lile.tools.Asserts;

public class _343整数拆分 {
    /**
     * 1、dp[i]：分拆数字i，可以得到的最大乘积为dp[i]
     * 2、递推公式：dp[i] = max({dp[i], (i - j) * j, dp[i - j] * j});
     *          j * (i - j) 是单纯的把整数拆分为两个数相乘
     *          j * dp[i - j]是拆分成两个以及两个以上的个数相乘
     * 3、初始化：dp[2] = 1
     * 4、确定遍历顺序：dp[i] 是依靠 dp[i - j]的状态，所以遍历i一定是从前向后遍历，先有dp[i - j]再有dp[i]
     * 5、举例推导dp
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    /**
     * 贪心
     * 每次拆成n个3，如果剩下是4，则保留4，然后相乘，但是这个结论需要数学证明其合理性！
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        result *= n;
        return result;
    }

    public static void main(String[] args) {
        _343整数拆分 obj = new _343整数拆分();
        Asserts.test(obj.integerBreak(10) == 36);
        Asserts.test(obj.integerBreak2(10) == 36);
    }
}
