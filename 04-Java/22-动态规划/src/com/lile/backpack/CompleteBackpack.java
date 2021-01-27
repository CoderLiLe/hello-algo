package com.lile.backpack;

/**
 * 【完全背包问题】
 * 有N件物品和一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i]。
 * 每件物品都有无限个（也就是可以放入背包多次），求解将那些物品装入背包里物品价值总和最大。
 */

/**
 * 完全背包问题和01背包问题唯一不同的地方就是每种物品有无限件。
 * 二者在解决过程中唯一不同体现在遍历顺序上
 *
 * leetcode 上没有纯完全背包问题，都是基于完全背包的各种应用，需要转化成完全背包问题。
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
 * 每件物品有无限个，问背包能背的物品最大价值是多少？
 *
 */

public class CompleteBackpack {

    public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWeight = 4;
        completeBackpack1(weight, value, bagWeight);
        completeBackpack2(weight, value, bagWeight);
    }

    private static void completeBackpack1(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int i = 0; i < weight.length; i++) { // 遍历物品
            for (int j = weight[i]; j <= bagWeight; j++) { // 遍历背包容量
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        System.out.println(dp[bagWeight]);
    }

    private static void completeBackpack2(int[] weight, int[] value, int bagWeight) {
        int[] dp = new int[bagWeight + 1];
        for (int j = 0; j <= bagWeight; j++) { // 遍历背包容量
            for (int i = 0; i < weight.length; i++) { // 遍历物品
                if (j - weight[i] >= 0) {
                    dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
                }
            }
        }

        System.out.println(dp[bagWeight]);
    }
}
