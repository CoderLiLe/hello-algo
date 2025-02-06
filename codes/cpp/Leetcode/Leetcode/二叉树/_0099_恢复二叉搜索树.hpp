//
//  _0099_恢复二叉搜索树.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#ifndef _0099_________hpp
#define _0099_________hpp

#include <stdio.h>
#include "TreeNode.hpp"

class RecoverBinaryTree {
private:
    /**
     *  上一次中序遍历过的节点
     */
    TreeNode* prev;
    /**
     *  第一个错误节点
     */
    TreeNode* first;
    /**
     *  第二个错误节点
     */
    TreeNode* second;
    
    void findWrongNodes(TreeNode* root);
    
    void find(TreeNode* node);
public:
    RecoverBinaryTree();
    ~RecoverBinaryTree();
    
    void recoverTree(TreeNode* root);
    
    void recoverTree_morris(TreeNode* root);
    
};

#endif /* _0099_________hpp */
