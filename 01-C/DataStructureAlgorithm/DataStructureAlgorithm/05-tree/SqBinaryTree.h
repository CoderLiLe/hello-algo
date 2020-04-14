//
//  SqBinaryTree.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef SqBinaryTree_h
#define SqBinaryTree_h

#include <stdio.h>
#include <stdbool.h>
#include "Common.h"

static const int MAX_TREE_SIZE = 100;

typedef T SqBinaryTree[MAX_TREE_SIZE];

typedef struct {
    int level, order;
} Position;

/** 构造空二叉树 tree。因为 tree 是固定数组，不会改变，故不需要& */
bool sq_binary_tree_init(SqBinaryTree tree);

/** 按层序次序输入二叉树中结点的值(字符型或整型), 构造顺序存储的二叉树 */
bool sq_binary_tree_create(SqBinaryTree tree);

bool sq_binary_tree_destory(SqBinaryTree tree);

/**
 初始条件: 二叉树存在
 操作结果: 若 tree 为空二叉树,则返回 tree, 否则 false
 */
bool sq_binary_tree_empty(SqBinaryTree tree);

/**
 初始条件: 二叉树 tree存在
 操作结果: 返回 tree 的深度
 */
int sq_binary_tree_depth(SqBinaryTree tree);

/**
 初始条件: 二叉树 tree 存在
 操作结果: 当 tree 不空,用 value 返回 tree 的根, 返回 true; 否则返回 false, value 无定义
 */
bool sq_binary_tree_root(SqBinaryTree tree, T *value);
T sq_binary_tree_value(SqBinaryTree tree, Position posi);
bool sq_binary_tree_assign(SqBinaryTree tree, Position posi, T value);

/** 若 value 所在节点是非根节点，则返回它的双亲；否则返回空 */
T sq_binary_tree_parent(SqBinaryTree tree, T value);

/** 若 value 所在节点是非根节点，则返回它的最左孩子；否则返回空 */
T sq_binary_tree_left_child(SqBinaryTree tree, T value);

/** 若 value 所在节点是非根节点，则返回它的最右孩子；否则返回空 */
T sq_binary_tree_right_child(SqBinaryTree tree, T value);

/** 若 value 所在节点有左兄弟，则返回它的左兄弟；否则返回空 */
T sq_binary_tree_left_sibling(SqBinaryTree tree, T value);

/** 若 value 所在节点有右兄弟，则返回它的左兄弟；否则返回空 */
T sq_binary_tree_right_sibling(SqBinaryTree tree, T value);

/** 前序遍历 */
void sq_binary_tree_preorder_traverse(SqBinaryTree tree);

/** 中序遍历 */
void sq_binary_tree_inorder_traverse(SqBinaryTree tree);

/** 后序遍历 */
void sq_binary_tree_post_order_traverse(SqBinaryTree tree);

/** 层序遍历 */
void sq_binary_tree_level_order_traverse(SqBinaryTree tree);

/** 打印 */
void sq_binary_tree_print(SqBinaryTree tree);
#endif /* SqBinaryTree_h */
