//
//  CPPQueue.hpp
//  09_QueueWithTwoStacks
//
//  Created by LiLe on 2020/2/29.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef CPPQueue_hpp
#define CPPQueue_hpp

#include <stdio.h>
#include <stack>

using namespace std;

template <typename T>
class CPPQueue {
public:
    CPPQueue(void);
    ~CPPQueue(void);
    
    void appendTail(const T & element);
    T deleteHead();
private:
    stack<T> stack1;
    stack<T> stack2;
};

#endif /* Queue_hpp */
