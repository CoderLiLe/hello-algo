//
//  main.cpp
//  50_01_FirstNotRepeatingChar
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题50:第一个只出现一次的字符
 题目1：字符串中第一个只出现一次的字符。
 在字符串中找第一个只出现一次的字符。如输入"abaccdeff"，则输出'b'。
 */

/**
 分析：
 蛮力法：从头开始扫描这个字符串中的每个字符。当访问到某个字符时，拿这个字符和后面
 的每个字符相比较，如果在后面没有发现重复的字符，则该字符就是只出现一次的字符。时
 间复杂度为 O(n^2)。
 
 方法二：
    由于题目与字符出现的次数相关，我们可以统计每个字符在该字符串中出现的次数。
 需要一个数据容器来存储每个字符出现的次数，在这个数据容器中，可以根据字符来查找它
 出现的次数。也就是说这个容器的作用是把一个字符映射成一个数字。在常用的数据容器中，
 哈希表正是这个用途。
    为了解决这个问题，我们可以定义哈希表的键值(Key)是字符，而值(Value)是该字符
 出现的次数。同时我们需要从头开始扫描字符串两次，第一次扫描字符串时，每扫描到一个
 字符，就在哈希表的对应项中把次数加1，接下来第二次扫描时，每扫描到一个字符，就能从
 哈希表中得到该字符出现的次数。这样，第一个只出现一次的字符就是符合要求的输出。
    哈希表是一种复杂的数据结构，C++标准模板库中的 map 和 unordered_map 实现了
 哈希表的功能，我们可以直接拿来使用。由于本体的特殊性，我们可以考虑实现一个简单的哈
 希表。字符(char)是一个长度为8的数据类型（一个字节长，即8位），因此总共有2^8 = 256
 种可能。于是我们创建一个长度为256的数组，每个字母根据其 ASCII 码值作为数组的下标
 对应数组的一个数字，因而数组中存储的是每个字符出现的次数。这样我们就创建了一个大小为
 256、以字符 ASCII 码为键值的哈希表。
    第一次扫描时，在哈希表中更新一个字符出现的次数的时间是O(1)。如果字符串长度为n，
 那么第一次扫描的时间复杂度是O(n)。第二次扫描时，同样在 O(1) 时间内能读出一个字符出现
 的次数，所以时间复杂度仍然是O(n)。总的时间复杂度为O(n)。同样，我们需要一个包含256
 个字符的辅助数组，它的大小是 1KB。由于此数组大小是一个常数，因此可以认为这种算法的
 空间复杂度为O(1)。
 */

#include <iostream>
using namespace std;

char FirstNotRepeatingChar(const char *pString) {
    if (pString == nullptr) {
        return '\0';
    }
    
    const int tableSize = 256;
    unsigned int hashTable[tableSize];
    for (unsigned int i = 0; i < tableSize; ++i) {
        hashTable[i] = 0;
    }
    
    // 第一次扫描，在哈希表中更新每个字符出现的次数，O(n)
    const char *pHashKey = pString;
    while (*(pHashKey) != '\0') {
        hashTable[*(pHashKey++)]++;
    }
    
    // 第二次扫描，O(n)
    pHashKey = pString;
    while (*pHashKey != '\0') {
        if (hashTable[*pHashKey] == 1) {
            return *pHashKey;
        }
        pHashKey++;
    }
    
    return '\0';
}

// ====================测试代码====================
void Test(const char* pString, char expected)
{
    printf("【%s】：", pString);
    if(FirstNotRepeatingChar(pString) == expected)
        cout << "passed." << endl;
    else
        cout << "failed." << endl;
}

int main(int argc, const char * argv[]) {
    // 常规输入测试，存在只出现一次的字符
    Test("google", 'l');

    // 常规输入测试，不存在只出现一次的字符
    Test("aabccdbd", '\0');

    // 常规输入测试，所有字符都只出现一次
    Test("abcdefg", 'a');

    // 鲁棒性测试，输入nullptr
    Test(nullptr, '\0');
    
    return 0;
}
