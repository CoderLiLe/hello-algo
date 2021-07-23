//
//  _0239_滑动窗口最大值.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/22.
//

#ifndef _0239_________hpp
#define _0239_________hpp

#include <stdio.h>
#include <vector>
using namespace std;

/**
 方法一：优先队列
 */
vector<int> maxSlidingWindow1(vector<int>& nums, int k);

/**
 方法二：单调队列
 */
vector<int> maxSlidingWindow2(vector<int>& nums, int k);

vector<int> maxSlidingWindow3(vector<int>& nums, int k);

#endif /* _0239_________hpp */
