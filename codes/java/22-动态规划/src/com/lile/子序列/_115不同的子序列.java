package com.lile.子序列;

import com.lile.tools.Asserts;

public class _115不同的子序列 {
    /**
     * 1、dp[i][j]：以i-1为结尾的s子序列中出现以j-1为结尾的t的个数为dp[i][j]
     * 2、递推公式：
     * s[i - 1] 与 t[j - 1]相等：dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
     *      一部分是用s[i - 1]来匹配，那么个数为dp[i - 1][j - 1]。即不需要考虑当前s子串和t子串的最后一位字母，所以只需要 dp[i-1][j-1]。
     *      一部分是不用s[i - 1]来匹配，个数为dp[i - 1][j]。
     * s[i - 1] 与 t[j - 1] 不相等：dp[i][j] = dp[i - 1][j]
     *      dp[i][j]只有一部分组成，不用s[i - 1]来匹配（就是模拟在s中删除这个元素），即：dp[i - 1][j]
     * 3、初始化：可以看出dp[i][j] 是从上方和左上方推导而来，那么 dp[i][0] 和dp[0][j]是一定要初始化的。
     *      dp[i][0]：以i-1为结尾的s可以随便删除元素，出现空字符串的个数
     *      那么dp[i][0]一定都是1，因为也就是把以i-1为结尾的s，删除所有元素，出现空字符串的个数就是1
     *      dp[0][j]：空字符串s可以随便删除元素，出现以j-1为结尾的字符串t的个数
     *      那么dp[0][j]一定都是0，s如论如何也变成不了t
     * 4、遍历顺序：从上到下，从左到右
     * 5、举例推导dp数组
     *
     * 时间复杂度: O(n * m)
     * 空间复杂度: O(n * m)
     */
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 0; i <= sLen; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= tLen; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[sLen][tLen];
    }

    public static void main(String[] args) {
        _115不同的子序列 obj = new _115不同的子序列();
        String s = "rabbbit";
        String t = "rabbit";
        Asserts.test(obj.numDistinct(s, t) == 3);
    }
}
