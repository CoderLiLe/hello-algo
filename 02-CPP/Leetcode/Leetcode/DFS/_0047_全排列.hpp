//
//  _0047_全排列.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#ifndef _0047_____hpp
#define _0047_____hpp

#include <stdio.h>
#include <vector>

using namespace std;

class PermuteUnique {
    vector<vector<int>> result;
    vector<int> path;
    void backTracking(const vector<int>& nums, vector<bool>& used);
    
public:
    vector<vector<int>> permuteUnique(vector<int>& nums);
};



#endif /* _0047_____hpp */
