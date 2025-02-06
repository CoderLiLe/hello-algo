//
//  LinkList.c
//  线性表
//
//  Created by LiLe on 2020/2/13.
//  Copyright © 2020 lile. All rights reserved.
//

#include "LinkList.h"
#include "stdlib.h"
#include "math.h"
#include "time.h"

Status initLinkList(LinkList *L)
{
    // 创建头节点，并使L指向头节点
    *L = (LinkList)malloc(sizeof(Node));
    if (!(*L)) { // 存储空间分配失败
        return ERROR;
    }
    (*L)->next = NULL;
    return OK;
}

Status linkListEmpty(LinkList L)
{
    if (L->next) {
        return FALSE;
    } else {
        return TRUE;
    }
}

Status linkListClear(LinkList *L)
{
    LinkList p, q;
    p = (*L)->next; // p 指向第一个节点
    while (p) {
        q = p->next;
        free(p);
        p = q;
    }
    (*L)->next = NULL; // 头节点指针域置为空
    return OK;
}

int linkListLength(LinkList *L)
{
    int len = 0;
    LinkList p = (*L)->next;
    while (p) {
        ++len;
        p = p->next;
    }
    return len;
}

Status getLinkListElem(LinkList L, int i, ElemType *e)
{
    int j;
    LinkList p;
    p = L->next;
    j = 1; // j 为计数器
    while (p && j < i) { // p 不为空并且 j 小于 i时继续循环
        p = p->next;
        j++;
    }
    if (!p || j > i) {
        return ERROR;
    }
    *e = p->data;
    return OK;
}

int localLinkListElem(LinkList L, ElemType e)
{
    int i = 0;
    LinkList p = L->next;
    while (p) {
        i++;
        if (p->data == e) {
            return i;
        }
        p = p->next;
    }
    return -1;
}

Status linkListInsert(LinkList *L, int i, ElemType e)
{
    int j;
    LinkList p, s;
    p = *L;
    j = 1;
    while (p && j < i) {
        p = p->next;
        ++j;
    }
    if (!p || j > i) {
        return ERROR;
    }
    s = (LinkList)malloc(sizeof(Node));
    s->data = e;
    s->next = p->next;
    p->next = s;
    return OK;
}

Status linkListDelete(LinkList *L, int i, ElemType *e)
{
    int j;
    LinkList p, q;
    p = *L;
    j = 1;
    while (p->next && j < i) {
        p = p->next;
        ++j;
    }
    if (!(p->next) || j > i) {
        return ERROR;
    }
    q = p->next;
    p->next = q->next;
    *e = q->data;
    free(q); // 系统回收此节点，释放内存
    return OK;
}

Status linkListTraverse(LinkList L)
{
    LinkList p = L->next;
    while (p) {
        printf("%d ", p->data);
        p = p->next;
    }
    printf("\n");
    return OK;
}

void createLinkListHead(LinkList *L, int n)
{
    LinkList p;
    int i;
    srand(time(0)); // 初始化随机数种子
    *L = (LinkList *)malloc(sizeof(Node));
    (*L)->next = NULL;
    for (i = 0; i < n; i++) {
        p = (LinkList)malloc(sizeof(Node));
        p->data = rand() % 100 + 1; // 随机生成100以内的数字
        p->next = (*L)->next;
        (*L)->next = p; // 插入到表头
    }
}

void createLinkListTail(LinkList *L, int n)
{
    LinkList p, r;
    int i;
    srand(time(0)); // 初始化随机数种子
    *L = (LinkList *)malloc(sizeof(Node));
    r = *L;
    for (i = 0; i < n; i++) {
        p = (LinkList)malloc(sizeof(Node));
        p->data = rand() % 100 + 1; // 随机生成100以内的数字
        r->next = p;
        r = p;
    }
    r->next = NULL;
}
