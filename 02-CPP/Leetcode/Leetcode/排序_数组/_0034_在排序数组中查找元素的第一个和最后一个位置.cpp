//
//  _0034_在排序数组中查找元素的第一个和最后一个位置.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/31.
//

#include "_0034_在排序数组中查找元素的第一个和最后一个位置.hpp"

int getLeftBorder(vector<int>& nums, int target) {
    int left = 0;
    int right = nums.size() - 1;
    // 记录一下leftBorder没有被赋值的情况
    int leftBorder = -2;
    while (left <= right) {
        int middle = left + ((right - left) / 2);
        // 寻找左边界，nums[middle] == target的时候更新right
        if (nums[middle] >= target) {
            right = middle -1;
            leftBorder = right;
        } else {
            left = middle + 1;
        }
    }
    return leftBorder;
}

int getRightBorder(vector<int>& nums, int target) {
    int left = 0;
    int right = nums.size() - 1;
    // 记录一下rightBorder没有被赋值的情况
    int rightBorder = -2;
    while (left <= right) {
        int middle = left + ((right - left) / 2);
        if (nums[middle] > target) {
            right = middle - 1;
        } else { // 寻找右边界，nums[middle] == target的时候更新left
            left = middle + 1;
            rightBorder = left;
        }
    }
    return rightBorder;
}

vector<int> searchRange(vector<int>& nums, int target) {
    int leftBorder = getLeftBorder(nums, target);
    int rightBorder = getRightBorder(nums, target);
    
    // 情况一：target 在数组范围的右边或者左边
    if (-2 == leftBorder || -2 == rightBorder) {
        return {-1, -1};
    }
    
    // 情况三：target 在数组范围中，且数组中存在 target
    if (rightBorder - leftBorder > 1) {
        return {leftBorder + 1, rightBorder - 1};
    }
    
    // 情况二：target 在数组范围中，且数组中不存在 target
    return {-1, -1};
}
