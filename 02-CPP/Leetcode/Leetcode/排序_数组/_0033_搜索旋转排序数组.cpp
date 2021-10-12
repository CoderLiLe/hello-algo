//
//  _0033_搜索旋转排序数组.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/31.
//

#include "_0033_搜索旋转排序数组.hpp"

/**
 暴力法
 T = O(n), S = O(1)
 */
int searchTarget(vector<int>& nums, int target) {
    for (int i = 0; i < nums.size(); i++) {
        if (nums[i] == target) {
            return i;
        }
    }
    return -1;
}

int searchTarget2(vector<int>& nums, int target) {
    int size = (int) nums.size();
    if (0 == size) {
        return -1;
    }
    if (1 == size) {
        return nums[0] == target ? 0 : -1;
    }
    
    int l = 0, r = size - 1;
    while (l <= r) {
        int mid = (l + r) / 2;
        if (nums[mid] == target) return mid;
        if (nums[0] <= nums[mid]) { // [0, mid)：从小到大，则左边有序
            if (nums[0] <= target && target < nums[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        } else { // （mid, size - 1]：从小到大，则右边有序
            if (nums[mid] < target && target <= nums[size - 1]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
    }
    
    return -1;
}
