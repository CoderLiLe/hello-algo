package com.lile;

import com.lile.tools.Asserts;

public class _055跳跃游戏 {
    /**
     * 不用拘泥于每次究竟跳几步，而是看覆盖范围，覆盖范围内一定是可以跳过来的，不用管是怎么跳的
     * 贪心算法局部最优解：每次取最大跳跃步数（取最大覆盖范围），整体最优解：最后得到整体最大覆盖范围，看是否能到终点
     * 局部最优推出全局最优，找不出反例，试试贪心！
     *
     * 时间复杂度: O(n)
     * 空间复杂度: O(1)
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        // 只有一个元素，就是能达到
        if (nums.length == 1) {
            return true;
        }
        // 覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
        int coverRange = 0;
        // 在覆盖范围内更新最大的覆盖范围，注意这里是小于等于coverRange
        for (int i = 0; i <= coverRange; i++) {
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 贪心算法
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     * 如果可以一直跳到最后，就成功了
     *
     * @param nums
     * @return
     */
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        int rightmost = 0;
        for (int i = 0; i < n; ++i) {
            if (i > rightmost) {
                return false;
            }
            rightmost = Math.max(rightmost, i + nums[i]);
            if (rightmost >= n - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _055跳跃游戏 obj = new _055跳跃游戏();
        int[] nums = {2, 3, 1, 1, 4};
        Asserts.test(obj.canJump(nums));
        Asserts.test(obj.canJump2(nums));
    }

}
