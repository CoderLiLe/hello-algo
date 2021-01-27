package com.lile.backpack;

/**
 * 8、【泛化物品/背包问题求方案数】
 * https://www.acwing.com/problem/content/11/
 *
 * 有 num 件物品和一个容量是 capacity 的背包。每件物品只能使用一次。
 *
 * 第 i 件物品的体积是 bulk[i]，价值是 value[i]。
 *
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 *
 * 输出 最优选法的方案数。注意答案可能很大，请输出答案模 10^9+7 的结果。
 *
 * 输入格式
 * 第一行两个整数，num，capacity，用空格隔开，分别表示物品数量和背包容积。
 *
 * 接下来有 num 行，每行两个整数 bulk[i], value[i]，用空格隔开，分别表示第 i 件物品的体积和价值。
 *
 * 输出格式
 * 输出一个整数，表示 方案数 模 10^9+7的结果。
 *
 * 数据范围
 * 0 < num,capacity ≤ 1000
 * 0 < bulk[i], value[i] ≤ 1000
 * 输入样例
 * 4 5
 * 1 2
 * 2 4
 * 3 4
 * 4 6
 * 输出样例：
 * 2
 */
public class GeneralizedItems {

    private final static int mod = (int)Math.pow(10, 9) + 7;

    public static void main(String[] args) {
        // 物品数量
        int num = 4;
        // 背包容量
        int capacity = 5;
        // 物品体积数组
        int[] bulk = {1, 2, 3, 4};
        // 物品价值数组
        int[] value = {2, 4, 4, 6};

        int[] result = new int[100];
        int[] dp = new int[100];

        result[0] = 1;

        for (int i = 0; i < num; i++) { // 遍历物品
            for (int j = capacity; j >= bulk[i]; j--) { // 倒序遍历背包
                if (dp[j - bulk[i]] + value[i] > dp[j]) {
                    dp[j] = dp[j - bulk[i]] + value[i];
                    result[j] = result[j - bulk[i]];
                } else if (dp[j - bulk[i]] + value[i] == dp[j]) {
                    result[j] = (result[j] + result[j - bulk[i]]) % mod;
                }
            }
        }

        int maxW = 0;
        for (int i = 0; i <= capacity; i++) {
            maxW = Math.max(maxW, dp[i]);
        }
        int ans = 0;
        for (int i = 0; i <= capacity; i++) {
            if (dp[i] == maxW) {
                ans = (ans + result[i]) % mod;
            }
        }

        System.out.println(ans);
    }
}
