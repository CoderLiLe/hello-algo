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

using namespace std;

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
    cout << findKthLargest(nums, 2) << endl;
//    assert(5 == findKthLargest(nums, 2));
}

void sortArraytest() {
//    test0075();
    test0215();
}
