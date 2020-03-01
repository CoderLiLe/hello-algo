//
//  CPPStack.hpp
//  09_QueueWithTwoStacks
//
//  Created by LiLe on 2020/3/1.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef CPPStack_hpp
#define CPPStack_hpp

#include <stdio.h>
#include <queue>

using namespace std;

template <typename T>
class CPPStack {
    
public:
    CPPStack();
    ~CPPStack();
    
    void push(const T & element);
    void pop();
    T top();
private:
    queue<T> queue1;
    queue<T> queue2;
};


#endif /* CPPStack_hpp */
