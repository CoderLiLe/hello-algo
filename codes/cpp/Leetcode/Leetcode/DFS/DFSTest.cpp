//
//  DFSTest.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "DFSTest.hpp"
#include "_0017_电话号码的字母组合.hpp"
#include "_0022_括号生成.hpp"
#include "_0046_全排列.hpp"
#include "_0047_全排列.hpp"
#include "_0051_N皇后.hpp"
#include "_0052_N皇后II.hpp"
#include "_130_被围绕的区域.hpp"

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

void test0022() {
    vector<string> res = generateParenthesis(3);
    vectorPrint(res);
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

void test0047() {
    vector<int> nums = {1,1,2};
    PermuteUnique *pu = new PermuteUnique();
    vector<vector<int>> pathList = pu->permuteUnique(nums);
    vectorPrint(pathList);
}

void test0051() {
    vector<vector<string>> result = solveNQueens(4);
    vectorPrint(result);
}

void test0052() {
    cout << totalNQueens(4) << endl;
}

void test0130() {
    vector<vector<char>> board = {{'X','X','X','X'}, {'X','O','O','X'}, {'X','X','O','X'}, {'X','O','X','X'}};
    solve(board);
    vectorPrint(board);
}

void test0130_01() {
    vector<vector<char>> board = {{'O','X','X','O','X'}, {'X','O','O','X','O'}, {'X','O','X','O','X'}, {'O','X','O','O','O'}, {'X','X','O','X','O'}};
    cout << "原始数据为：" << endl;
    vectorPrint(board);
    solve(board);
    cout << "输出结果为：" << endl;
    vectorPrint(board);
    cout << "预期结果为：" << endl;
    vector<vector<char>> result = {{'O','X','X','O','X'}, {'X','X','X','X','O'}, {'X','X','X','O','X'}, {'O','X','O','O','O'}, {'X','X','O','X','O'}};
    vectorPrint(result);
}

void test0130_02() {
    vector<vector<char>> board = {{'O','X','X','O','X'}, {'X','O','O','X','O'}, {'X','O','X','O','X'}, {'O','X','O','O','O'}, {'X','X','O','X','O'}};
    cout << "原始数据为：" << endl;
    vectorPrint(board);
    solve2(board);
    cout << "输出结果为：" << endl;
    vectorPrint(board);
    cout << "预期结果为：" << endl;
    vector<vector<char>> result = {{'O','X','X','O','X'}, {'X','X','X','X','O'}, {'X','X','X','O','X'}, {'O','X','O','O','O'}, {'X','X','O','X','O'}};
    vectorPrint(result);
}

void dfsTest() {
//    test0017();
//    test0022();
//    test0046();
//    test0046_02();
//    test0047();
//    test0051();
//    test0052();
//    test0130();
    test0130_02();
}
