//
//  DFSTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "DFSTest.hpp"
#include "_0017_电话号码的字母组合.hpp"

#include <iostream>

using namespace std;

void test0017() {
    vector<string> result = letterCombinations("23");
    for (int i = 0; i < result.size(); i++) {
        cout << result[i] << " ";
    }
    cout << endl;
}

void dfsTest() {
    test0017();
}
