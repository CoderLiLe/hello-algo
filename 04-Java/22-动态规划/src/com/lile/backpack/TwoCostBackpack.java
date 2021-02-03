package com.lile.backpack;

/**
 * 5、【二维费用的背包问题】
 *     二维费用的背包问题是指：对于每件物品，具有两种不同的空间耗费，选
 * 择这件物品必须同时付出这两种代价。对于每种代价都有一个可付出的最大值
 * （背包容量）。问怎样选择物品可以得到最大的价值。
 *     设这两种代价分别为代价一和代价二，第i件物品所需的两种代价分别
 * 为Ci和Di。两种代价可付出的最大值（两种背包容量）分别为V 和U。物品的
 * 价值为Wi。
 */

/**
 * https://www.acwing.com/problem/content/8/
 *
 * 有 num 件物品和一个容量是 capacity 的背包，背包能承受的最大重量是 maxW。
 *
 * 每件物品只能用一次。体积是 bulk[i]，重量是 weight[i]，价值是 value[i]。
 *
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行三个整数，num，capacity, maxW，用空格隔开，分别表示物品件数、背包容积和背包可承受的最大重量。
 *
 * 接下来有 num 行，每行三个整数 bulk[i], weight[i], value[i]，用空格隔开，分别表示第 i 件物品的体积、重量和价值。
 *
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0 < num ≤ 1000
 * 0 < capacity,maxW ≤ 100
 * 0 < bulk[i], weight[i] ≤ 100
 * 0 < value[i] ≤ 1000
 * 输入样例
 * 4 5 6
 * 1 2 3
 * 2 4 4
 * 3 4 5
 * 4 5 6
 * 输出样例：
 * 8
 */

/**
 * 思路：这个题也很简单，就是在01背包的基础上加了一维重量，枚举的时候多一层循环就行了。因为是01背包的变形，所以重量和体积枚举的时候都从大到小枚举。
 */
public class TwoCostBackpack {
    // T = O(num * capacity * maxW), S = O(capacity * maxW)
    public static void main(String[] args) {
        // 物品种数
        int num = 4;
        // 背包容积
        int capacity = 5;
        // 背包可承受的最大重量
        int maxW = 6;
        // 体积
        int[] bulk = {1, 2, 3, 4};
        // 重量
        int[] weight = {2, 4, 4, 5};
        // 价值
        int[] value = {3, 4, 5, 6};
        int[][] dp = new int[capacity + 1][maxW + 1];
        for (int i = 0; i < num; i++) { // 从小到大遍历物品
            for (int j = capacity; j >= bulk[i]; j--) { // 从大到小遍历体积
                for (int k = maxW; k >= weight[i]; k--) { // 从大到小遍历重量
                    dp[j][k] = Math.max(dp[j][k], dp[j - bulk[i]][k - weight[i]] + value[i]);
                }
            }
        }

        System.out.println(dp[capacity][maxW]);
    }
}
