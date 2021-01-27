package com.lile.backpack;

/**
 * 4、【混合三种背包问题】
 *     有的物品可以取一次（01背包），有的物品可以取无限次（完全背包），有的物品可以取的次数有一个上限（多重背包），
 * 应该怎么求解？
 */

/**
 * https://www.acwing.com/problem/content/7/
 *
 * 有 N 种物品和一个容量是 V 的背包。
 *
 * 物品一共有三类：
 *
 * 第一类物品只能用1次（01背包）；
 * 第二类物品可以用无限次（完全背包）；
 * 第三类物品最多只能用 si 次（多重背包）；
 * 每种体积是 vi，价值是 wi。
 *
 * 求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
 * 输出最大价值。
 *
 * 输入格式
 * 第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。
 *
 * 接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。
 *
 * si = −1 表示第 i 种物品只能用1次；
 * si = 0 表示第 i 种物品可以用无限次；
 * si > 0 表示第 i 种物品可以使用 si 次；
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0 < N,V ≤ 1000
 * 0 < vi,wi ≤ 1000
 * −1 ≤ si ≤ 1000−1
 * 输入样例
 * 4 5
 * 1 2 -1
 * 2 4 1
 * 3 4 0
 * 4 5 2
 * 输出样例：
 * 8
 */
public class HybridBackpack {
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
        // 01 背包相当于物品数量为 1 的多重背包，故将 -1 替换为 1
        int[] m = {1, 1, 0, 2};
        int[] dp = new int[105];
        for (int i = 0; i < n; i++) { // 从小到大遍历物品
            // 第 i 件物品可以用无限次，按照完全背包的方式从小到大枚举体积
            if (0 == m[i]) {
                for (int j = c[i]; j <= v; j++) {
                    dp[j] = Math.max(dp[j], dp[j - c[i]] + w[i]);
                }
            }

            // 多重背包进行二进制优化
            for (int k = 1; k <= m[i]; m[i] -= k, k *= 2) {
                for (int j = v; j >= 0; j--) {
                    if (j >= k * c[i]) {
                        dp[j] = Math.max(dp[j], dp[j - c[i] * k] + k * w[i]);
                    }
                }
            }

            for (int j = v; j >= 0; j--) {
                if (j >= m[i] * c[i]) {
                    dp[j] = Math.max(dp[j], dp[j - c[i] * m[i]] + w[i] * m[i]);
                }
            }
        }

        System.out.println(dp[v]);
    }
}
