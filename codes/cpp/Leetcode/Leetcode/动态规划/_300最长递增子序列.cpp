//
//  _300最长递增子序列.cpp
//  Leetcode
//
//  Created by lile on 2025/1/2.
//

#include "_300最长递增子序列.hpp"

// T = O(n * logn), S = O(n)
int lengthOfLIS(vector<int>& nums) {
    if (nums.size() == 0) return 0;
    // 牌堆的数量
    int len = 0;
    // 牌顶数量
    vector<int> top(nums.size(), 0);
    // 遍历所有的牌
    for (auto num : nums) {
        // 局部二分法
        int begin = 0;
        int end = len;
        while (begin < end) {
            int mid = (begin + end) >> 1;
            if (num <= top[mid]) {
                end = mid;
            } else {
                begin = mid + 1;
            }
        }
        
        // 覆盖牌顶
        top[begin] = num;
        // 检查是否要新建一个牌堆
        if (begin == len) len++;
    }
    
    return len;
}
