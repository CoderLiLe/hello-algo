package com.lile.backpack;

/**
 * 3、【多重背包问题】
 *     有 N 种物品和一个容量为 V 的背包。第 i 种物品最多有 Mi 件可用，每件耗费
 * 的空间是 Ci，价值是 Wi。求解将哪些物品装入背包可使这些物品的耗费的空间
 * 总和不超过背包容量，且价值总和最大。
 *
 * https://www.acwing.com/problem/content/4/
 */

/**
 * 01背包是选或者不选：dp[j] = max(dp[j], dp[j - weight[i]] + value[i])
 * 多重背包是选0个、1个、2个......M[i]个：dp[j] = max(dp[j], dp[j - C[i] * k] + W[i] * k), k = 1, 2, 3, ... M[i]
 * 那么再加一层循环表示选多少个就可以了
 * 因为是 01背包的扩展，所以第二层循环应该从大到小
 */

public class MultipleBackpack {
    public static void main(String[] args) {
        // 物品种数
        int n = 4;
        // 背包容积
        int v = 5;
        // 体积
        int[] c = {1, 2, 3, 4};
        // 价值
        int[] w = {2, 4, 4, 5};
        // 可用数量
        int[] m = {3, 1, 3, 2};
        int[] dp = new int[105];
        for (int i = 0; i < n; i++) { // 从小到大遍历物品
            for (int j = v; j >= c[i]; j--) { // 从大到小遍历背包
                for (int k = 1; k <= m[i] && j >= k * c[i]; k++) { // 遍历第 i 种物品可选的数量
                    dp[j] = Math.max(dp[j], dp[j - k * c[i]] + k * w[i]);
                }
            }
        }

        System.out.println(dp[v]);
    }
}
