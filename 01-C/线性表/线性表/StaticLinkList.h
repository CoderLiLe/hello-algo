//
//  StaticLinkList.h
//  线性表
//
//  Created by LiLe on 2020/2/20.
//  Copyright © 2020 lile. All rights reserved.
//  静态链表

#ifndef StaticLinkList_h
#define StaticLinkList_h

#include <stdio.h>
#include "Const.h"

#define SLLMAXSIZE 10 // 存储空间初始分配大小

typedef struct {
    ElemType data;
    int cur; // 游标(cursor)，为 0 时表示无指向
} Component, StaticLinkList[SLLMAXSIZE];

/**
 将一维数组 space 中各分量链成一个备用链表，space[0].cur 为头指针，"0"表示空指针
 */
Status initStaticLinkList(StaticLinkList space);

/**
 若备用空间链表非空，则返回分配的节点下标，否则返回 0
 */
int mallocSSL(StaticLinkList space);

/**
 将下标为 K 的空闲节点回收到备用链表
 */
void freeSSL(StaticLinkList space, int k);

/**
 初始条件：静态链表已存在。
 操作结果：返回 SLL 中数据元素个数
 */
int staticLinkListLength(StaticLinkList SLL);

/**
 在 SLL 中第 i 个元素之前插入新的元素 e
 */
Status staticLinkListInsert(StaticLinkList SLL, int i, ElemType e);

/**
 删除 SLL 中第 i 个元素
 */
Status staticLinkListDelete(StaticLinkList SLL, int i);

/**
 遍历 SLL 中的所有元素
 */
Status staticLinkListTraverse(StaticLinkList SLL);

#endif /* StaticLinkList_h */
