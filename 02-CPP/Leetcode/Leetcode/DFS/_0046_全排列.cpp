//
//  _0046_全排列.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "_0046_全排列.hpp"

vector<vector<int>> paths;
vector<int> path;

void backtracking(const vector<int>& nums, vector<bool>& used) {
    if (path.size() == nums.size()) {
        paths.push_back(path);
        return;
    }
    
    for (int i = 0; i < nums.size(); i++) {
        if (used[i]) continue;
        
        used[i] = true;
        path.push_back(nums[i]);
        backtracking(nums, used);
        used[i] = false;
        path.pop_back();
    }
}

vector<vector<int>> permute(vector<int>& nums) {
    vector<bool> used(nums.size(), false);
    backtracking(nums, used);
    return paths;
}

