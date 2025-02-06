//
//  BinaryTree.h
//  Leetcode
//
//  Created by LiLe on 2021/7/21.
//

#ifndef BinaryTree_h
#define BinaryTree_h

#include <stdio.h>

struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
};

typedef struct TreeNode TreeNode;

/**
 借助队列，实现按照层序创建二叉树，元素为 0 则表示该节点为空。

 @param elements 元素数组
 @param size elements 大小
 @return 根结点指针
 */
TreeNode *createBinaryTree(int *elements, int size);

/**
 二叉树的最大深度
 */
int maxDepth(TreeNode *tree);

/**
 二叉树的最小深度
 */
int minDepth(TreeNode *tree);

/**
 前序遍历打印二叉树
 */
void preOrderRecursive(TreeNode *tree);

void testDepth(void);

#endif /* BinaryTree_h */
