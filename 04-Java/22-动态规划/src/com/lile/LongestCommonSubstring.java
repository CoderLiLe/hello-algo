package com.lile;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

/**
 * 最长公共子串（Longest Common Substring）：子串是连续的子序列
 */
public class LongestCommonSubstring {
    public static void main(String[] args) {
        String s1 = "ABCBA";
        String s2 = "BABCA";
        Times.test("二维数组实现", () -> {
            Asserts.test(lcs1(s1, s2) == 3);
        });
        Times.test("一维数组实现", () -> {
            Asserts.test(lcs2(s1, s2) == 3);
        });
    }

    private static int lcs1(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        char[] chars1 = s1.toCharArray();
        if (chars1.length == 0) {
            return 0;
        }

        char[] chars2 = s2.toCharArray();
        if (chars2.length == 0) {
            return 0;
        }

        int[][] dp = new int[chars1.length + 1][chars2.length + 1];
        int max = 0;

        for (int i = 1; i <= chars1.length; i++) {
            for (int j = 1; j <= chars2.length; j++) {
                // 如果 str1[i – 1] = str2[j – 1]，那么 dp(i, j) = dp(i – 1, j – 1) + 1
                // 如果 str1[i – 1] ≠ str2[j – 1]，那么 dp(i, j) = 0
                if (chars1[i - 1] != chars2[j - 1]) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
               }

                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    private static int lcs2(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0;
        }

        char[] chars1 = s1.toCharArray();
        if (chars1.length == 0) {
            return 0;
        }

        char[] chars2 = s2.toCharArray();
        if (chars2.length == 0) {
            return 0;
        }

        char[] rowChars = chars1, colChars = chars2;
        if (chars1.length < chars2.length) {
            rowChars = chars2;
            colChars = chars1;
        }

        int[] dp = new int[colChars.length + 1];
        int max = 0;

        for (int i = 1; i <= rowChars.length; i++) {
            // 注意倒着遍历，防止覆盖了之前计算的结果
            for (int j = colChars.length; j >= 1; j--) {
                if (rowChars[i - 1] != colChars[j - 1]) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j - 1] + 1;
                }

                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }
}
