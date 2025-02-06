package com.lile.backpack.zero_one;

import com.lile.tools.Asserts;

import java.util.Arrays;

/**
 * 474. 一和零
 * https://leetcode-cn.com/problems/ones-and-zeroes/
 * 难度：中等
 *
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 示例 1：
 * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
 * 输出：4
 * 解释：最多有 5 个 0 和 3 个 1 的最大子集是 {"10","0001","1","0"} ，因此答案是 4 。
 * 其他满足题意但较小的子集包括 {"0001","1"} 和 {"10","1","0"} 。{"111001"} 不满足题意，因为它含 4 个 1 ，大于 n 的值 3 。
 *
 * 提示：
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] 仅由 '0' 和 '1' 组成
 * 1 <= m, n <= 100
 *
 */
public class _474一和零 {

    /**
     * 记忆化搜索
     *
     * dfs(i,j,k) 表示在 [0,i] 中选字符串，在 0 的个数至多为 j，1 的个数至多为 k 的约束下，至多可以选多少个字符串
     *
     * 考虑 strs[i] 选或不选：
     *
     * 不选：问题变成在 [0,i−1] 中选字符串，在 0 的个数至多为 j，1 的个数至多为 k 的约束下，至多可以选多少个字符串，即 dfs(i,j,k)=dfs(i−1,j,k)。
     * 选：如果j >= zeroNum && k >= oneNum则可以选。 dfs(i,j,k)=dfs(i−1,j−zeroNum,k-oneNum) + 1
     * 两种情况取最大值，得 dfs(i,j,k) = max(dfs(i−1,j,k), dfs(i−1,j−zeroNum,k-oneNum) + 1)
     *
     * 时间复杂度：O(kmn+L)，其中 k 为 strs 的长度，L 为 strs 中所有字符串的长度之和。
     * 空间复杂度：O(kmn)。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int k = strs.length;
        int[] zeroNums = new int[k];
        for (int i = 0; i < k; i++) {
            zeroNums[i] = (int) strs[i].chars().filter(ch -> ch == '0').count();
        }

        int[][][] memo = new int[strs.length][m + 1][n + 1];
        for (int[][] mat : memo) {
            for (int[] arr : mat) {
                // -1 表示没有计算过
                Arrays.fill(arr, -1);
            }
        }
        return dfs(k - 1, m, n, strs, zeroNums, memo);
    }

    private int dfs(int i, int j, int k, String[] strs, int[] zeroNums, int[][][] memo) {
        if (i < 0) {
            return 0;
        }
        // 之前计算过
        if (memo[i][j][k] != -1) {
            return memo[i][j][k];
        }
        // 不选 strs[i]
        int res = dfs(i - 1, j, k, strs, zeroNums, memo);
        int zeroNum = zeroNums[i];
        int oneNum = strs[i].length() - zeroNum;
        if (j >= zeroNum && k >= oneNum) {
            // 选 strs[i]
            res = Math.max(res, dfs(i - 1, j - zeroNum, k - oneNum, strs, zeroNums, memo) + 1);
        }
        // 记忆化
        return memo[i][j][k] = res;
    }

    /**
     * 1:1 翻译成递推,即三维的动态规划，01背包问题
     *
     * 时间复杂度：O(kmn+L)，其中 k 为 strs 的长度，L 为 strs 中所有字符串的长度之和。
     * 空间复杂度：O(kmn)。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm2(String[] strs, int m, int n) {
        int oneNum, zeroNum;
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] zerosOnes = getZerosOnes(str);
            zeroNum = zerosOnes[0];
            oneNum = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j >= zeroNum && k >= oneNum) {
                        dp[i + 1][j][k] = Math.max(dp[i][j][k], dp[i][j - zeroNum][k - oneNum] + 1);
                    } else {
                        dp[i + 1][j][k] = dp[i][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    /**
     * 思路：
     * 本题中strs 数组里的元素就是物品，每个物品都是一个！
     * 而m 和 n相当于是一个背包，两个维度的背包
     * 动规五部曲分析如下：
     * （1）确定dp数组以及下标的含义
     *      dp[i][j]：最多有i个0和j个1的strs的最大子集的大小为dp[i][j]。
     * （2）确定递推公式
     *      01背包的递推公式：dp[j] = max(dp[j], dp[j - weight[i]] + value[i]);
     *      本题递推公式：dp[i][j] = max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
     * （3）dp数组如何初始化
     *      因为物品价值不会是负数，初始为0，保证递推的时候dp[i][j]不会被初始值覆盖
     *
     * （4）确定遍历顺序
     *      遍历物品放在外循环，遍历背包在内循环，且内循环倒序（为了保证物品只使用一次）
     *      遍历背包容量的两层for循环先后没有要求，都是物品重量的一个维度，先遍历哪个都行！
     * （5）举例推导dp数组
     *
     * 时间复杂度：O(kmn+L)，其中 k 为 strs 的长度，L 为 strs 中所有字符串的长度之和。
     * 空间复杂度：O(mn)。
     *
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm3(String[] strs, int m, int n) {
        // dp[i][j]表示i个0和j个1时的最大子集
        int[][] dp = new int[m + 1][n + 1];
        int oneNum, zeroNum;
        for (String str : strs) {
            int[] zerosOnes = getZerosOnes(str);
            zeroNum = zerosOnes[0];
            oneNum = zerosOnes[1];

            //倒序遍历
            for (int i = m; i >= zeroNum; i--) {
                for (int j = n; j >= oneNum; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zeroNum][j - oneNum] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    public static void main(String[] args) {
        _474一和零 obj = new _474一和零();
        String[] strs = {"10", "0001", "111001", "1", "0"};
        Asserts.test(obj.findMaxForm(strs, 5, 3) == 4);
        Asserts.test(obj.findMaxForm2(strs, 5, 3) == 4);
        Asserts.test(obj.findMaxForm3(strs, 5, 3) == 4);
    }
}
