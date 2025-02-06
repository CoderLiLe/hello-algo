package com.lile.houseRobbery;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

import java.util.HashMap;
import java.util.Map;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装
 * 有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class _198打家劫舍 {
    public static void main(String[] args) {
        _198打家劫舍 obj = new _198打家劫舍();

        int[] nums1 = {1, 2, 3, 1};
        Integer result1 = 4;

        int[] nums2 = {2, 7, 9, 3, 1};
        Integer result2 = 12;

        int[] nums3 = {114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240};
        Integer result3 = 4173;

        Map<int[], Integer> map = new HashMap<>();
        map.put(nums1, result1);
        map.put(nums2, result2);
        map.put(nums3, result3);

        Times.test("暴力递归，超出时间限制", () -> {
            map.forEach((nums, result) -> {
                Asserts.test(obj.rob(nums) == result);
            });
        });

        Times.test("动态规划", () -> {
            map.forEach((nums, result) -> {
                Asserts.test(obj.rob2(nums) == result);
            });
        });

        Times.test("动态规划,优化空间复杂度", () -> {
            map.forEach((nums, result) -> {
                Asserts.test(obj.rob3(nums) == result);
            });
        });
    }

    /**
     * 暴力递归，超出时间限制
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        return process(nums, 0);
    }

    /**
     * 解决打家劫舍问题，计算在不触动相邻警报系统的情况下，能从一排房屋中偷到的最大金额
     *
     * @param nums 一个整数数组，每个元素代表一座房屋内的金额
     * @param i 当前考虑的房屋索引
     * @return 返回从当前房屋开始，能偷到的最大金额
     */
    private int process(int[] nums, int i) {
        // 边界条件：如果当前索引超出或等于数组长度，说明已经没有更多的房屋可以考虑，返回0
        if (i >= nums.length) {
            return 0;
        }
        // 偷当前房屋的情况：获得当前房屋的金额，加上跳过下一个房屋（避免警报），从下下个房屋开始能偷到的最大金额
        int p1 = nums[i] + process(nums, i + 2);
        // 不偷当前房屋的情况：考虑从下一个房屋开始能偷到的最大金额
        int p2 = process(nums, i + 1);
        // 返回两种情况中金额较大的一种
        return Math.max(p1, p2);
    }

    /**
     * 动态规划
     * 状态定义：dp[i] 代表前 i 个房子在满足条件下的能偷窃到的最高金额
     * 转移方程：dp[n+1]=max(dp[n],dp[n−1]+num)
     * 初始状态：前 0 间房子的最大偷窃价值为 0 ，即 dp[0]=0 。
     * 返回值：返回 dp 列表最后一个元素值，即所有房间的最大偷窃价值。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        // 处理边界条件，如果数组为空或者长度为0，则返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // 初始化dp数组，长度为n+1，为了方便处理边界情况
        int[] dp = new int[n + 1];
        // 前0间房子的最大偷窃价值为0
        dp[0] = 0;
        // 前1间房子的最大偷窃价值即为第一间房子的价值
        dp[1] = nums[0];
        // 从第二间房子开始，动态规划计算每增加一间房子时的最大偷窃价值
        for (int i = 2; i <= n; i++) {
            // 对于第i间房子，有两种选择：偷或不偷。如果不偷，最大价值保持为dp[i-1]；如果偷，最大价值为dp[i-2]+nums[i-1]
            // 选择两种情况中的最大值作为dp[i]的值
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        // 返回所有房间的最大偷窃价值
        return dp[n];
    }

    /**
     * 动态规划,优化空间复杂度
     * 状态定义：dp[i] 代表前 i 个房子在满足条件下的能偷窃到的最高金额
     * 转移方程：dp[n+1]=max(dp[n],dp[n−1]+num)
     * 初始状态：前 0 间房子的最大偷窃价值为 0 ，即 dp[0]=0 。
     * 返回值：返回 dp 列表最后一个元素值，即所有房间的最大偷窃价值。
     * 简化空间复杂度：dp[n] 只与 dp[n−1] 和 dp[n−2] 有关系，因此可以设两个变量 cur和 pre 交替记录，将空间复杂度降到 O(1)
     *
     * 时间复杂度 O(N) ： 遍历 nums 需要线性时间；
     * 空间复杂度 O(1) ： cur和 pre 使用常数大小的额外空间。
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {
        // 处理边界条件，如果数组为空或者长度为0，则返回0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}
