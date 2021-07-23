//
//  栈队列测试.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/22.
//

#include "栈队列测试.hpp"
#include <iostream>

#include "_0239_滑动窗口最大值.hpp"
#include "_0739_每日温度.hpp"

using namespace std;

void test0239() {
    vector<int> nums = {1,3,-1,-3,5,3,6,7};
    vector<int> result = maxSlidingWindow1(nums, 3);
    for (auto val : result) {
        cout << val << " ";
    }
    cout << endl;
    
    vector<int> result2 = maxSlidingWindow2(nums, 3);
    for (auto val : result2) {
        cout << val << " ";
    }
    cout << endl;
    
    vector<int> result3 = maxSlidingWindow3(nums, 3);
    for (auto val : result2) {
        cout << val << " ";
    }
    cout << endl;
}

void test0739() {
    vector<int> temperatures = {73,74,75,71,69,72,76,73};
    vector<int> result = dailyTemperatures(temperatures);
    for (auto val : result) {
        cout << val << " ";
    }
    cout << endl;
    
    vector<int> result2 = dailyTemperatures2(temperatures);
    for (auto val : result2) {
        cout << val << " ";
    }
    cout << endl;
}

void stackQueueTest() {
    test0239();
//    test0739();
}
