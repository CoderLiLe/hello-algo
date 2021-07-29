//
//  VectorUtil.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/28.
//

#include "VectorUtil.hpp"

#include <iostream>

void vectorPrint(vector<int>& nums) {
    size_t size = nums.size();
    for (size_t i = 0; i < size; i++) {
        if (i < size - 1) {
            cout << nums[i] << ", ";
        } else {
            cout << nums[i] << endl;
        }
    }
}

void vectorPrint(vector<vector<int>>& nums) {
    for (size_t i = 0; i < nums.size(); i++) {
        vectorPrint(nums[i]);
    }
}

#pragma mark - string

void vectorPrint(vector<string>& nums) {
    size_t size = nums.size();
    for (size_t i = 0; i < size; i++) {
        if (i < size - 1) {
            cout << nums[i] << ", ";
        } else {
            cout << nums[i] << endl;
        }
    }
}

void vectorPrint(vector<vector<string>>& nums) {
    for (size_t i = 0; i < nums.size(); i++) {
        vectorPrint(nums[i]);
    }
}

#pragma mark - char

void vectorPrint(vector<char>& chars) {
    size_t size = chars.size();
    for (size_t i = 0; i < size; i++) {
        if (i < size - 1) {
            cout << chars[i] << ", ";
        } else {
            cout << chars[i] << endl;
        }
    }
}

void vectorPrint(vector<vector<char>>& chars) {
    for (size_t i = 0; i < chars.size(); i++) {
        vectorPrint(chars[i]);
    }
}
