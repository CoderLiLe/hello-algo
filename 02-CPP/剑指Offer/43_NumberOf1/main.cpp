//
//  main.cpp
//  43_NumberOf1
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

#include <iostream>
using namespace std;

/**
 面试题43:从 1 到 n 整数中 1 出现的次数。
 题目：输入一个整数n, 求从1到n这n个数的十进制表示中1出现的次数。例如：输入12，从1到12这些数中包含1的数字有1、10、11、12，1一共出现了5次。
 */

int NumberOf1(unsigned int n);

int NumberOf1Between1AndN_Solution1(unsigned int n) {
    int number = 0;
    
    for (unsigned int i = 1; i <= n; ++i) {
        number += NumberOf1(i);
    }
    
    return number;
}

int NumberOf1(unsigned int n) {
    int number = 0;
    while (n) {
        if (n % 10 == 1) {
            number++;
        }
        
        n = n / 10;
    }
    
    return number;
}

int NumberOf1(const char *strN);
int PowerBase10(unsigned int n);

int NumberOf1Between1AndN_Solution2(unsigned int n) {
    if (n <= 0) {
        return n;
    }
    
    char strN[50];
    sprintf(strN, "%d", n);
    
    return NumberOf1(strN);
}

int NumberOf1(const char *strN) {
    if (!strN || *strN < '0' || *strN > '9' || *strN == '\0') {
        return 0;
    }
    
    int first = *strN - '0';
    unsigned int length = static_cast<unsigned int>(strlen(strN));
    
    if (length == 1 && first == 0) {
        return 0;
    }
    
    if (length == 1 && first > 0) {
        return 1;
    }
    
    // 假设strN = "21345"
    // numFirstDigit 是数字 10000 ～ 19999 的第一个位中1的数目
    int numFirstDigit = 0;
    if (first > 1) {
        numFirstDigit = PowerBase10(length - 1);
    } else if (first == 1) {
        numFirstDigit = atoi(strN + 1) + 1;
    }
    
    // numOtherDigit 是 01346 ～ 21345除了第一位之外的数位中1的数目
    int numOtherDigit = first * (length - 1) * PowerBase10(length - 2);
    // numRecursive 是 1 ～ 1345 中 1 的数目
    int numRecursive = NumberOf1(strN + 1);
    
    return numFirstDigit + numOtherDigit + numRecursive;
}

int PowerBase10(unsigned int n) {
    int result = 1;
    for (unsigned int i = 0; i < n; i++) {
        result *= 10;
    }
    return result;
}

void Test(const char *testName, int n, int expected) {
    if (testName != nullptr) {
        cout << "----------- " << testName << " -----------" << endl;
    }
    
    auto start1 = chrono::high_resolution_clock::now();
    if (NumberOf1Between1AndN_Solution1(n) == expected) {
        cout << "Solution1 passed." << endl;
    } else {
        cout << "Solution1 failed." << endl;
    }
    auto end1 = chrono::high_resolution_clock::now();
    int64_t duration1 = (end1 - start1).count();
    cout << "Solution1 运行时间：" << duration1 / 1000000000.0 << "s; " << duration1 / 1000000.0 << "ms; " << duration1 / 1000.0 << "us; " << duration1 << "ns" << endl;
    

//    clock_t start2 = clock();
    auto start2 = chrono::high_resolution_clock::now();
    if (NumberOf1Between1AndN_Solution2(n) == expected) {
        cout << "Solution2 passed." << endl;
    } else {
        cout << "Solution2 failed." << endl;
    }
//    clock_t end2 = clock();
//    clock_t duration2 = end2 - start2;
//    cout << "Solution2 运行时间：" << duration2 / 1000.0 << "s; " << duration2 << "ms" << endl;
    auto end2 = chrono::high_resolution_clock::now();
    int64_t duration2 = (end2 - start2).count();
    cout << "Solution2 运行时间：" << duration2 / 1000000000.0 << "s; " << duration2 / 1000000.0 << "ms; " << duration2 / 1000.0 << "us; " << duration2 << "ns" << endl;
}

int main(int argc, const char * argv[]) {
    Test("Test1", 1, 1);
    Test("Test2", 5, 1);
    Test("Test3", 10, 2);
    Test("Test4", 55, 16);
    Test("Test5", 99, 20);
    Test("Test6", 10000, 4001);
    Test("Test7", 21345, 18821);
    Test("Test8", 0, 0);
    return 0;
}
