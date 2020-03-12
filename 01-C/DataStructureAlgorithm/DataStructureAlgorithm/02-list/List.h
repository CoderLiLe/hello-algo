//
//  List.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef List_h
#define List_h

#include <stdio.h>

typedef struct List *List;
struct List {
    void *val;
    List next;
};

extern List list_init(void);
extern List list_push(List list, void *val);
extern int list_length(List list);
extern bool list_empty(List list);
extern void **list_toArray(List list);
extern List list_append(List list, List tail);
extern List list_list(List list, void *val, ...);
extern List list_copy(List list);
extern List list_pop(List *list);

#endif /* List_h */
