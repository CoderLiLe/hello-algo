//
//  _0239_滑动窗口最大值.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/22.
//

#include "_0239_滑动窗口最大值.hpp"
#include <deque>
#include <queue>

/**
 方法一：优先队列
 
 对于「最大值」，我们可以想到一种非常合适的数据结构，那就是优先队列（堆），其中的大根堆可以帮助我们实时维护一系列元素中的最大值
 
 T = O(nlogn), S = O(n)
 */
vector<int> maxSlidingWindow1(vector<int>& nums, int k) {
    priority_queue<pair<int, int>> q;
    for (int i = 0; i < k; ++i) {
        q.emplace(nums[i], i);
    }
    vector<int> res = {q.top().first};
    for (int i = k; i < nums.size(); ++i) {
        q.emplace(nums[i], i);
        while (q.top().second <= i - k) {
            q.pop();
        }
        res.push_back(q.top().first);
    }
    return res;
}

/**
 方法二：单调队列
 T = O(n), S = O(k)
 */
vector<int> maxSlidingWindow2(vector<int>& nums, int k) {
    if (1 == k) {
        return nums;
    }
    
    vector<int> maxes(nums.size() - k + 1);
    
    deque<int> q;
    for (int i = 0; i < nums.size(); i++) {
        // 1、只要 nums[队尾] <= nums[i] ，就删除队尾
        while (!q.empty() && nums[i] >= nums[q.back()]) {
            q.pop_back();
        }
        
        // 2、将 i 加到队尾
        q.push_back(i);
        
        // 3、检查窗口的索引是否合法
        int w = i - k + 1;
        if (w < 0) continue;
        
        // 4、检查队头的合法性
        if (q.front() < w) {
            q.pop_front();
        }
        
        // 5、设置窗口的最大值
        maxes[w] = nums[q.front()];
    }
    
    return maxes;
}

vector<int> maxSlidingWindow3(vector<int>& nums, int k) {
    if (1 == k) {
        return nums;
    }
    
    vector<int> maxes(nums.size() - k + 1);
    
    // 当前滑动窗口的最大索引
    int maxIdx = 0;
    // 求出前 k 个元素的最大索引
    for (int i = 1; i < k; i++) {
        if (nums[i] > nums[maxIdx]) {
            maxIdx = i;
        }
    }
    
    // li 是滑动窗口的最左边索引
    for (int li = 0; li < maxes.size(); li++) {
        // ri 是滑动窗口的最右边索引
        int ri = li + k - 1;
        if (maxIdx < li) { // 最大值的索引不在滑动窗口的合理范围内
            // 求 [li, ri] 范围内最大值的索引
            maxIdx = li;
            for (int i = li + 1; i <= ri; i++) {
                if (nums[i] > nums[maxIdx]) maxIdx = i;
            }
        } else if (nums[ri] >= nums[maxIdx]) {
            maxIdx = ri;
        }
        maxes[li] = nums[maxIdx];
    }
    
    return maxes;
}
