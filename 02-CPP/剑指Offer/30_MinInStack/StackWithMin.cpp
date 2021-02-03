//
//  StackWithMin.cpp
//  30_MinInStack
//
//  Created by LiLe on 2020/3/1.
//  Copyright © 2020 lile. All rights reserved.
//

#include "StackWithMin.hpp"
#include <assert.h>

template <typename T>
StackWithMin<T>::StackWithMin()
{
    
}

template <typename T>
StackWithMin<T>::~StackWithMin()
{
    
}

template <typename T>
void StackWithMin<T>::push(const T & value)
{
    m_data.push(value);
    
    if (m_min.size() == 0 || value < m_min.top()) {
        m_min.push(value);
    } else {
        m_min.push(m_min.top());
    }
}

template <typename T>
void StackWithMin<T>::pop()
{
    assert(m_data.size() > 0 && m_min.size() > 0);
    
    m_data.pop();
    m_min.pop();
}

template <typename T>
const T & StackWithMin<T>::min() const
{
    assert(m_data.size() > 0 && m_min.size() > 0);
    
    return m_min.top();
}

template <typename T>
T& StackWithMin<T>::top()
{
    return m_data.top();
}

template <typename T>
const T& StackWithMin<T>::top() const
{
    return m_data.top();
}

template <typename T>
bool StackWithMin<T>::empty() const
{
    return m_data.empty();
}

template <typename T>
size_t StackWithMin<T>::size() const
{
    return m_data.size();
}
