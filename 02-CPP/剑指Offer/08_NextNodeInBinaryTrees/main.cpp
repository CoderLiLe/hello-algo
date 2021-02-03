//
//  main.cpp
//  08_NextNodeInBinaryTrees
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题8：二叉树的下一个结点
 题目：给定一棵二叉树和其中的一个结点，如何找出中序遍历顺序的下一个结点？
 树中的结点除了有两个分别指向左右子结点的指针以外，还有一个指向父结点的指针。
 
 */

/**
 1、如果一个节点有右子树，那么它的下一个节点就是右子树中最左子节点
 2、节点没有右子树，
    2.1 它是父节点的右子节点，沿着指向父节点的指针向上遍历，直到找到一个是它父节点的左子节点的节点。
 如果这样的节点存在，那么此节点的父节点就是要找的下一个节点。否则没有
    2.2 它是父节点的左子节点，则下一个节点为父节点
 
 */

#include <iostream>
#include "BinaryTree.hpp"

using namespace std;

BinaryTreeNode* GetNext(BinaryTreeNode* pNode)
{
    if(pNode == nullptr)
        return nullptr;
    
    BinaryTreeNode *pNext = nullptr;
    // 1、如果一个节点有右子树，那么它的下一个节点就是右子树中最左子节点
    if (pNode->m_pRight != nullptr) {
        BinaryTreeNode *pRight = pNode->m_pRight;
        while (pRight->m_pLeft != nullptr) {
            pRight = pRight->m_pLeft;
        }
        pNext = pRight;
    } else if (pNode->m_pParent != nullptr) { // 2、节点没有右子树，
        BinaryTreeNode *pCurrent = pNode;
        BinaryTreeNode *pParent = pNode->m_pParent;
        // 2.1 它是父节点的右子节点，沿着指向父节点的指针向上遍历，直到找到一个是它父节点的左子节点的节点。
        // 如果这样的节点存在，那么此节点的父节点就是要找的下一个节点。否则没有
        while (pParent != nullptr && pCurrent == pParent->m_pRight) {
            pCurrent = pParent;
            pParent = pParent->m_pParent;
        }
        
        // 2.2 它是父节点的左子节点，则下一个节点为父节点
        pNext = pParent;;
    }
    return pNext;
}

// ====================测试代码====================
void Test(const char* testName, BinaryTreeNode* pNode, BinaryTreeNode* expected)
{
    if(testName != nullptr)
        cout << testName << " : ";

    BinaryTreeNode* pNext = GetNext(pNode);
    if(pNext == expected)
        cout << "passed." << endl;
    else
        cout << "failed." << endl;
}

//            8
//        6      10
//       5 7    9  11
void Test1_7()
{
    BinaryTreeNode* pNode8 = CreateBinaryTreeNode(8);
    BinaryTreeNode* pNode6 = CreateBinaryTreeNode(6);
    BinaryTreeNode* pNode10 = CreateBinaryTreeNode(10);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);
    BinaryTreeNode* pNode7 = CreateBinaryTreeNode(7);
    BinaryTreeNode* pNode9 = CreateBinaryTreeNode(9);
    BinaryTreeNode* pNode11 = CreateBinaryTreeNode(11);

    ConnectTreeNodes(pNode8, pNode6, pNode10);
    ConnectTreeNodes(pNode6, pNode5, pNode7);
    ConnectTreeNodes(pNode10, pNode9, pNode11);

    Test("Test1", pNode8, pNode9);
    Test("Test2", pNode6, pNode7);
    Test("Test3", pNode10, pNode11);
    Test("Test4", pNode5, pNode6);
    Test("Test5", pNode7, pNode8);
    Test("Test6", pNode9, pNode10);
    Test("Test7", pNode11, nullptr);

    DestroyTree(pNode8);
}

//            5
//          4
//        3
//      2
void Test8_11()
{
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);

    ConnectTreeNodes(pNode5, pNode4, nullptr);
    ConnectTreeNodes(pNode4, pNode3, nullptr);
    ConnectTreeNodes(pNode3, pNode2, nullptr);

    Test("Test8", pNode5, nullptr);
    Test("Test9", pNode4, pNode5);
    Test("Test10", pNode3, pNode4);
    Test("Test11", pNode2, pNode3);

    DestroyTree(pNode5);
}

//        2
//         3
//          4
//           5
void Test12_15()
{
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);

    ConnectTreeNodes(pNode2, nullptr, pNode3);
    ConnectTreeNodes(pNode3, nullptr, pNode4);
    ConnectTreeNodes(pNode4, nullptr, pNode5);

    Test("Test12", pNode5, nullptr);
    Test("Test13", pNode4, pNode5);
    Test("Test14", pNode3, pNode4);
    Test("Test15", pNode2, pNode3);

    DestroyTree(pNode2);
}

void Test16()
{
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);

    Test("Test16", pNode5, nullptr);

    DestroyTree(pNode5);
}


int main(int argc, const char * argv[]) {
    Test1_7();
    Test8_11();
    Test12_15();
    Test16();
    
    return 0;
}
