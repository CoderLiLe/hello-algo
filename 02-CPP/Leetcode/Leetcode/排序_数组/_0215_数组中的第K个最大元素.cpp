//
//  _0215_数组中的第K个最大元素.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/21.
//

#include "_0215_数组中的第K个最大元素.hpp"
#include <iostream>
using namespace std;

#pragma mark - 方法一：基于快速排序的选择方法
inline int partition(vector<int>& nums, int l, int r, int pivot_index) {
    int pivot = nums[pivot_index];
    
    swap(nums[pivot_index], nums[r]);
    int store_index = l;
    
    for (int i = l; i <= r; i++) {
        if (nums[i] < pivot) swap(nums[store_index++], nums[i]);
    }

    swap(nums[store_index], nums[r]);

    return store_index;
}

int quickSelect(vector<int>& nums, int l, int r, int k_smallest) {
    if (l == r) return nums[l];
    
    // ① 随机选择一个枢纽
    int pivot_index = rand() % (r - l + 1) + l;
    cout << "privot_index = " << pivot_index << endl;
    
    // ② 使用划分算法将枢纽放在合适的位置 pos ，将小于枢纽的元素移动到左边，大于枢纽的元素移动到右边
    pivot_index = partition(nums, l, r, pivot_index);
    
    // ③ pivot_index == n - k，找到了
    //    pivot_index < n - k，pivot_index 右边找
    //    pivot_index > n - k，pivot_index 左边找
    if (pivot_index == k_smallest) {
        return nums[pivot_index];
    } else if (pivot_index < k_smallest) {
        return quickSelect(nums, pivot_index + 1, r, k_smallest);
    } else {
        return quickSelect(nums, l, pivot_index - 1, k_smallest);
    }
}

int findKthLargest(vector<int>& nums, int k) {
    if (0 == nums.size() || nums.size() < k) return -1;
    if (1 == nums.size()) return nums[0];
    return quickSelect(nums, 0, nums.size() - 1, nums.size() - k);
}

#pragma mark - 方法二：基于堆排序的选择方法

void maxHeapify(vector<int>& arr, int i, int heapSize) {
    int l = i * 2 + 1, r = i * 2 + 2, largest = i;
    if (l < heapSize && arr[l] > arr[largest]) {
        largest = l;
    }
    if (r < heapSize && arr[r] > arr[largest]) {
        largest = r;
    }
    if (largest != i) {
        swap(arr[i], arr[largest]);
        maxHeapify(arr, largest, heapSize);
    }
}

void buildMaxHeap(vector<int>& arr, int heapSize) {
    for (int i = heapSize / 2; i >= 0; --i) {
        maxHeapify(arr, i, heapSize);
    }
}

int findKthLargest2(vector<int>& nums, int k) {
    int heapSize = nums.size();
    buildMaxHeap(nums, heapSize);
    for (int i = nums.size() - 1; i >= nums.size() - k + 1; --i) {
        swap(nums[0], nums[i]);
        --heapSize;
        maxHeapify(nums, 0, heapSize);
    }
    return nums[0];
}
