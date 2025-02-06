package com.lile.基础;

import com.lile.tools.Asserts;

public class _063不同路径 {
    /**
     * 二维dp动规五部曲：
     * （1）dp[i][j]：表示从（0，0）出发，到(i, j) 有dp[i][j]条不同的路径
     * （2）递推公式：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
     * （3）dp数组初始化：dp[i][0] = 1;
     * 				    dp[0][j] = 1;
     * 				    一旦遇到obstacleGrid[i][0] == 1的情况就停止dp[i][0]的赋值1的操作，dp[0][j]同理
     * （4）遍历顺序：从左到右一层一层遍历
     * （5）举例推导
     *
     * T = O(m * n), S = O(m * n)
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                break;
            } else {
                dp[i][0] = 1;
            }
        }
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1) {
                break;
            } else {
                dp[0][j] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                //System.out.println("[" + i + ", " + j + "] = " + dp[i][j]);
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * 空间优化版本
     *
     * T = O(m * n), S = O( n)
     */
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[j] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else if (j > 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        _063不同路径 uniquePaths = new _063不同路径();

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        int[][] obstacleGrid2 = {
                {0, 1},
                {0, 0}
        };
        int[][] obstacleGrid3 = {
                {1, 0}
        };

        Asserts.test(uniquePaths.uniquePathsWithObstacles(obstacleGrid) == 2);
        Asserts.test(uniquePaths.uniquePathsWithObstacles(obstacleGrid2) == 1);
        Asserts.test(uniquePaths.uniquePathsWithObstacles(obstacleGrid3) == 0);

        Asserts.test(uniquePaths.uniquePathsWithObstacles2(obstacleGrid) == 2);
        Asserts.test(uniquePaths.uniquePathsWithObstacles2(obstacleGrid2) == 1);
        Asserts.test(uniquePaths.uniquePathsWithObstacles2(obstacleGrid3) == 0);
    }
}
