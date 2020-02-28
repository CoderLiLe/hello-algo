//
//  main.cpp
//  53_02_MissingNumber
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 题目2: 0 ～ n-1 中缺少的数字。
 一个长度为 n-1 的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围 0 ~ n-1 之内。在范围 0 ~ n-1 内的 n 个
 数字中有且只有一个数字不在该数组中，请找出这个数字。
 */

/**
 分析：
 方法一：s1 = n*(n-1)/2, s2 为数组中 n 个数字之和。则不在 0 ~ n-1 内的数字 = s2 - s1。T = O(n)。
 方法二：由于数组递增排序，故 m 之前的数字和下标一致，m 之后的数字下标减1。此问题可以转化为【在排序数组中找出第一个值和
 下标不一致的元素】。
    二分查找法：
    （1）如果中间元素的值和下标相等，下一轮只需要查找右半边；
    （2）如果中间元素的值和下标不相等，且前一个元素的值和下标相等，则中间数字正好是第一个值和下标不相等的数字；
    （3）如果中间元素的值和下标不相等，且前一个元素的值和下标不相等，则下一轮只需要查找左边。
 */

#include <iostream>

int GetMissingNumber(const int *number, int length) {
    if (number == nullptr || length == 0) {
        return -1;
    }
    
    int left = 0;
    int right = length - 1;
    while (left <= right) {
        int middle = ((right - left) >> 1) + left;
        if (number[middle] != middle) {
            if (middle == 0 || number[middle-1] == middle - 1) {
                //（2）如果中间元素的值和下标不相等，且前一个元素的值和下标相等，则中间数字正好是第一个值和下标不相等的数字
                return middle;
            } else {
                //（3）如果中间元素的值和下标不相等，且前一个元素的值和下标不相等，则下一轮只需要查找左边
                right = middle - 1;
            }
        } else { //（1）如果中间元素的值和下标相等，下一轮只需要查找右半边；
            left = middle + 1;
        }
    }
    
    if (left == length) {
        return length;
    }
    
    return -1;
}

// ====================测试代码====================
void Test(const char* testName, int numbers[], int length, int expected)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    int result = GetMissingNumber(numbers, length);
    if (result == expected)
        printf("Passed.\n");
    else
        printf("Failed.\n");
}

// 缺失的是第一个数字0
void Test1()
{
    int numbers[] = {1, 2, 3, 4, 5 };
    int expected = 0;
    Test("Test1", numbers, sizeof(numbers) / sizeof(int), expected);
}

// 缺失的是最后一个数字
void Test2()
{
    int numbers[] = {0, 1, 2, 3, 4 };
    int expected = 5;
    Test("Test2", numbers, sizeof(numbers) / sizeof(int), expected);
}

// 缺失的是中间某个数字0
void Test3()
{
    int numbers[] = {0, 1, 2, 4, 5 };
    int expected = 3;
    Test("Test3", numbers, sizeof(numbers) / sizeof(int), expected);
}

// 数组中只有一个数字，缺失的是第一个数字0
void Test4()
{
    int numbers[] = {1};
    int expected = 0;
    Test("Test4", numbers, sizeof(numbers) / sizeof(int), expected);
}

// 数组中只有一个数字，缺失的是最后一个数字1
void Test5()
{
    int numbers[] = {0};
    int expected = 1;
    Test("Test5", numbers, sizeof(numbers) / sizeof(int), expected);
}

// 空数组
void Test6()
{
    int expected = -1;
    Test("Test6", nullptr, 0, expected);
}


int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    Test6();
    
    return 0;
}
