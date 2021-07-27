//
//  _0333_最大BST子树.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#include "_0333_最大BST子树.hpp"
#include <limits.h>
#include <algorithm>

bool isBST(TreeNode* root, int max, int min) {
    if (nullptr == root) return true;
    
    return min < root->val && root->val < max && isBST(root->left, root->val, min) && isBST(root->right, max, root->val);
}

bool isBST(TreeNode* root) {
    return isBST(root, INT_MAX, INT_MIN);
}

int nodesCount(TreeNode* root) {
    if (nullptr == root) return 0;
    return 1 + nodesCount(root->left) + nodesCount(root->right);
}

int largestBSTSubtree1(TreeNode* root) {
    if (nullptr == root) return 0;
    if (isBST(root)) return nodesCount(root);
    return std::max(largestBSTSubtree1(root->left), largestBSTSubtree1(root->right));
}

#pragma mark -
struct Info {
    /** 根节点 */
    TreeNode *root;
    /** 节点总数 */
    int size;
    /** 最小值 */
    int min;
    /** 最大值 */
    int max;

    Info() : root(nullptr), size(0), min(INT_MIN), max(INT_MAX) {}
    Info(TreeNode *root, int size, int min, int max) : root(root), size(size), min(min), max(max) {}
};

Info* getInfo(TreeNode * root) {
    if (nullptr == root) return nullptr;
    
    // li(left info)：左子树的最大BST子树信息
    Info* li = getInfo(root->left);
    
    // ri(right info)：右子树的最大BST子树信息
    Info* ri = getInfo(root->right);
    
    /**
     * 有4种情况，以 root 为根节点的二叉树就是一棵BST，最大BST就是其本身
     * ① li != null && ri != null
     * && li.root == root.left && root.val > li.max
     * && ri.root == root.right && root.val < ri.min
     *
     * ② li != null && ri == null
     * && li.root == root.left && root.val > li.max
     *
     * ③ li == null && ri != null
     * && ri.root == root.right && root.val < ri.min
     *
     * ④ li == null && ri == null
     */
    
    int leftBstSize = -1, rightBstSize = -1, max = root->val, min = root->val;
    if (nullptr == li) {
        leftBstSize = 0;
    } else if (li->root == root->left && root->val > li->max) {
        leftBstSize = li->size;
        min = li->min;
    }
    
    if (nullptr == ri) {
        rightBstSize = 0;
    } else if (ri->root == root->right && root->val < ri->min) {
        rightBstSize = ri->size;
        max = ri->max;
    }
    
    if (leftBstSize >= 0 && rightBstSize >= 0) {
        Info * info = (Info *)malloc(sizeof(Info));
        memset(info, 0, sizeof(info));
        info->root = root;
        info->size = 1 + leftBstSize + rightBstSize;
        info->max = max;
        info->min = min;
        return info;
    }
    
    // 以 root 为根节点的二叉树不是BST
    // 返回最大BST子树的节点数量较多的Info
    if (nullptr != li && nullptr != ri) {
        return (li->size > ri->size) ? li : ri;
    }
    
    // 返回 li、ri中不为null的那个 Info
    return (nullptr != li) ? li : ri;
}

int largestBSTSubtree2(TreeNode* root) {
    return (nullptr == root) ? 0 : getInfo(root)->size;
}

