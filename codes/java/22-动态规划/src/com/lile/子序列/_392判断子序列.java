package com.lile.子序列;

import com.lile.tools.Asserts;

public class _392判断子序列 {
    /**
     * 1、dp[i][j]：表示以下标i-1为结尾的字符串s，和以下标j-1为结尾的字符串t，相同子序列的长度为dp[i][j]
     * 2、递推公式：if (s[i - 1] == t[j - 1])，那么dp[i][j] = dp[i - 1][j - 1] + 1
     *    if (s[i - 1] != t[j - 1])，此时相当于t要删除元素，t如果把当前元素t[j - 1]删除，那么dp[i][j] 的数值就是
     *    看s[i - 1]与 t[j - 2]的比较结果了，即：dp[i][j] = dp[i][j - 1];
     * 3、初始化：从递推公式可以看出dp[i][j]都是依赖于dp[i - 1][j - 1] 和 dp[i][j - 1]，所以dp[0][0]和dp[i][0]是一定要初始化的
     * 4、遍历顺序：从上到下，从左到右
     * 5、举例推导dp数组
     *
     * 时间复杂度：O(n × m)
     * 空间复杂度：O(n × m)
     *
     * 这道题目算是编辑距离的入门题目（毕竟这里只是涉及到减法），也是动态规划解决的经典题型。
     */
    public boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int[][] dp = new int[sLen + 1][tLen + 1];
        for (int i = 1; i <= sLen; i++) {
            for (int j = 1; j <= tLen; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[sLen][tLen] == sLen;
    }

    /**
     * 状态压缩
     * 从递推公式可以看出dp[i][j]都是依赖于dp[i - 1][j - 1] 和 dp[i][j - 1]，所以i从小到大，j从大到小，可以简化为dp[j]来表示状态
     */
    public boolean isSubsequence2(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        int[] dp = new int[sLen + 1];
        for (int i = 0; i < tLen; i++) {
            // 需要使用上一轮的dp[j - 1]，所以使用倒序遍历
            for (int j = sLen; j > 0; j--) {
                // i遍历的是t字符串，j遍历的是s字符串
                if (t.charAt(i) == s.charAt(j - 1)) {
                    dp[j] = dp[j - 1] + 1;
                }
            }
        }
        return dp[sLen] == sLen;
    }

    /**
     * 状态压缩 + boolean状态
     */
    public boolean isSubsequence3(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        boolean[] dp = new boolean[sLen + 1];
        // 表示 “” 是t的子序列
        dp[0] = true;
        for (int i = 0; i < tLen; i ++) {
            // 需要使用上一轮的dp[j - 1]，所以使用倒序遍历
            for (int j = sLen; j > 0; j --) {
                // i遍历的是t字符串，j遍历的是s字符串
                if (t.charAt(i) == s.charAt(j - 1)) {
                    dp[j] = dp[j - 1];
                }
            }
        }
        return dp[sLen];
    }

    public static void main(String[] args) {
        _392判断子序列 obj = new _392判断子序列();
        String s = "abc";
        String t = "ahbgdc";
        Asserts.test(obj.isSubsequence(s, t));
        Asserts.test(obj.isSubsequence2(s, t));
        Asserts.test(obj.isSubsequence3(s, t));
    }
}
