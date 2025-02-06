//
//  LinkedListStack.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef LinkedListStack_h
#define LinkedListStack_h

#include <stdio.h>

typedef struct Stack *Stack;

Stack stack_init(void);
void stack_push(Stack stack, void *val);
void * stack_pop(Stack stack);
int stack_size(Stack stack);
int stack_empty(Stack stack);
void *stack_top(Stack stack);
void stack_print(Stack stack);

#endif /* LinkedListStack_h */
