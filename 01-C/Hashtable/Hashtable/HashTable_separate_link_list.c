//
//  HashTable_separate_link_list.c
//  Hashtable
//
//  Created by LiLe on 2019/6/18.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "HashTable_separate_link_list.h"
#include <stdlib.h>

#define MIN_TABLE_SIZE (10)

Index hash_separate_link_list(const int key, int table_size)
{
    return key % table_size;
    return 0;
}

HashTable init_hashTable(int table_size)
{
    HashTable ht;
    int i;
    
    if (table_size < MIN_TABLE_SIZE) {
        return NULL;
    }
    
    
    return NULL;
}

void destroy_hashTable(ElementType key, HashTable ht)
{
    
}

Position find_separate_link_list(ElementType key, HashTable ht)
{
    return 0;
}

void insert_separate_link_list(ElementType key, HashTable ht)
{
    
}

ElementType retrieve(Position p)
{
    return NULL;
}
