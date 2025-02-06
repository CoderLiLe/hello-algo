package com.lile;

import tools.Asserts;

/*
描述
把m个同样的苹果放在n个同样的盘子里，允许有的盘子空着不放，问共有多少种不同的分法？
注意：如果有7个苹果和3个盘子，（5，1，1）和（1，5，1）被视为是同一种分法。

数据范围：0 ≤ m ≤ 10 ，1 ≤ n ≤ 10 。

输入描述：
输入两个int整数

输出描述：
输出结果，int型
* */
public class HJ61放苹果 {
    public static void main(String[] args) {
        int m = 7;
        int n = 3;
        int res = dp(m, n);
        Asserts.test(res == 8);
        Asserts.test(count(m, n) == 8);
    }

    private static int dp(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i <= 1 && j > 0) {
                    dp[i][j] = 1;
                } else if (j == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i][j - 1] + (i >= j ? dp[i - j][j] : 0);
                }
            }
        }
        return  dp[m][n];
    }

    /**
     * 递归法
     * m 苹果数
     * n 盘子数
     */
    private static int count(int m, int n){
        // 一个盘 或者 没有苹果 代表一种方案
        if(n == 1 ||m == 0 ) return 1;
            // 盘子过多情况，多余的盘子不起任何作用，最大的有效盘子是 m 个
        else if(n > m) return count(m, m);
            // 情况一： 只用 b - 1个盘子
            // 情况二： 每个盘子里先放一个苹果，等价于 a - b个苹果放到 b 个盘子
        else return count(m, n - 1) + count(m - n, n);
    }

}
