//
//  main.cpp
//  66_ConstuctArray
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题66：构建乘积数组
 题目：给定一个数组A[0, 1, ⋯, n-1]，请构建一个数组B[0, 1, ⋯, n-1]，其
 中B中的元素B[i] =A[0]×A[1]×⋯ ×A[i-1]×A[i+1]×⋯×A[n-1]。不能使用除法。
 */

/**
 分析：
 
 那么对于新数组ans[i]，我们从前往后遍历，可求得了A[1]A[2]...*A[i-1]，
 然后我们再从尾到头扫描一遍，对于当前第i位，我们任然按照前面的思想，累乘A[len-1]*...A[i+1]
 
 
 例如：A[]={1,2,3}求B[]

 B[0]=A[1]×A[2]=2×3=6

 B[1]=A[0]×A[2]=1×3=3

 B[2]=A[0]×A[1]=1×2=2
 
 
 1> B[0]初始化为1，从下标i=1开始，先求出C[i]的值并放入B[i]，即B[i]=C[i]=C[i-1]×A[i-1]，所以
 B[1]=B[1-1]×A[1-1]=B[0]×A[0]=1×1=1,i++

 2> B[2]=B[2-1]×A[2-1]=B[1]×A[1]=1×2=2,i++超出长度停止循环

 3> C[i]计算完毕求D[i],设置一个临时变量temp初始化为1

 4> 从后往前变量数组，LengthA=3初始化i=LengthA-2=1,结束条件为i>=0

 5> 第一次循环，temp=temp×A[i+1]=1×A[2]=3,计算出A中最后一个元素的值放入temp,temp相当于D[i]的值

 6> 因为之前的B[i]=C[i],所以让B[i]×D[i]就是要保存的结果，即B[i]=B[1]=B[1]×temp=1×3=3,i–=0

 7> 计算B[i]=B[0],temp上一步中的值是A[2],在这次循环中temp=temp×A[0+1]=A[2]×A[1]=3×2=6

 8> B[i]=B[0]=B0]×temp=1×6=6,i–<0循环结束
 */

#include <iostream>
#include <vector>

using namespace std;

// T = O(n)
void BuildProductionArray(const vector<double>& array1, vector<double>& array2) {
    int length1 = (int)array1.size();
    int length2 = (int)array2.size();

    if (length1 == length2 && length2 > 1) {
        array2[0] = 1;
        for (int i = 1; i < length1; ++i) {
            array2[i] = array2[i-1] * array1[i-1];
        }

        double temp = 1;
        for (int i = length1 - 2; i >= 0; --i) {
            temp *= array1[i];
            array2[i] *= temp;
        }
    }
}

//================= Test Code =================
bool EqualArrays(const vector<double>& input, const vector<double>& output)
{
    int length1 = (int)input.size();
    int length2 = (int)output.size();

    if(length1 != length2)
        return false;

    for(int i = 0; i < length1; ++i)
    {
        double distance = input[i] - output[i];
        if (distance > 0.0000001 || distance < -0.0000001) {
            return false;
        }
    }

    return true;
}

void Test(const char * testName, const vector<double> input, vector<double> output, const vector<double> expected)
{
    if (testName != nullptr) {
        cout << "----------------- " << testName << " -----------------" << endl;
    }

    BuildProductionArray(input, output);
    if (EqualArrays(output, expected)) {
        cout << " passed" << endl;
    } else {
        cout << " failed" << endl;
    }
}

void Test(const char * testName, double *input, double *output, double *expected) {
    Test(testName,
         vector<double>(input, input + sizeof(input) / sizeof(double)),
         vector<double>(output, output + sizeof(output) / sizeof(double)),
         vector<double>(expected, expected + sizeof(expected) / sizeof(double)));
}

void test1()
{
    // 输入数组中没有0
    double input[] = { 1, 2, 3, 4, 5 };
    double output[] = { 0, 0, 0, 0, 0 };
    double expected[] = { 120, 60, 40, 30, 24 };
    Test("Test1", input, output, expected);
}

void test2()
{
    // 输入数组中有一个0
    double input[] = { 1, 2, 0, 4, 5 };
    double output[] = { 0, 0, 0, 0, 0 };
    double expected[] = { 0, 0, 40, 0, 0 };
    Test("Test2", input, output, expected);
}

void test3()
{
    // 输入数组中有两个0
    double input[] = { 1, 2, 0, 4, 0 };
    double output[] = { 0, 0, 0, 0, 0 };
    double expected[] = { 0, 0, 0, 0, 0 };
    Test("Test3", input, output, expected);
}

void test4()
{
    // 输入数组中有正、负数
    double input[] = { 1, -2, 3, -4, 5 };
    double output[] = { 0, 0, 0, 0, 0 };
    double expected[] = { 120, -60, 40, -30, 24 };
    Test("Test4", input, output, expected);
}

void test5()
{
    // 输入输入中只有两个数字
    double input[] = { 1, -2 };
    double output[] = { 0, 0 };
    double expected[] = { -2, 1 };
    Test("Test5", input, output, expected);
}

void test_build_production_array() {
    test1();
    test2();
    test3();
    test4();
    test5();
}

//------------------------------------------

class Solution
{
public:
    static vector<int> multiply(const vector<int>& A)
    {
        int n = (int)A.size();
        vector<int> res(n);
        // C[i] = A[0] * A[1] * ... *A[i - 1]
        for(int i = 0, temp = 1; i < n; i++)
        {
            res[i] = temp;
            temp *= A[i];
        }

        // D[i] = A[i + 1] * A[i + 2] * ... *A[n - 1]
        for(int i = n - 1, temp = 1; i >= 0; i--)
        {
            res[i] *= temp;
            temp *= A[i];

        }
        return res;
    }
};

void test_multiply() {
    int input[] = {1, 2, 3};
//    int input[] = {1, 2, 3, 4, 5};
    
    vector<int> res = Solution::multiply(vector<int>(input, input + sizeof(input) / sizeof(int)));
    for (unsigned int i = 0; i < res.size(); i++) {
        cout << res[i] << " ";
    }
    cout << endl;
}

int main(int argc, const char * argv[]) {
//    test_multiply();
    test_build_production_array();
    return 0;
}
