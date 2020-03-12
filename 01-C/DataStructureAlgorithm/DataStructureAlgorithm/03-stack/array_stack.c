//
//  array_stack.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "array_stack.h"


/*
  actual stack data structure
  This pointer will pointing at the actual field (of void * pointers)
  that represents the stack.
*/
void **array;

/* the current capacity of the stack */
int capacity = 10;

/* counter variable for counting the elements of the stack. */
int counter = 0;

/*
    offset address
    points at the top element of the stack.
*/
int offset = -1;

void array_stack_init()
{
    array = malloc(sizeof(void *) * capacity);
    assert(array); /* tests whether pointer is assigned to memory. */
}

void capacity_enlarge();
void array_stack_push(void *val)
{
    assert(val); /* tests whether pointer isn't null */
    if (counter >= capacity) { /* stack is full */
        capacity_enlarge();
    }

    offset++; /* increases the element-pointer */

    /*
        moves pointer by the offset address
        pushs the object onto stack
     */
    *(array + offset) = val;

    /* increases the inner counter */
    counter++;
}

void *array_stack_pop()
{
    void *top = *(array + offset);

    /* check pointers */
    assert(top);

    /* if use the pop-function, stack must not empty. */
    assert(!array_stack_empty());

    /* decreases the offset address for pointing of
        the new top element */
    offset--;

    /* decreases the inner counter */
    counter--;

    return top;
}

int array_stack_size()
{
    return counter;
}

int array_stack_empty()
{
    return counter == 0;
}

void *array_stack_top()
{
    /* offset address points to the top element */
    return array[offset];
}

void array_stack_print()
{
    int i;
    for (i = offset; i >= 0; i--) {
        char *top = *(array + i);
        printf("%c ", (char *)top);
    }
    printf("\n");
}

#pragma mark - private method
/*
    grow: increases the stack by 10 elements.
          This utility function isn't part of the public interface
*/
void capacity_enlarge()
{
    capacity += 10; /* increases the capacity */

    int i; // for the loop
    void **tmp = malloc(sizeof(void *) * capacity);

    /* copies the elements from the origin array in the new one. */
    for (i = 0; i < capacity - 10; i++)
    {
        *(tmp + i) = *(array + i);
    }
    
    /*free the memory */
    free(array);
    array = tmp;
}
