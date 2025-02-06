//
//  array_test.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "array_test.h"
#include "Array.h"
#include <stdlib.h>
#include <time.h>

void array_test()
{
    printf("\n");
    printf(" +-------------------------------------+\n");
    printf(" |                                     |\n");
    printf(" |               C Array               |\n");
    printf(" |                                     |\n");
    printf(" +-------------------------------------+\n");
    printf("\n");
    
    Array *array = get_array(10);
    
    int i;
    for (i = 0; i < array->size; i++) {
        array_insert(array, i, i+1);
    }
    print(array);
    
    array_push(array, 11);
    
    for (i = 0; i < array->size; i++) {
        array_remove(array, i);
    }
    print(array);
    
    array_remove(array, -1);
    array_insert(array, -1, 1);
    
    printf("\n-------------- Erase ---------------\n");
    // Erase
    for (i = 0; i < array->size; i++) {
        array_insert(array, i, i + 1);
    }
    print(array);
    array_erase(array);
    print(array);
    
    printf("\n-------------- Switching ---------------\n");
    Array *arr = get_array(13);
    for (i = 0; i < arr->size; i++) {
        array_insert(arr, i , i + 1);
    }
    print(arr);
    for (i = 0; i < arr->size / 2; i++) {
        array_switch_values(arr, i, arr->size - i - 1);
    }
    print(arr);
    
    array_reverse(arr);
    print(arr);
    
    printf("\n-------------- Sort ---------------\n");
    srand(time(NULL));
    Array *arr1 = get_array(20);
    for (i = 0; i < arr1->size; i++) {
        array_insert(arr1, i, rand() % arr1->size);
    }
    Array *arr2 = get_copy_array(arr1);
    Array *arr3 = get_copy_array(arr1);
    Array *arr4 = get_copy_array(arr1);
    Array *arr5 = get_copy_array(arr1);
    Array *arr6 = get_copy_array(arr1);
    Array *arr7 = get_copy_array(arr1);
    Array *arr8 = get_copy_array(arr1);
    Array *arr9 = get_copy_array(arr1);
    Array *arr10 = get_copy_array(arr1);
    printf("not sorted array:");
    print(arr1);
    
    printf("Bubble sort:");
    clock_t bubble_begin = clock();
    array_bubble_sort(arr1);
    clock_t bubble_end = clock();
    double bubble_time = (double)(bubble_end - bubble_begin) / CLOCKS_PER_SEC;
    print(arr1);
    
    printf("Select sort:");
    clock_t select_begin = clock();
    array_select_sort(arr2);
    clock_t select_end = clock();
    double select_time = (double)(select_end - select_begin) / CLOCKS_PER_SEC;
    print(arr2);
    
    printf("Heap sort:");
    clock_t heap_begin = clock();
    array_heap_sort(arr3);
    clock_t heap_end = clock();
    double heap_time = (double)(heap_end - heap_begin) / CLOCKS_PER_SEC;
    print(arr3);
    
    printf("Insert sort:");
    clock_t insert_begin = clock();
    array_insert_sort(arr4);
    clock_t insert_end = clock();
    double insert_time = (double)(insert_end - insert_begin) / CLOCKS_PER_SEC;
    print(arr4);
    
    printf("Merge sort:");
    clock_t merge_begin = clock();
    array_merge_sort(arr5);
    clock_t merge_end = clock();
    double merge_time = (double)(merge_end - merge_begin) / CLOCKS_PER_SEC;
    print(arr5);
    
    printf("Quick sort:");
    clock_t quick_begin = clock();
    array_quick_sort(arr6);
    clock_t quick_end = clock();
    double quick_time = (double)(quick_end - quick_begin) / CLOCKS_PER_SEC;
    print(arr6);
    
    printf("\nTotal time spent for bubble sort: %lf seconds", bubble_time);
    printf("\nTotal time spent for select sort: %lf seconds", select_time);
    printf("\nTotal time spent for heap sort: %lf seconds", heap_time);
    printf("\nTotal time spent for insert sort: %lf seconds", insert_time);
    printf("\nTotal time spent for merge sort: %lf seconds", merge_time);
    printf("\nTotal time spent for quick sort: %lf seconds", quick_time);
    
    printf("\n-------------- Searching ---------------\n");
    Array *search_arr = get_array(1000);
    for (i = 0; i < search_arr->size; i++) {
        array_insert(search_arr, i, rand() % 100);
    }

    int j = 24;
    printf("\nOccurrences of the number %d in the array: %d", j,
        array_value_ocurrance(search_arr, j));
    printf("\nAnd its positions:\n");
    Array *positions = array_value_positions(search_arr, j);
    print(positions);
    
    // This should all give value of j
    printf("\nAll %d : ", j);
    for (i = 0; i < positions->size; i++) {
        printf("\nPosition %d has a value of %d",
            positions->data[i], search_arr->data[positions->data[i]]);
    }
    printf("\nThe list has a minimum value of %d and a maximum value of %d",
        array_min(search_arr), array_max(search_arr));
    
    free(arr);
    free(arr1);
    free(arr2);
    free(arr3);
    free(arr4);
    free(arr5);
    free(arr6);
    free(arr7);
    free(arr8);
    free(arr9);
    free(arr10);
    free(search_arr);
    printf("\n");

}
