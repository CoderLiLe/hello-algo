//
//  _0063_不同路径II.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "_0063_不同路径II.hpp"
#include <vector>

// T = O(n ^ 2), S = O(n ^ 2)
int uniquePathsWithObstacles(vector<vector<int>>& obstacleGrid) {
    
    int rowNum = obstacleGrid.size();
    int colNum = obstacleGrid[0].size();
    
    vector<vector<int>> dp(rowNum, vector<int>(colNum, 0));
    
    dp[0][0] = 0 == obstacleGrid[0][0] ? 1 : 0;
    
    // 设置第 0 行
    for (int col = 1; col < colNum; col++) {
        // 如果没有障碍物 & 左侧也没有障碍物，则可以到达
        if (0 == obstacleGrid[0][col] && 1 == dp[0][col - 1]) {
            dp[0][col] = 1;
        } else {
            break;
        }
    }
    
    // 设置第 0 列
    for (int row = 1; row < rowNum; row++) {
        // 如果没有障碍物 & 上方也没有障碍物，则可以到达
        if (0 == obstacleGrid[row][0] && 1 == dp[row - 1][0]) {
            dp[row][0] = 1;
        } else {
            break;
        }
    }
    
    // 设置其它格子
    for (int row = 1; row < rowNum; row++) {
        for (int col = 1; col < colNum; col++) {
            if (1 == obstacleGrid[row][col]) {
                dp[row][col] = 0;
            } else {
                // 如果左侧可以到达
                if (dp[row][col - 1] > 0) {
                    dp[row][col] += dp[row][col - 1];
                }
                
                // 如果上方可以到达
                if (dp[row - 1][col] > 0) {
                    dp[row][col] += dp[row - 1][col];
                }
            }
        }
    }
    
    return dp[rowNum - 1][colNum - 1];
}
