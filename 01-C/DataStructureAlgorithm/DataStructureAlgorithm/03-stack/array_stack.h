//
//  array_stack.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef Stack_h
#define Stack_h

#include <stdio.h>

void array_stack_init();
void array_stack_push(void *val);
void * array_stack_pop();
int array_stack_size();
int array_stack_empty();
void *array_stack_top();
void array_stack_print();

#endif /* Stack_h */
