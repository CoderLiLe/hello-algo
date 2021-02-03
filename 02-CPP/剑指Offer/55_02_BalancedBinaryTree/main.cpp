//
//  main.cpp
//  55_02_BalancedBinaryTree
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 题目二：平衡二叉树
 题目：输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，
 那么它就是一棵平衡二叉树。
 */

#include <iostream>
#include "BinaryTree.hpp"

using namespace std;

#pragma mark - 方法一
int TreeDepth(const BinaryTreeNode *pRoot) {
    if (pRoot == nullptr) {
        return 0;
    }
    
    int leftDepth = TreeDepth(pRoot->m_pLeft);
    int rightDepth = TreeDepth(pRoot->m_pRight);
    
    return (leftDepth > rightDepth) ? leftDepth + 1 : rightDepth + 1;
}

bool IsBalanced_Solution1(BinaryTreeNode *pRoot) {
    if (pRoot == nullptr) {
        return true;
    }
    
    int leftHeight = TreeDepth(pRoot->m_pLeft);
    int rightHeight = TreeDepth(pRoot->m_pRight);
    int diff = leftHeight - rightHeight;
    if (diff > 1 || diff < -1) {
        return false;
    }
    
    return IsBalanced_Solution1(pRoot->m_pLeft) && IsBalanced_Solution1(pRoot->m_pRight);
}

#pragma mark - 方法二
/**
 每个节点只遍历一次：后序遍历二叉树，遍历某个节点时已经遍历它的左右子树。只要在遍历每个节点时，记录它的深度，
 就可以一边遍历一边判断每个节点是否平衡。
 */
bool IsBalancedCore(BinaryTreeNode *pRoot, int *depth);

bool IsBalanced_Solution2(BinaryTreeNode *pRoot) {
    int depth = 0;
    return IsBalancedCore(pRoot, &depth);
}

bool IsBalancedCore(BinaryTreeNode *pRoot, int *depth) {
    if (pRoot == nullptr) {
        *depth = 0;
        return true;
    }
    
    int left, right;
    if (IsBalancedCore(pRoot->m_pLeft, &left) && IsBalancedCore(pRoot->m_pRight, &right)) {
        int diff = left - right;
        if (diff <= 1 && diff >= -1) {
            *depth = 1 + (left > right ? left : right);
            return true;
        }
    }
    
    return false;
}

// ====================测试代码====================
void Test(const char* testName, BinaryTreeNode* pRoot, bool expected)
{
    if(testName != nullptr)
        cout << "--------------- " << testName << " ---------------" << endl;

    cout << "Solution1 : ";
    if(IsBalanced_Solution1(pRoot) == expected)
        cout << "passed." << endl;
    else
        cout << "failed." << endl;

    cout << "Solution2 : ";
    if(IsBalanced_Solution2(pRoot) == expected)
        cout << "passed." << endl;
    else
        cout << "failed." << endl;
    
    cout << endl;
}

// 完全二叉树
//             1
//         /      \
//        2        3
//       /\       / \
//      4  5     6   7
void Test1()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);
    BinaryTreeNode* pNode6 = CreateBinaryTreeNode(6);
    BinaryTreeNode* pNode7 = CreateBinaryTreeNode(7);

    ConnectTreeNodes(pNode1, pNode2, pNode3);
    ConnectTreeNodes(pNode2, pNode4, pNode5);
    ConnectTreeNodes(pNode3, pNode6, pNode7);

    Test("Test1", pNode1, true);

    DestroyTree(pNode1);
}

// 不是完全二叉树，但是平衡二叉树
//             1
//         /      \
//        2        3
//       /\         \
//      4  5         6
//        /
//       7
void Test2()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);
    BinaryTreeNode* pNode6 = CreateBinaryTreeNode(6);
    BinaryTreeNode* pNode7 = CreateBinaryTreeNode(7);

    ConnectTreeNodes(pNode1, pNode2, pNode3);
    ConnectTreeNodes(pNode2, pNode4, pNode5);
    ConnectTreeNodes(pNode3, nullptr, pNode6);
    ConnectTreeNodes(pNode5, pNode7, nullptr);

    Test("Test2", pNode1, true);

    DestroyTree(pNode1);
}

// 不是平衡二叉树
//             1
//         /      \
//        2        3
//       /\
//      4  5
//        /
//       6
void Test3()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);
    BinaryTreeNode* pNode6 = CreateBinaryTreeNode(6);

    ConnectTreeNodes(pNode1, pNode2, pNode3);
    ConnectTreeNodes(pNode2, pNode4, pNode5);
    ConnectTreeNodes(pNode5, pNode6, nullptr);

    Test("Test3", pNode1, false);

    DestroyTree(pNode1);
}


//               1
//              /
//             2
//            /
//           3
//          /
//         4
//        /
//       5
void Test4()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);

    ConnectTreeNodes(pNode1, pNode2, nullptr);
    ConnectTreeNodes(pNode2, pNode3, nullptr);
    ConnectTreeNodes(pNode3, pNode4, nullptr);
    ConnectTreeNodes(pNode4, pNode5, nullptr);

    Test("Test4", pNode1, false);

    DestroyTree(pNode1);
}

// 1
//  \
//   2
//    \
//     3
//      \
//       4
//        \
//         5
void Test5()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    BinaryTreeNode* pNode2 = CreateBinaryTreeNode(2);
    BinaryTreeNode* pNode3 = CreateBinaryTreeNode(3);
    BinaryTreeNode* pNode4 = CreateBinaryTreeNode(4);
    BinaryTreeNode* pNode5 = CreateBinaryTreeNode(5);

    ConnectTreeNodes(pNode1, nullptr, pNode2);
    ConnectTreeNodes(pNode2, nullptr, pNode3);
    ConnectTreeNodes(pNode3, nullptr, pNode4);
    ConnectTreeNodes(pNode4, nullptr, pNode5);

    Test("Test5", pNode1, false);

    DestroyTree(pNode1);
}

// 树中只有1个结点
void Test6()
{
    BinaryTreeNode* pNode1 = CreateBinaryTreeNode(1);
    Test("Test6", pNode1, true);

    DestroyTree(pNode1);
}

// 树中没有结点
void Test7()
{
    Test("Test7", nullptr, true);
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    Test6();
    Test7();
    
    return 0;
}
