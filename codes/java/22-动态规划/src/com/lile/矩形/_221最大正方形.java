package com.lile.矩形;

import com.lile.tools.Asserts;

public class _221最大正方形 {
    /**
     * 二维dp动规五部曲：
     * （1）dp[i][j]：以 (i,j) 为右下角，且只包含 1 的正方形的边长最大值
     * （2）递推公式：dp(i,j) = min(dp(i−1,j), dp(i−1,j−1), dp(i,j−1)) + 1
     * （3）dp数组初始化：如果 i 和 j 中至少有一个为 0，则以位置 (i,j) 为右下角的最大正方形的边长只能是 1，因此 dp(i,j) = 1
     * （4）遍历顺序：从上到下，从前到后
     * （5）举例推导dp数组
     *
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    public int maximalSquare(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return maxSide;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }
        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        _221最大正方形 obj = new _221最大正方形();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        Asserts.test(obj.maximalSquare(matrix) == 4);
    }
}
