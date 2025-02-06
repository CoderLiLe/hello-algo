//
//  _1235_规划兼职工作.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "_1235_规划兼职工作.hpp"
#include <numeric>

/**
 参考背包模型
 */
int jobScheduling(vector<int>& startTime, vector<int>& endTime, vector<int>& profit) {
    int num = startTime.size();
    // 加上在1号工作之前最近能做的0号工作(虚拟的工作，收益也是0)的最大收益0元
    vector<int> job(num + 1);
    // iota：对一定范围内的数据进行赋值
    iota(job.begin(), job.end(), 0);
    sort(job.begin() + 1, job.end(), [&](int& a, int& b) {
        return endTime[a - 1] < endTime[b - 1];
    });
    
    // prev[i]表示i号工作之前最近能做的工作
    vector<int> prev(num + 1);
    for (int i = 1; i <= num; i++) {
        for (int j = i - 1; j >= 1; j--) {
            if (endTime[job[j] - 1] <= startTime[job[i] - 1]) {
                prev[i] = j;
                break;
            }
        }
    }
    
    // dp[i]表示做包括i号工作之前的所有工作能取得的最大收益
    vector<int> dp(num + 1);
    dp[1] = profit[job[1] - 1];
    for (int i = 1; i <= num; i++) {
        // 状态转移方程：dp[i]=max(dp[i-1],dp[prev[i]]+profit[i])
        dp[i] = max(dp[i - 1], profit[job[i] - 1] + dp[prev[i]]);
    }
    
    return dp[num];
}
