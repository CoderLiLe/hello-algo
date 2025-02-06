//
//  main.cpp
//  58_01_ReverseWordsInSentence
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题58:翻转字符串
 题目一：翻转单词顺序。
    输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 例如输入字符串"I am a student."，则输出"student. a am I"。
 */

/**
 分析：两次翻转，第一次翻转句子中所有字符，第二次翻转每个单词中字符的顺序。
 */

#include <iostream>

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

char *ReverseSentence(char *str) {
    if (str == nullptr) {
        return nullptr;
    }
    
    char *begin = str;
    
    char *end = str;
    while (*end != '\0') {
        end++;
    }
    end--;
    
    // 翻转整个句子
    Reverse(begin, end);
    
    // 翻转句子中每个单词
    begin = end = str;
//    while (*begin != '\0') {
//        if (*begin == ' ') {
//            begin++;
//            end++;
//        } else if (*end == ' ' || *end == '\0') {
//            Reverse(begin, --end);
//            begin = ++end;
//        } else {
//            end++;
//        }
//    }
    
    while (*(end+1) != '\0') {
        if (*(end+1) == ' ') {
            Reverse(begin, end);
            end += 2;
            begin = end;
        } else {
            end++;
            if (*(end+1) == '\0') {
                Reverse(begin, end);
            }
        }
    }
    
    return str;
}

// ====================测试代码====================
void Test(const char* testName, char* input, const char* expectedResult)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    ReverseSentence(input);

    if((input == nullptr && expectedResult == nullptr)
        || (input != nullptr && strcmp(input, expectedResult) == 0))
        printf("Passed.\n");
    else
        printf("Failed.\n");
}

// 功能测试，句子中有多个单词
void Test1()
{
    char input[] = "I am a student.";
    char expected[] = "student. a am I";

    Test("Test1", input, expected);
}

// 功能测试，句子中只有一个单词
void Test2()
{
    char input[] = "Wonderful";
    char expected[] = "Wonderful";

    Test("Test2", input, expected);
}

// 鲁棒性测试
void Test3()
{
    Test("Test3", nullptr, nullptr);
}

// 边界值测试，测试空字符串
void Test4()
{
    Test("Test4", "", "");
}

// 边界值测试，字符串中只有空格
void Test5()
{
    char input[] = "   ";
    char expected[] = "   ";
    Test("Test5", input, expected);
}


int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    
    return 0;
}
