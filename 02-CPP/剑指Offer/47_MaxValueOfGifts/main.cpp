//
//  main.cpp
//  47_MaxValueOfGifts
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题47:礼物的最大价值
 题目：在一个 m * n 的棋盘的每一格都放有一个礼物🎁，每个礼物都有一定的价值（价值大于0）。
 你可以从棋盘的左上角开始开始拿格子里的礼物，并每次向下或向右移动一格，直到到达棋盘的右下角
 。给定一个棋盘及其上
 面的礼物，请计算你最多能拿到多少价值的礼物？
 */

/**
 分析：这是一个动态规划解决的典型问题。递归思路分析：定义函数 f(i, j) 表示到达格子 (i, j)
 时能拿到的礼物总和最大值。根据题目要求，我们有两种方式到达坐标为 (i, j) 的格子：通过格子
 (i - 1, j) 或 (i, j - 1)。所以 f(i, j) = max(f(i - 1, j), f(i, j - 1)) + gift[i, j]，
 gift[i, j] 表示坐标为 (i, j) 的格子中礼物的价值。
    尽管我们用递归来分析问题，但由于有大量重复的计算，导致递归的代码并不是最优的。相对而言，
 基于循环的代码效率要高很多。为了缓存中间计算结果，我们需要一个辅助的二位数组。数组中坐标为 (i, j)
 的元素表示到达坐标为 (i, j) 的格子时能拿到的礼物价值总和的最大值。
 */

#include <iostream>

using namespace std;

int GetMaxValue_Solution1(const int *values, int rows, int cols) {
    if (values == nullptr || rows <= 0 || cols <= 0) {
        return 0;
    }
    
    // 二维数组，用来存放到达坐标为 (i, j) 的格子时能拿到的礼物价值总和的最大值。
    int **maxValues = new int*[rows];
    for (int i = 0; i < rows; i++) {
        maxValues[i] = new int[cols];
    }
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int left = 0;
            int up = 0;
            
            if (i > 0) {
                up = maxValues[i - 1][j];
            }
            
            if (j > 0) {
                left = maxValues[i][j - 1];
            }
            
            maxValues[i][j] = max(left, up) + values[i * cols + j];
        }
    }
    
    int maxValue = maxValues[rows - 1][cols - 1];
    
    for (int i = 0; i < rows; i++) {
        delete[] maxValues[i];
    }
    delete [] maxValues;
    
    return maxValue;
}

/**
 优化：前面分析我们知道，到达坐标为 (i, j) 的格子时能够拿到的礼物的最大价值只依赖坐标为 (i - 1, j)
 和 (i, j - 1) 的两个格子，因此第 i - 2 行及更上面的所有格子礼物的最大价值实际上没有必要保存下来。
    我们可以用一个一维数组来替代前面代码中的二位矩阵 maxValues。该一维数组的长度为棋盘的列数 n。当我
 们计算到达坐标为 (i, j) 的格子时能够拿到的礼物的最大价值 f(i, j)，数组中前 j 个数字分别是 f(i, 0),
 f(i, 1),...,f(i, j - 1)，数组从下标为  j 的数字开始到最后一个数字，分别是 f(i - 1, j), f(i - 1, j + 1)
 ,...,f(i - 1, n - 1)。该数组前面 j 个数字分别是当前 i 行前面 j 个格子的礼物的最大值，而后面的数字
 分别保存前面第 i - 1 行 n - j 个格子礼物的最大值。
 */

int GetMaxValue_Solution2(const int *values, int rows, int cols) {
    if (values == nullptr || rows <= 0 || cols <= 0) {
        return 0;
    }
    
    int *maxValues = new int[cols];
    
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            int left = 0;
            int up = 0;
            
            if (i > 0) {
                up = maxValues[j];
            }
            
            if (j > 0) {
                left = maxValues[j - 1];
            }
            
            maxValues[j] = max(left, up) + values[i * cols + j];
        }
    }
    
    int maxValue = maxValues[cols - 1];
    
    delete [] maxValues;
    
    return maxValue;
}

// ====================测试代码====================
void test(const char* testName, const int* values, int rows, int cols, int expected)
{
    if(GetMaxValue_Solution1(values, rows, cols) == expected)
        std::cout << testName << ": solution1 passed." << std::endl;
    else
        std::cout << testName << ": solution1 FAILED." << std::endl;

    if(GetMaxValue_Solution2(values, rows, cols) == expected)
        std::cout << testName << ": solution2 passed." << std::endl;
    else
        std::cout << testName << ": solution2 FAILED." << std::endl;
}

void test1()
{
    // 三行三列
    int values[][3] = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 9 }
    };
    int expected = 29;
    test("test1", (const int*) values, 3, 3, expected);
}

void test2()
{
    //四行四列
    int values[][4] = {
        { 1, 10, 3, 8 },
        { 12, 2, 9, 6 },
        { 5, 7, 4, 11 },
        { 3, 7, 16, 5 }
    };
    int expected = 53;
    test("test2", (const int*) values, 4, 4, expected);
}

void test3()
{
    // 一行四列
    int values[][4] = {
        { 1, 10, 3, 8 }
    };
    int expected = 22;
    test("test3", (const int*) values, 1, 4, expected);
}

void test4()
{
    int values[4][1] = {
        { 1 },
        { 12 },
        { 5 },
        { 3 }
    };
    int expected = 21;
    test("test4", (const int*) values, 4, 1, expected);
}

void test5()
{
    // 一行一列
    int values[][1] = {
        { 3 }
    };
    int expected = 3;
    test("test5", (const int*) values, 1, 1, expected);
}

void test6()
{
    // 空指针
    int expected = 0;
    test("test6", nullptr, 0, 0, expected);
}

int main(int argc, const char * argv[]) {
    test1();
    test2();
    test3();
    test4();
    test5();
    test6();
    
    return 0;
}
