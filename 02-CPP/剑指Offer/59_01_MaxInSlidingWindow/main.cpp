//
//  main.cpp
//  59_01_MaxInSlidingWindow
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题59:队列的最大值
 题目一：滑动窗口的最大值
    给定一个数组和滑动窗口的大小，请找出所有滑动窗口里的最大值。例如，如果输入数组{2, 3, 4, 2, 6, 2, 5, 1}及
 滑动窗口的大小3，那么一共存在6个滑动窗口，它们的最大值分别为{4, 4, 6, 6, 6, 5}。
 
        滑动窗口                   滑动窗口中的最大值
 {[2, 3, 4], 2, 6, 2, 5, 1}             4
 {2, [3, 4, 2], 6, 2, 5, 1}             4
 {2, 3, [4, 2, 6], 2, 5, 1}             6
 {2, 3, 4, [2, 6, 2], 5, 1}             6
 {2, 3, 4, 2, [6, 2, 5], 1}             6
 {2, 3, 4, 2, 6, [2, 5, 1]}             5
 */

/**
 分析
 蛮力法：扫描每个滑动窗口中的所有数字并找出其中的最大值，记滑动窗口大小为k，数组长度为n，T = O(n*k)
 
 方法2:一个滑动窗口可以看作一个队列，当窗口滑动时，窗口的第一个数字删除，同时在窗口的尾部添加一个新的数字。
 符合“先进先出”的特性，如果能找出队列中的最大值此问题就解决了。面试题30实现了一个O(1)时间找出栈中的最小值，同样可以实现
 一个O(1)时间找出栈中的最大值。面试题9中用两个栈实现了一个队列。综合这两个问题的方案，时间复杂度就降到了 T = O(n)
 
 方法3:不把滑动窗口中的每个数值都放入队列，而是只把有可能成为滑动窗口最大值的数值存入一个两端开口的队列（如 C++ 标准模版库中的 deque）
 */

#include <iostream>
#include <vector>
#include <deque>

using namespace std;

vector<int> maxInWindows(const vector<int> & num, unsigned int size) {
    vector<int> maxInWindows;
    if (num.size() >= size && size >= 1) {
        deque<int> index;
        for (unsigned int i = 0; i < size; i++) {
            // 如果 i 位置的数字比队列后边的数字大，则队列后边的数字不可能成为滑动窗口中的最大值，出队列
            while (!index.empty() && num[i] >= num[index.back()]) {
                index.pop_back();
            }
            index.push_back(i);
        }
        
        for (unsigned int i = size; i < num.size(); i++) {
            maxInWindows.push_back(num[index.front()]);
            
            // 如果 i 位置的数字比队列后边的数字大，则队列后边的数字不可能成为滑动窗口中的最大值，出队列
            while (!index.empty() && num[i] >= num[index.back()]) {
                index.pop_back();
            }
            
            // 当一个数字的下标与当前处理的数字下标之差 >= 滑动窗口大小，这个数字已经从滑动窗口中滑出，可以从队列中删除
            //if (!index.empty() && index.front() <= (int)(i-size)) {
            if (!index.empty() && i - index.front() >= size) {
                index.pop_front();
            }
            
            index.push_back(i);
        }
        maxInWindows.push_back(num[index.front()]);
    }
    return maxInWindows;
}

// ====================测试代码====================
void Test(const char* testName, const vector<int>& num, unsigned int size, const vector<int>& expected)
{
    if(testName != nullptr)
        printf("%s begins: ", testName);

    vector<int> result = maxInWindows(num, size);

    vector<int>::const_iterator iterResult = result.begin();
    vector<int>::const_iterator iterExpected = expected.begin();
    while(iterResult < result.end() && iterExpected < expected.end())
    {
        if(*iterResult != *iterExpected)
            break;

        ++iterResult;
        ++iterExpected;
    }

    if(iterResult == result.end() && iterExpected == expected.end())
        printf("Passed.\n");
    else
        printf("FAILED.\n");
}

void Test1()
{
    int num[] = { 2, 3, 4, 2, 6, 2, 5, 1 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 4, 4, 6, 6, 6, 5 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 3;

    Test("Test1", vecNumbers, size, vecExpected);
}

void Test2()
{
    int num[] = { 1, 3, -1, -3, 5, 3, 6, 7 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 3, 3, 5, 5, 6, 7 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 3;

    Test("Test2", vecNumbers, size, vecExpected);
}

// 输入数组单调递增
void Test3()
{
    int num[] = { 1, 3, 5, 7, 9, 11, 13, 15 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 7, 9, 11, 13, 15 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 4;

    Test("Test3", vecNumbers, size, vecExpected);
}

// 输入数组单调递减
void Test4()
{
    int num[] = { 16, 14, 12, 10, 8, 6, 4 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 16, 14, 12 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 5;

    Test("Test4", vecNumbers, size, vecExpected);
}

// 滑动窗口的大小为1
void Test5()
{
    int num[] = { 10, 14, 12, 11 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 10, 14, 12, 11 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 1;

    Test("Test5", vecNumbers, size, vecExpected);
}

// 滑动窗口的大小等于数组的长度
void Test6()
{
    int num[] = { 10, 14, 12, 11 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    int expected[] = { 14 };
    vector<int> vecExpected(expected, expected + sizeof(expected) / sizeof(int));

    unsigned int size = 4;

    Test("Test6", vecNumbers, size, vecExpected);
}

// 滑动窗口的大小为0
void Test7()
{
    int num[] = { 10, 14, 12, 11 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    vector<int> vecExpected;

    unsigned int size = 0;

    Test("Test7", vecNumbers, size, vecExpected);
}

// 滑动窗口的大小大于输入数组的长度
void Test8()
{
    int num[] = { 10, 14, 12, 11 };
    vector<int> vecNumbers(num, num + sizeof(num) / sizeof(int));

    vector<int> vecExpected;

    unsigned int size = 5;

    Test("Test8", vecNumbers, size, vecExpected);
}

// 输入数组为空
void Test9()
{
    vector<int> vecNumbers;
    vector<int> vecExpected;

    unsigned int size = 5;

    Test("Test9", vecNumbers, size, vecExpected);
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
    
    return 0;
}
