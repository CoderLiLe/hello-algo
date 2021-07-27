//
//  BinaryTreeTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/27.
//

#include "BinaryTreeTest.hpp"
#include "_0099_恢复二叉搜索树.hpp"
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

void binaryTreeTest() {
//    test0099();
    test0099_02();
}
