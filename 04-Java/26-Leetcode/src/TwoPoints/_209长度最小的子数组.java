package TwoPoints;

import tools.Asserts;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的
 * 子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class _209长度最小的子数组 {

    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= s) {
                return 1;
            }

            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    public int minSubArrayLen2(int target, int[] nums) {
        // 滑动窗口的长度
        int res = Integer.MAX_VALUE;
        // 滑动窗口的左右边界
        int l = 0, r = 0;
        // 滑动窗口的区间和
        int sum = 0;
        while (r < nums.length) {
            // 滑动窗口的区间和
            sum += nums[r];
            // 当滑动窗口的区间和大于等于 target 时，缩小滑动窗口的区间和
            while (sum >= target) {
                // 更新滑动窗口的长度
                res = Math.min(res, r - l + 1);
                // 缩小滑动窗口的区间和
                sum -= nums[l++];
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        _209长度最小的子数组 obj = new _209长度最小的子数组();
        int[] nums = {2, 3, 1, 2, 4, 3};
        Asserts.test(2 == obj.minSubArrayLen(7, nums));
        Asserts.test(2 == obj.minSubArrayLen2(7, nums));
    }
}
