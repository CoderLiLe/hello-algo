//
//  List.c
//  线性表
//
//  Created by LiLe on 2020/2/11.
//  Copyright © 2020 lile. All rights reserved.
//

#include "List.h"

Status initList(SqList *L) {
    L->length = 0;
    return OK;
}

Status listEmpty(SqList *L) {
    if (L->length == 0) {
        return TRUE;
    } else {
        return FALSE;
    }
}

Status listClear(SqList *L) {
    L->length = 0;
    return OK;
}

int listLength(SqList *L) {
    return L->length;
}

Status getElem(SqList L, int i, ElemType *e) {
    if (L.length == 0 || i < 1 || i > L.length)
        return ERROR;
    *e = L.data[i - 1];
    return OK;
}

int localElem(SqList L, ElemType e) {
    int i;
    if (L.length == 0)
        return 0;
    for (i = 0; i < L.length; i++) {
        if (L.data[i] == e)
            break;
    }
    if (i >= L.length)
        return 0;
    return i + 1;
}

Status listInsert(SqList *L, int i, ElemType e) {
    int k;
    if (L->length == MAXSIZE)
        return ERROR;
    if (i < 1 || i > L->length + 1)
        return ERROR;
    if (i <= L->length) {
        for (k = L->length - 1; k >= i - 1; k--) {
            L->data[k + 1] = L->data[k];
        }
    }
    L->data[i - 1] = e;
    L->length++;
    
    return OK;
}

Status listDelete(SqList *L, int i, ElemType *e) {
    int k;
    if (L->length == 0)
        return ERROR;
    if (i < 1 || i > L->length) {
        return ERROR;
    }
    *e = L->data[i - 1];
    if (i < L->length) {
        for (k = i; k < L->length; k++) {
            L->data[k - 1] = L->data[k];
        }
    }
    L->length--;
    return OK;
}

Status listTraverse(SqList L) {
    int i;
    for (i = 0; i < L.length; i++) {
        printf("%d ", L.data[i]);
    }
    printf("\n");
    return OK;
}

void unionLists(SqList *la, SqList lb) {
    int la_len, lb_len, i;
    ElemType e;
    la_len = la->length;
    lb_len = lb.length;
    for (i = 1; i <= lb_len; i++) {
        getElem(lb, i, &e);
        if (!localElem(*la, e)) {
            listInsert(la, ++la_len, e);
        }
    }
}



