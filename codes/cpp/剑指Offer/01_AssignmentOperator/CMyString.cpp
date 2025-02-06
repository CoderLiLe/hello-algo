//
//  CMyString.cpp
//  01_AssignmentOperator
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

/**
需要关注的4点：
1. 是否把返回值的类型声明为该类型的引用，并在函数结束前返回实例自身的引用(*this)。只有返回一个引用，才可以连续赋值。
2. 是否把传入的参数声明为常量引用。
3. 是否释放自身已有的内存。
4. 判断传入的参数和当前实例是不是同一个实例。
*/

#include "CMyString.hpp"
#include <string.h>
#include <iostream>

using namespace std;

CMyString::CMyString(char *pData)
{
    if (pData == nullptr) {
        m_data = new char[1];
        m_data[0] = '\0';
    } else {
        size_t len = strlen(pData);
        m_data = new char[len + 1];
        strcpy(m_data, pData);
    }
}

CMyString::CMyString(const CMyString &str)
{
    size_t len = strlen(str.m_data);
    m_data = new char[len + 1];
    strcpy(m_data, str.m_data);
}

CMyString::~CMyString(void)
{
    delete [] m_data;
}

CMyString & CMyString::operator =(const CMyString &str)
{
    if (this == &str) return *this;
    
    delete [] m_data;
    m_data = nullptr;
    
    m_data = new char[strlen(str.m_data) + 1];
    strcpy(m_data, str.m_data);
    
    return *this;
}

void CMyString::Print()
{
    cout << m_data << endl;
}
