//
//  main.cpp
//  62_LastNumberInCircle
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题62:圆圈中最后剩下的数字
 题目：0, 1, ..., n-1 这 n 个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第 m 个数字。
 求出这个圆圈里剩下的最后一个数字。
 */

/**
 分析：约瑟夫（Josephuse）环问题
 方法一【经典】：环形链表模拟圆圈
 方法二【创新】：分析每次被删除的数字的规律直接计算出最后一个数字
 */
#include <iostream>
#include <list>

using namespace std;

#pragma mark - 环形链表模拟圆圈
/**
 n 个节点的环形链表，每次删除链表中第 m 个节点
 T = O(m*n), S = O(n)
 */
int LastRemaining_Solution1(unsigned int n, unsigned int m) {
    if (n < 1 || m < 1) {
        return -1;
    }
    
    list<int> numbers;
    for (int i = 0; i < n; i++) {
        numbers.push_back(i);
    }
    
    list<int>::iterator current = numbers.begin();
    // n
    while (numbers.size() > 1) {
        // m
        for (int i = 1; i < m; ++i) {
            current++;
            if (current == numbers.end()) {
                current = numbers.begin();
            }
        }
        
        list<int>::iterator next = ++current;
        if (next == numbers.end()) {
            next = numbers.begin();
        }
        --current;
        numbers.erase(current);
        current = next;
    }
    
    return *current;
}

#pragma mark - 创新
/**
 f(n, m) ：每次在 n 个数字 0, 1, ..., n-1 中删除第 m 个数字后剩下的数字

    第一个被删除的数字 k = (m-1) % n，剩余 0, 1, ... , k - 1, k + 1, ..., n - 1，下一次
 删除从 k + 1 开始。
    相当于从序列  k + 1, ..., n - 1, 0, 1, ... , k - 1 中删除第 m 个数字。该序列最后剩下的
 数字也应该是关于 m 和 n 的函数。此函数记为 f'(n - 1, m)
    最初序列最后剩下的数字 f(n, m) 一定是删除一个数字之后序列最后剩下的数字，即 f(n, m) = f'(n - 1, m)
 
将这 n-1 个数字的序列 {k + 1, ..., n - 1, 0, 1, ... , k - 1} 映射成一个 0 ~ n-2的序列
 
    k + 1 -> 0
    k + 2 -> 1
    ...
    n - 1 -> n - k - 2
    0     -> n - k - 1
    1     -> n - k
    ...
    k - 1 -> n - 2
 
此映射定义为 p, p(x) = (x-k-1) % n, 逆映射为 p^(-1)(x) = (x+k+1) % n
 
映射之前序列中剩下的数字
 f'(n-1, m) = p^(-1)[f(n-1, m)] = [f(n-1, m)+k+1] % n = [f(n-1, m)+m] % n = f(n, m)
 
 */

/**
 例子：
 m = 3
 
 从复杂到简单推理
--------------------------------------------------------------------
| n |     初始数据      |     删除后     |    最后剩余数据在删除后中的索引 |
-------------------------------------------------------------------
| 5 | {0, 1, 2, 3, 4} -> {0, 1, 3, 4}  |            2              |
| 4 | {3, 4, 0, 1}    -> {3, 4, 1}     |            0              |
| 3 | {1, 3, 4}       -> {1, 3}        |            1              |
| 2 | {1, 3}          -> {3}           |            0              |
--------------------------------------------------------------------
 
 k = (m - 1) % n = (3 - 1) % 5 = 2
 对索引进行映射：
 
 3 = 2 + 1 -> (index - k - 1) % 5 = (3 - 2 - 1) % 5 = 0
 4 = 2 + 2 -> (index - k - 1) % 5 = (4 - 2 - 1) % 5 = 1
 0         -> n - k - 1 = 5 - 2 - 1 = 2
 1         -> n - k = n - 2 = 5 - 2 = 3
 
 
 
 
 由简单到复杂推理
 
 <1> {3}, n = 1, f(n, m) = f(1, 3) = 0;
 
 <2> {1, 3}, n = 2,   f(n, m) = f(2, 3)
                    = [f(n - 1, m) + m] % n = [f(1, 3) + 3)] % 2 = (0 + 3) % 2 = 1
 
 <3> {1, 3, 4}, n = 3,   f(n, m) = f(3, 3)
                       = [f(n - 1, m) + m] % n = [f(2, 3) + 3)] % 3 = (1 + 3) % 3 = 1;
 
 <4> {3, 4, 0, 1}, n = 4,   f(n, m) = f(4, 3)
                          = [f(n - 1, m) + m] % n = [f(3, 3) + 3)] % 4 = = (1 + 3) % 4 = 0;
 
 <5> {0, 1, 2, 3, 4}, n = 5,   f(n, m) = f(5, 3)
                             = [f(n - 1, m) + m] % n = [f(4, 3) + 3)] % 5 = (0 + 3) % 5 = 3;
 */

int LastRemaining_Solution2(unsigned int n, unsigned int m)
{
    if(n < 1 || m < 1)
        return -1;

    int last = 0;
    for (int i = 2; i <= n; i++)
        last = (last + m) % i;

    return last;
}

// ====================测试代码====================
void Test(const char* testName, unsigned int n, unsigned int m, int expected)
{
    if(testName != nullptr)
        printf("----------- 【%s】 -----------\n", testName);

    if(LastRemaining_Solution1(n, m) == expected)
        printf("Solution1 passed.\n");
    else
        printf("Solution1 failed.\n");

    if(LastRemaining_Solution2(n, m) == expected)
        printf("Solution2 passed.\n");
    else
        printf("Solution2 failed.\n");

    printf("\n");
}

void Test1()
{
    Test("Test1", 5, 3, 3);
}

void Test2()
{
    Test("Test2", 5, 2, 2);
}

void Test3()
{
    Test("Test3", 6, 7, 4);
}

void Test4()
{
    Test("Test4", 6, 6, 3);
}

void Test5()
{
    Test("Test5", 0, 0, -1);
}

void Test6()
{
    Test("Test6", 4000, 997, 1027);
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
