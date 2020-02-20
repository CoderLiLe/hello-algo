//
//  StaticLinkList.c
//  线性表
//
//  Created by LiLe on 2020/2/20.
//  Copyright © 2020 lile. All rights reserved.
//

#include "StaticLinkList.h"

Status visit(ElemType c) {
    printf("%c ", c);
    return OK;
}

/**
 将一维数组 space 中各分量链成一个备用链表，space[0].cur 为头指针，"0"表示空指针
 */
Status initStaticLinkList(StaticLinkList space)
{
    int i;
    for (i = 0; i < SLLMAXSIZE - 1; i++) {
        space[i].cur = i + 1;
    }
    // 当前的静态链表为空，最后一个元素的 cur 指向空
    space[SLLMAXSIZE - 1].cur = 0;
    return OK;
}

/**
 若备用空间链表非空，则返回分配的节点下标，否则返回 0
 */
int mallocStaticLinkList(StaticLinkList space)
{
    // 当前数组中第一个元素 cur 存储的值就是要返回的第一个备用空间的下标
    int i = space[0].cur;
    
    if (space[0].cur) {
        space[0].cur = space[i].cur;
    }
    
    return i;
}

/**
 将下标为 K 的空闲节点回收到备用链表
 */
void freeStaticLinkList(StaticLinkList space, int k)
{
    // 交换要删除元素和第一个元素的下标
    
    // 把第一个元素的 cur 赋值给要删除的元素的 cur
    space[k].cur = space[0].cur;
    // 把要删除的元素的下标赋值给第一个元素的 cur
    space[0].cur = k;
}

/**
 初始条件：静态链表已存在。
 操作结果：返回 SLL 中数据元素个数
 */
int staticLinkListLength(StaticLinkList SLL)
{
    int j = 0;
    int i = SLL[SLLMAXSIZE - 1].cur;
    while (i) {
        i = SLL[i].cur;
        j++;
    }
    
    return j;
}

/**
 在 SLL 中第 i 个元素之前插入新的元素 e
 */
Status staticLinkListInsert(StaticLinkList SLL, int i, ElemType e)
{
    int j, k, l;
    k = SLLMAXSIZE - 1;
    if (i < 1 || i > staticLinkListLength(SLL) + 1) {
        return ERROR;
    }
    
    j = mallocStaticLinkList(SLL); // 获取空闲分量的下标
    if (j) {
        SLL[j].data = e;
        for (l = 1; l <= i - 1; l++) { // 找到第 i 个元素之前的位置
            k = SLL[k].cur;
        }
        SLL[j].cur = SLL[k].cur; // 把第 i 个元素之前元素的 cur 赋值给新元素的 cur
        SLL[k].cur = j; // 把新元素的下标赋值给第 i 个元素之前元素的 cur
        return OK;
    }
    return ERROR;
}

/**
 删除 SLL 中第 i 个元素
 */
Status staticLinkListDelete(StaticLinkList SLL, int i)
{
    int j, k;
    if (i < 1 || i > staticLinkListLength(SLL)) {
        return ERROR;
    }
    k = SLLMAXSIZE - 1;
    for (j = 1; j <= i - 1; j++) {
        k = SLL[k].cur;
    }
    j = SLL[k].cur;
    SLL[k].cur = SLL[j].cur;
    freeStaticLinkList(SLL, j);
    return OK;
}

/**
 遍历 SLL 中的所有元素
 */
Status staticLinkListTraverse(StaticLinkList SLL)
{
    int i = SLL[SLLMAXSIZE - 1].cur;
    while (i) {
        visit(SLL[i].data);
        i = SLL[i].cur;
    }
    printf("\n");
    return OK;
}

