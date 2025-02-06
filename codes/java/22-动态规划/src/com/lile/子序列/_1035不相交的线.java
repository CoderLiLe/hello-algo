package com.lile.子序列;

import com.lile.tools.Asserts;

public class _1035不相交的线 {
    /**
     * 本题说是求绘制的最大连线数，其实就是求两个字符串的最长公共子序列的长度！
     * 和 1143.最长公共子序列 的解题思路是一样的
     *
     * 1、dp[i][j]：表示nums1[0...i-1]和nums2[0...j-1]的最大不相交线数（即最长公共子序列）
     * 2、递推公式： if nums1[i - 1] == nums2[j - 1] dp[i][j] = dp[i - 1][j - 1] + 1
     *             else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1])
     * 3、初始化：dp[0][j] = 0, dp[i][0] = 0
     * 4、遍历顺序：根据递推公式从前向后，从上到下来遍历这个矩阵
     * 5、举例推导dp数组
     */
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        _1035不相交的线 obj = new _1035不相交的线();
        int[] nums1 = new int[]{1, 4, 2};
        int[] nums2 = new int[]{1, 2, 4};
        Asserts.test(obj.maxUncrossedLines(nums1, nums2) == 2);
    }
}
