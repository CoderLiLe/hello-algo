package com.lile.backpack.zero_one;

import com.lile.tools.Asserts;
import com.lile.tools.Times;

import java.util.Arrays;

/**
 * 题目难易：中等
 *
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意: 每个数组中的元素不会超过 100 数组的大小不会超过 200
 *
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 *
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class _416分割等和子集 {
    /**
     * 递归搜索 + 保存递归返回值 = 记忆化搜索
     *
     * 定义 dfs(i,j) 表示能否从 nums[0] 到 nums[i] 中选出一个和恰好等于 j 的子序列。
     *
     * 考虑 nums[i] 选或不选：
     * 选：问题变成能否从 nums[0] 到 nums[i−1] 中选出一个和恰好等于 j−nums[i] 的子序列，即 dfs(i−1,j−nums[i])。
     * 不选：问题变成能否从 nums[0] 到 nums[i−1] 中选出一个和恰好等于 j 的子序列，即 dfs(i−1,j)。
     *
     * 这两个只要有一个成立，dfs(i,j) 就是 true。所以有
     * dfs(i,j) = dfs(i−1,j−nums[i]) || dfs(i−1,j)
     *
     * 递归边界：dfs(−1,0)=true, dfs(−1,>0)=false。
     * 递归入口：dfs(n−1,s/2)，即答案。
     *
     * 时间复杂度：O(ns)，其中 n 是 nums 的长度，s 是 nums 的元素和（的一半）。由于每个状态只会计算一次，动态规划的
     * 时间复杂度 = 状态个数 × 单个状态的计算时间。本题状态个数等于 O(ns)，单个状态的计算时间为 O(1)，所以动态规划的时间复杂度为 O(ns)。
     * 空间复杂度：O(ns)。保存多少状态，就需要多少空间。
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 特判：如果是奇数，就不符合要求
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int len = nums.length;
        int[][] memo = new int[len][target + 1];
        for (int[] row : memo) {
            // -1 表示没有计算过
            Arrays.fill(row, -1);
        }
        return dfs(len - 1, target, nums, memo);
    }

    /**
     * 使用深度优先搜索（DFS）来解决特定问题
     * 该方法主要用于解决是否存在一种选择使得选取的数字之和等于j的问题
     * 通过递归调用和记忆化技术来优化计算过程
     *
     * @param i 当前考虑的数字索引
     * @param j 当前目标和
     * @param nums 输入的数字数组
     * @param memo 记忆化数组，避免重复计算
     * @return 如果存在一种选择使得选取的数字之和等于j，则返回true；否则返回false
     */
    private boolean dfs(int i, int j, int[] nums, int[][] memo) {
        // 初始条件：如果i小于0，表示已经考虑完所有数字，此时j应为0才返回true
        if (i < 0) {
            return j == 0;
        }

        // 之前计算过
        if (memo[i][j] != -1) {
            return memo[i][j] == 1;
        }

        // 主逻辑：选择当前数字或不选择当前数字，两种情况只要有一直满足条件即可
        // dfs(i,j) = dfs(i−1,j−nums[i]) || dfs(i−1,j)
        boolean res = j >= nums[i] && dfs(i - 1, j - nums[i], nums, memo) || dfs(i - 1, j, nums, memo);

        // 记忆化：将当前结果存储在memo数组中，以供后续使用
        memo[i][j] = res ? 1 : 0;
        return res;
    }

    /**
     * 将上述代码翻译为递推 - 二维动态规划
     *
     * 去掉递归中的「递」，只保留「归」的部分，即自底向上计算
     * 具体来说，dp[i][j] 的定义和 dfs(i,j) 的定义是一样的，都表示能否从 nums[0] 到 nums[i] 中选出一个和恰好等于 j 的子序列。
     * 相应的递推式（状态转移方程）也和 dfs 一样：
     *      dp[i][j] = dp[i−1][j − nums[i]] || dp[i−1][j]
     * 但是，这种定义方式没有状态能表示递归边界，即 i=−1 的情况
     * 解决办法：在二维数组 dp 的最上边插入一排状态，那么其余状态全部向下偏移一位，把 dp[i] 改为 dp[i+1]，把 dp[i−1] 改为 dp[i]。
     *
     * 修改后 dp[i+1][j] 表示能否从 nums[0] 到 nums[i] 中选出一个和为 j 的子序列。dp[0] 对应递归边界。
     * 修改后的递推式为
     *       dp[i+1][j] = dp[i][j−nums[i]] || f[i][j]
     * 如果把 nums[i] 也改成 nums[i+1]，那么 nums[0] 就被我们给忽略掉了。
     * 初始值 dp[0][0]=true，翻译自递归边界 dfs(−1,0) = true。其余值初始化成 false。
     * 答案为 dp[n][s/2]，翻译自递归入口 dfs(n−1,s/2)。
     *
     * 时间复杂度：O(ns)，其中 n 是 nums 的长度，s 是 nums 的元素和（的一半）。
     * 空间复杂度：O(ns)。
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 特判：如果是奇数，就不符合要求
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int len = nums.length;

        boolean[][] dp = new boolean[len + 1][target + 1];
        dp[0][0] = true;
        for (int i = 0; i < len; i++) {
            int x = nums[i];
            for (int j = 0; j <= target; j++) {
                dp[i + 1][j] = j >= x && dp[i][j - x] || dp[i][j];
            }
        }
        return dp[len][target];
    }

    /**
     * 优化空间 - 一维动态规划
     * 观察上面的状态转移方程，在计算 f[i+1] 时，只会用到 f[i]，不会用到比 i 更早的状态。
     * 因此可以去掉第一个维度，反复利用同一个一维数组。
     * 状态转移方程改为 dp[j] = dp[j] || dp[j−nums[i]]
     * 初始值 f[0]=true，答案为 f[s/2]。
     *
     * 时间复杂度：O(ns)，其中 n 是 nums 的长度，s 是 nums 的元素和（的一半）。
     * 空间复杂度：O(s)。
     *
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 特判：如果是奇数，就不符合要求
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        // 设前 i 个数的和为 s′,，由于子序列的元素和不可能比 s′还大，j 可以从 min(s′, s/2) 开始倒着枚举。
        // 比如 nums 前两个数的和等于 5，那么我们无法在前两个数中，选出一个元素和大于 5 的子序列，所以对于 j>5 的 f 值，一定是 false，无需计算。
        int s2 = 0;
        for (int num : nums) {
            s2 = Math.min(s2 + num, target);
            for (int j = s2; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
            if (dp[target]) {
                return true;
            }
        }

        return false;
    }

    /**
     * 动规五部曲
     * 判断一个给定的数组能否被分割成两个具有相同和的子数组。
     * 本题的本质是，能否把容量为 sum / 2的背包装满。
     * 这是 背包算法可以解决的经典类型题目。
     *
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      01背包中，dp[j] 表示： 容量（所能装的重量）为j的背包，所背的物品价值最大可以为dp[j]
     *      本题中每一个元素的数值既是重量，也是价值，所以，当 dp[target] == target 的时候，背包就装满了
     * （2）确定递推公式
     *      01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *      本题，相当于背包里放入数值，那么物品i的重量是nums[i]，其价值也是nums[i]。
     *      所以递推公式：dp[j] = max(dp[j], dp[j - nums[i]] + nums[i]);
     * （3）dp数组如何初始化
     *      如果题目给的价值都是正整数那么非0下标都初始化为0就可以了，如果题目给的价值有负数，那么非0下标就要初始化为负无穷
     *      这样才能让dp数组在递推的过程中取得最大的价值，而不是被初始值覆盖了。
     *      从dp[j]的定义来看，首先dp[0]一定是0。
     *      本题题目中 只包含正整数的非空数组，所以非0下标的元素初始化为0就可以了。
     * （4）确定遍历顺序
     *      如果使用一维dp数组，物品遍历的for循环放在外层，遍历背包的for循环放在内层，且内层for循环倒序遍历！
     * （5）举例推导dp数组
     *      dp[j]的数值一定是小于等于j的。
     *      如果dp[j] == j 说明，集合中的子集总和正好可以凑成总和j，理解这一点很重要。
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)，虽然dp数组大小为一个常数，但是大常数
     *
     * @param nums 给定的整数数组
     * @return 如果数组可以被分割成两个具有相同和的子数组，则返回 true；否则返回 false。
     */
    public boolean canPartition4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 特判：如果是奇数，就不符合要求
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        int[] dp = new int[target + 1];

        // 开始 01背包
        for (int i = 0; i < nums.length; i++) {
            // 每一个元素一定是不可重复放入，所以从大到小遍历
            for (int j = target; j >= nums[i]; j--) {
                // 物品 i 的重量是 nums[i]，其价值也是 nums[i]
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }

            // 剪枝一下，每一次完成內層的for-loop，立即檢查是否dp[target] == target
            if (dp[target] == target) {
                return true;
            }
        }
        return dp[target] == target;
    }

    /**
     * 总结：
     * 这道题目就是一道01背包经典应用类的题目，需要我们拆解题目，然后才能发现可以使用01背包。
     *
     * 01背包相对于本题，主要要理解，题目中物品是nums[i]，重量是nums[i]，价值也是nums[i]，背包体积是sum/2。
     *
     * 做完本题后，需要大家清晰：背包问题，不仅可以求 背包能被的最大价值，还可以求这个背包是否可以装满。
     */

    public static void main(String[] args) {
        _416分割等和子集 obj = new _416分割等和子集();
        int[] nums = {1, 5, 11, 5};
        int[] nums2 = {1, 2, 3, 5};
        int[] nums3 = {1, 2, 3, 5, 6, 8, 9, 4, 10, 12, 16};

        Times.test("递归搜索 + 保存递归返回值 = 记忆化搜索", () -> {
            Asserts.test(obj.canPartition(nums));
            Asserts.test(!obj.canPartition(nums2));
            Asserts.test(obj.canPartition(nums3));
        });

        Times.test("将上述代码翻译为递推 - 二维动态规划", () -> {
            Asserts.test(obj.canPartition2(nums));
            Asserts.test(!obj.canPartition2(nums2));
            Asserts.test(obj.canPartition2(nums3));
        });

        Times.test("优化空间 - 一维动态规划", () -> {
            Asserts.test(obj.canPartition3(nums));
            Asserts.test(!obj.canPartition3(nums2));
            Asserts.test(obj.canPartition3(nums3));
        });

        Times.test("动规五部曲 - 一维动态规划", () -> {
            Asserts.test(obj.canPartition4(nums));
            Asserts.test(!obj.canPartition4(nums2));
            Asserts.test(obj.canPartition4(nums3));
        });
    }
}
