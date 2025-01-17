package com.lile.backpack;

/**
 * 1、【01背包问题】
 * 有 N 件物品和一个最大可装物品重量为 W 的背包，第 i 件物品的重量是 weight[i]，价值是 value[i]。
 * 每件物品只能用一次，求解将哪些物品装入背包里物品价值总和最大
 */

/**
 * 例子：
 * 背包最大重量为4，物品如下：
 * -----------------------------------------------
 * |   物品     |     重量      |      价值       |
 * -----------------------------------------------
 * |   物品1    |      1       |       15        |
 * -----------------------------------------------
 * |   物品2    |      3       |       20        |
 * -----------------------------------------------
 * |   物品3    |      4       |       30        |
 * -----------------------------------------------
 * 问背包能背的物品最大价值是多少？
 *
 */

import com.lile.tools.Asserts;

/**
 * 动态规划五部曲分析：
 * 1、确定 dp 数组以及下标的含义
 * dp[i][j]：从下标为[0, i] 的物品里任意取，放进容量为 j 的背包，价值总和最大是多少
 *
 * 2、确定递推公式
 * - 由 dp[i - 1][j] 推出，即背包容量为 j，里面不放物品 i 的最大价值，此时 dp[i][j] = dp[i - 1][j]
 * - 由 dp[i - 1][j - weight[i]] 推出，dp[i - 1][j - weight[i]] 为背包容量为 j - weight[i] 的时候
 *   不放物品 i 的最大价值，则 dp[i][j] = dp[i - 1][j - weight[i]] + value[i]
 * 所以递推公式为：dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
 *
 * 3、dp 数组如何初始化
 * 首先从 dp[i][j] 的定义触发
 * （1）如果背包容量 j 为 0 的话，即 dp[i][0]，无论是选取哪些物品，背包价值总和一定为0
 * （2）dp[0][j]如果不是初始值的话，就应该都是物品0的价值，也就是15
 * （3）dp[i][j]在推导的时候一定是取价值最大的数，如果题目给的价值都是正整数那么非0下标都初始化为0就可以了，因为0就是最小的了，不会影响取最大价值的结果
 *
 * 4、确定遍历顺序
 * 两个遍历纬度：物品与背包重量
 * 先遍历物品更好理解
 *
 * 5、举例推导 dp 数组
 */
public class ZeroOneBackpack {

    public static void main(String[] args) {
        ZeroOneBackpack obj = new ZeroOneBackpack();

        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        Asserts.test(obj.zeroOneBackpack1(weight, value, bagWeight) == 35);
        Asserts.test(obj.zeroOneBackpack2(weight, value, bagWeight) == 35);
        Asserts.test(obj.zeroOneBackpack3(weight, value, bagWeight) == 35);
        Asserts.test(obj.zeroOneBackpack4(weight, value, bagWeight) == 35);
    }

    private int zeroOneBackpack1(int[] weight, int[] value, int bagWeight) {
        int[][] dp = new int[weight.length + 1][bagWeight + 1];

        for (int j = bagWeight; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j - weight[0]] + value[0];
        }

        for (int i = 1; i < weight.length; i++) { // 遍历物品
            for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        return dp[weight.length - 1][bagWeight];
    }

    private int zeroOneBackpack2(int[] weight, int[] value, int bagWeight) {
        int[][] dp = new int[weight.length + 1][bagWeight + 1];

        for (int j = bagWeight; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j - weight[0]] + value[0];
        }

        for (int i = 1; i < weight.length; i++) { // 遍历物品
            for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        return dp[weight.length - 1][bagWeight];
    }

    private int zeroOneBackpack3(int[] weight, int[] value, int bagWeight) {
        int[][] dp = new int[weight.length + 1][bagWeight + 1];

        for (int j = bagWeight; j >= weight[0]; j--) {
            dp[0][j] = dp[0][j - weight[0]] + value[0];
        }

        for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
            for (int i = 1; i < weight.length; i++) { // 遍历物品
                if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                }
            }
        }

        return dp[weight.length - 1][bagWeight];
    }

    /**
     * 滚动数组
     *
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
     * 如果把 dp[i - 1] 那层拷贝到 dp[i] 上，表达式可以表示为：
     * dp[i][j] = max(dp[i][j], dp[i][j - weight[i]] + value[i]);
     * 则可以只用一个一维数组来表示（可以理解为一个滚动数组：上一层可以重复利用，直接拷贝到当前层）
     *
     * 动态规划五部曲分析：
     * 1、确定 dp 数组以及下标的含义
     * dp[j]：容量为 j 的背包，所背的物品价值最大为 dp[j]
     *
     * 2、确定递推公式
     * dp[j] 有两个选择：一个是取自己 dp[j]，一个是取 dp[j - weight[i]] + value[i]，指定取最大的
     * 所以递推公式为：dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
     *
     * 3、dp 数组如何初始化
     * （1） dp[0] = 0，因为背包容量为 0 所背的物品最大价值为0
     * （2）其他下标初始为 0 即可
     *
     * 4、确定遍历顺序
     * （1）先遍历物品
     * （2）从大到小遍历背包：倒序遍历是为了保证物品 i 只被放入一次
     *
     * 注意：可不可以先遍历背包容量嵌套遍历物品呢？
     * 不可以！！！如果遍历背包容量放在上一层，那么每个dp[j]就只会放入一个物品，即：背包里只放入了一个物品
     *
     * 5、举例推导 dp 数组
     */
    private int zeroOneBackpack4(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight +1];
        for (int i = 0; i < weight.length; i++) { // 遍历物品
            for (int j = bagWeight; j >= weight[i]; j--) { // 从小到大遍历背包
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagWeight];
    }

}
