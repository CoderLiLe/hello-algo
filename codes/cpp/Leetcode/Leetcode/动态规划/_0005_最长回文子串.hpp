//
//  _0005_最长回文子串.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#ifndef _0005________hpp
#define _0005________hpp

#include <stdio.h>
#include <string>
using namespace std;

/**
 动态规划法
 */
string longestPalindrome(string s);

/**
 扩展中心法
 */
string longestPalindrome2(string s);

/**
 扩展中心法2
 */
string longestPalindrome3(string s);

/**
 Manacher 算法（马拉车算法）
 */
string longestPalindrome4(string s);

#endif /* _0005________hpp */
