//
//  main.cpp
//  50_02_FirstCharacterInStream
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题50:第一个只出现一次的字符
 题目：字符流中第一个只出现一次的字符。
 请实现一个函数，用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符
 "go"时，第一个只出现一次的字符是'g'；当从该字符流中读出前6个字符"google"时，第一个只出
 现一次的字符是'l'。
 */

/**
 分析：受上一题的启发，用哈希表作为数据容器。
 */

#include <iostream>

using namespace std;

class CharStatistics {
public:
    CharStatistics() : index(0)
    {
        for (int i = 0; i < 256; i++) {
            occurrence[i] = -1;
        }
    }
    
    void Insert(char ch) {
        if (occurrence[ch] == -1) {
            occurrence[ch] = index;
        } else if (occurrence[ch] >= 0) {
            occurrence[ch] = -2;
        }
        index++;
    }
    
    char FirstAppearOnce() {
        char ch = '\0';
        int minIndex = numeric_limits<int>::max();
        for (int i = 0; i < 256; i++) {
            if (occurrence[i] >= 0 && occurrence[i] < minIndex) {
                ch = (char)i;
                minIndex = occurrence[i];
            }
        }
        return ch;
    }
private:
    // occurrence[i]: A character with ASCII value i;
    // occurrence[i] == -1 : The character has not found
    // occurrence[i] == -2 : The character has been found for mutlple times
    // occurrence[i] >= 0 : The character has been found only once
    int occurrence[256];
    int index;
};

// ====================测试代码====================
void Test(const char* testName, CharStatistics chars, char expected)
{
    if(testName != nullptr)
        printf("【%s】：", testName);

    if(chars.FirstAppearOnce() == expected)
        printf("Passed.\n");
    else
        printf("FAILED.\n");
}

int main(int argc, const char * argv[]) {
    CharStatistics chars;

    Test("Test1", chars, '\0');

    chars.Insert('g');
    Test("Test2", chars, 'g');

    chars.Insert('o');
    Test("Test3", chars, 'g');

    chars.Insert('o');
    Test("Test4", chars, 'g');

    chars.Insert('g');
    Test("Test5", chars, '\0');

    chars.Insert('l');
    Test("Test6", chars, 'l');

    chars.Insert('e');
    Test("Test7", chars, 'l');
    
    return 0;
}
