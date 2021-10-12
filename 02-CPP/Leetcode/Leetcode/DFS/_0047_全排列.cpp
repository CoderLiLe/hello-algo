//
//  _0047_全排列.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "_0047_全排列.hpp"

vector<vector<int>> PermuteUnique::permuteUnique(vector<int>& nums) {
    vector<bool> used(nums.size(), false);
    sort(nums.begin(), nums.end());
    backTracking(nums, used);
    return result;
}

void PermuteUnique::backTracking(const vector<int>& nums, vector<bool>& used) {
    if (path.size() == nums.size()) {
        result.push_back(path);
        return;
    }
    
    for (int i = 0; i < nums.size(); i++) {
        // 如果同一树层nums[i - 1]使用过则直接跳过
        if (i > 0 && nums[i - 1] == nums[i] && used[i - 1] == false) {
            continue;
        }
        
        // 如果 nums[i] 已经被使用过，则直接跳过
        if (used[i]) {
            continue;
        }
        
        used[i] = true;
        path.push_back(nums[i]);
        backTracking(nums, used);
        used[i] = false;
        path.pop_back();
    }
}
