//
//  _0121_买卖股票的最佳时机.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "_0121_买卖股票的最佳时机.hpp"

/**
 T = O(n)
 S = O(n^2)
 */
int maxProfit(vector<int>& prices) {
    if (prices.size() < 2) return 0;
    
    // dp[i][0]：第 i 天持有股票的最大利润
    // dp[i][1]：第 i 天不持有股票的最大利润
    vector<vector<int>> dp(prices.size(), vector<int>(2, 0));
    dp[0][0] = -prices[0];
    dp[0][1] = 0;
    for (int i = 1; i < prices.size(); i++) {
        dp[i][0] = max(dp[i - 1][0], -prices[i]);
        dp[i][1] = max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
    }
    
    return dp[prices.size() - 1][1];
}

/**
 T = O(n)
 S = O(1)
 */
int maxProfit2(vector<int>& prices) {
    if (prices.size() < 2) return 0;
    
    // 前面扫描过的最小价格
    int minPrice = prices[0];
    // 前面扫过的最大利润
    int maxProfit = 0;
    // 扫描所有价格
    for (int i = 0; i < prices.size(); i++) {
        if (prices[i] < minPrice) {
            minPrice = prices[i];
        } else { // 把第 i 天的股票卖出
            maxProfit = max(maxProfit, prices[i] - minPrice);
        }
    }
    
    return maxProfit;
}
