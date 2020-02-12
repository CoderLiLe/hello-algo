//
//  List.h
//  线性表
//
//  Created by LiLe on 2020/2/11.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef List_h
#define List_h

#include <stdio.h>

#define MAXSIZE 20
#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

typedef int Status;
typedef int ElemType;

typedef struct
{
    ElemType data[MAXSIZE]; // 存储数据的数组
    int length; // 线性表当前长度
}SqList;

Status initList(SqList *L);

Status listEmpty(SqList *L);

Status listClear(SqList *L);

int listLength(SqList *L);

Status getElem(SqList L, int i, ElemType *e);

int localElem(SqList L, ElemType e);

Status listInsert(SqList *L, int i, ElemType e);

Status listDelete(SqList *L, int i, ElemType *e);

Status listTraverse(SqList L);

void unionLists(SqList *la, SqList lb);

#endif /* List_h */
