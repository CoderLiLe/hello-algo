//
//  main.cpp
//  05_ReplaceSpaces
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题5：替换空格
 题目：请实现一个函数，把字符串中的每个空格替换成"%20"。例如输入“We are happy.”，则输出“We%20are%20happy.”。
 */

#include <iostream>

using namespace std;

void replaceBlank(char str[], int length) {
    if (str == nullptr || length == 0) {
        return;
    }
    
    int originLen = 0;
    int blankNum = 0;
    int i = 0;
    while (str[i] != '\0') {
        originLen++;
        if (str[i] == ' ') {
            blankNum++;
        }
        i++;
    }
    
    int newLen = originLen + blankNum * 2;
    int indexOfOrigin = originLen;
    int indexOfNew = newLen;
    while (indexOfOrigin >= 0 && indexOfNew > indexOfOrigin) {
        if (str[indexOfOrigin] == ' ') {
            str[indexOfNew--] = '0';
            str[indexOfNew--] = '2';
            str[indexOfNew--] = '%';
        } else {
            str[indexOfNew--] = str[indexOfOrigin];
        }
        indexOfOrigin--;
    }
}

// ====================测试代码====================
void Test(const char* testName, char str[], int length, const char expected[])
{
    if(testName != nullptr)
        cout << testName << " : ";

    replaceBlank(str, length);

    if(expected == nullptr && str == nullptr)
        cout << "passed." << endl;
    else if(expected == nullptr && str != nullptr)
        cout << "failed." << endl;
    else if(strcmp(str, expected) == 0)
        cout << "passed." << endl;
    else
        cout << "failed." << endl;
}

// 空格在句子中间
void Test1()
{
    const int length = 100;

    char str[length] = "hello world";
    Test("Test1", str, length, "hello%20world");
}

// 空格在句子开头
void Test2()
{
    const int length = 100;

    char str[length] = " helloworld";
    Test("Test2", str, length, "%20helloworld");
}

// 空格在句子末尾
void Test3()
{
    const int length = 100;

    char str[length] = "helloworld ";
    Test("Test3", str, length, "helloworld%20");
}

// 连续有两个空格
void Test4()
{
    const int length = 100;

    char str[length] = "hello  world";
    Test("Test4", str, length, "hello%20%20world");
}

// 传入nullptr
void Test5()
{
    Test("Test5", nullptr, 0, nullptr);
}

// 传入内容为空的字符串
void Test6()
{
    const int length = 100;

    char str[length] = "";
    Test("Test6", str, length, "");
}

//传入内容为一个空格的字符串
void Test7()
{
    const int length = 100;

    char str[length] = " ";
    Test("Test7", str, length, "%20");
}

// 传入的字符串没有空格
void Test8()
{
    const int length = 100;

    char str[length] = "helloworld";
    Test("Test8", str, length, "helloworld");
}

// 传入的字符串全是空格
void Test9()
{
    const int length = 100;

    char str[length] = "   ";
    Test("Test9", str, length, "%20%20%20");
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
