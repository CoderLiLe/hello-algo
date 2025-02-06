//
//  StringTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/23.
//

#include "StringTest.hpp"
#include <iostream>
#include "_0151_翻转字符串里的单词.hpp"

using namespace std;

void test0151() {
    string s = "the sky is blue";
    cout << reverseWords(s) << endl;
    
    string s1 = "  hello world  ";
    cout << reverseWords(s1) << endl;
    
    string s2 = "a good   example";
    cout << reverseWords(s2) << endl;
    
    string s3 = "  Bob    Loves  Alice   ";
    cout << reverseWords(s3) << endl;
    
    string s4 = "Alice does not even like bob";
    cout << reverseWords(s4) << endl;
}

void stringTest() {
    test0151();
}
