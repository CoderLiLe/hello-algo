//
//  排序数组测试.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/21.
//

#include "排序数组测试.hpp"
#include <iostream>

#include "_0075_ 颜色分类.hpp"
#include "_0215_数组中的第K个最大元素.hpp"
#include "_0033_搜索旋转排序数组.hpp"

using namespace std;

void test0033() {
    vector<int> nums = {4,5,6,7,0,1,2};
    cout << searchTarget(nums, 0) << endl;
    cout << searchTarget(nums, 3) << endl;
    
    vector<int> nums2 = {1};
    cout << searchTarget(nums2, 0) << endl;
}

void test0033_02() {
    vector<int> nums = {4,5,6,7,0,1,2};
    cout << searchTarget2(nums, 0) << endl;
    cout << searchTarget2(nums, 3) << endl;
    
    vector<int> nums2 = {1};
    cout << searchTarget2(nums2, 0) << endl;
}

void test0075() {
    vector<int> nums = {2,0,2,1,1,0};
    sortColors(nums);
    for (auto val : nums) {
        cout << val << " ";
    }
    cout << endl;
}

void test0215() {
    vector<int> nums = {3, 2, 1, 5, 6, 4};
    assert(5 == findKthLargest(nums, 2));
    assert(5 == findKthLargest2(nums, 2));
}

void sortArraytest() {
//    test0033();
    test0033_02();
//    test0075();
//    test0215();
}
