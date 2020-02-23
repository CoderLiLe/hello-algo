//
//  main.cpp
//  46_TranslateNumbersToStrings
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

#include <iostream>

using namespace std;

/**
 面试题46:把数字翻译成字符串。
 题目：给定一个数字，我们按照如下规则把它翻译为字符串：0翻译为"a"，1翻译为"b"，...，11翻译为"l",...,25翻译为'z'。一个数字
 可能有多个翻译。例如，12258有5种不同的翻译，分别是"bccfi"、"bwfi"、"bczi"、"mcfi"、"mzi"。请编程实现一个函数，用来计算
 一个数字有多少种不同的翻译方法。
 */

/**
 递归实现：f(i) = f(i + 1) + g(i, i + 1) * f(i + 2)
 f(i)表示从第i位数字开始的不同翻译的数目。当第 i 位和第 i + 1 位两位数字拼接起来的数字在 10 ～ 25 的范围内时，函数 g(i, i + 1)
 的值为1；否则为0。
 */

int GetTranslationCount(const string &number);

int GetTranslationCount(int number) {
    if (number < 0) {
        return 0;
    }
    
    string numberInString = to_string(number);
    return GetTranslationCount(numberInString);
}

int GetTranslationCount(const string &number) {
    int length = number.length();
    int *counts = new int[length];
    int count = 0;
    
    for (int i = length - 1; i >= 0; --i) {
        count = 0;
        if (i < length - 1) {
            count = counts[i + 1];
        } else {
            count = 1;
        }
        
        if (i < length - 1) {
            int digit1 = number[i] - '0';
            int digit2 = number[i + 1] - '0';
            int converted = digit1 * 10 + digit2;
            if (converted >= 10 && converted <= 25) {
                if (i < length - 2) {
                    count += counts[i + 2];
                } else {
                    count += 1;
                }
            }
        }
        
        counts[i] = count;
    }
    
    count = counts[0];
    delete[] counts;
    
    return count;
}

void Test(const string& testName, int number, int expected)
{
    if(GetTranslationCount(number) == expected)
        cout << testName << " passed." << endl;
    else
        cout << testName << " FAILED." << endl;
}

void Test1()
{
    int number = 0;
    int expected = 1;
    Test("Test1", number, expected);
}

void Test2()
{
    int number = 10;
    int expected = 2;
    Test("Test2", number, expected);
}

void Test3()
{
    int number = 125;
    int expected = 3;
    Test("Test3", number, expected);
}

void Test4()
{
    int number = 126;
    int expected = 2;
    Test("Test4", number, expected);
}

void Test5()
{
    int number = 426;
    int expected = 1;
    Test("Test5", number, expected);
}

void Test6()
{
    int number = 100;
    int expected = 2;
    Test("Test6", number, expected);
}

void Test7()
{
    int number = 101;
    int expected = 2;
    Test("Test7", number, expected);
}

void Test8()
{
    int number = 12258;
    int expected = 5;
    Test("Test8", number, expected);
}

void Test9()
{
    int number = -100;
    int expected = 0;
    Test("Test9", number, expected);
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
