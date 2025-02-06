package com.lile;

import com.lile.tools.Asserts;

/**
 * https://leetcode.cn/problems/interleaving-string/description
 */
public class _097交叉字符串 {
    /**
     * 动归五部曲：
     * （1）dp[i][j]：表示s1[0...i]和s2[0...j]是否可以拼接成s3[0...i+j]
     * (2) 递推公式：dp[i][j] = dp[i-1][j] && s1[i-1] == s3[i+j-1] || dp[i][j-1] && s2[j-1] == s3[i+j-1]
     * (3) 初始化：dp[0][0] = true;
     *          第一列 dp[i][0] = dp[i−1][0] and s1[i−1] == s3[i−1]：s1 的前 i 位是否能构成 s3 的前 i 位
     *          第一行 dp[0][i] = dp[0][i−1] and s2[i−1]== s3[i−1]：s2 的前 i 位是否能构成 s3 的前 i 位
     * （4）遍历顺序：每一行 i，遍历区间 [1, s1Len + 1)：
     *                每一列 j，遍历区间 [1, s2Len + 1)：
     *                  dp[i][j]= (dp[i][j−1] and s2[j−1] == s3[i+j−1]) or (dp[i−1][j] and s1[i−1] == s3[i+j−1])
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Len = s1.length(), s2Len = s2.length(), s3Len = s3.length();

        if (s1Len + s2Len != s3Len) {
            return false;
        }

        boolean[][] dp = new boolean[s1Len + 1][s2Len + 1];
        dp[0][0] = true;

        for (int i = 1; i <= s1Len; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i <= s2Len; i++) {
            dp[0][i] = dp[0][i - 1] && s2.charAt(i - 1) == s3.charAt(i - 1);
        }

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                dp[i][j] = (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)) || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1));
            }
        }
        return dp[s1Len][s2Len];
    }

    public static void main(String[] args) {
        _097交叉字符串 obj = new _097交叉字符串();
        Asserts.test(obj.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
