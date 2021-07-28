//
//  DFSTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "DFSTest.hpp"
#include "_0017_电话号码的字母组合.hpp"
#include "_0046_全排列.hpp"

#include "VectorUtil.hpp"

#include <iostream>

using namespace std;

void test0017() {
    vector<string> result = letterCombinations("23");
    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << " ";
    }
    cout << endl;
}

void test0046() {
    vector<int> nums = {1, 2, 3};
    vector<vector<int>> pathList = permute(nums);
    vectorPrint(pathList);
}

void test0046_02() {
    vector<int> nums = {1, 2, 3};
    vector<vector<int>> pathList = permute2(nums);
    vectorPrint(pathList);
}

void dfsTest() {
//    test0017();
//    test0046();
    test0046_02();
}
