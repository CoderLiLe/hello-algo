//
//  TreeNode.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#ifndef TreeNode_hpp
#define TreeNode_hpp

#include <stdio.h>

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

#endif /* TreeNode_hpp */
