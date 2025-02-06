//
//  _0739_每日温度.cpp
//  Leetcode
//
//  Created by LiLe on 2021/7/22.
//

#include "_0739_每日温度.hpp"
#include <stack>

/**
 利用栈求右边第一个比它大的数
 */
vector<int> dailyTemperatures(vector<int>& temperatures) {
    vector<int> result(temperatures.size());
    stack<int> st;
    for (int i = 0; i < temperatures.size(); i++) {
        while (!st.empty() && temperatures[i] > temperatures[st.top()]) {
            result[st.top()] = i - st.top();
            st.pop();
        }
        st.push(i);
    }
    
    return result;
}

/**
 * 倒推法
 *
 * i 用来扫描所有的元素，从右往左扫描（i逐渐递减），一开始 i 指向倒数第 2 个元素
 * 对于每一个 i，一开始令 j = i + 1
 * 1> 如果 T[i] < T[j]，那么 values[i] = j - i，然后 i--
 * 2> 如果 values[j] == 0，那么 values[i] = 0，然后 i--
 * 3> 否则，设置 j = j + values[j]，回到步骤 1
 */
vector<int> dailyTemperatures2(vector<int>& temperatures) {
    vector<int> result(temperatures.size());
    for (int i = temperatures.size() - 2; i >= 0; i--) {
        int j = i + 1;
        while (true) {
            if (temperatures[i] < temperatures[j]) {
                result[i] = j - i;
                break;
            } else if (0 == result[j]) {
                result[i] = 0;
                break;
            }
            
            j = j + result[j];
        }
    }
    return result;
}

