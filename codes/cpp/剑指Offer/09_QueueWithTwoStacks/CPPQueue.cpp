//
//  CPPQueue.cpp
//  09_QueueWithTwoStacks
//
//  Created by LiLe on 2020/2/29.
//  Copyright © 2020 lile. All rights reserved.
//

#include "CPPQueue.hpp"

template <typename T>
CPPQueue<T>::CPPQueue(void)
{
    
}

template <typename T>
CPPQueue<T>::~CPPQueue(void)
{
    
}

template <typename T>
void CPPQueue<T>::appendTail(const T & element)
{
    stack1.push(element);
}

template <typename T>
T CPPQueue<T>::deleteHead()
{
    if (stack2.size() <= 0) {
        while (stack1.size() > 0) {
            T & data = stack1.top();
            stack1.pop();
            stack2.push(data);
        }
    }
    
    if (stack2.size() == 0) {
        throw new exception();
    }
    
    T head = stack2.top();
    stack2.pop();

    return head;
}
