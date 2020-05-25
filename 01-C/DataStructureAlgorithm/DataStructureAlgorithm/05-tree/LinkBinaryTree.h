//
//  LinkBinaryTree.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef LinkBinaryTree_h
#define LinkBinaryTree_h

#include <stdio.h>
#include <stdbool.h>
#include "Common.h"

typedef struct BiTNode {
    C data; // 节点数据
    struct BiTNode *left, *right; // 左右孩子指针
} BiTNode, *BiTree;

/** 构造空二叉树 tree */
bool link_binary_tree_init(BiTree *tree);

/** 按前序输入二叉树中结点的值（一个字符）#表示空树，构造二叉链表表示二叉树T */
bool link_binary_tree_create(BiTree *tree);

/**
 初始条件：二叉树存在
 操作结果：销毁二叉树
 */
bool link_binary_tree_destory(BiTree *tree);

/**
 初始条件: 二叉树存在
 操作结果: 若 tree 为空二叉树,则返回 tree, 否则 false
 */
bool link_binary_tree_empty(BiTree tree);

/**
 初始条件: 二叉树 tree存在
 操作结果: 返回 tree 的深度
 */
int link_binary_tree_depth(BiTree tree);

/**
 初始条件: 二叉树 tree 存在
 操作结果: 当 tree 不空,用 value 返回 tree 的根, 返回 true; 否则返回 false, value 无定义
 */
bool link_binary_tree_root(BiTree tree, C *value);

/**
 初始条件：二叉树存在，pTree 指向 Tree 中某个结点
 操作结果：返回 pTree 所指向结点的值
 */
T link_binary_tree_value(BiTree pTree);

/**
 给 p 所指结点赋值为 value
 */
bool link_binary_tree_assign(BiTree pTree, C value);

/** 若 value 所在节点是非根节点，则返回它的双亲；否则返回空 */
C link_binary_tree_parent(BiTree pTree, C value);

/** 若 value 所在节点是非根节点，则返回它的最左孩子；否则返回空 */
C link_binary_tree_left_child(BiTree pTree, C value);

/** 若 value 所在节点是非根节点，则返回它的最右孩子；否则返回空 */
C link_binary_tree_right_child(BiTree pTree, C value);

/** 若 value 所在节点有左兄弟，则返回它的左兄弟；否则返回空 */
C link_binary_tree_left_sibling(BiTree pTree, C value);

/** 若 value 所在节点有右兄弟，则返回它的左兄弟；否则返回空 */
C link_binary_tree_right_sibling(BiTree pTree, C value);

/** 前序遍历 */
void link_binary_tree_preorder_traverse(BiTree tree);

/** 中序遍历 */
void link_binary_tree_inorder_traverse(BiTree tree);

/** 后序遍历 */
void link_binary_tree_post_order_traverse(BiTree tree);

/** 层序遍历 */
void link_binary_tree_level_order_traverse(BiTree tree);

/** 打印 */
void link_binary_tree_print(BiTree tree);

#endif /* LinkBinaryTree_h */
