package com.lile.backpack.zero_one;

import com.lile.tools.Asserts;

/**
 * 494.目标和
 * https://leetcode.cn/problems/target-sum/description/
 * 难度：中等
 *
 * 给你一个非负整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 示例：
 * 输入：nums: [1, 1, 1, 1, 1], S: 3
 * 输出：5
 * 解释：
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 一共有5种方法让最终目标和为3。
 *
 * 提示：
 * 数组非空，且长度不会超过 20 。
 * 初始的数组的和不会超过 1000 。
 * 保证返回的最终结果能被 32 位整数存下。
 */
public class _494目标和 {

    /**
     * 这道题目咋眼一看和动态规划背包啥的也没啥关系。
     * 本题要如何使表达式结果为target，
     * 既然为target，那么就一定有 left组合 - right组合 = target。
     * left + right = sum，而sum是固定的。right = sum - left
     * left - (sum - left) = target 推导出 left = (target + sum)/2 。
     * target是固定的，sum是固定的，left就可以求出来。
     * 此时问题就是在集合nums中找出和为left的组合。
     */

    /**
     * 思路：假设加法的总和为x，那么减法对应的总和就是sum - x
     * 要求的是 x - (sum - x) = target，x = (target + sum) / 2
     * 此时问题就转化为，用nums装满容量为x的背包，有几种方法
     * if ((target + sum) % 2 == 1) return 0; // 此时没有方案
     * if (abs(target) > sum) return 0; // 此时没有方案
     * 因为每个物品（题目中的1）只用一次！
     * 本题则是装满有几种方法。其实这就是一个组合问题了。
     *
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[i][j] 表示从下标为[0-i]的物品里任意取，放进容量为j的背包，价值总和最大是多少
     *      相对于 01背包，本题中每一个元素的数值既是重量，也是价值 。
     *      dp[i][j]：表示从下标为[0, i]的nums中任意取元素能够凑满j（包括j）这么大容量的包，有dp[i][j]种方法
     * （2）确定递推公式
     *      不放物品i：背包容量为j，里面不放物品i的最大价值是dp[i - 1][j]。
     *      放物品i：背包空出物品i的容量后，背包容量为j - weight[i]，dp[i - 1][j - weight[i]] 为背包容量为j - weight[i]且不放物品i的最大价值，
     *              那么dp[i - 1][j - weight[i]] + value[i] （物品i的价值），就是背包放物品i得到的最大价值
     *      01背包的递推公式为：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     *      本题中，物品i的容量是nums[i]，价值也是nums[i]：
     *          不放物品i：即背包容量为j，里面不放物品i，装满有dp[i - 1][j]中方法
     *          放物品i： 即先空出物品i的容量，背包容量为（j - 物品i容量），放满背包有 dp[i - 1][j - 物品i容量] 种方法
     *      所以递推公式：if (nums[i] > j) dp[i][j] = dp[i - 1][j];
     *                  else dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
     * （3）dp数组如何初始化
     *      从上面的递推公式可以看出二维数组的最上行 和 最左列一定要初始化，这是递推公式推导的基础
     *      a.初始化最上行（dp[0][j])，当nums[0] == j时（注意nums[0]和j都一定是大于等于零的，因此不需要判断等于-j时的情况），
     *          有唯一一种取法可取到j，dp[0][j]此时等于1，其他情况dp[0][j] = 0
     *      b.初始化最左列（dp[i][0])
     *        当从nums数组的索引0到i的部分有n个0时（n > 0)，每个0可以取+/-，因此有2的n次方种可以取到j = 0的方案
     *        numZeros = 0说明当前遍历到的数组部分没有0全为正数，因此只有一种方案可以取到j = 0（就是所有数都不取）
     *
     * （4）确定遍历顺序
     *      当前值是由上方和左上方推出
     *      遍历顺序一定是 从上到下，从左到右 或者 从左到右，再从上到下
     * （5）举例推导dp数组
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // 注意nums[i] >= 0的题目条件，意味着sum也是所有nums[i]的绝对值之和
        // 这里保证了sum + target一定是大于等于零的，也就是left大于等于零（毕竟我们定义left大于right）
        if(sum < Math.abs(target)){
            return 0;
        }

        // 利用二元一次方程组将left用target和sum表示出来（替换掉right组合），详见代码随想录对此题的分析
        // 如果所求的left数组和为小数，则作为整数数组的nums里的任何元素自然是没有办法凑出这个小数的
        if((sum + target) % 2 != 0) {
            return 0;
        }

        int bagSize = (sum + target) / 2;

        // dp[i][j]：遍历到数组第i个数时， left为j时的能装满背包的方法总数
        int[][] dp = new int[nums.length][bagSize + 1];

        // 初始化最上行（dp[0][j])，当nums[0] == j时（注意nums[0]和j都一定是大于等于零的，因此不需要判断等于-j时的情况），
        // 有唯一一种取法可取到j，dp[0][j]此时等于1
        // 其他情况dp[0][j] = 0
        // java整数数组默认初始值为0
        if (nums[0] <= bagSize) {
            dp[0][nums[0]] = 1;
        }

        // 初始化最左列（dp[i][0])
        // 当从nums数组的索引0到i的部分有n个0时（n > 0)，每个0可以取+/-，因此有2的n次方中可以取到j = 0的方案
        // numZeros = 0说明当前遍历到的数组部分没有0全为正数，因此只有一种方案可以取到j = 0（就是所有数都不取）
        int numZeros = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                numZeros++;
            }
            dp[i][0] = (int) Math.pow(2, numZeros);

        }

        // 递推公式分析：
        // 当nums[i] > j时，这时候nums[i]一定不能取，所以是dp[i - 1][j]种方案数
        // nums[i] <= j时，num[i]可取可不取，因此方案数是dp[i - 1][j] + dp[i - 1][j - nums[i]]
        // 由递推公式可知，先遍历i或j都可
        for(int i = 1; i < nums.length; i++) {
            for(int j = 1; j <= bagSize; j++) {
                if(nums[i] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i]];
                }
            }
        }

        return dp[nums.length - 1][bagSize];
    }

    /**
     * 动态规划 （一维dp数组）
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      在一维dp数组中，dp[j]表示：容量为j的背包，所背的物品价值可以最大为dp[j]。
     *      在本题种，dp[j]表示凑到和为j，有dp[j]种方法
     * （2）确定递推公式
     *      二维dp数组的递推公式为： dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
     *      一维dp数组，其实就从上一层 dp[i-1] 拷贝到dp[i]来，
     *      01背包的递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *      dp[j]可以通过dp[j - weight[i]]推导出来，dp[j - weight[i]]表示容量为j - weight[i]的背包所背的最大价值
     *      dp[j - weight[i]] + value[i] 表示 容量为 [j - 物品i重量] 的背包 加上 物品i的价值。（也就是容量为j的背包，放入物品i了之后的价值即：dp[j]）
     *      此时dp[j]有两个选择，一个是取自己dp[j] 相当于 二维dp数组中的dp[i-1][j]，即不放物品i，一个是取dp[j - weight[i]] + value[i]，即放物品i，指定是取最大的，毕竟是求最大价值
     *
     *      所以本题递推公式：dp[j] = dp[j] + dp[j - nums[i]] ，即：dp[j] += dp[j - nums[i]]
     * （3）dp数组如何初始化
     *      从上面的递推公式可以看出二维数组的最上行 和 最左列一定要初始化，这是递推公式推导的基础
     *      a.初始化最上行（dp[0][j])，当nums[0] == j时（注意nums[0]和j都一定是大于等于零的，因此不需要判断等于-j时的情况），
     *          有唯一一种取法可取到j，dp[0][j]此时等于1，其他情况dp[0][j] = 0
     *      b.初始化最左列（dp[i][0])
     *        当从nums数组的索引0到i的部分有n个0时（n > 0)，每个0可以取+/-，因此有2的n次方种可以取到j = 0的方案
     *        numZeros = 0说明当前遍历到的数组部分没有0全为正数，因此只有一种方案可以取到j = 0（就是所有数都不取）
     *
     * （4）确定遍历顺序
     *      当前值是由上方和左上方推出
     *      遍历物品放在外循环，遍历背包在内循环，且内循环倒序（为了保证物品只使用一次）
     * （5）举例推导dp数组
     *
     * 时间复杂度：O(n × m)，n为正数个数，m为背包容量
     * 空间复杂度：O(m)，m为背包容量
     */
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        //如果target的绝对值大于sum，那么是没有方案的
        if (Math.abs(target) > sum) {
            return 0;
        }
        //如果(target+sum)除以2的余数不为0，也是没有方案的
        if ((target + sum) % 2 == 1) {
            return 0;
        }

        int bagSize = (target + sum) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagSize];
    }

    /**
     * 回溯：递归解法
     * 时间复杂度：O(2^n)，其中 n 是数组 nums 的长度。回溯需要遍历所有不同的表达式，共有 2^n种不同的表达式，每种表达式计算结果需要 O(1) 的时间
     * 空间复杂度：O(n)，其中 n 是数组 nums 的长度。空间复杂度主要取决于递归调用的栈空间，栈的深度不超过 n
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays3(int[] nums, int target) {
        return backtrack(nums, target, 0, 0);
    }

    public int backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        } else {
            return backtrack(nums, target, index + 1, sum + nums[index]) +
                    backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }

    public static void main(String[] args) {
        _494目标和 obj = new _494目标和();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        Asserts.test(obj.findTargetSumWays(nums, target) == 5);
        Asserts.test(obj.findTargetSumWays2(nums, target) == 5);
        Asserts.test(obj.findTargetSumWays3(nums, target) == 5);
    }

}
