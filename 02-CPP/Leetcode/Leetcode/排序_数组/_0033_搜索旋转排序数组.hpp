//
//  _0033_搜索旋转排序数组.hpp
//  Leetcode
//
//  Created by LiLe on 2021/7/31.
//

#ifndef _0033__________hpp
#define _0033__________hpp

#include <stdio.h>
#include <vector>
using namespace std;

/**
 暴力法
 */
int searchTarget(vector<int>& nums, int target);

/**
 二分法
 
 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
 
 T = O(logn), S = O(1)
 */
int searchTarget2(vector<int>& nums, int target);

#endif /* _0033__________hpp */
