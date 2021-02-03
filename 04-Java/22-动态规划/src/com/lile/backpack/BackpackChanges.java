package com.lile.backpack;

/**
 * 9、【背包问题的变化】/【背包问题求具体方案】
 *
 * https://www.acwing.com/problem/content/12/
 *
 *
 * 有 num 件物品和一个容量是 capacity 的背包。每件物品只能使用一次。
 *
 * 第 i 件物品的体积是 bulk[i]，价值是 value[i]。
 *
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 *
 * 输出 字典序最小的方案。这里的字典序是指：所选物品的编号所构成的序列。物品的编号范围是 1…num。
 *
 * 输入格式
 * 第一行两个整数，num，capacity 用空格隔开，分别表示物品数量和背包容积。
 *
 * 接下来有 num 行，每行两个整数 bulk[i], value[i]，用空格隔开，分别表示第 i 件物品的体积和价值。
 *
 * 输出格式
 * 输出一行，包含若干个用空格隔开的整数，表示最优解中所选物品的编号序列，且该编号序列的字典序最小。
 *
 * 物品编号范围是 1…num。
 *
 * 数据范围
 * 0 < num, capacity ≤ 1000
 * 0 < bulk[i], value[i] ≤ 1000
 * 输入样例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 6
 * 输出样例：
 * 1 4
 */
public class BackpackChanges {
    public static void main(String[] args) {
        // 物品数量
        int num = 4;
        // 背包容积
        int capacity = 5;
        // 每件物品的体积
        int[] bulk = {0, 1, 2, 3,4};
        // 每件物品的价值
        int[] value = {0, 2, 4, 4, 6};
        int[][] dp = new int[100][100];
        for (int i = num; i >= 1; i--) {
            for (int j = 0; j <= capacity; j++) {
                dp[i][j] = dp[i + 1][j];
                if (j >= bulk[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - bulk[i]] + value[i]);
                }
            }
        }

        int tmpCapacity = capacity;
        for (int i = 1; i <= num; i++) {
            // 如果是最后一个元素，特判一下，防止越界即可
            if (i == num && tmpCapacity >= bulk[i]) {
                System.out.println(i);
                break;
            }
            if (tmpCapacity <= 0) {
                break;
            }
            if (tmpCapacity - bulk[i] >= 0 && dp[i][tmpCapacity] == dp[i + 1][tmpCapacity - bulk[i]] + value[i]) {
                System.out.println(i);
                tmpCapacity -= bulk[i];
            }
        }
    }
}
