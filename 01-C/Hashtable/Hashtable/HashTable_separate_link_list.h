//
//  HashTable_separate_link_list.h
//  Hashtable
//
//  Created by LiLe on 2019/6/18.
//  Copyright © 2019 LiLe. All rights reserved.
//  分离链表法
//  https://blog.csdn.net/shuxiao9058/article/details/7433895

#ifndef HashTable_separate_link_list_h
#define HashTable_separate_link_list_h

#include <stdio.h>

typedef int ElementType;
typedef unsigned int Index;

struct ListNode;
typedef struct ListNode *Position;
struct HashTbl;
typedef struct HashTbl *HashTable;

Index hash_separate_link_list(const int key, int table_size);

HashTable init_hashTable(int table_size);

void destroy_hashTable(ElementType key, HashTable ht);

Position find_separate_link_list(ElementType key, HashTable ht);

void insert_separate_link_list(ElementType key, HashTable ht);

ElementType retrieve(Position p);

#endif /* HashTable_separate_link_list_h */
