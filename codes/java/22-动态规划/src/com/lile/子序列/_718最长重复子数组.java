package com.lile.子序列;

import com.lile.tools.Asserts;

public class _718最长重复子数组 {
    /**
     * 1、dp[i][j]: 以下标i - 1为结尾的A，和以下标j - 1为结尾的B，最长重复子数组长度为dp[i][j]
     * 2、递推公式：dp[i][j] = dp[i - 1][j - 1] + 1 if nums1[i - 1] == nums2[j - 1]
     * 3、初始化：dp[i][0] = 0, dp[0][j] = 0
     * 4、遍历顺序：外层for循环遍历A，内层for循环遍历B，反过来也行
     * 5、举例推导dp数组
     *
     * 时间复杂度：O(n × m)，n 为A长度，m为B长度
     * 空间复杂度：O(n × m)
     */
    public int findLength(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[][] dp = new int[len1 + 1][len2 + 1];
        int max = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    /**
     * 滚动数组
     *
     * 时间复杂度：O(n × m)，n 为A长度，m为B长度
     * 空间复杂度：O(m)
     */
    public int findLength2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] dp = new int[len2 + 1];
        int max = 0;
        for (int i = 1; i <= len1; i++) {
            for (int j = len2; j >= 1; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        _718最长重复子数组 obj = new _718最长重复子数组();
        int[] nums1 = {1, 2, 3, 2, 1};
        int[] nums2 = {3, 2, 1, 4, 7};
        Asserts.test(obj.findLength(nums1, nums2) == 3);
        Asserts.test(obj.findLength2(nums1, nums2) == 3);

        int[] nums3 = {1,0,0,0,1};
        int[] nums4 = {1,0,0,1,1};
        Asserts.test(obj.findLength(nums3, nums4) == 3);
        Asserts.test(obj.findLength2(nums3, nums4) == 3);
    }
}
