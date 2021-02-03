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

    // 物品种数
    private int n = 4;
    // 背包容积
    private int v = 5;
    // 体积
    private int[] c = {1, 2, 3, 4};
    // 价值
    private int[] w = {2, 4, 4, 5};
    // 可用数量
    private int[] m = {3, 1, 3, 2};
    private int[] dp = new int[105];

    public static void main(String[] args) {
        MultipleBackpack mb = new MultipleBackpack();
        mb.multipleBackpack1();
        mb.multipleBackpack2();
    }

    private void multipleBackpack1() {
        for (int i = 0; i < n; i++) { // 从小到大遍历物品
            for (int j = v; j >= c[i]; j--) { // 从大到小遍历背包
                for (int k = 1; k <= m[i] && j >= k * c[i]; k++) { // 遍历第 i 种物品可选的数量
                    dp[j] = Math.max(dp[j], dp[j - k * c[i]] + k * w[i]);
                }
            }
        }

        System.out.println(dp[v]);
    }

    private void multipleBackpack2() {
        int[] bulks = new int[4];
        int[] values = new int[4];
        int k = 0;

        for (int i = 0; i < n; i++) { // 从小到大遍历物品
            // 二进制优化
            for (int j = 1; j < m[i]; j *= 2) {
                bulks[k] = c[i] * j;
                values[k++] = w[i] * j;
                m[i] -= j;
            }

            if (m[i] > 0) {
                bulks[k] = c[i] * m[i];
                values[k++] = w[i] * m[i];
            }
        }

        // 01背包
        for (int i = 0; i < k; i++) {
            for (int j = bulks[i]; j >= values[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - values[i]] + bulks[i]);
            }
        }

        System.out.println(dp[v]);
    }
}
