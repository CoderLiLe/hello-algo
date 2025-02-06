//
//  main.cpp
//  07_ConstructBinaryTree
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/*
 面试题7：重建二叉树
 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含
 重复的数字。例如输入前序遍历序列{1, 2, 4, 7, 3, 5, 6, 8}和中序遍历序列{4, 7, 2, 1, 5, 3, 8, 6}，
 则重建出图2.6所示的二叉树并输出它的头结点。
 **/

#include <iostream>
#include "BinaryTree.hpp"
#include <exception>

using namespace std;

BinaryTreeNode * constructCore(int *startPreOrder, int *endPreOrder, int *startInOrder, int *endInOrder);

BinaryTreeNode * construct(int *preOrder, int *inOrder, int length)
{
    if (preOrder == nullptr || inOrder == nullptr || length == 0) {
        return nullptr;
    }
    return constructCore(preOrder, preOrder+length-1, inOrder, inOrder+length-1);
}

BinaryTreeNode * constructCore(int *startPreOrder, int *endPreOrder,
                               int *startInOrder, int *endInOrder) {
    int rootValue = startPreOrder[0];
    BinaryTreeNode *root = new BinaryTreeNode();
    root->m_nValue = rootValue;
    root->m_pLeft = root->m_pRight = nullptr;
    
    if (startPreOrder == endPreOrder) {
        if (startInOrder == endInOrder && *startPreOrder == *startInOrder) {
            return root;
        } else {
            throw exception();
        }
    }
    
    // 在中序遍历中找到根结点的位置
    int *rootInOrder = startInOrder;
    while (rootInOrder <= endInOrder && *rootInOrder != rootValue) {
        ++rootInOrder;
    }
    
    if(rootInOrder == endInOrder && *rootInOrder != rootValue)
        throw exception();

    int leftLength = rootInOrder - startInOrder;
    int *leftPreOrderEnd = startPreOrder + leftLength;
    if(leftLength > 0)
    {
        // 构建左子树
        root->m_pLeft = constructCore(startPreOrder + 1, leftPreOrderEnd,
            startInOrder, rootInOrder - 1);
    }
    if(leftLength < endPreOrder - startPreOrder)
    {
        // 构建右子树
        root->m_pRight = constructCore(leftPreOrderEnd + 1, endPreOrder,
            rootInOrder + 1, endInOrder);
    }

    return root;
}

// ====================测试代码====================
void Test(const char* testName, int* preorder, int* inorder, int length)
{
    if(testName != nullptr)
        cout << "---------------- " << testName << " ----------------" << endl;

    cout << "The preorder sequence is: ";
    for(int i = 0; i < length; ++ i)
        cout << preorder[i] << " ";
    cout << endl;

    cout << "The inorder sequence is: ";
    for(int i = 0; i < length; ++ i)
        cout << preorder[i] << " ";
    cout << endl;

    try
    {
        BinaryTreeNode* root = construct(preorder, inorder, length);
        PrintTree(root);

        DestroyTree(root);
    } catch(exception& exception) {
        cout << "Invalid Input." << endl;
    }
}

// 普通二叉树
//              1
//           /     \
//          2       3
//         /       / \
//        4       5   6
//         \         /
//          7       8
void Test1()
{
    const int length = 8;
    int preorder[length] = {1, 2, 4, 7, 3, 5, 6, 8};
    int inorder[length] = {4, 7, 2, 1, 5, 3, 8, 6};

    Test("Test1", preorder, inorder, length);
}

// 所有结点都没有右子结点
//            1
//           /
//          2
//         /
//        3
//       /
//      4
//     /
//    5
void Test2()
{
    const int length = 5;
    int preorder[length] = {1, 2, 3, 4, 5};
    int inorder[length] = {5, 4, 3, 2, 1};

    Test("Test2", preorder, inorder, length);
}

// 所有结点都没有左子结点
//            1
//             \
//              2
//               \
//                3
//                 \
//                  4
//                   \
//                    5
void Test3()
{
    const int length = 5;
    int preorder[length] = {1, 2, 3, 4, 5};
    int inorder[length] = {1, 2, 3, 4, 5};

    Test("Test3", preorder, inorder, length);
}

// 树中只有一个结点
void Test4()
{
    const int length = 1;
    int preorder[length] = {1};
    int inorder[length] = {1};

    Test("Test4", preorder, inorder, length);
}

// 完全二叉树
//              1
//           /     \
//          2       3
//         / \     / \
//        4   5   6   7
void Test5()
{
    const int length = 7;
    int preorder[length] = {1, 2, 4, 5, 3, 6, 7};
    int inorder[length] = {4, 2, 5, 1, 6, 3, 7};

    Test("Test5", preorder, inorder, length);
}

// 输入空指针
void Test6()
{
    Test("Test6", nullptr, nullptr, 0);
}

// 输入的两个序列不匹配
void Test7()
{
    const int length = 7;
    int preorder[length] = {1, 2, 4, 5, 3, 6, 7};
    int inorder[length] = {4, 2, 8, 1, 6, 3, 7};

    Test("Test7: for unmatched input", preorder, inorder, length);
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
