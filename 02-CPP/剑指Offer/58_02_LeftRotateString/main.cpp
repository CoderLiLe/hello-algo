//
//  main.cpp
//  58_02_LeftRotateString
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题58:翻转字符串
 题目二：左旋转字符串
    字符串的左旋转操作是把字符串前面若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位的到结果"cdefgab"。
 */

#include <iostream>

using namespace std;

void Reverse(char *begin, char *end) {
    if (begin == nullptr || end == nullptr) {
        return;
    }
    
    while (begin < end) {
        char tmp = *begin;
        *begin = *end;
        *end = tmp;
        
        begin++;
        end--;
    }
}

char *LeftRotateString(char *str, int n) {
    if (str != nullptr) {
        int len = static_cast<int>(strlen(str));
        if (len > 0 && n > 0 && n < len) {
            char *firstStart = str;
            char *firstEnd = firstStart + n - 1;
            char *secondStart = firstEnd + 1;
            char *secondEnd = str + len-1;
            
            Reverse(firstStart, firstEnd);
            cout << " 1 : " << str << endl;
            Reverse(secondStart, secondEnd);
            cout << " 2 : " << str << endl;
            Reverse(firstStart, secondEnd);
            cout << " 3 : " << str << endl;
        }
    }
    
    return str;
}

// ====================测试代码====================
void Test(const char* testName, char* input, int num, const char* expectedResult)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    char* result = LeftRotateString(input, num);

    if((input == nullptr && expectedResult == nullptr)
        || (input != nullptr && strcmp(result, expectedResult) == 0))
        printf("Passed.\n");
    else
        printf("Failed.\n");
}

// 功能测试
void Test1()
{
    char input[] = "abcdefg";
    char expected[] = "cdefgab";

    Test("Test1", input, 2, expected);
    
    /**
     abcdefg
     
     ab      --> ba
     cdefg   --> gfedc
     
     bagfedc --> cdefgab
     
     
     abcdefg --> gfedc ba --> cdefg ab
     
     
     */
}

// 边界值测试
void Test2()
{
    char input[] = "abcdefg";
    char expected[] = "bcdefga";

    Test("Test2", input, 1, expected);
}

// 边界值测试
void Test3()
{
    char input[] = "abcdefg";
    char expected[] = "gabcdef";

    Test("Test3", input, 6, expected);
}

// 鲁棒性测试
void Test4()
{
    Test("Test4", nullptr, 6, nullptr);
}

// 鲁棒性测试
void Test5()
{
    char input[] = "abcdefg";
    char expected[] = "abcdefg";

    Test("Test5", input, 0, expected);
}

// 鲁棒性测试
void Test6()
{
    char input[] = "abcdefg";
    char expected[] = "abcdefg";

    Test("Test6", input, 7, expected);
}


int main(int argc, const char * argv[]) {
    Test1();
//    Test2();
//    Test3();
//    Test4();
//    Test5();
//    Test6();
    
    return 0;
}
