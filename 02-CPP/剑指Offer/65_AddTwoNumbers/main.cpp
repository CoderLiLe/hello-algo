//
//  main.cpp
//  65_AddTwoNumbers
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题65：不用加减乘除做加法
 题目：写一个函数，求两个整数之和，要求在函数体内不得使用＋、－、×、÷ 四则运算符号。
 */

/**
 分析：
 
 5   + 17    = 22
 101 + 10001 = 10110
 
 步骤                十进制加法       二进制      位运算
 1> 各位相加不进位        12          10100       异或
 2> 进位                10             10        与
 3> 前两个结果相加   12 + 10 = 22     10110        与
 */

#include <iostream>

int Add(int num1, int num2)
{
    int sum, carry;
    do {
        // 各位相加不进位，异或
        sum = num1 ^ num2;
        // 进位，与
        carry = (num1 & num2) << 1;

        num1 = sum;
        num2 = carry;
    } while(num2 != 0);

    return num1;
}

// ====================测试代码====================
void Test(int num1, int num2, int expected)
{
    int result = Add(num1, num2);
    if(result == expected)
        printf("%d + %d is %d. passed\n", num1, num2, result);
    else
        printf("%d + %d is %d. failed\n", num1, num2, result);
}

int main(int argc, const char * argv[]) {
    Test(1, 2, 3);
    Test(5, 17, 22);
    Test(111, 899, 1010);

    Test(-1, 2, 1);
    Test(1, -2, -1);

    Test(3, 0, 3);
    Test(0, -4, -4);

    Test(-2, -8, -10);
    
    return 0;
}
