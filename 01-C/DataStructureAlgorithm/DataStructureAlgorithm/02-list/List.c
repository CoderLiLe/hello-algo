//
//  List.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include <assert.h>
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdarg.h>
#include "List.h"

List list_init(void)
{
    List list = (List)malloc(sizeof(List));
    list->next = NULL;
    return list;
}

List list_push(List list, void *val)
{
    List new_ele = (List)malloc(sizeof(List));
    new_ele->val = val;
    new_ele->next = list;
    return new_ele;
}

int list_length(List list)
{
    int length = 0;
    while (list) {
        list = list->next;
        length++;
    }
    return length;
}

void **list_toArray(List list)
{
    int i, length = list_length(list);
    void **array = (void **)malloc((length + 1) * sizeof(list));
    for (i = 0; i < length; i++) {
        array[i] = list->val;
        list = list->next;
    }
    array[i] = NULL;
    return array;
}

/* Append 2 lists together */
List list_append(List list, List tail)
{
    List *p = &list;
    while ((*p)->next) {
        p = &(*p)->next;
    }
    *p = tail;
    return list;
}

List list_list(List list, void *val, ...)
{
    va_list ap;
    List *p = &list;
    
    va_start(ap, val);
    for (; val; val = va_arg(ap, void *)) {
        *p = malloc(sizeof(List));
        (*p)->val = val;
        p = &(*p)->next;
    }
    *p = NULL;
    va_end(ap);
    return list;
}

List list_copy(List list)
{
    List copy_list = (List)malloc(sizeof(List));
    copy_list->val = list->val;
    copy_list->next = NULL;
    
    list = list->next;
    List p_list = copy_list;
    
    while (list) {
        List new_ele = (List)malloc(sizeof(List));
        new_ele->val = list->val;
        p_list->next = new_ele;
        p_list = p_list->next;
        list = list->next;
    }
    p_list->next = NULL;
    
    return copy_list;
}

List list_pop(List *list)
{
    List pop_ele = *list;
    *list = (*list)->next;
    return pop_ele;
}
