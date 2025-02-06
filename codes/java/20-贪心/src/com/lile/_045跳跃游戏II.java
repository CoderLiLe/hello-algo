package com.lile;

import com.lile.tools.Asserts;

public class _045跳跃游戏II {
    /**
     * 以最小的步数增加最大的覆盖范围，直到覆盖范围覆盖了终点
     * <p>
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        // 记录跳跃的次数
        int count = 0;
        // 当前覆盖最远距离下标
        int curDistance = 0;
        // 下一步覆盖最远距离下标
        int nextDistance = 0;
        for (int i = 0; i < nums.length; i++) {
            // 更新下一步覆盖最远距离下标
            nextDistance = Math.max(nextDistance, i + nums[i]);
            // 说明当前一步，再跳一步就到达了末尾
            if (nextDistance >= nums.length - 1) {
                count++;
                break;
            }
            // 遇到当前覆盖最远距离下标
            if (i == curDistance) {
                // 更新当前覆盖的最远距离下标
                curDistance = nextDistance;
                count++;
            }
        }
        return count;
    }

    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        // 记录跳跃的次数
        int count = 0;
        // 当前覆盖最远距离下标
        int curDistance = 0;
        // 下一步覆盖最远距离下标
        int nextDistance = 0;
        // 注意这里是小于nums.length - 1，这是关键所在，题目假设总是可以到达数组的最后一个位置
        for (int i = 0; i < nums.length - 1; i++) {
            // 更新下一步覆盖最远距离下标
            nextDistance = Math.max(nextDistance, i + nums[i]);
            // 遇到当前覆盖最远距离下标
            if (i == curDistance) {
                // 更新当前覆盖的最远距离下标
                curDistance = nextDistance;
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        _045跳跃游戏II obj = new _045跳跃游戏II();
        int[] nums = {2, 3, 1, 1, 4};
        Asserts.test(obj.jump(nums) == 2);
        Asserts.test(obj.jump2(nums) == 2);
    }

}
