//
//  CMyString.hpp
//  01_AssignmentOperator
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef CMyString_hpp
#define CMyString_hpp

#include <stdio.h>

class CMyString
{
public:
    CMyString(char *pData = nullptr);
    CMyString(const CMyString &str);
    ~CMyString(void);
    
    CMyString & operator =(const CMyString &str);
    
    void Print();
private:
    char *m_data;
};

#endif /* CMyString_hpp */
