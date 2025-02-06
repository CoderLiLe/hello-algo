//
//  main.cpp
//  03_01_DuplicationInArray
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题3（一）：找出数组中重复的数字
 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
 那么对应的输出是重复的数字2或者3。
 */

/**
 分析：
 1. 数组排序后，从头到尾扫描。T = O(nlogn)
 2. 哈希表法，T = O(n), S = O(1)
 3. 如果数组中没有重复数字，那么当数组排序后，数字 i 将出现在下标为 i 的位置。由于数组中存在重复数字，
 所以一些位置存在多个数字，一些位置为空。从头扫描数组，当扫描到下标为 i 的数字时：
    1> arr[i] == i，扫描下一个
    2> arr[i] != i 时
        arr[i] == arr[arr[i]]，找到了第一个重复的数字
        arr[i] != arr[[arr[i]]]，交换第 i 个数字和第 arr[i] 个数字，将 arr[i] 放到对应的位置
 
 
 {[2], 3, 1, 0, 2, 5, 3}  arr[0] == 2 != 0，交换
 {[1], 3, 2, 0, 2, 5, 3}  arr[0] == 1 != 0，交换
 {[3], 1, 2, 0, 2, 5, 3}  arr[0] == 3 != 0，交换
 {[0], 1, 2, 3, 2, 5, 3}  arr[0] == 0，扫描下一个
 {0, [1], 2, 3, 2, 5, 3}  arr[1] == 1，扫描下一个
 {0, 1, [2], 3, 2, 5, 3}  arr[2] == 2，扫描下一个
 {0, 1, 2, [3], 2, 5, 3}  arr[3] == 3，扫描下一个
 {0, 1, 2, 3, [2], 5, 3}  arr[4] == 2 == arr[2]，找到了第一个重复的数字
 */

#include <iostream>

using namespace std;

// T = O(n), S = O(1)
bool duplicate(int numbers[], int length, int *duplication) {
    if (numbers == nullptr || length == 0) {
        return false;
    }
    
    for (int i = 0; i < length; i++) {
        if (numbers[i] < 0 || numbers[i] > length - 1) {
            return false;
        }
    }
    
    for (int i = 0; i < length; i++) {
        while (numbers[i] != i) {
            if (numbers[i] == numbers[numbers[i]]) {
                *duplication = numbers[i];
                return true;
            }
            
            int tmp = numbers[numbers[i]];
            numbers[numbers[i]] = numbers[i];
            numbers[i] = tmp;
        }
    }
    
    return false;
}

// ====================测试代码====================
bool contains(int array[], int length, int number)
{
    for(int i = 0; i < length; ++i)
    {
        if(array[i] == number)
            return true;
    }

    return false;
}

void test(const char * testName, int numbers[], int numbersLen, int expected[], int expectedLen, bool validArgument)
{
    if (testName != nullptr) {
        cout << testName << " : ";
    }

    int duplication;
    bool validInput = duplicate(numbers, numbersLen, &duplication);

    if(validArgument == validInput)
    {
        if(validArgument)
        {
            if(contains(expected, expectedLen, duplication))
                cout << "passed." << endl;
            else
                cout << "failed." << endl;
        }
        else
            cout << "passed." << endl;
    }
    else
        cout << "failed." << endl;
}

// 重复的数字是数组中最小的数字
void test1()
{
    int numbers[] = { 2, 1, 3, 1, 4 };
    int duplications[] = { 1 };
    test("Test1", numbers, sizeof(numbers) / sizeof(int), duplications, sizeof(duplications) / sizeof(int), true);
}

// 重复的数字是数组中最大的数字
void test2()
{
    int numbers[] = { 2, 4, 3, 1, 4 };
    int duplications[] = { 4 };
    test("Test2", numbers, sizeof(numbers) / sizeof(int), duplications, sizeof(duplications) / sizeof(int), true);
}

// 数组中存在多个重复的数字
void test3()
{
    int numbers[] = { 2, 4, 2, 1, 4 };
    int duplications[] = { 2, 4 };
    test("Test3", numbers, sizeof(numbers) / sizeof(int), duplications, sizeof(duplications) / sizeof(int), true);
}

// 没有重复的数字
void test4()
{
    int numbers[] = { 2, 1, 3, 0, 4 };
    int duplications[] = { -1 }; // not in use in the test function
    test("Test4", numbers, sizeof(numbers) / sizeof(int), duplications, sizeof(duplications) / sizeof(int), false);
}

// 没有重复的数字
void test5()
{
    int numbers[] = { 2, 1, 3, 5, 4 };
    int duplications[] = { -1 }; // not in use in the test function
    test("Test5", numbers, sizeof(numbers) / sizeof(int), duplications, sizeof(duplications) / sizeof(int), false);
}

// 无效的输入
void test6()
{
    int* numbers = nullptr;
    int duplications[] = { -1 }; // not in use in the test function
    test("Test6", numbers, 0, duplications, sizeof(duplications) / sizeof(int), false);
}


int main(int argc, const char * argv[]) {
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    
    return 0;
}
