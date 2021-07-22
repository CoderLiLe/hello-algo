//
//  _0215_数组中的第K个最大元素.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/21.
//

#ifndef _0215______K______hpp
#define _0215______K______hpp

#include <stdio.h>
#include <vector>
using namespace std;

/**
 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode-/
 */

/**
 方法一：基于快速排序的选择方法
 
 快速选择
 步骤：
 ① 随机选择一个枢纽
 ② 使用划分算法将枢纽放在合适的位置 pivot_index ，将小于枢纽的元素移动到左边，大于枢纽的元素移动到右边
 ③ pivot_index == n - k，找到了
    pivot_index < n - k，pivot_index 右边找
    pivot_index > n - k，pivot_index 左边找
 
 T(n) = O(n)
 S(n) = O(1)
 */
int findKthLargest(vector<int>& nums, int k);

/**
 方法二：基于堆排序的选择方法
 
 T(n) = O(nlogn)
 S(n) = O(logn)
 建堆的时间代价是 O(n)，删除的总代价是 O(klogn)，因为 k<n，故渐进时间复杂为 O(n + klogn) = O(nlogn)
 */
int findKthLargest2(vector<int>& nums, int k);

#endif /* _0215______K______hpp */
