package com.lile.backpack;

/**
 * 6、【分组背包问题】
 * https://www.acwing.com/problem/content/9/
 *
 * 有 group 组物品和一个容量是 capacity 的背包。
 *
 * 每组物品有若干个，同一组内的物品最多只能选一个。
 * 每件物品的体积是 bulk[i][j]，价值是 value[i][j]，其中 i 是组号，j 是组内编号。
 *
 * 求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。
 *
 * 输出最大价值。
 *
 * 输入格式
 * 第一行有两个整数 group，capacity，用空格隔开，分别表示物品组数和背包容量。
 *
 * 接下来有 group 组数据：
 *
 * 每组数据第一行有一个整数 num[i]，表示第 i 个物品组的物品数量；
 * 每组数据接下来有 num[i] 行，每行有两个整数 bulk[i][j], value[i][j]，用空格隔开，分别表示第 i 个物品组的第 j 个物品的体积和价值；
 * 输出格式
 * 输出一个整数，表示最大价值。
 *
 * 数据范围
 * 0 < group,capacity ≤ 100
 * 0 < num[i] ≤ 100
 * 0 < bulk[i][j],value[i][j] ≤ 100
 * 输入样例
 * 3 5
 * 2
 * 1 2
 * 2 4
 * 1
 * 3 4
 * 1
 * 4 5
 * 输出样例：
 * 8
 */
public class GroupBackpack {
    public static void main(String[] args) {
        // 物品组数
        int group = 3;
        // 背包容量
        int capacity = 5;
        // 每组数据的物品数量
        int[] num = {2, 1, 1};
        // 第 i 个物品组的第 j 个物品的体积
        int[][] bulk = {{1, 2}, {3}, {4}};
        // 第 i 个物品组的第 j 个物品的价值
        int[][] value = {{2, 4}, {4}, {5}};

        int[] dp = new int[100];
        for (int i = 0; i < group; i++) { //  遍历物品组数
            for (int j = capacity; j >= 0; j--) { // 倒序遍历背包容量
                for (int k = 0; k < bulk[i].length; k++) { // 遍历每组物品
                    if (j >= bulk[i][k]) {
                        dp[j] = Math.max(dp[j], dp[j - bulk[i][k]] + value[i][k]);
                    }
                }
            }
        }

        System.out.println(dp[capacity]);
    }
}
