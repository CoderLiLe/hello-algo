//
//  _0099_恢复二叉搜索树.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#include "_0099_恢复二叉搜索树.hpp"

RecoverBinaryTree::RecoverBinaryTree() {
    prev = nullptr;
    first = nullptr;
    second = nullptr;
}

RecoverBinaryTree::~RecoverBinaryTree() {
    delete prev;
    delete first;
    delete second;
}

void RecoverBinaryTree::find(TreeNode* node) {
    // 出现了逆序对
    if (nullptr != prev && prev->val > node->val) {
        // 第二个错误节点：最后一个逆序对中较小的那个节点
        second = node;
        
        // 第一个错误节点：第一个逆序对中较大的那个节点
        if (nullptr != first) return;
        first = prev;
    }
    prev = node;
}

void RecoverBinaryTree::findWrongNodes(TreeNode* root) {
    if (nullptr == root) return;
    
    findWrongNodes(root->left);
    find(root);
    findWrongNodes(root->right);
}

void RecoverBinaryTree::recoverTree(TreeNode* root) {
    findWrongNodes(root);
    
    int tmp = first->val;
    first->val = second->val;
    second->val = tmp;
}

#pragma mark - 二叉树的 Morris 遍历
void RecoverBinaryTree::recoverTree_morris(TreeNode* root) {
    TreeNode* node = root;
    while (nullptr != node) {
        if (nullptr != node->left) {
            // 找到前驱节点(predecessor)、后继节点(successor)
            TreeNode* pred = node->left;
            while (nullptr != pred->right && pred->right != node) {
                pred = pred->right;
            }
            
            if (nullptr == pred->right) {
                pred->right = node;
                node = node->left;
            } else {
                find(node);
                pred->right = nullptr;
                node = node->right;
            }
        } else {
            find(node);
            node = node->right;
        }
    }
    
    int tmp = first->val;
    first->val = second->val;
    second->val = tmp;
}
