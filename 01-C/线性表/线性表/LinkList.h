//
//  LinkList.h
//  线性表
//
//  Created by LiLe on 2020/2/13.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef LinkList_h
#define LinkList_h

#include <stdio.h>
#include "Const.h"

typedef struct Node
{
    ElemType data;
    struct Node *next;
}Node;
typedef struct Node *LinkList; // 定义LinkList

Status initLinkList(LinkList *L);

Status linkListEmpty(LinkList L);

Status linkListClear(LinkList *L);

int linkListLength(LinkList *L);

Status getLinkListElem(LinkList L, int i, ElemType *e);

int localLinkListElem(LinkList L, ElemType e);

Status linkListInsert(LinkList *L, int i, ElemType e);

Status linkListDelete(LinkList *L, int i, ElemType *e);

Status linkListTraverse(LinkList L);

/**
随机产生n个元素的值，建立带表头结点的单链线性表L（头插法）
*/
void createLinkListHead(LinkList *L, int n);

/**
随机产生n个元素的值，建立带表头结点的单链线性表L（尾插法）
*/
void createLinkListTail(LinkList *L, int n);

#endif /* LinkList_h */
