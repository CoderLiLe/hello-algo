package 动态规划;

import java.util.Scanner;

/**
 * 描述
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。
 * 他希望在不超过 n 元（可以等于 n 元）的前提下，使每件物品的价格与重要度的乘积的总和最大。
 * <p>
 * 设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j1 ， j2 ，……， jk ，则所求的总和为：
 * <p>
 * v[j1] * w[j1] + v[j2] * w[j2] + … +v[jk] * w[jk]。（其中 * 为乘号）
 * <p>
 * 请你帮助王强设计一个满足要求的购物单。
 * <p>
 * <p>
 * 输入描述：输入的第 1 行，为两个正整数，用一个空格隔开：n m
 * <p>
 * （其中 n （1 <= n <= 32000 ）表示总钱数， m （1 <= m <= 60 ）为希望购买物品的个数。）
 * <p>
 * 从第 2 行到第 m + 1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 * <p>
 * （其中 v 表示该物品的价格（0 <= v <= 10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 * <p>
 * <p>
 * 输出描述： 输出文件只有一个正整数，为不超过总钱数的物品的价格与重要度乘积的总和的最大值（ <200000 ）。
 */
public class HJ16购物单 {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test1() {
        Scanner sc = new Scanner(System.in);
        // 总钱数
        int n = sc.nextInt();
        // 希望购买物品的个数
        int m = sc.nextInt();

        Good[] goods = new Good[m];
        for (int i = 0; i < m; i++) {
            goods[i] = new Good();
        }

        for (int i = 0; i < m; i++) {
            // 该物品的价格（0 <= v <= 10000 ）
            int v = sc.nextInt();
            // 该物品的重要度（ 1 ~ 5）
            int p = sc.nextInt();
            // 该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号
            int q = sc.nextInt();

            goods[i].v = v;
            goods[i].p = p * v;  // 直接用p*v，方便后面计算

            if (q == 0) {
                goods[i].main = true;
            } else if (goods[q - 1].a1 == -1) {
                goods[q - 1].a1 = i;
            } else {
                goods[q - 1].a2 = i;
            }
        }

        int[][] dp = new int[m + 1][n + 1];
        // 先从1开始遍历物品个数
        for (int i = 1; i <= m; i++) {
            // 再从0开始遍历钱数
            for (int j = 0; j <= n; j++) {
                // 购买物品有以下5种情况

                // 不买当前物品
                dp[i][j] = dp[i - 1][j];
                if (!goods[i - 1].main) {
                    continue;
                }

                // 买当前物品
                if (j >= goods[i - 1].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v] + goods[i - 1].p);
                }

                // 买当前物品和附件1
                if (goods[i - 1].a1 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a1].v] + goods[i - 1].p + goods[goods[i - 1].a1].p);
                }

                // 买当前物品和附件2
                if (goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a2].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a2].p);
                }

                // 买当前物品和附件1和附件2
                if (goods[i - 1].a1 != -1 && goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v + goods[goods[i - 1].a2].v) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - goods[i - 1].v - goods[goods[i - 1].a1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a1].p + goods[goods[i - 1].a2].p);
                }
            }
        }
        System.out.println(dp[m][n]);
    }

    private static void test2() {
        Scanner sc = new Scanner(System.in);
        // 总钱数
        int n = sc.nextInt();
        // 希望购买物品的个数
        int m = sc.nextInt();

        Good[] goods = new Good[m];
        for (int i = 0; i < m; i++) {
            goods[i] = new Good();
        }

        for (int i = 0; i < m; i++) {
            // 该物品的价格（0 <= v <= 10000 ）
            int v = sc.nextInt();
            // 该物品的重要度（ 1 ~ 5）
            int p = sc.nextInt();
            // 该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号
            int q = sc.nextInt();

            goods[i].v = v;
            goods[i].p = p * v;  // 直接用p*v，方便后面计算

            if (q == 0) {
                goods[i].main = true;
            } else if (goods[q - 1].a1 == -1) {
                goods[q - 1].a1 = i;
            } else {
                goods[q - 1].a2 = i;
            }
        }

        /**
         * 由于我们是否取第i个商品时的数组只与是否取第i-1个商品时的数组相关，所以可以进行空间压缩，将时间复杂度压缩到
         * O(N)，但需注意逆循环，防止前面修改被后面应用。
         */
        int[] dp = new int[n + 1];
        // 先从1开始遍历物品个数
        for (int i = 1; i <= m; i++) {
            // 再从n开始倒序遍历钱数
            for (int j = n; j >= 0; j--) {
                // 购买物品有以下5种情况

                // 不买当前物品
                //dp[j] = dp[j];
                if (!goods[i - 1].main) {
                    continue;
                }

                // 买当前物品
                if (j >= goods[i - 1].v) {
                    dp[j] = Math.max(dp[j], dp[j - goods[i - 1].v] + goods[i - 1].p);
                }

                // 买当前物品和附件1
                if (goods[i - 1].a1 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v) {
                    dp[j] = Math.max(dp[j], dp[j - goods[i - 1].v - goods[goods[i - 1].a1].v] + goods[i - 1].p + goods[goods[i - 1].a1].p);
                }

                // 买当前物品和附件2
                if (goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a2].v) {
                    dp[j] = Math.max(dp[j], dp[j - goods[i - 1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a2].p);
                }

                // 买当前物品和附件1和附件2
                if (goods[i - 1].a1 != -1 && goods[i - 1].a2 != -1 && j >= goods[i - 1].v + goods[goods[i - 1].a1].v + goods[goods[i - 1].a2].v) {
                    dp[j] = Math.max(dp[j], dp[j - goods[i - 1].v - goods[goods[i - 1].a1].v - goods[goods[i - 1].a2].v] + goods[i - 1].p + goods[goods[i - 1].a1].p + goods[goods[i - 1].a2].p);
                }
            }
        }
        System.out.println(dp[n]);
    }


    /**
     * 定义物品类
     */
    private static class Good {
        // 物品的价格
        public int v;
        // 物品的重要度
        public int p;
        // 该物品是主件还是附件
        public boolean main = false;

        // 附件1ID
        public int a1 = -1;
        // 附件2ID
        public int a2 = -1;
    }
}
