//
//  SqBinaryTree.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "SqBinaryTree.h"

static const T Nil = 0;

bool sq_binary_tree_init(SqBinaryTree tree)
{
    int i;
    for (i = 0; i < MAX_TREE_SIZE; i++) {
        tree[i] = Nil;
    }
    return true;
}

bool sq_binary_tree_create(SqBinaryTree tree)
{
    int i = 0;
    printf("请按层序输入结点的值(整型)，0表示空结点，输999结束。结点数 ≤ %d:\n",MAX_TREE_SIZE);
    while(i < 10)
    {
        tree[i] = i + 1;
        
        if(i != 0 && tree[(i+1)/2-1] == Nil && tree[i] != Nil) /* 此结点(不空)无双亲且不是根 */
        {
            printf("出现无双亲的非根结点%d\n", tree[i]);
            return false;
        }
        i++;
    }
    
    while(i<MAX_TREE_SIZE)
    {
        tree[i] = Nil; /* 将空赋值给T的后面的结点 */
        i++;
    }
    return true;
}

bool sq_binary_tree_destory(SqBinaryTree tree)
{
    return true;
}

bool sq_binary_tree_empty(SqBinaryTree tree)
{
    return tree[0] == Nil;
}

int sq_binary_tree_depth(SqBinaryTree tree)
{
    int i, depth = -1;
    for(i = MAX_TREE_SIZE-1; i >= 0; i--) /* 找到最后一个结点 */
    {
        if(tree[i] != Nil)
        break;
    }

    i++;
    do {
        depth++;
    } while (i >= powl(2, depth)); /* 计算2的j次幂。 */
    
    return depth;
}

bool sq_binary_tree_root(SqBinaryTree tree, T *value)
{
    if (sq_binary_tree_empty(tree)) {
        return false;
    } else {
        *value = tree[0];
    }
    return true;
}

T sq_binary_tree_value(SqBinaryTree tree, Position posi)
{
    return 0;
}

bool sq_binary_tree_assign(SqBinaryTree tree, Position posi, T value)
{
    return true;
}

T sq_binary_tree_parent(SqBinaryTree tree, T value)
{
    return 0;
}

T sq_binary_tree_left_child(SqBinaryTree tree, T value)
{
    return 0;
}

T sq_binary_tree_right_child(SqBinaryTree tree, T value)
{
    return 0;
}

T sq_binary_tree_left_sibling(SqBinaryTree tree, T value)
{
    return 0;
}

T sq_binary_tree_right_sibling(SqBinaryTree tree, T value)
{
    return 0;
}

#pragma mark - 四种二叉树的遍历
void sq_binary_tree_preorder_traverse(SqBinaryTree tree)
{
    
}

void sq_binary_tree_inorder_traverse(SqBinaryTree tree)
{
    
}

void sq_binary_tree_post_order_traverse(SqBinaryTree tree)
{
    
}

void sq_binary_tree_level_order_traverse(SqBinaryTree tree)
{
    
}

#pragma mark - 打印
void sq_binary_tree_print(SqBinaryTree tree)
{
    
}
