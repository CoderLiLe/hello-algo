package 动态规划.剑指offer;

import tools.Asserts;

public class _46_把数字翻译成字符串 {

    public static void main(String[] args) {
        Asserts.test(translateNum(12258) == 5);
    }

    /**
     * 翻译方案可能与前两步骤有关，令 dp[n] 表示第 n 个位置方案数量，第 [n] 位与区间 [0:n-1]
     * 位必然可以组成 dp[n-1] 个方案，如果前两位 [n-1:n] 组成的数字在区间 10-26 之间，第 [n-1:n]
     * 位与区间 [0:n-2] 位必然可以组成 dp[n-2]个方案，所以可以根据该思路写出动态规划方程
     *
     * 算法流程：
     * （1）状态定义： dp[n] 表示第 n 个位置方案数量
     * （2）转移方程：
     *    令 [n-1:n] 组成的数字为 x
     *      dp[n] = dp[n-1] + dp[n-2], x >= 10 && x <= 25
     *      dp[n] = dp[n-1], x < 10 && x > 25
     * （3）初始状态：dp[0] = 1，dp[1] = 1
     * （4）因为该动态规划只需要 n-1 和 n-2 步骤的数据即可，所以可以不使用 dp 数组存储，使用 2 个
     *     变量存储数值即可
     *
     *  T = O(n), S = O(n)
     */
    public static int translateNum(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        int x = 1;
        int y = 1;
        for (int i = 2; i <= len; i++) {
            String sub = numStr.substring(i - 2, i);
            int z = sub.compareTo("10") >= 0 && sub.compareTo("25") <= 0 ? x + y : x;
            y = x;
            x = z;
        }
        return x;
    }
}
