//
//  stack_test.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdlib.h>
#include "stack_test.h"
#include "LinkedListStack.h"
#include "array_stack.h"

void linked_list_stack_test()
{
    Stack stack;
    stack = stack_init();
    stack_push(stack, (int *)1);
    stack_push(stack, (int *)2);
    stack_push(stack, (int *)3);
    stack_push(stack, (int *)4);
    printf("Size: %d\n", stack_size(stack));
    stack_print(stack);
    stack_pop(stack);
    printf("Stack after popping: \n");
    stack_print(stack);
    stack_pop(stack);
    printf("Stack after popping: \n");
    stack_print(stack);
}

void array_stack_test()
{
    array_stack_init();
    array_stack_push((char *)'a');
    array_stack_push((char *)'b');
    array_stack_push((char *)'c');
    array_stack_push((char *)'d');
    printf("Size: %d\n", array_stack_size());
    array_stack_print();
    array_stack_pop();
    printf("Stack after popping: \n");
    array_stack_print();
    array_stack_pop();
    printf("Stack after popping: \n");
    array_stack_print();
}
