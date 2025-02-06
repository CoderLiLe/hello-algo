//
//  Array.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "Array.h"

void swap(int index1, int index2, Array *array);
int cmp(int index1, int index2, Array *array);

Array * get_array(int size)
{
    Array *array = (Array *)malloc(sizeof(Array));
    array->data = (int *)malloc(sizeof(int) * size);
    array->size = size;
    int i;
    for (i = 0; i < size; i++) {
        array->data[i] = 0;
    }
    return array;
}

Array * get_copy_array(Array *arr)
{
    Array *array = (Array *)malloc(sizeof(Array));
    array->data = (int *)malloc(sizeof(int) * arr->size);
    array->size = arr->size;
    int i;
    for (i = 0; i < arr->size; i++) {
        array->data[i] = arr->data[i];
    }
    return array;
}

bool array_insert(Array *array, int position, int value)
{
    if (position >= 0 && position < array->size) {
        if (array->data[position] == 0) {
            array->data[position] = value;
            return true;
        } else {
            printf("array_insert 插入失败\n");
        }
    } else {
        printf("array_insert 下标越界\n");
    }
    return false;
}

bool array_remove(Array *array, int position)
{
    if (position >= 0 && position < array->size) {
        if (array->data[position] != 0) {
            array->data[position] = 0;
            return true;
        } else {
            printf("array_remove 此处为空\n");
        }
    } else {
        printf("array_remove 下标越界\n");
    }
    return false;
}

bool array_push(Array *array, int value)
{
    int i = 0;
    for (i = 0; i < array->size; i++) {
        if (array->data[i] == 0) {
            array->data[i] = value;
            return true;
        }
    }
    printf("array_push 数组已满，压入失败！\n");
    return false;
}

bool array_update(Array *array, int position, int value)
{
    if (position >= 0 && position < array->size) {
        if (array->data[position] != 0) {
            array->data[position] = value;
        } else {
            return false;
        }
    }
    return false;
}

void array_erase(Array *array)
{
    int i;
    for (i = 0; i < array->size; i++) {
        array->data[i] = 0;
    }
}

bool array_switch_values(Array *array, int position1, int position2)
{
    if (position1 >= 0 && position1 < array->size && position2 >= 0 && position2 < array->size) {
        swap(position1, position2, array);
        return true;
    }
    return false;
}

void array_reverse(Array *array)
{
    int i;
    for (i = 0; i < array->size/2; i++) {
        int lastIndex = array->size - 1 - i;
        swap(i, lastIndex, array);
    }
}

#pragma mark - sort

void array_bubble_sort(Array *array)
{
    int end, begin;
    for (end = array->size - 1; end > 0; end--) {
        int sortedIndex = 1;
        for (begin = 1; begin <= end; begin++) {
            if (cmp(begin - 1, begin, array) > 0) {
                swap(begin-1, begin, array);
                sortedIndex = begin;
            }
        }
        end = sortedIndex;
    }
}

/**
 1、从序列中找出最大的那个元素，然后与最末尾的元素交换
 2、忽略1中最大元素（最后那个元素就是最大元素），重复执行步骤1
 */
void array_select_sort(Array *array)
{
    int end, begin;
    for (end = array->size - 1; end > 0; end--) {
        int max = 0;
        for (begin = 1; begin <= end; begin++) {
            if (cmp(max, begin, array) <= 0) {
                max = begin;
            }
        }
        if (max != end) {
            swap(max, end, array);
        }
    }
}

void sift_down(int index, Array *array, int heapSize);
/**
 堆排序
    可以认为是对选择排序的一种优化
 执行流程：
  1. 对序列进行原地建堆
  2. 重复以下操作，直到堆中元素为1
    2.1 交换堆顶元素与尾元素
    2.2 堆的数量减1
    2.3 对 0 位置进行一次 siftDown 操作
 */
void array_heap_sort(Array *array)
{
    // 原地建堆
    int heapSize = array->size;
    int i;
    for (i = (heapSize >> 1) - 1; i >= 0; i--) {
        sift_down(i, array, heapSize);
    }

    while (heapSize > 1) {
        // 交换堆顶元素和尾部元素
        swap(0, --heapSize, array);
        // 对 0 位置进行 siftDown（恢复堆的性质）
        sift_down(0, array, heapSize);
    }
}

/**
 1、在执行过程中，插入排序将序列分为两部分：头部是已经排好序的，尾部是待排序的
 2、从头开始扫描每一个元素：每扫描到一个元素，就将它插入到头部合适的位置
 */
void array_insert_sort(Array *array)
{
    int begin, cur;
    for (begin = 1; begin < array->size; begin++) {
        cur = begin;
        while (cur > 0 && cmp(cur - 1, cur, array) > 0) {
            swap(cur - 1, cur, array);
            cur--;
        }
    }
}


/**
 归并排序
 1. 不断的将当前序列平分为两个子序列，直到不能再分割（序列中只有一个元素）
 2. 不断的将两个子序列合并成一个有序序列（直到最终只剩下一个有序序列）
 
 T = O(N * longN), S = O(N)，稳定排序
 */

int *leftData;
void merge_sort_sort(int begin, int end, Array *array);
void merge_sort_merge(int begin, int mid, int end, Array *array);

void array_merge_sort(Array *array)
{
    if (leftData == NULL) {
        leftData = (int *)malloc(sizeof(int) * (array->size >> 1));
    }
    
    merge_sort_sort(0, array->size, array);
}

/**
 [begin, end)
 */
void merge_sort_sort(int begin, int end, Array *array) {
    // 至少要有 2 个元素
    if (end - begin < 2) {
        return;
    }
    
    int mid = (begin + end) >> 1;
    merge_sort_sort(begin, mid, array);
    merge_sort_sort(mid, end, array);
    merge_sort_merge(begin, mid, end, array);
}

/**
 [begin, mid)
 [mid, end)
 */
void merge_sort_merge(int begin, int mid, int end, Array *array)
{
    // 左边数组（基于 leftData）
    int li = 0, le = mid - begin;
    // 右边数组（基于 array->data）
    int ri = mid, re = end;
    // array->data 的索引
    int ai = begin;
    // 拷贝左边数组到 leftData
    int i;
    for (i = li; i < le; i++) {
        leftData[i] = array->data[begin + i];
    }
    while (li < le) {
        if (ri < re && array->data[ri] < leftData[li]) {
            // 拷贝右边数组到 array->data
            array->data[ai++] = array->data[ri++];
        } else {
            // 拷贝左边数组到 array->data
            array->data[ai++] = leftData[li++];
        }
    }
}

/**
 快速排序
 1. 从序列中选择一个轴点元素（pivot）
    假设每次选择 0 位置的元素为轴点元素
 
 2. 利用 pivot 将序列分为 2 个子序列
    将小于 pivot 的元素放在 pivot 前面（左侧）
    将大于 pivot 的元素放在 pivot 后面（右侧）
    等于 pivot 的元素放那边都可以
 
 3. 对子序列进行 1、2 操作
    直到不能分割为止（子序列中只剩下 1 个元素）
 
 本质：逐渐将每个元素都转换为轴点元素
 */

void quick_sort(int begin, int end, Array *array);
int pivotIndex(int begin, int end, Array *array);

void array_quick_sort(Array *array)
{
    quick_sort(0, array->size, array);
}

void quick_sort(int begin, int end, Array *array)
{
    // 至少要有 2 个元素
    if (end - begin < 2) {
        return;
    }
    
    int middle = pivotIndex(begin, end, array);
    quick_sort(begin, middle, array);
    quick_sort(middle + 1, end, array);
}

int pivotIndex(int begin, int end, Array *array)
{
    swap(begin, begin + (rand() % (end - begin)), array);
    int pivot = array->data[begin];
    end--;
    while (begin < end) {
        while (begin < end) {
            if (pivot < array->data[end]) {
                end--;
            } else {
                array->data[begin++] = array->data[end];
                break;
            }
        }
        while (begin < end) {
            if (pivot > array->data[begin]) {
                begin++;
            } else {
                array->data[end--] = array->data[begin];
                break;
            }
        }
    }
    array->data[begin] = pivot;
    return begin;
}

void array_shell_sort(Array *array)
{
    
}

void array_counting_sort(Array *array)
{
    
}

void array_radix_sort(Array *array)
{
    
}

void array_bucket_sort(Array *array)
{
    
}

void array_blender(Array *array)
{
    srand(time(NULL) * array->size);
    int i;
    int total = array->size * 100;
    for (i = 0; i < total; i++) {
        swap(rand() % array->size, rand() % array->size, array);
    }
}

#pragma mark - search
int array_value_ocurrance(Array *array, int value)
{
    int i, total = 0;
    for (i = 0; i < array->size; i++) {
        if (array->data[i] == value) {
            total++;
        }
    }
    return total;
}

Array * array_value_positions(Array *array, int value)
{
    int i, j = 0;
    int total = array_value_ocurrance(array, value);
    Array *positions = get_array(total);
    for (i = 0; i < array->size; i++) {
        if (array->data[i] == value) {
            positions->data[j++] = i;
        }
    }
    return positions;
}

int array_max(Array *array)
{
    int i;
    int max = array->data[0];
    for (i = 1; i < array->size; i++) {
        if (max < array->data[i]) {
            max = array->data[i];
        }
    }
    return max;
}

int array_min(Array *array)
{
    int i;
    int min = array->data[0];
    for (i = 1; i < array->size; i++) {
        if (min > array->data[i]) {
            min = array->data[i];
        }
    }
    return min;
}

#pragma mark - print

void print(Array *array)
{
    int i;
    for (i = 0; i < array->size; i++) {
        printf("%d ", array->data[i]);
    }
    printf("\n");
}

#pragma mark - private method
void swap(int index1, int index2, Array *array)
{
    int tmp = array->data[index1];
    array->data[index1] = array->data[index2];
    array->data[index2] = tmp;
}

int cmp(int index1, int index2, Array *array)
{
    int result = array->data[index1] - array->data[index2];
    return result;
}

void sift_down(int index, Array *array, int heapSize)
{
    int element = array->data[index];
    int half = heapSize >> 1;
    while (index < half) { // index 必须是非叶子节点
        // 默认是左边跟父节点比
        int childIndex = (index << 1) + 1;
        int rightIndex = childIndex + 1;
        // 右子节点比左子节点大
        if (rightIndex < heapSize && cmp(rightIndex, childIndex, array) > 0) {
            childIndex = rightIndex;
        }
        // 大于等于子节点
        if (element >= array->data[childIndex]) {
            break;
        }
        array->data[index] = array->data[childIndex];
        index = childIndex;
    }
    array->data[index] = element;
}
