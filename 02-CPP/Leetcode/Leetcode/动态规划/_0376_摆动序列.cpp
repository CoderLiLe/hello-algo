//
//  _0376_摆动序列.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "_0376_摆动序列.hpp"

/**
 T = O(n)，其中 n 是序列的长度。我们只需要遍历该序列一次。
 S = O(n)，其中 n 是序列的长度。我们需要开辟两个长度为 n 的数组。
 */
int wiggleMaxLength(vector<int>& nums) {
    int len = nums.size();
    if (len < 2) {
        return len;
    }
    
    vector<int> up(len), down(len);
    up[0] = down[0] = 1;
    for (int i = 1; i < len; i++) {
        if (nums[i] > nums[i - 1]) {
            up[i] = max(up[i - 1], down[i - 1] + 1);
            down[i] = down[i - 1];
        } else if (nums[i] < nums[i - 1]) {
            up[i] = up[i - 1];
            down[i] = max(up[i - 1] + 1, down[i - 1]);
        } else {
            up[i] = up[i - 1];
            down[i] = down[i - 1];
        }
    }
    return max(up[len - 1], down[len - 1]);
}

/**
 优化的动态规划1
 
 注意到方法一中，我们仅需要前一个状态来进行转移，所以我们维护两个变量即可
 
 T = O(n)
 S = O(1)
 */
int wiggleMaxLength2(vector<int>& nums) {
    int len = nums.size();
    if (len < 2) {
        return len;
    }
    
    int up = 1, down = 1;
    for (int i = 1; i < len; i++) {
        if (nums[i] > nums[i - 1]) {
            up = max(up, down + 1);
        } else if (nums[i] < nums[i - 1]) {
            down = max(up + 1, down);
        }
    }
    
    return max(up, down);
}

/**
 优化的动态规划2
 
 注意到每有一个「峰」到「谷」的下降趋势，down 值才会增加，每有一个「谷」到「峰」的上升趋势，up 值才会增加。且过程中 down 与 up 的差的绝对值值恒不大于 1，即 up ≤ down + 1 且 down ≤ up + 1，于是有 max(up, down+1) = down+1 且 max(up+1, down) = up+1。这样我们可以省去不必要的比较大小的过程。
 
 T = O(n)
 S = O(1)
 */
int wiggleMaxLength3(vector<int>& nums) {
    if (nums.size() < 2) {
        return nums.size();
    }
    
    int up = 1, down = 1;
    for (int i = 1; i < nums.size(); i++) {
        if (nums[i] > nums[i - 1]) { // 上升
            up = down + 1;
        } else if (nums[i] < nums[i - 1]) { // 下降
            down = up + 1;
        }
    }
    
    return max(up, down);
}
