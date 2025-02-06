//
//  HashTable_linear_detection.c
//  Hashtable
//
//  Created by LiLe on 2019/6/18.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "HashTable_linear_detection.h"

#include <stdlib.h>

#define OK 1
#define ERROR 1
#define HASHSIZE 7
#define NULLKEY -32786

typedef int Status;
typedef struct {
    int *elem; // 基址
    int count; // 当前数据元素个数
}HashTable;

int m = 0; // 散列表表长

/* 初始化 */
Status init(HashTable *hashTable)
{
    int i;
    m = HASHSIZE;
    hashTable->elem = (int *)malloc(m * sizeof(int)); // 申请内存
    hashTable->count = m;
    for (i = 0; i < m; i++) {
        hashTable->elem[i] = NULLKEY;
    }
    return OK;
}

/* 哈希函数（除留余数法） */
int hash(int data)
{
    return data % m;
}

/* 插入 */
void insert(HashTable *hashTable, int data)
{
    int hashAddress = hash(data); // 求哈希地址
    
    while (hashTable->elem[hashAddress] != NULLKEY) {
        // 利用开放定址的线性探测解决冲突
        hashAddress = (++hashAddress)%m;
    }
    
    // 插入值
    hashTable->elem[hashAddress] = data;
}

/* 查找 */
int search(HashTable *hashTable, int data)
{
    int hashAddress = hash(data);
    
    while (hashTable->elem[hashAddress] != data) {
        hashAddress = (++hashAddress)%m;
        
        if (hashTable->elem[hashAddress] == NULLKEY || hashAddress == hash(data)) return -1;
    }
    
    return hashAddress;
}

/* 打印 */
void display(HashTable *hashTable)
{
    int i;
    printf("\n//=========================//\n");
    
    for (i = 0; i < hashTable->count; i++) {
        printf("%d ", hashTable->elem[i]);
    }
    
    printf("\n//=========================//\n");
}

void hashTable_linear_detectio_test()
{
    int i, result;
    HashTable hashTable;
    int arr[HASHSIZE] = {13, 29, 27, 28, 26, 30, 38};
    
    printf("***************Hash哈希算法***************\n");
    
    init(&hashTable);
    
    for (i = 0; i < HASHSIZE; i++) {
        insert(&hashTable, arr[i]);
    }
    
    display(&hashTable);
    
    result = search(&hashTable, 23);
    if (-1 == result) {
        printf("对不起，没有找到！\n");
    } else {
        printf("29在哈希表中的位置是:%d\n",result);
    }
}
