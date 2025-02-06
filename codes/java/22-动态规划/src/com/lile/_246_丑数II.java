package com.lile;


import com.lile.tools.Asserts;

public class _246_丑数II {
    public static void main(String[] args) {
        Asserts.test(nthUglyNumber(10) == 12);
    }

    /**
     * （1）状态定义： dp[n] 表示第 n 个丑数，a 表示 2 倍数字的索引用于 dp[a]*2,b 表示 3 倍数字的索引用于 dp[b]*3,c 表示 5 倍数字的索引用于 dp[c]*5
     * 转移方程：
     * （2）dp[n] = min(dp[a]*2, dp[b]*3, dp[c]*5)dp[n]=min(dp[a]∗2,dp[b]∗3,dp[c]∗5)
     * 每次计算之后，如果 2 倍的数字最小，则 a++，如果 3 倍的数字最小，则 b++，如果 5 倍的数字最小，则 c++
     * （3）初始状态： dp[0]=1，因为第一个丑数是 1
     */
    public static int nthUglyNumber(int n) {
        int a = 0, b = 0, c = 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int n2 = dp[a] * 2;
            int n3 = dp[b] * 3;
            int n5 = dp[c] * 5;
            dp[i] = Math.min(Math.min(n2, n3), n5);
            if (dp[i] == n2) {
                a++;
            }
            if (dp[i] == n3) {
                b++;
            }
            if (dp[i] == n5) {
                c++;
            }
        }
        return dp[n - 1];
    }
}
