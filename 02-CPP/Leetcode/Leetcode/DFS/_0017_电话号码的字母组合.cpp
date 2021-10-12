//
//  _0017_电话号码的字母组合.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "_0017_电话号码的字母组合.hpp"

const string lettersMap[] = {
    "", // 0
    "", // 1
    "abc", // 2
    "def", // 3
    "ghi", // 4
    "jkl", // 5
    "mno", // 6
    "pqrs", // 7
    "tuv", // 8
    "wxyz", // 9
};

vector<string> result;
string s;

void backtracking(const string& digits, int index) {
    if (index == digits.size()) {
        result.push_back(s);
        return;
    }
    
    int digit = digits[index] - '0';
    string letters = lettersMap[digit];
    for (int i = 0; i < letters.size(); i++) {
        s.push_back(letters[i]);
        backtracking(digits, index + 1);
        s.pop_back();
    }
}

vector<string> letterCombinations(string digits) {
    if (0 == digits.size()) {
        return result;
    }
    
    backtracking(digits, 0);
    return result;
}
