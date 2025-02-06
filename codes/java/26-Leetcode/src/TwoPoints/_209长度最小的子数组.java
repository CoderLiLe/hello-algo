package TwoPoints;

import tools.Asserts;

import java.util.Arrays;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 *
 * 找出该数组中满足其总和大于等于 target 的长度最小的
 * 子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 */
public class _209长度最小的子数组 {
    /**
     * 暴力求解
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= s)
                return 1;
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

    public int minSubArrayLen3(int target, int[] nums) {
        // 滑动窗口的长度
        int res = Integer.MAX_VALUE;
        // 滑动窗口的左右边界
        int l = 0, r = 0;
        while (r < nums.length) {
            // 滑动窗口的区间和
            target -= nums[r];
            // 当滑动窗口的区间和大于等于 target 时，缩小滑动窗口的区间和
            while (0 >= target) {
                // 更新滑动窗口的长度
                res = Math.min(res, r - l + 1);
                // 缩小滑动窗口的区间和
                target += nums[l++];
            }
            r++;
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 使用二分法查找最小长度的子数组，其和至少为s
     *
     * @param s 目标和
     * @param nums 输入的整数数组
     * @return 返回满足条件的最小长度，如果不存在则返回0
     */
    public int minSubArrayLen4(int s, int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        // sums[i] 表示的是原数组 nums 前 i 个元素的和
        int[] sums = new int[length + 1];
        // 计算前缀和数组，便于后续查找
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        // 遍历前缀和数组，寻找最小长度的子数组
        for (int i = 0; i <= length; i++) {
            int target = s + sums[i];
            // 使用二分法在前缀和数组中查找目标值或第一个大于目标值的位置
            int index = Arrays.binarySearch(sums, target);
            // 如果未找到目标值，binarySearch 返回插入点的补码
            if (index < 0)
                index = ~index;
            // 如果找到的位置在有效范围内，更新最小长度
            if (index <= length) {
                min = Math.min(min, index - i);
            }
        }
        // 如果min未被更新，说明没有找到符合条件的子数组，返回0；否则返回最小长度
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * 使用二分查找确定最小子数组的长度，该子数组的和至少为s
     * 通过不断调整窗口大小来寻找最小长度的子数组
     *
     * @param s 目标和
     * @param nums 输入的整数数组
     * @return 最小子数组的长度，如果不存在则返回0
     */
    public int minSubArrayLen5(int s, int[] nums) {
        // 初始化窗口的最小长度为1，最大长度为数组长度，最小长度初始化为0
        int lo = 1, hi = nums.length, min = 0;
        // 使用二分查找来确定最小子数组的长度
        while (lo <= hi) {
            // 计算中间值作为当前窗口的长度
            int mid = (lo + hi) >> 1;
            // 判断当前窗口长度的子数组是否存在和至少为s的情况
            if (windowExist(mid, nums, s)) {
                // 找到就缩小窗口的大小
                hi = mid - 1;
                // 如果找到就记录下来
                min = mid;
            } else
            // 没找到就扩大窗口的大小
            lo = mid + 1;
        }
        // 返回最小子数组的长度，如果不存在则返回0
        return min;
    }

    /**
     * 检查数组中是否存在大小为size的子数组，其元素之和至少为s
     * 该方法通过滑动窗口技术实现，遍历数组并计算每个可能的子数组的和
     *
     * @param size 窗口的大小，即子数组的长度
     * @param nums 原始数组，其中包含整数元素
     * @param s 目标和，即子数组元素之和至少要达到的值
     * @return 如果存在至少一个子数组的元素之和达到或超过s，则返回true；否则返回false
     */
    private boolean windowExist(int size, int[] nums, int s) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当当前索引达到窗口大小时，减去窗口最左侧元素的值
            if (i >= size) {
                sum -= nums[i - size];
            }

            // 将当前元素的值加到窗口内元素的总和上
            sum += nums[i];
            // 如果当前窗口内元素的总和达到或超过目标值s，返回true
            if (sum >= s) {
                return true;
            }
        }
        // 遍历结束后，如果没有找到符合条件的子数组，返回false
        return false;
    }

    public static void main(String[] args) {
        _209长度最小的子数组 obj = new _209长度最小的子数组();
        int[] nums = {2, 3, 1, 2, 4, 3};
        Asserts.test(2 == obj.minSubArrayLen(7, nums));
        Asserts.test(2 == obj.minSubArrayLen2(7, nums));
        Asserts.test(2 == obj.minSubArrayLen3(7, nums));
        Asserts.test(2 == obj.minSubArrayLen4(7, nums));
        Asserts.test(2 == obj.minSubArrayLen5(7, nums));
    }
}
