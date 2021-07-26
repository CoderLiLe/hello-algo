//
//  _0072_编辑距离.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "_0072_编辑距离.hpp"
#include <vector>

// T = O(n ^ 2), S = O(n ^ 2)
int minDistance(string word1, string word2) {
    vector<vector<int>> dp(word1.size() + 1, vector<int>(word2.size() + 1, 0));
    
    // 最左上角 dp[0][0] = 0
    
    // 设置第0列
    for (int i = 1; i < word1.size(); i++) {
        dp[i][0] = i;
    }
    
    // 设置第0行
    for (int j = 1; j < word2.size(); j++) {
        dp[0][j] = j;
    }
    
    // 设置其他行列
    for (int i = 1; i <= word1.size(); i++) {
        for (int j = 1; j <= word2.size(); j++) {
            int top = dp[i - 1][j] + 1;
            int left = dp[i][j - 1] + 1;
            int leftTop = dp[i - 1][j - 1];
            if (word1[i - 1] != word2[j - 1]) {
                leftTop++;
            }
            dp[i][j] = min(min(top, left), leftTop);
        }
    }
    
    return dp[word1.size()][word2.size()];
}
