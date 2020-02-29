//
//  main.cpp
//  56_01_NumbersAppearOnce
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题56:数组中数字出现的次数
 题目一：数组中只出现一次的两个数字。
    一个整型数组里除两个数字之外，其它数字都出现了两次。请编写程序找出这两个只出现一次的数字。要求时间复杂度是 O(n)，空间复杂度是 O(1)。
 */

/**
 异或操作：任何一个数字异或它自己等于0.
 
 字(word) = 2字节(byte) = 2 * 8 位(bit)
 1KB = 2^10 Bytes
 1MB = 2^10 KB
 1GB = 2^10 MB
 
 一个字的长度为16
 一个字节的长度为8
 bit 是电脑记忆体中最小的单位，在二进制电脑系统中，每一 bit 可以代表 0 或者 1 的数位讯号。
 */

#include <iostream>

using namespace std;

unsigned int FindFirstBitIs1(int num);
bool IsBit1(int num, unsigned int indexBit);

void FindNumsAppearOnce(int data[], int length, int *num1, int *num2) {
    if (data == nullptr || length < 2) {
        return;
    }
    
    int resultExclusiveOR = 0;
    for (int i = 0; i < length; i++) {
        resultExclusiveOR ^= data[i];
    }
    
    unsigned int indexOf1 = FindFirstBitIs1(resultExclusiveOR);
    
    *num1 = *num2 = 0;
    for (int i = 0; i < length; i++) {
        if (IsBit1(data[i], indexOf1)) {
            *num1 ^= data[i];
        } else {
            *num2 ^= data[i];
        }
    }
}

unsigned int FindFirstBitIs1(int num) {
    int indexBit = 0;
    while (((num & 1) == 0) && (indexBit < 8 * sizeof(int))) {
        num = num >> 1;
        ++indexBit;
    }
    
    return indexBit;
}

bool IsBit1(int num, unsigned int indexBit) {
    num = num >> indexBit;
    return (num & 1);
}

// ====================测试代码====================
void Test(const char* testName, int data[], int length, int expected1, int expected2)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    int result1, result2;
    FindNumsAppearOnce(data, length, &result1, &result2);

    if((expected1 == result1 && expected2 == result2) ||
        (expected2 == result1 && expected1 == result2))
        printf("Passed.\n");
    else
        printf("Failed.\n");
}

void Test1()
{
    int data[] = {2, 4, 3, 6, 3, 2, 5, 5};
    Test("Test1", data, sizeof(data) / sizeof(int), 4, 6);
    
    /**
     2 = 0010  4 = 0100  3 = 0011  6 = 0110  3 = 0011  2 = 0010  5 = 0101  5 = 0101
     
     0000        0010      0110      0101      0011      0000      0010      0111
   ^ 0010      ^ 0100    ^ 0011    ^ 0110    ^ 0011    ^ 0010    ^ 0101    ^ 0101
  --------     -------   -------  -------   --------   -------   -------   -------
     0010        0110      0101      0011      0000      0010      0111      0010
     
     依据二进制数倒数第二位是否为1分为两组，分别异或
     
     0010     0001
   & 0010   & 0001
  -------- --------
     0010     0001
     
     {2, 3, 6, 3, 2}
   
       2           3         6         3         2
     
      0000        0010      0001      0111      0100
    ^ 0010      ^ 0011    ^ 0110    ^ 0011    ^ 0010
   --------     -------   -------  -------   --------
      0010        0001      0111      0100      0110
     
     
     {4, 5, 5}
   
        4           5         5
       
      0000        0100      0001
    ^ 0100      ^ 0101    ^ 0101
   --------     -------   -------
      0100        0001      0100
     */
}

void Test2()
{
    int data[] = {4, 6};
    Test("Test2", data, sizeof(data) / sizeof(int), 4, 6);
}

void Test3()
{
    int data[] = {4, 6, 1, 1, 1, 1};
    Test("Test3", data, sizeof(data) / sizeof(int), 4, 6);
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    
    return 0;
}
