//
//  main.cpp
//  44_DigitsInSequence
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

#include <iostream>
#include <cmath>

using namespace std;

/**
 面试题44:数字序列中某一位的数字。
 题目：数字以01234567891011121314...的格式序列化到一个字符序列中。在这个序列中，
 第5位（从0开始计算）是5，第13位是1，第19位是4，等等。请写一个函数求任意位对应的数字。
 */

int countOfIntegers(int digits);
int digitAtIndex(int index, int digits);
int beginNumber(int digits);

int digitAtIndex(int index) {
    if (index < 0) {
        return -1;
    }
    
    int digits = 1;
    while (true) {
        int numbers = countOfIntegers(digits);
        if (index < numbers * digits) {
            return digitAtIndex(index, digits);
        }
        index -= digits * numbers;
        digits++;
    }
    
    return -1;
}

/**
 得到 m 位的数字总共有多少个
 */
int countOfIntegers(int digits) {
    if (digits == 1) {
        return 10;
    }
    
    int count = (int) pow(10.0, digits - 1);
    return 9 * count;
}

int digitAtIndex(int index, int digits) {
    int number = beginNumber(digits) + index / digits;
    int indexFromRight = digits - index % digits;
    for (int i = 1; i < indexFromRight; i++) {
        number /= 10;
    }
    return number % 10;
}

/**
 m 位数的第一个数字
 */
int beginNumber(int digits) {
    if (digits == 1) {
        return 0;
    }
    return (int) pow(10, digits - 1);
}

void Test(const char *testName, int inputIndex, int expectedOutput) {
    if (digitAtIndex(inputIndex) == expectedOutput) {
        cout << testName << " passd." << endl;
    } else {
        cout << testName << " failed." << endl;
    }
}

int main(int argc, const char * argv[]) {
    Test("Test1", 0, 0);
    Test("Test2", 1, 1);
    Test("Test3", 9, 9);
    Test("Test4", 10, 1);
    Test("Test5", 189, 9); // 99的第2位 - 9
    Test("Test6", 190, 1); // 100的1位 - 1
    Test("Test7", 1000, 3); // 370的第1位 - 3
    Test("Test8", 1001, 7); // 370的第2位 - 7
    Test("Test9", 1002, 0); // 370的第3位 - 0
    return 0;
}
