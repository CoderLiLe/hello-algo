//
//  main.cpp
//  60_DicesProbability
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题60: n个骰子的点数
 题目：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s
 的所有可能的值出现的概率。
 */

#include <iostream>
#include <math.h>

using namespace std;

#pragma mark - 递归法
/**
 要想求出 n 个骰子的点数和，可以先把 n 个骰子分为两堆，一堆只有一个，另一堆剩下的 n-1 个；然后再对剩下的 n-1
 个骰子像上次一样再分为两堆，第一堆只有一个，另一堆剩下的 n-2 个。把上一轮单独的那个骰子的点数和这一轮单独的那
 个骰子的点数相加，再和剩下的 n-2 个骰子来计算点数和。递归公式：f(n) = f(1) + f(n-1) = f(1) + f(1) + f(n - 2)
 */
int g_maxValue = 6;

void Probability(int number, int *pProbabilities);
void Probability(int original, int current, int sum, int *pProbilities);

void PrintProbability_Solution1(int number) {
    if (number < 1) {
        return;
    }
    
    int maxSum = number * g_maxValue;
    int * pProbabilities = new int[maxSum - number + 1];
    for (int i = number; i <= maxSum; i++) {
        pProbabilities[i - number] = 0;
    }
    
    Probability(number, pProbabilities);
    
    int total = pow((double)g_maxValue, number);
    for (int i = number; i <= maxSum; i++) {
        double ratio = (double)pProbabilities[i - number] / total;
        cout << i << " : " << ratio << endl;
    }
    
    delete[] pProbabilities;
}

void Probability(int number, int *pProbabilities) {
    for (int i = 1; i <= g_maxValue; i++) {
        Probability(number, number, i, pProbabilities);
    }
}

void Probability(int original, int current, int sum, int *pProbilities) {
    if (current == 1) {
        pProbilities[sum-original]++;
    } else {
        for (int i = 1; i <= g_maxValue; i++) {
            Probability(original, current - 1, i + sum, pProbilities);
        }
    }
}

#pragma mark - 循环
void PrintProbability_Solution2(int number) {
    if (number < 1) {
        return;
    }

    int *pProbabilities[2];
    pProbabilities[0] = new int[g_maxValue*number+1];
    pProbabilities[1] = new int[g_maxValue*number+1];
    for (int i = 0; i < g_maxValue*number+1; ++i) {
        pProbabilities[0][i] = 0;
        pProbabilities[1][i] = 0;
    }
    
    int flag = 0;
    for (int i = 1; i <= g_maxValue; i++) {
        pProbabilities[flag][i] = 1;
    }
    
    for (int k = 2; k <= number; ++k) {
        for (int i = 0; i < k; ++i) {
            pProbabilities[1 - flag][i] = 0;
        }
        
        for (int i = k; i <= g_maxValue*k; ++i) {
            pProbabilities[1 - flag][i] = 0;
            for (int j = 1; j <= i && j <= g_maxValue; ++j) {
                pProbabilities[1 - flag][i] += pProbabilities[flag][i-j];
            }
        }
        flag = 1 -flag;
    }
    
    int total = pow((double)g_maxValue, number);
    for (int i = number; i <= g_maxValue * number; i++) {
        double ratio = (double)pProbabilities[flag][i] / total;
        cout << i << " : " << ratio << endl;
    }
    
    delete[] pProbabilities[0];
    delete[] pProbabilities[1];
}
// ====================测试代码====================
void Test(int n)
{
    cout << "**************【" << n << "】**************" << endl;
    
    cout << "------- solution1 -------" << endl;
    PrintProbability_Solution1(n);
    
    cout << "------- solution2 -------" << endl;
    PrintProbability_Solution2(n);

    cout << endl;
}

int main(int argc, const char * argv[]) {
    
    Test(0);
    Test(1);
    Test(2);
    Test(3);
    Test(4);
    Test(11);
    
    return 0;
}
