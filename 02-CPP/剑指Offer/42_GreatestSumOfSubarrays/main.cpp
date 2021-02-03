//
//  main.cpp
//  42_GreatestSumOfSubarrays
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

#include <iostream>

using namespace std;

bool g_InvalidInput = false;

int FindGreatestSumOfSubArray(int *pData, int nLength) {
    if (pData == nullptr || nLength <= 0) {
        g_InvalidInput = true;
        return 0;
    }
    
    g_InvalidInput = false;
    
    int nCurSum = 0;
    int nGreatestsum = 0x80000000;
    for (int i = 0; i < nLength; i++) {
        if (nCurSum < 0) {
            nCurSum = pData[i];
        } else {
            nCurSum += pData[i];
        }
        
        if (nCurSum > nGreatestsum) {
            nGreatestsum = nCurSum;
        }
    }
    
    return nGreatestsum;
}

void Test(char* testName, int *pData, int nLength, int expected, bool expectedFlag) {
    if (testName != nullptr) {
        cout << "------------ " << testName << "begin -------------" << endl;
    } else {
        cout << "------------ begin -------------" << endl;
    }
    
    int result = FindGreatestSumOfSubArray(pData, nLength);
    if (result == expected && expectedFlag == g_InvalidInput) {
        cout << "Passed" << endl;
    } else {
        cout << "Failed" << endl;
    }
    cout << "------------- end ------------" << endl;
}

void Test1() {
    int data[] = {1, -2, 3, 10, -4, 7, 2, -5};
    Test("Test1", data, sizeof(data) / sizeof(int), 18, false);
}

void Test2() {
    int data[] = {-2, -8, -1, -5, -9};
    Test("Test2", data, sizeof(data) / sizeof(int), -1, false);
}

void Test3() {
    int data[] = {2, 8, 1, 5, 9};
    Test("Test3", data, sizeof(data) / sizeof(int), 25, true);
}

void Test4() {
    int data[] = {2, 8, 1, 5, 9};
    Test("Test4", nullptr, 0, 0, false);
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    return 0;
}
