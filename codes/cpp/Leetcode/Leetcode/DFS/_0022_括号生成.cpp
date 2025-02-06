//
//  _0022_括号生成.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "_0022_括号生成.hpp"

void dfs(string curStr, int left, int right, int n, vector<string>& res) {
    if (left == n && right == n) {
        res.push_back(curStr);
        return;
    }
    
    // 剪枝
    if (left < right) {
        return;
    }
    
    if (left < n) {
        dfs(curStr + "(", left + 1, right, n, res);
    }
    
    if (right < n) {
        dfs(curStr + ")", left, right + 1, n, res);
    }
}

vector<string> generateParenthesis(int n) {
    vector<string> res;
    dfs("", 0, 0, n, res);
    return res;
}
