//
//  BinaryTree.hpp
//  54_KthNodeInBST
//
//  Created by LiLe on 2020/2/28.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef BinaryTree_hpp
#define BinaryTree_hpp

#include <stdio.h>

struct BinaryTreeNode
{
    int                    m_nValue;
    BinaryTreeNode *       m_pLeft;
    BinaryTreeNode *       m_pRight;
};

BinaryTreeNode *CreateBinaryTreeNode(int value);
void ConnectTreeNodes(BinaryTreeNode *pParent, BinaryTreeNode *pLeft, BinaryTreeNode *pRight);
void PrintTreeNode(const BinaryTreeNode *pNode);
void PrintTree(const BinaryTreeNode *pRoot);
void DestroyTree(BinaryTreeNode *pRoot);

#endif /* BinaryTree_hpp */
