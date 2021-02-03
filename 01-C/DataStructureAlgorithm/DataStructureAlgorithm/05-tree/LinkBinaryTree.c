//
//  LinkBinaryTree.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "LinkBinaryTree.h"

typedef char String[24]; // 0 号单元存放串的长度
String str;

bool link_binary_tree_init(BiTree *tree)
{
    *tree = NULL;
    return true;
}

bool link_binary_tree_nodes(char *chars)
{
    int i;
    
    str[0] = strlen(chars);
    for (i = 1; i <= str[0]; i++) {
        str[i] = *(chars + i - 1);
    }
    return true;
}

int char_index = 1;
void link_binary_tree_create(BiTree *tree)
{
    C ch;
    
    ch = str[char_index++];
    
    if (ch == '#') {
        *tree = NULL;
    } else {
        *tree = (BiTree)malloc(sizeof(BiTNode));
        (*tree)->data = ch; // 生成根结点
        link_binary_tree_create(&(*tree)->left); // 构造左子树
        link_binary_tree_create(&(*tree)->right); // 构造右子树
    }
}

bool link_binary_tree_destory(BiTree *tree)
{
    return true;
}

bool link_binary_tree_empty(BiTree tree)
{
    return true;
}

int link_binary_tree_depth(BiTree tree)
{
    return 0;
}

bool link_binary_tree_root(BiTree tree, C *value)
{
    return true;
}

C link_binary_tree_value(BiTree pTree)
{
    return 'a';
}

bool link_binary_tree_assign(BiTree pTree, C value)
{
    return true;
}

C link_binary_tree_parent(BiTree pTree, C value)
{
    return 'a';
}

C link_binary_tree_left_child(BiTree pTree, C value)
{
    return 'a';
}

C link_binary_tree_right_child(BiTree pTree, C value)
{
    return 'a';
}

C link_binary_tree_left_sibling(BiTree pTree, C value)
{
    return 'a';
}

C link_binary_tree_right_sibling(BiTree pTree, C value)
{
    return 'a';
}

void link_binary_tree_preorder_traverse(BiTree tree)
{
    if (tree == NULL) return;
    
    printf("%c", tree->data);
    link_binary_tree_preorder_traverse(tree->left);
    link_binary_tree_preorder_traverse(tree->right);
}

void link_binary_tree_inorder_traverse(BiTree tree)
{
    
}

void link_binary_tree_post_order_traverse(BiTree tree)
{
    
}

void link_binary_tree_level_order_traverse(BiTree tree)
{
    
}

void link_binary_tree_print(BiTree tree)
{
    
}
