package com.lile.剑指offer;


import com.lile.tools.Asserts;

public class _62_LastNumberInCircle {
    public static void main(String[] args) {
        Asserts.test(lastRemaining(5,3) == 3);
        Asserts.test(lastRemaining(10, 17) == 2);
    }

    /**
     * 动态规划
     *
     * （1）状态定义：dp[n] 表示还有 n 个人时最后剩下的数字索引号，一定要注意！是索引，而不是数字本身！
     * （2）初始状态：dp[1] = 0，因为只剩 1 个数字的时候，索引号从 0 开始，所以一定为 0
     * （3）转移方程：
     * dp[1] = 0
     *
     * dp[2] = (dp[1] + m) % 2
     *
     * dp[3] = (dp[2] + m) % 3
     * ...
     *
     * 从下往上推可以得出方程为
     * dp[n]= (dp[n−1] + m)
     *
     * 因为每个状态的下一步推导只需要上一个状态，所以用一个变量存储即可，不需要使用数组，将时间复杂度降低到O(1)
     */

    public static int lastRemaining(int n, int m) {
        int dp = 0;
        for (int i = 2; i <= n; i++) {
            dp = (m + dp) % i;
        }
        return dp;
    }
}
