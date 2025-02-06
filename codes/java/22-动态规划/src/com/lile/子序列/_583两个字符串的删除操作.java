package com.lile.子序列;

import com.lile.tools.Asserts;

/**
 * 给定两个单词 word1 和 word2 ，返回使得 word1 和  word2 相同所需的最小步数。
 *
 * 每步 可以删除任意一个字符串中的一个字符。
 */
public class _583两个字符串的删除操作 {
    /**
     * 1、dp[i][j]：以i-1为结尾的字符串word1，和以j-1位结尾的字符串word2，想要达到相等，所需要删除元素的最少次数
     * 2、递推公式：
     *   当word1[i - 1] 与 word2[j - 1]相同时：dp[i][j] = dp[i - 1][j - 1]
     *   当word1[i - 1] 与 word2[j - 1]不同时：dp[i][j] = min(dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
     *      情况一：删word1[i - 1]，最少操作次数为dp[i - 1][j] + 1
     *      情况二：删word2[j - 1]，最少操作次数为dp[i][j - 1] + 1
     *      情况三：同时删word1[i - 1]和word2[j - 1]，操作的最少次数为dp[i - 1][j - 1] + 2
     * 3、初始化：从递推公式中，可以看出来，dp[i][0] 和 dp[0][j]是一定要初始化的
     *    dp[i][0]：word2为空字符串，以i-1为结尾的字符串word1要删除i个元素，才能和word2相同，dp[i][0] = i
     *    dp[0][j]：word1为空字符串，以j-1为结尾的字符串word2要删除j个元素，才能和word1相同，dp[0][j] = j
     * 4、遍历顺序：从左到右一层一层遍历
     *
     * 时间复杂度: O(n * m)
     * 空间复杂度: O(n * m)
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i < len1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < len2; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1] + 2, dp[i - 1][j] + 1), dp[i][j - 1] + 1);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 题目又要求删除次数要尽可能少，那么就找最长公共子序列的长度，然后就能算出最少的删除次数了
     * 参考1143. 最长公共子序列问题
     */
    public int minDistance2(String word1, String word2) {
        int maxCommonLen = longestCommonSubsequence(word1, word2);
        return word1.length() - maxCommonLen + word2.length() - maxCommonLen;
    }

    /**
     * 二维dp动规五部曲：
     * （1）dp[i][j]：长度为[0, i - 1]的字符串text1与长度为[0, j - 1]的字符串text2的最长公共子序列为dp[i][j]
     * （2）递推公式：如果text1[i - 1] 与 text2[j - 1]相同，那么找到了一个公共元素，所以dp[i][j] = dp[i - 1][j - 1] + 1;
     * 				如果text1[i - 1] 与 text2[j - 1]不相同，那就看看text1[0, i - 2]与text2[0, j - 1]的最长公共子序列
     * 				和 text1[0, i - 1]与text2[0, j - 2]的最长公共子序列，取最大的。即：dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
     * （3）dp数组初始化：dp[i][0] = 0;
     * 				    dp[0][j] = 0;
     * （4）遍历顺序：从左到右一层一层遍历
     * （5）举例推导
     *
     * T = O(m * n), S = O(m * n)
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        // 先对dp数组做初始化操作
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1 ; i <= len1 ; i++) {
            char char1 = text1.charAt(i - 1);
            for (int j = 1; j <= len2; j++) {
                char char2 = text2.charAt(j - 1);
                // 开始列出状态转移方程
                if (char1 == char2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        _583两个字符串的删除操作 obj = new _583两个字符串的删除操作();
        String word1 = "sea";
        String word2 = "eat";
        Asserts.test(obj.minDistance(word1, word2) == 2);
        Asserts.test(obj.minDistance2(word1, word2) == 2);
    }
}
