//
//  BinaryTreeTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#include "BinaryTreeTest.hpp"
#include "_0099_恢复二叉搜索树.hpp"
#include "_0333_最大BST子树.hpp"

#include <iostream>

using namespace std;

void test0099() {
    TreeNode* node2 = new TreeNode(2);
    TreeNode* node3 = new TreeNode(3, nullptr, node2);
    TreeNode* node1 = new TreeNode(1, node3, nullptr);
    
    RecoverBinaryTree bt;
    bt.recoverTree(node1);
    
    cout << "恢复二叉搜索树完毕" << endl;
}

void test0099_02() {
    TreeNode* node2 = new TreeNode(2);
    TreeNode* node3 = new TreeNode(3, nullptr, node2);
    TreeNode* node1 = new TreeNode(1, node3, nullptr);
    
    RecoverBinaryTree bt;
    bt.recoverTree_morris(node1);
    
    cout << "恢复二叉搜索树完毕" << endl;
}

void test0333() {
    TreeNode* node1 = new TreeNode(1);
    TreeNode* node7 = new TreeNode(7);
    TreeNode* node8 = new TreeNode(8);
    TreeNode* node5 = new TreeNode(5, node1, node8);
    TreeNode* node15 = new TreeNode(15, nullptr, node7);
    TreeNode* node10 = new TreeNode(10, node5, node15);
    
    cout << largestBSTSubtree1(node10) << endl;
}

void test0333_02() {
    TreeNode* node1 = new TreeNode(1);
    TreeNode* node7 = new TreeNode(7);
    TreeNode* node8 = new TreeNode(8);
    TreeNode* node5 = new TreeNode(5, node1, node8);
    TreeNode* node15 = new TreeNode(15, nullptr, node7);
    TreeNode* node10 = new TreeNode(10, node5, node15);
    
    cout << largestBSTSubtree2(node10) << endl;
}

void binaryTreeTest() {
//    test0099();
//    test0099_02();
    
//    test0333();
    test0333_02();
}
