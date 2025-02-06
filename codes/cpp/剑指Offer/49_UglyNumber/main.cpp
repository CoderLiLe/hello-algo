//
//  main.cpp
//  49_UglyNumber
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题49:丑数
 题目：我们把只包含因子2、3和5的数称作丑数(Ugly Number)。求从小到大的顺序第1500个丑数。
 例如，6，8都是丑数，但14不是，因为它包含因子7.习惯上我们把1当作第一个丑数。
 */

/**
 分析：
 方法1:逐个判断每个整数是不是丑数，直观但不高效
    所谓一个数 m 是另一个数 n 的因子，是指 n 能被 m 整除，即 n % m = 0
 
 方法2:创建数组保存已经找到的丑数，空间换时间
    根据丑数的定义，丑数应该是另一个丑数乘以2、3或者5的结果（1除外）。
 */

#include <iostream>

using namespace std;

bool IsUgly(int number) {
    while (number % 2 == 0) {
        number /= 2;
    }
    while (number % 3 == 0) {
        number /= 3;
    }
    while (number % 5 == 0) {
        number /= 5;
    }
    
    return (number == 1) ? true : false;
}

int GetUglyNumber_Solution1(int index) {
    if (index <= 0) {
        return 0;
    }
    
    int number = 0;
    int uglyFound = 0;
    while (uglyFound < index) {
        ++number;
        
        if (IsUgly(number)) {
            ++uglyFound;
        }
    }
    
    return number;
}

int Min(int number1, int number2, int number3) {
    int min = (number1 < number2) ? number1 : number2;
    min = (min < number3) ? min : number3;
    
    return min;
}

int GetUglyNumber_Solution2(int index) {
    if (index <= 0) {
        return 0;
    }
    
    int *pUglyNumbers = new int[index];
    pUglyNumbers[0] = 1;
    int nextUglyIndex = 1;
    
    int *pMultiply2 = pUglyNumbers;
    int *pMultiply3 = pUglyNumbers;
    int *pMultiply5 = pUglyNumbers;
    
    while (nextUglyIndex < index) {
        int min = Min(*pMultiply2 * 2, *pMultiply3 * 3, *pMultiply5 * 5);
        pUglyNumbers[nextUglyIndex] = min;
        
        while (*pMultiply2 * 2 <= pUglyNumbers[nextUglyIndex]) {
            ++pMultiply2;
        }
        while (*pMultiply3 * 3 <= pUglyNumbers[nextUglyIndex]) {
            ++pMultiply3;
        }
        while (*pMultiply5 * 5 <= pUglyNumbers[nextUglyIndex]) {
            ++pMultiply5;
        }
        
        ++nextUglyIndex;
    }
    
    int ugly = pUglyNumbers[nextUglyIndex - 1];
    delete[] pUglyNumbers;
    return ugly;
}

// ====================测试代码====================
void Test(int index, int expected)
{
    cout << "----------------------------- begin -----------------------------" << endl;
    
    auto start1 = chrono::high_resolution_clock::now();
    if(GetUglyNumber_Solution1(index) == expected)
        printf("solution1 passed\n");
    else
        printf("solution1 failed\n");
    auto end1 = chrono::high_resolution_clock::now();
    int64_t duration1 = (end1 - start1).count();
    cout << "Solution1 运行时间：" << duration1 / 1000000000.0 << "s; " << duration1 / 1000000.0 << "ms; " << duration1 / 1000.0 << "us; " << duration1 << "ns" << endl;

    auto start2 = chrono::high_resolution_clock::now();
    if(GetUglyNumber_Solution2(index) == expected)
        printf("solution2 passed\n");
    else
        printf("solution2 failed\n");
    auto end2 = chrono::high_resolution_clock::now();
    int64_t duration2 = (end2 - start2).count();
    cout << "Solution2 运行时间：" << duration2 / 1000000000.0 << "s; " << duration2 / 1000000.0 << "ms; " << duration2 / 1000.0 << "us; " << duration2 << "ns" << endl;
}


int main(int argc, const char * argv[]) {
    Test(1, 1);

    Test(2, 2);
    Test(3, 3);
    Test(4, 4);
    Test(5, 5);
    Test(6, 6);
    Test(7, 8);
    Test(8, 9);
    Test(9, 10);
    Test(10, 12);
    Test(11, 15);

    Test(1500, 859963392);

    Test(0, 0);

    return 0;
}
