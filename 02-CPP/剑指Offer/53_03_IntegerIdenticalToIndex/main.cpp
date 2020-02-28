//
//  main.cpp
//  53_03_IntegerIdenticalToIndex
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 题目三：数组中数值和下标相等的元素。
 假设一个单调递增的数组里每个元素都是整数并且是唯一的。请编程实现一个函数，找出数组中任意一个数值等于其下标的元素。
 例如，在数组 {-3, -1, 1, 3, 5} 中，数字3和它的下标相等。
 */

/**
 分析：
 方法一：从前往后扫描，检查数字和下标是否相等。T = O(n)
 方法二：【二分查找法】，下标记为 i，值记为 m。
    （1）i == m，找到了
    （2）i < m，对于任意大于0的 k，位于下标 i+k 的数字的值 < m+k（i < m ==> i+k < m+k）。所以第 i 个数字的值
        大于 i，则它右边的数字都大于其下标，下一轮只需要在左边查找。
    （3）i > m，和（2）类似，它左边的数字都小于其下标，下一轮只需要在右边查找。
 */

#include <iostream>

int GetNumberSameAsIndex(const int *number, int length) {
    if (number == nullptr || length == 0) {
        return -1;
    }
    
    int left = 0;
    int right = length - 1;
    while (left <= right) {
        int mid = ((right - left) >> 1) + left;
        int midValue = number[mid];
        if (mid == midValue) {
            return mid;
        } else if (mid < midValue) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    
    return -1;
}

// ====================测试代码====================
void Test(const char* testName, int numbers[], int length, int expected)
{
    if (testName != nullptr)
        printf("%s : ", testName);
    
    if (GetNumberSameAsIndex(numbers, length) == expected)
        printf("passed.\n");
    else
        printf("failed.\n");
}

void Test1()
{
    int numbers[] = {-3, -1, 1, 3, 5};
    int expected = 3;
    Test("Test1", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test2()
{
    int numbers[] = {0, 1, 3, 5, 6};
    int expected = 0;
    Test("Test2", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test3()
{
    int numbers[] = {-1, 0, 1, 2, 4};
    int expected = 4;
    Test("Test3", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test4()
{
    int numbers[] = {-1, 0, 1, 2, 5};
    int expected = -1;
    Test("Test4", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test5()
{
    int numbers[] = {0};
    int expected = 0;
    Test("Test5", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test6()
{
    int numbers[] = {10};
    int expected = -1;
    Test("Test6", numbers, sizeof(numbers) / sizeof(int), expected);
}

void Test7()
{
    Test("Test7", nullptr, 0, -1);
}


int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    Test6();
    Test7();
    
    return 0;
}
