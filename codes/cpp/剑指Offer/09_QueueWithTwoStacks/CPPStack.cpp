//
//  CPPStack.cpp
//  09_QueueWithTwoStacks
//
//  Created by LiLe on 2020/3/1.
//  Copyright © 2020 lile. All rights reserved.
//

#include "CPPStack.hpp"

template <typename T>
CPPStack<T>::CPPStack()
{
    
}

template <typename T>
CPPStack<T>::~CPPStack()
{
    
}

template <typename T>
void CPPStack<T>::push(const T & element)
{
    queue1.push(element);
}

template <typename T>
void CPPStack<T>::pop()
{
    if (queue2.size() == 0) {
        if (queue1.size() > 1) {
            do {
                T element = queue1.front();
                queue2.push(element);
                queue1.pop();
            } while (queue1.size() > 1);
        }
        
        if (queue1.size() == 1) {
            queue1.pop();
        }
    } else { // queue1.size() == 0
        if (queue2.size() > 1) {
            do {
                T element = queue2.front();
                queue1.push(element);
                queue2.pop();
            } while (queue2.size() > 1);
        }
        
        if (queue2.size() == 1) {
            queue2.pop();
        }
    }
    
}

template <typename T>
T CPPStack<T>::top()
{
    if (queue2.size() == 0) {
        return queue1.back();
    } else if (queue1.size() == 0) {
        return queue2.back();
    } else {
        return NULL;
    }
}
