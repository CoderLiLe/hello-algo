package com.lile.backpack.完全背包;

import com.lile.tools.Asserts;

/**
 * 2、【完全背包问题】
 * 有N件物品和一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i]。
 * 每件物品都有无限个（也就是可以放入背包多次），求解将那些物品装入背包里物品价值总和最大。
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
 * 每件物品有无限个，问背包能背的物品最大价值是多少？
 */

/**
 * 题目描述
 * 小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的重量，并且具有不同的价值。
 *
 * 小明的行李箱所能承担的总重量是有限的，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料可以选择无数次，并且可以重复选择。
 *
 * 输入描述
 * 第一行包含两个整数，n，v，分别表示研究材料的种类和行李所能承担的总重量
 *
 * 接下来包含 n 行，每行两个整数 wi 和 vi，代表第 i 种研究材料的重量和价值
 *
 * 输出描述
 * 输出一个整数，表示最大价值。
 * 输入示例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 5
 * 输出示例
 * 10
 *
 * 提示信息
 * 第一种材料选择五次，可以达到最大值。
 *
 * 数据范围：
 *
 * 1 <= n <= 10000;
 * 1 <= v <= 10000;
 * 1 <= wi, vi <= 10^9.
 */
public class CompleteBackpack {

    public static void main(String[] args) {
        CompleteBackpack obj = new CompleteBackpack();

        int[] weight = {1, 2, 3, 4};
        int[] value = {2, 4, 4, 5};
        int bagWeight = 5;
        Asserts.test(obj.completeBackpack(weight, value, bagWeight) == 10);
        Asserts.test(obj.completeBackpack1(weight, value, bagWeight) == 10);
        Asserts.test(obj.completeBackpack2(weight, value, bagWeight) == 10);
    }

    /**
     * 思路：
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[i][j] 表示从下标为[0-i]的物品，每个物品可以取无限次，放进容量为j的背包，价值总和最大是多少。
     * （2）确定递推公式
     *      不放物品i：背包容量为j，里面不放物品i的最大价值是dp[i - 1][j]。
     *      放物品i：背包空出物品i的容量后，背包容量为j - weight[i]，dp[i][j - weight[i]] 为背包容量为j - weight[i]且不放物品i的最大价值，
     *      那么dp[i][j - weight[i]] + value[i] （物品i的价值），就是背包放物品i得到的最大价值
     *      递推公式： dp[i][j] = max(dp[i - 1][j], dp[i][j - weight[i]] + value[i]);
     * （3）dp数组如何初始化
     *      j < weight[0]的时候，dp[0][j] 应该是 0，因为背包容量比编号0的物品重量还小
     *      当j >= weight[0]时，dp[0][j] 如果能放下weight[0]的话，就一直装，每一种物品有无限个
     *
     * （4）确定遍历顺序
     *      既可以 先遍历物品再遍历背包
     *      也可以 先遍历背包再遍历物品
     * （5）举例推导dp数组
     */
    private int completeBackpack(int[] weight, int[] value, int bagWeight) {
        int[][] dp = new int[weight.length][bagWeight + 1];

        // 初始化
        for (int j = weight[0]; j <= bagWeight; j++) {
            dp[0][j] = dp[0][j - weight[0]] + value[0];
        }

        for (int i = 1; i < weight.length; i++) { // 遍历物品
            for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
                if (j < weight[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i]] + value[i]);
                }
            }
        }
        return dp[weight.length - 1][bagWeight];
    }

    private int completeBackpack1(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) { // 遍历物品
            for (int j = weight[i]; j <= bagWeight; j++) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        return dp[bagWeight];
    }

    private int completeBackpack2(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
            for (int i = 0; i < weight.length; i++) { // 遍历物品
                if (j - weight[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }

        return dp[bagWeight];
    }



}
