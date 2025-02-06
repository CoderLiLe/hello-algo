//
//  _0029_两数相除.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/31.
//

#include "_0029_两数相除.hpp"
#include <limits.h>
#include <cstdlib>

/**
 有一种水草每天长一倍，8天能长满一池塘，那么，长满半池塘要多少天？
 答案是7天。
 */

/**
 把 dividend 看成 起点 到 终点 的距离
 把 dividsor 看成 每一步能走的距离
 如果从起点到终点要走 n+1 步，而第 n+1 步就会超过终点，而我们求的就是 n
 */
int divide(int dividend, int divisor) {
    if (dividend == INT_MIN && -1 == divisor) {
        return INT_MAX;
    }
    // terminus：终点，stride：步长
    long terminus = labs(dividend), stride = labs(divisor), res = 0;
    while (stride <= terminus) {
        long span = stride, sub = 1;
        while (terminus >= span << 1) { // 步长按 *2 增长
            span <<= 1, sub <<= 1; // 记录步数增长的倍数
        }
        // 砍掉一半的距离，并记录步数的增长
        terminus -= span, res += sub;
    }
    
    // 返回带符号的值
    return (dividend < 0) ^ (divisor < 0) ? -res : res;
}


