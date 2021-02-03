//
//  StackWithMin.hpp
//  30_MinInStack
//
//  Created by LiLe on 2020/3/1.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef StackWithMin_hpp
#define StackWithMin_hpp

#include <stdio.h>
#include <stack>

using namespace std;

template <typename T>
class StackWithMin {
public:
    StackWithMin();
    ~StackWithMin();
    
    void push(const T & value);
    void pop();
    const T & min() const;
    T& top();
    const T& top() const;
    bool empty() const;
    size_t size() const;
private:
    stack<T> m_data; // 数据栈
    stack<T> m_min; // 辅助栈
};

#endif /* StackWithMin_hpp */
