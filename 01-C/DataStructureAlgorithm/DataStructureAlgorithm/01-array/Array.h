//
//  Array.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef Array_h
#define Array_h

#include <stdbool.h>

typedef struct Array {
    int *data;
    int size;
} Array;

// +-------------------------------------+
// |           Returns array             |
// +-------------------------------------+
Array * get_array(int size);
Array * get_copy_array(Array *arr);

// +-------------------------------------+
// |           Input / Output            |
// +-------------------------------------+
bool array_insert(Array *array, int position, int value);
bool array_remove(Array *array, int position);
bool array_push(Array *array, int value);
bool array_update(Array *array, int position, int value);

// +-------------------------------------+
// |               Erase                 |
// +-------------------------------------+
void array_erase(Array *array);

// +-------------------------------------+
// |             Switching               |
// +-------------------------------------+
bool array_switch_values(Array *array, int position1, int position2);
void array_reverse(Array *array);

// +-------------------------------------+
// |              Sorting                |
// +-------------------------------------+
void array_bubble_sort(Array *array);
void array_select_sort(Array *array);
void array_heap_sort(Array *array);
void array_insert_sort(Array *array);
void array_merge_sort(Array *array);
void array_quick_sort(Array *array);
void array_shell_sort(Array *array);
void array_counting_sort(Array *array);
void array_radix_sort(Array *array);
void array_bucket_sort(Array *array);
void array_blender(Array *array);

// +-------------------------------------+
// |             Searching               |
// +-------------------------------------+
int array_value_ocurrance(Array *array, int value);
Array * array_value_positions(Array *array, int value);
int array_max(Array *array);
int array_min(Array *array);

// +-------------------------------------+
// |               Print                 |
// +-------------------------------------+
void print(Array *array);

#endif /* Array_h */
