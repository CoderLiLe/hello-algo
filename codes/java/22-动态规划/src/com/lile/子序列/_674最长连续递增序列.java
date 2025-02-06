package com.lile.子序列;

import com.lile.tools.Asserts;

public class _674最长连续递增序列 {
    /**
     * 1、dp[i]：以nums[i]结尾的最长连续递增子序列的长度
     * 2、递推公式：dp[i] = dp[i - 1] + 1 if nums[i] > nums[i - 1] else 1
     * 3、初始化：dp[0] = 1
     * 4、遍历顺序：从前往后遍历
     * 5、举例推导dp数组
     */
    public int findLengthOfLCIS(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] > nums[i - 1] ? dp[i - 1] + 1 : 1;
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int findLengthOfLCIS2(int[] nums) {
        int len = nums.length;
        int pre = 1;
        int max = 1;
        for (int i = 1; i < len; i++) {
            pre = nums[i] > nums[i - 1] ? pre + 1 : 1;
            max = Math.max(max, pre);
        }
        return max;
    }

    public static void main(String[] args) {
        _674最长连续递增序列 obj = new _674最长连续递增序列();
        int[] nums = {1, 3, 5, 4, 7};
        Asserts.test(obj.findLengthOfLCIS(nums) == 3);
        Asserts.test(obj.findLengthOfLCIS2(nums) == 3);
    }
}
