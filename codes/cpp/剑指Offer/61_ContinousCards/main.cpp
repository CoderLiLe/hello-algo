//
//  main.cpp
//  61_ContinousCards
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题61:扑克牌中的顺子
 题目：从扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这 5 张牌是不是连续的。2 ～ 10 为数字本身，
 A 为1，J为11，Q为12，K为13，而大小王可以看成任意数字。
 */

/**
 分析：
    需要将扑克牌的背景抽象成计算机语言：将5张牌看成5个数字组成的数组。大小王为特殊数字，定义为0。
    判断5个数字是不是连续的：如果非0数字重复出现数组肯定不连续
       1> 数组排序
       2> 统计 0 的个数
       3> 如果相邻数字之间的空缺数 <= 0 的个数，则数组连续；否则不连续
 */

#include <iostream>
#include <cstdlib>

int compare(const void *arg1, const void *arg2) {
    return *(int *)arg1 - *(int *)arg2;
}

bool IsContinuous(int* numbers, int length)
{
    if(numbers == nullptr || length < 1)
        return false;
        
    // 1. 数组排序
    // void qsort(void *__base, size_t __nel, size_t __width, int (* _Nonnull __compar)(const void *, const void *));
    qsort(numbers, length, sizeof(int), compare);
    
    // 2. 统计 0 的个数
    int numberOfZero = 0;
    for (int i = 0; i < length && numbers[i] == 0; i++) {
        numberOfZero++;
    }
    
    // 3. 统计数组中的间隔数目
    int small = numberOfZero;
    int big = small + 1;
    int numberOfGap = 0;
    while (big < length) {
        // 如果非0数字重复出现数组肯定不连续
        if (numbers[small] == numbers[big]) {
            return false;
        }
        
        numberOfGap += numbers[big] - numbers[small] - 1;
        small = big;
        ++big;
    }
    
    return (numberOfGap > numberOfZero) ? false : true;
}

// ====================测试代码====================
void Test(const char* testName, int* numbers, int length, bool expected)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    if(IsContinuous(numbers, length) == expected)
        printf("passed.\n");
    else
        printf("failed.\n");
}

void Test1()
{
    int numbers[] = { 1, 3, 2, 5, 4 };
    Test("Test1", numbers, sizeof(numbers) / sizeof(int), true);
}

void Test2()
{
    int numbers[] = { 1, 3, 2, 6, 4 };
    Test("Test2", numbers, sizeof(numbers) / sizeof(int), false);
}

void Test3()
{
    int numbers[] = { 0, 3, 2, 6, 4 };
    Test("Test3", numbers, sizeof(numbers) / sizeof(int), true);
}

void Test4()
{
    int numbers[] = { 0, 3, 1, 6, 4 };
    Test("Test4", numbers, sizeof(numbers) / sizeof(int), false);
}

void Test5()
{
    int numbers[] = { 1, 3, 0, 5, 0 };
    Test("Test5", numbers, sizeof(numbers) / sizeof(int), true);
}

void Test6()
{
    int numbers[] = { 1, 3, 0, 7, 0 };
    Test("Test6", numbers, sizeof(numbers) / sizeof(int), false);
}

void Test7()
{
    int numbers[] = { 1, 0, 0, 5, 0 };
    Test("Test7", numbers, sizeof(numbers) / sizeof(int), true);
}

void Test8()
{
    int numbers[] = { 1, 0, 0, 7, 0 };
    Test("Test8", numbers, sizeof(numbers) / sizeof(int), false);
}

void Test9()
{
    int numbers[] = { 3, 0, 0, 0, 0 };
    Test("Test9", numbers, sizeof(numbers) / sizeof(int), true);
}

void Test10()
{
    int numbers[] = { 0, 0, 0, 0, 0 };
    Test("Test10", numbers, sizeof(numbers) / sizeof(int), true);
}

// 有对子
void Test11()
{
    int numbers[] = { 1, 0, 0, 1, 0 };
    Test("Test11", numbers, sizeof(numbers) / sizeof(int), false);
}

// 鲁棒性测试
void Test12()
{
    Test("Test12", nullptr, 0, false);
}


int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    Test6();
    Test7();
    Test8();
    Test9();
    Test10();
    Test11();
    Test12();
    
    return 0;
}
