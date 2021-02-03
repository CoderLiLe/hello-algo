//
//  LinkedListStack.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdlib.h>
#include <assert.h>
#include "LinkedListStack.h"

typedef struct elem {
    void *val;
    struct elem *next;
} elem_t;

struct Stack {
    int count;
    elem_t *head;
};

Stack stack_init(void)
{
    Stack stack = (Stack)malloc(sizeof(Stack));
    stack->count = 0;
    stack->head = NULL;
    return stack;
}

void stack_push(Stack stack, void *val)
{
    assert(stack);
    
    elem_t *elem;
    elem = (elem_t *)malloc(sizeof(elem_t));
    elem->val = val;
    elem->next = stack->head;
    stack->head = elem;
    stack->count++;
}

void * stack_pop(Stack stack)
{
    assert(stack);
    assert(stack->count > 0);
    
    elem_t *elem;
    void *val;
    elem = stack->head;
    stack->head = elem->next;
    stack->count--;
    val = elem->val;
    free(elem);
    return val;
}

int stack_size(Stack stack)
{
    assert(stack);
    return stack->count;
}

int stack_empty(Stack stack)
{
    return stack_size(stack) == 0;
}

void *stack_top(Stack stack)
{
    assert(stack);
    assert(stack);
    assert(stack->count > 0);
    
    elem_t *elem;
    elem = stack->head;
    return elem->val;
}

void stack_print(Stack stack)
{
    assert(stack);
    
    elem_t *elem = stack->head;
    while (elem) {
        printf("%p ", (int *)elem->val);
        elem = elem->next;
    }
    printf("\n");
}


