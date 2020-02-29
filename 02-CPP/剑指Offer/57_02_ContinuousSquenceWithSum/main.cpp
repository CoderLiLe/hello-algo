//
//  main.cpp
//  57_02_ContinuousSquenceWithSum
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
面试题57:和为 s 的数字
题目二：和为 s 的连续正数序列
   输入一个正数 s， 打印出所有和为 s 的连续正数序列（至少含有两个）。例如，输入15，由于
 1+2+3+4+5 = 4+5+6 = 7+8 = 15，z所以打印出 3 个连续序列 1 ~ 5, 4 ～ 6，7～8。
*/

/**
 分析：
 用 small 和 big 分别表示序列的最小值和最大值，small ~ big 的序列和记为 curSum，目标和记为 sum,
 初始化 small = 1，big = 2，需要满足：small < (1+sum)/2
 1> curSum == sum，打印序列；big++, curSum += big
 2> curSum > sum，curSum -= small, small++
 3> curSum < sum, big++, curSum += big
 */

#include <iostream>

using namespace std;

void PrintContinuousSequence(int small, int big);

void FindContinuousSequence(int sum) {
    if (sum < 3) {
        return;
    }
    
    int small = 1;
    int big = 2;
    int curSum = small + big;
    int middle = (1 + sum) / 2;
    
    while (small < middle) {
        if (curSum == sum) {
            PrintContinuousSequence(small, big);
            big++;
            curSum += big;
        }
        
        if (curSum > sum) {
            curSum -= small;
            small++;
        }
        
        if (curSum < sum) {
            big++;
            curSum += big;
        }
    }
}

void PrintContinuousSequence(int small, int big) {
    for (int i = small; i <= big; i++) {
        cout << i << " ";
    }
    cout << endl;
}

// ====================测试代码====================
void Test(const char* testName, int sum)
{
    if(testName != nullptr)
        cout << "----------- " << testName << " for " << sum << " ----------" << endl;

    FindContinuousSequence(sum);
}


int main(int argc, const char * argv[]) {
    Test("test1", 1);
    Test("test2", 3);
//    Test("test3", 4);
//    Test("test4", 9);
//    Test("test5", 15);
//    Test("test6", 100);
    
    return 0;
}
