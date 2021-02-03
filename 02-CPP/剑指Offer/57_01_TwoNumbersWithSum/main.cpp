//
//  main.cpp
//  57_01_TwoNumbersWithSum
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题57:和为 s 的数字
 题目一：和为 s 的两个数字。
    输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 */

/**
 蛮力法：两层嵌套循环。T = O(n^2)
 
 方法二：两个指针分 front、behid 别指向有序数组第一个和最后一个元素，二者和记作 curSum，目标和为 sum
    1> curSum == sum，则找到
    2> curSum > sum, behind前移
    3> curSum < sum, front后移
 */

#include <iostream>

// T = O(n)
bool FindNumbersWithSum(int data[], int length, int sum, int *num1, int *num2) {
    if (data == nullptr || length < 2 || num1 == nullptr || num2 == nullptr) {
        return false;
    }
    
    int front = 0;
    int behind = length - 1;
    while (behind > front) {
        long long curSum = data[front] + data[behind];
        
        if (sum == curSum) {
            *num1 = data[front];
            *num2 = data[behind];
            return true;
        }
        else if (curSum > sum) {
            behind--;
        } else {
            front++;
        }
    }
    
    return false;
}

// ====================测试代码====================
void Test(const char* testName, int data[], int length, int sum, bool expectedReturn)
{
    if(testName != nullptr)
        printf("%s : ", testName);
    
    int num1, num2;
    int result = FindNumbersWithSum(data, length, sum, &num1, &num2);
    if(result == expectedReturn)
    {
        if(result)
        {
            if(num1 + num2 == sum)
                printf("passed. \n");
            else
                printf("failed. \n");
        }
        else
            printf("passed. \n");
    }
    else
        printf("failed. \n");
}

// 存在和为s的两个数字，这两个数字位于数组的中间
void Test1()
{
    int data[] = {1, 2, 4, 7, 11, 15};
    Test("Test1", data, sizeof(data) / sizeof(int), 15, true);
}

// 存在和为s的两个数字，这两个数字位于数组的两段
void Test2()
{
    int data[] = {1, 2, 4, 7, 11, 16};
    Test("Test2", data, sizeof(data) / sizeof(int), 17, true);
}

// 不存在和为s的两个数字
void Test3()
{
    int data[] = {1, 2, 4, 7, 11, 16};
    Test("Test3", data, sizeof(data) / sizeof(int), 10, false);
}

// 鲁棒性测试
void Test4()
{
    Test("Test4", nullptr, 0, 0, false);
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    
    return 0;
}
