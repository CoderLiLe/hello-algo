package com.lile.子序列;

import com.lile.tools.Asserts;

import java.util.Arrays;

public class _152乘积最大子数组 {
    /**
     * 一维dp动规五部曲：
     * （1）dpMax[i]含义：右端点下标为 i 的子数组的最大乘积
     *     dpMin[i]含义：右端点下标为 i 的子数组的最小乘积
     * （2）递推公式：dpMax[i] = max(max(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]), nums[i]);
     * 				dpMin[i] = min(min(dpMax[i - 1] * nums[i], dpMin[i - 1] * nums[i]), nums[i]);
     * （3）dp数组初始化：由于以 nums[0] 为右端点的子数组乘积只能是 nums[0]，dpMax[0] = dpMin[0] = nums[0];
     * （4）遍历顺序： 从递推公式知道遍历i一定是从前向后遍历
     * （5）举例推导dp数组，输入：[2,3,-2,4]，dp数组的变化如下：
     *                   dpMax[0],dpMin[0]   dpMax[1],dpMin[1]   dpMax[2],dpMin[2]   dpMax[3],dpMin[3]
     *                         2, 2                6, 3               -2, -12             4, -48
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];
        dpMax[0] = dpMin[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int x = nums[i];
            // 把 x 加到右端点为 i-1 的（乘积最大/最小）子数组后面，
            // 或者单独组成一个子数组，只有 x 一个元素
            dpMax[i] = Math.max(Math.max(dpMax[i - 1] * x, dpMin[i - 1] * x), x);
            dpMin[i] = Math.min(Math.min(dpMax[i - 1] * x, dpMin[i - 1] * x), x);
            System.out.println(dpMax[i] + ", " + dpMin[i]);
        }
        return Arrays.stream(dpMax).max().getAsInt();
    }

    /**
     * 空间优化
     * dpMax[i]和dpMin[i]都只依赖前一个，所以可以优化为只用两个变量，空间复杂度 O(1)
     *
     * 动规五部曲：
     * （1）dpMax含义：右端点下标为 上一个 的子数组的最大乘积
     *     dpMin含义：右端点下标为 上一个 的子数组的最小乘积
     * （2）递推公式：dpMax[i] = max(max(dpMax * nums[i], dpMin * nums[i]), nums[i]);
     * 				dpMin[i] = min(min(dpMax * nums[i], dpMin * nums[i]), nums[i]);
     * （3）dp数组初始化：由于以 nums[0] 为右端点的子数组乘积只能是 nums[0]，dpMax = dpMin = nums[0];
     *                  因为 1 乘以 nums[0] 等于 nums[0]，这样我们可以从下标 0 开始遍历 nums，代码写起来更简单
     * （4）遍历顺序： 从递推公式知道遍历i一定是从前向后遍历
     * （5）举例推导dp数组，输入：[2,3,-2,4]，dp数组的变化如下：
     *                   dpMax,dpMin   dpMax,dpMin   dpMax,dpMin   dpMax,dpMin
     *                         2, 2       6, 3          -2, -12       4, -48
     *
     * @param nums
     * @return
     */
    public int maxProduct2(int[] nums) {
        // 注意答案可能是负数
        int res = Integer.MIN_VALUE;
        int dpMax = 1;
        int dpMin = 1;
        for (int x : nums) {
            int mx = dpMax;
            dpMax = Math.max(Math.max(dpMax * x, dpMin * x), x);
            dpMin = Math.min(Math.min(mx * x, dpMin * x), x);
            res = Math.max(res, dpMax);
            System.out.println(dpMax + ", " + dpMin);
        }
        return res;
    }


    public static void main(String[] args) {
        _152乘积最大子数组 obj = new _152乘积最大子数组();
        int[] nums = new int[] {2, 3, -2, 4};
        Asserts.test(obj.maxProduct(nums) == 6);
        Asserts.test(obj.maxProduct2(nums) == 6);
    }
}
