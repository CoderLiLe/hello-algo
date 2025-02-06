package com.lile.子序列;

import com.lile.tools.Asserts;

public class _516最长回文子序列 {
    /**
     * 1、dp[i][j]：字符串s在[i, j]范围内最长的回文子序列的长度为dp[i][j]
     * 2、递推公式：s[i] == s[j], dp[i][j] = dp[i + 1][j - 1] + 2
     *      s[i] != s[j], dp[i][j] = max(dp[i + 1][j], dp[i][j - 1])
     *          加入s[j]的回文子序列长度为dp[i + 1][j]
     *          加入s[i]的回文子序列长度为dp[i][j - 1]
     * 3、初始化：i == j, dp[i][j] = 1
     * 4、遍历顺序：dp[i][j] 依赖于 dp[i + 1][j - 1] ，dp[i + 1][j] 和 dp[i][j - 1]
     *    i从下到上，j从左到右
     * 5、举例推导dp数组
     */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        _516最长回文子序列 obj = new _516最长回文子序列();
        String s = "bbbab";
        Asserts.test(obj.longestPalindromeSubseq(s) == 4);
    }
}
