//
//  DpTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/26.
//

#include "DpTest.hpp"
#include <iostream>
#include "_0005_最长回文子串.hpp"
#include "_0063_不同路径II.hpp"
#include "_0072_编辑距离.hpp"

using namespace std;

void test0005_01() {
    cout << longestPalindrome("babad") << endl;
    cout << longestPalindrome("cbbd") << endl;
    cout << longestPalindrome("a") << endl;
    cout << longestPalindrome("ac") << endl;
}

void test0005_02() {
    cout << longestPalindrome2("babad") << endl;
    cout << longestPalindrome2("cbbd") << endl;
    cout << longestPalindrome2("a") << endl;
    cout << longestPalindrome2("ac") << endl;
}

void test0005_03() {
    cout << longestPalindrome3("babad") << endl;
    cout << longestPalindrome3("cbbd") << endl;
    cout << longestPalindrome3("a") << endl;
    cout << longestPalindrome3("ac") << endl;
}

void test0005_04() {
    cout << longestPalindrome4("babad") << endl;
    cout << longestPalindrome4("cbbd") << endl;
    cout << longestPalindrome4("a") << endl;
    cout << longestPalindrome4("ac") << endl;
}

void test0063() {
    vector<vector<int>> obstacleGrid = {{0,0,0}, {0,1,0}, {0,0,0}};
    cout << uniquePathsWithObstacles(obstacleGrid) << endl;
    
    vector<vector<int>> obstacleGrid2 = {{0,1}, {0,0}};
    cout << uniquePathsWithObstacles(obstacleGrid2) << endl;
    
    vector<vector<int>> obstacleGrid3 = {{1}};
    cout << uniquePathsWithObstacles(obstacleGrid3) << endl;
}

void test0072() {
    cout << minDistance("horse", "ros") << endl;
    cout << minDistance("intention", "execution") << endl;
}

void dpTest() {
//    test0005_01();
//    test0005_02();
//    test0005_03();
//    test0005_04();
    
    test0063();
    
//    test0072();
}
