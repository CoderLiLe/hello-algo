package com.lile.子序列;

import com.lile.tools.Asserts;

import java.util.ArrayDeque;
import java.util.Deque;

public class _918环形子数组的最大和 {
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] leftMax = new int[n];
        // 对坐标为 0 处的元素单独处理，避免考虑子数组为空的情况
        leftMax[0] = nums[0];
        int leftSum = nums[0];
        int pre = nums[0];
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            res = Math.max(res, pre);
            leftSum += nums[i];
            leftMax[i] = Math.max(leftMax[i - 1], leftSum);
        }

        // 从右到左枚举后缀，固定后缀，选择最大前缀
        int rightSum = 0;
        for (int i = n - 1; i > 0; i--) {
            rightSum += nums[i];
            res = Math.max(res, rightSum + leftMax[i - 1]);
        }
        return res;
    }

    public int maxSubarraySumCircular2(int[] nums) {
        int n = nums.length;
        Deque<int[]> queue = new ArrayDeque<>();
        int pre = nums[0], res = nums[0];
        queue.offerLast(new int[]{0, pre});
        for (int i = 1; i < 2 * n; i++) {
            while (!queue.isEmpty() && queue.peekFirst()[0] < i - n) {
                queue.pollFirst();
            }
            pre += nums[i % n];
            res = Math.max(res, pre - queue.peekFirst()[1]);
            while (!queue.isEmpty() && queue.peekLast()[1] >= pre) {
                queue.pollLast();
            }
            queue.offerLast(new int[]{i, pre});
        }
        return res;
    }

    public int maxSubarraySumCircular3(int[] nums) {
        int total = 0, maxSum = nums[0], curMax = 0, minSum = nums[0], curMin = 0;
        for (int num : nums) {
            curMax = Math.max(curMax + num, num);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + num, num);
            minSum = Math.min(minSum, curMin);
            total += num;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }

    public static void main(String[] args) {
        _918环形子数组的最大和 obj = new _918环形子数组的最大和();
        int[] nums = new int[]{1, -2, 3, -2};
        Asserts.test(obj.maxSubarraySumCircular(nums) == 3);
        Asserts.test(obj.maxSubarraySumCircular2(nums) == 3);
        Asserts.test(obj.maxSubarraySumCircular3(nums) == 3);
    }
}
