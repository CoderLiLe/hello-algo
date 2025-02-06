//
//  BinaryTree.cpp
//  54_KthNodeInBST
//
//  Created by LiLe on 2020/2/28.
//  Copyright © 2020 lile. All rights reserved.
//

#include "BinaryTree.hpp"
#include <iostream>

using namespace std;

BinaryTreeNode *CreateBinaryTreeNode(int value)
{
    BinaryTreeNode *node = new BinaryTreeNode();
    node->m_nValue = value;
    node->m_pLeft = nullptr;
    node->m_pRight = nullptr;
    return node;
}

void ConnectTreeNodes(BinaryTreeNode *pParent, BinaryTreeNode *pLeft, BinaryTreeNode *pRight)
{
    if (pParent != nullptr) {
        pParent->m_pLeft = pLeft;
        pParent->m_pRight = pRight;
    }
}

void PrintTreeNode(const BinaryTreeNode *pNode)
{
    if (pNode != nullptr) {
        cout << "value of this node is " << pNode->m_nValue << endl;
        if (pNode->m_pLeft != nullptr) {
            cout << "value of it's left child is " << pNode->m_pLeft->m_nValue << endl;
        } else {
            cout << "left child is nullptr." << endl;
        }
        
        if (pNode->m_pRight != nullptr) {
            cout << "value of it's right child is " << pNode->m_pRight->m_nValue << endl;
        } else {
            cout << "right child is nullptr." << endl;
        }
    } else {
        cout << "the node is nullptr" << endl;
    }
}

void PrintTree(const BinaryTreeNode *pRoot)
{
    PrintTreeNode(pRoot);
    
    if (pRoot != nullptr) {
        if (pRoot->m_pLeft != nullptr) {
            PrintTreeNode(pRoot->m_pLeft);
        }
        if (pRoot->m_pRight != nullptr) {
            PrintTreeNode(pRoot->m_pRight);
        }
    }
}

void DestroyTree(BinaryTreeNode *pRoot)
{
    if (pRoot != nullptr) {
        BinaryTreeNode *left = pRoot->m_pLeft;
        BinaryTreeNode *right = pRoot->m_pRight;
        
        delete pRoot;
        pRoot = nullptr;
        
        DestroyTree(left);
        DestroyTree(right);
    }
}
