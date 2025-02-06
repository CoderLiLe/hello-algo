//
//  main.cpp
//  48_LongestSubstringWithoutDup
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题48:最长不含重复字符的子字符串
 题目：请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长字符串的长度，假设字符串
 中只包含 'a'~'z' 的字符。例如，在字符串"arabcacfr"中，最长的不含重复字符的子字符串是 "acfr"，
 长度为4。
 */

/**
 分析：
 蛮力法：找出字符串的所有子字符串，然后判断每个子字符串中是否包含重复的字符串。时间复杂度：O(n^3)
 动态规划法：
 （1）如果第 i 个字符之前没有出现过，则 f(i) = f(i - 1) + 1
 （2）如果第 i 个字符之前出现过，先计算第 i 个字符和它上次出现在字符串中的位置的距离 d
    （2.1）如果 d <= f(i - 1)，则 f(i) = d。此时第 i 个字符上次出现在 f(i - 1) 对应的最长子
 字符串中，并且第 i 个字符出现两次所夹的子字符串中再也没有其他重复的字符了。
    （2.2）如果 d > f(i - 1)，则 f(i) = f(i - 1) + 1。此时第 i 个字符出现在 f(i - 1) 对应的最
 长子字符串之前。
 */

#include <iostream>

using namespace std;

#pragma mark - 蛮力法
bool hasDuplication(const string & str, int position[]);

int longestSubstringWithoutDuplication_1(const string & str) {
    int longest = 0;
    
    int *position = new int[26];
    for (int start = 0; start < str.length(); start++) {
        for (int end = start; end < str.length(); end++) {
            int count = end - start + 1;
            // basic_string substr(size_type __pos = 0, size_type __n = npos) const;
            const string & subString = str.substr(start, count);
            if (!hasDuplication(subString, position)) {
                if (count > longest) {
                    longest = count;
                }
            } else {
                break;
            }
        }
    }
    
    delete[] position;
    return longest;
}

bool hasDuplication(const string & str, int position[]) {
    for (int i = 0; i < 26; i++) {
        position[i] = -1;
    }
    
    for (int i = 0; i < str.length(); i++) {
        int index = str[i] - 'a';
        if (position[index] >= 0) {
            return true;
        }
        position[index] = index;
    }
    
    return false;
}

#pragma mark - 动态规划法
int longestSubstringWithoutDuplication_2(const string & str) {
    int curLength = 0;
    int maxLength = 0;
    
    // 最长的不包含重复字符的字符串由 a - z 26个英文字母组成
    int *position = new int[26];
    for (int i = 0; i < 26; i++) {
        position[i] = -1; // -1 表示该索引对应的英文字母没有出现过
    }
    
    for (int i = 0; i < str.length(); ++i) {
        int index = str[i] - 'a';
        int prevIndex = position[index];
        int distance = i - prevIndex;
        
        if (prevIndex < 0 //（1）如果第 i 个字符之前没有出现过，则 f(i) = f(i - 1) + 1
            || distance > curLength) //（2.2）如果 d > f(i - 1)，则 f(i) = f(i - 1) + 1。此时第 i 个字符出现在 f(i - 1) 对应的最长子字符串之前。
        {
            ++curLength;
        } else {
            // (2.1）如果 d <= f(i - 1)，则 f(i) = d。此时第 i 个字符上次出现在 f(i - 1) 对应的最长子字符串中，并且第 i 个字符出现两次所夹的子字符串中再也没有其他重复的字符了。
            // curLength == f(i - 1)
            if (curLength > maxLength) {
                maxLength = curLength;
            }
            // f(i) == curLength
            // f(i - 1) == distance
            curLength = distance;
        }
        position[index] = i;
    }
    
    if (curLength > maxLength) {
        maxLength = curLength;
    }
    
    delete [] position;
    return maxLength;
}

// ====================测试代码====================
void testSolution1(const string& input, int expected)
{
    int output = longestSubstringWithoutDuplication_1(input);
    if(output == expected)
        cout << "Solution 1 passed, with input: " << input << endl;
    else
        cout << "Solution 1 FAILED, with input: " << input << endl;
}

void testSolution2(const string& input, int expected)
{
    int output = longestSubstringWithoutDuplication_2(input);
    if(output == expected)
        cout << "Solution 2 passed, with input: " << input << endl;
    else
        cout << "Solution 2 FAILED, with input: " << input << endl;
}

void test(const char *testName, const string& input, int expected)
{
    if (testName != nullptr) {
        cout << "----------------------------- " << testName << " -----------------------------" << endl;
    }
    
    auto start1 = chrono::high_resolution_clock::now();
    testSolution1(input, expected);
    auto end1 = chrono::high_resolution_clock::now();
    int64_t duration1 = (end1 - start1).count();
    cout << "Solution1 运行时间：" << duration1 / 1000000000.0 << "s; " << duration1 / 1000000.0 << "ms; " << duration1 / 1000.0 << "us; " << duration1 << "ns" << endl;
    
    auto start2 = chrono::high_resolution_clock::now();
    testSolution2(input, expected);
    auto end2 = chrono::high_resolution_clock::now();
    int64_t duration2 = (end2 - start2).count();
    cout << "Solution2 运行时间：" << duration2 / 1000000000.0 << "s; " << duration2 / 1000000.0 << "ms; " << duration2 / 1000.0 << "us; " << duration2 << "ns" << endl;
}

void test1()
{
    const string input = "abcacfrar";
    int expected = 4;
    test("Test1", input, expected);
}

void test2()
{
    const string input = "acfrarabc";
    int expected = 4;
    test("Test2", input, expected);
}

void test3()
{
    const string input = "arabcacfr";
    int expected = 4;
    test("Test3", input, expected);
}

void test4()
{
    const string input = "aaaa";
    int expected = 1;
    test("Test4", input, expected);
}

void test5()
{
    const string input = "abcdefg";
    int expected = 7;
    test("Test5", input, expected);
}

void test6()
{
    const string input = "aaabbbccc";
    int expected = 2;
    test("Test6", input, expected);
}

void test7()
{
    const string input = "abcdcba";
    int expected = 4;
    test("Test7", input, expected);
}

void test8()
{
    const string input = "abcdaef";
    int expected = 6;
    test("Test8", input, expected);
}

void test9()
{
    const string input = "a";
    int expected = 1;
    test("Test9", input, expected);
}

void test10()
{
    const string input = "";
    int expected = 0;
    test("Test10", input, expected);
}

int main(int argc, const char * argv[]) {
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    test7();
    test8();
    test9();
    test10();
    
    return 0;
}
