//
//  main.c
//  线性表
//
//  Created by LiLe on 2020/2/11.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdio.h>
#include "List.h"

void testList() {
    SqList L;
    SqList Lb;
    
    ElemType e;
    Status i;
    int j, k;
    
    i = initList(&L);
    printf("初始化L后：L.length = %d\n", L.length);
    for (j = 1; j <= 5; j++) {
        listInsert(&L, 1, j);
    }
    printf("在L的表头插入1～5后：L.data = ");
    listTraverse(L);
    
    i = listClear(&L);
    printf("清空L后：L.length = %d\n", L.length);
    i = listEmpty(&L);
    printf("L 是否为空：i = %d(1:是 0:否)\n", i);
    
    for (j = 1; j <= 10; j++) {
        listInsert(&L, j, j);
    }
    printf("在L的表尾依次插入1～10后：L.data = ");
    listTraverse(L);
    printf("L.length = %d\n", L.length);
    
    listInsert(&L, 1, 0);
    printf("在L的表头插入0后：L.data = ");
    listTraverse(L);
    printf("L.length = %d\n", L.length);
    
    getElem(L, 5, &e);
    printf("第5个元素的值为：%d\n", e);
    for (j = 3; j <= 4; j++) {
        k = localElem(L, j);
        if (k) {
            printf("第 %d 个元素的值为 %d\n", k, j);
        } else {
            printf("没有值为 %d 的元素\n", j);
        }
    }
    
    k = listLength(&L);
    for (j = k + 1; j >= k; j--) {
        i = listDelete(&L, j, &e);
        if (i == ERROR) {
            printf("删除第 %d 个数据失败\n", j);
        } else {
            printf("删除第 %d 个元素的值为 %d\n", j, e);
        }
    }
    
    printf("依次输出L的元素：");
    listTraverse(L);
    
    i = initList(&Lb);
    for (j = 6; j <= 15; j++) {
        i = listInsert(&Lb, 1, j);
    }
    printf("在Lb的表头插入6～15后：Lb.data = ");
    listTraverse(Lb);
    
    unionLists(&L, Lb);
    
    printf("依次输出合并了 Lb 的 L 的元素");
    listTraverse(L);
}

int main(int argc, const char * argv[]) {
    testList();
    
    return 0;
}

