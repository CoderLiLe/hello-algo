//
//  tree_test.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "tree_test.h"
#include "SqBinaryTree.h"
#include "LinkBinaryTree.h"

void sq_binary_tree_test()
{
    T value;
    Position p;
    SqBinaryTree tree;
    sq_binary_tree_init(tree);
    sq_binary_tree_create(tree);
    
    if (sq_binary_tree_empty(tree)) {
        printf("建立二叉树后,树为空\n");
    } else {
        printf("建立二叉树后,树不为空\n");
    }
    
    printf("树的深度 = %d\n", sq_binary_tree_depth(tree));
    
    
    if (sq_binary_tree_root(tree, &value)) {
        printf("二叉树的根为：%d\n", value);
    } else {
        printf("树空，无根\n");
    }
    
    printf("前序遍历二叉树:\n");
    sq_binary_tree_preorder_traverse(tree);
    printf("中序遍历二叉树:\n");
    sq_binary_tree_inorder_traverse(tree);
    printf("后序遍历二叉树:\n");
    sq_binary_tree_post_order_traverse(tree);
    printf("层序遍历二叉树:\n");
    sq_binary_tree_level_order_traverse(tree);
    
    printf("修改结点的层号3本层序号2。");
    p.level = 3;
    p.order = 2;
    value = sq_binary_tree_value(tree, p);
    printf("待修改结点的原值为%d请输入新值:50 ",value);
    
    value = 50;
    sq_binary_tree_assign(tree, p, value);
    
    printf("前序遍历二叉树:\n");
    sq_binary_tree_preorder_traverse(tree);
    
    
    printf("结点%d的双亲为%d,左右孩子分别为", value, sq_binary_tree_parent(tree, value));
    
    printf("%d, %d, 左右兄弟分别为", sq_binary_tree_left_child(tree, value), sq_binary_tree_right_child(tree, value));
    
    printf("%d, %d\n", sq_binary_tree_left_sibling(tree, value), sq_binary_tree_right_sibling(tree, value));
    
    sq_binary_tree_init(tree);
    if (sq_binary_tree_empty(tree)) {
        printf("建立二叉树后,树为空\n");
    } else {
        printf("建立二叉树后,树不为空\n");
    }
    
    printf("树的深度 = %d\n", sq_binary_tree_depth(tree));
    
    
    if (sq_binary_tree_root(tree, &value)) {
        printf("二叉树的根为：%d\n", value);
    } else {
        printf("树空，无根\n");
    }
}

void link_binary_tree_test()
{
    BiTree tree;
    
    link_binary_tree_init(&tree);
    link_binary_tree_nodes("ABDH#K###E##CFI###G#J##");
    link_binary_tree_create(&tree);
    
    link_binary_tree_preorder_traverse(tree);
    
    printf("\n");
}
