//
//  main.cpp
//  45_SortArrayForMinNumber
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

#include <iostream>
#include <string>

using namespace std;

/**
 面试题45:把数组排成最小的数。
 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出来的所有数字中最小的一个。
 例如，输入数组{3, 32, 321}，则打印出这三个数字能排成的最小数字321323。
 */

const int g_MaxNumberLength = 10;

char *g_StrCombine1 = new char[g_MaxNumberLength * 2 + 1];
char *g_StrCombine2 = new char[g_MaxNumberLength * 2 + 1];

int compare(const void *strNumber1, const void *strNumber2);

void PrintMinNumber(int *numbers, int length) {
    if (numbers == nullptr || length <= 0) {
        cout << endl;
        return;
    }
    
    char **strNumbers = (char **)(new int[length]);
    for (int i = 0; i < length; ++i) {
        strNumbers[i] = new char[g_MaxNumberLength + 1];
        sprintf(strNumbers[i], "%d", numbers[i]);
    }
    
    // void qsort(void *__base, size_t __nel, size_t __width, int (* _Nonnull __compar)(const void *, const void *));
    // 对 __base 中的数从小到大排序，时间复杂度为 O(nlogn)
    qsort(strNumbers, length, sizeof(char *), compare);
    
    for (int i = 0; i < length; ++i) {
        cout << strNumbers[i];
    }
    cout << endl;
    
    for (int i = 0; i < length; ++i) {
        delete[] strNumbers[i];
    }
    delete[] strNumbers;
}

/**
 如果 [strNumber1][strNumber2] > [strNumber2][strNumber1]，则返回值大于0
 如果 [strNumber1][strNumber2] == [strNumber2][strNumber1]，则返回值等于0
 如果 [strNumber1][strNumber2] < [strNumber2][strNumber1]，则返回值小于0
 */
int compare(const void *strNumber1, const void *strNumber2) {
    strcpy(g_StrCombine1, *(const char **)strNumber1);
    strcat(g_StrCombine1, *(const char **)strNumber2);
    
    strcpy(g_StrCombine2, *(const char **)strNumber2);
    strcat(g_StrCombine2, *(const char **)strNumber1);
    
    return strcmp(g_StrCombine1, g_StrCombine2);
}

void Test(const char *testName, int *numbers, int length, const char *expectedResult) {
    if (testName != nullptr) {
        cout << "----------- " << testName << " -----------" << endl;
    }
    
    if (expectedResult != nullptr) {
        cout << "Expected result is " << expectedResult << endl;
    }
    
    cout << "Actual result is : ";
    PrintMinNumber(numbers, length);
}

void Test1() {
    int numbers[] = {3, 5, 1, 4, 2};
    Test("Test1", numbers, sizeof(numbers) / sizeof(int), "12345");
}

void Test2() {
    int numbers[] = {3, 32, 321};
    Test("Test2", numbers, sizeof(numbers) / sizeof(int), "321323");
}

void Test3() {
    int numbers[] = {3, 323, 32123};
    Test("Test3", numbers, sizeof(numbers) / sizeof(int), "321233233");
}

void Test4() {
    int numbers[] = {1, 11, 111};
    Test("Test4", numbers, sizeof(numbers) / sizeof(int), "111111");
}

void Test5() {
    int numbers[] = {321};
    Test("Test5", numbers, sizeof(numbers) / sizeof(int), "321");
}

void Test6() {
    Test("Test6", nullptr, 0, "Don't print anything.");
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
