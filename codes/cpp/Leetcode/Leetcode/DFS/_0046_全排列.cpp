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

#pragma mark -

void dfs(int idx, vector<int>& nums, vector<vector<int>>& list) {
    if (idx == nums.size()) {
        vector<int> resultList(nums.size());
        for (int i = 0; i < nums.size(); i++) {
            resultList[i] = nums[i];
        }
        list.push_back(resultList);
        return;
    }
    
    for (int i = idx; i < nums.size(); i++) {
        swap(nums[idx], nums[i]);
        dfs(idx + 1, nums, list);
        swap(nums[idx], nums[i]);
    }
}

vector<vector<int>> permute2(vector<int>& nums) {
    vector<vector<int>> list;
    if (0 == nums.size()) return list;
    dfs(0, nums, list);
    return list;
}

