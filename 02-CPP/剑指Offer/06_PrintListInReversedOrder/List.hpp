//
//  List.hpp
//  06_PrintListInReversedOrder
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef List_hpp
#define List_hpp

#include <stdio.h>

struct ListNode
{
    int       m_nValue;
    ListNode* m_pNext;
};

ListNode* CreateListNode(int value);
void ConnectListNodes(ListNode* pCurrent, ListNode* pNext);
void PrintListNode(ListNode* pNode);
void PrintList(ListNode* pHead);
void DestroyList(ListNode* pHead);
void AddToTail(ListNode** pHead, int value);
void RemoveNode(ListNode** pHead, int value);

#endif /* List_hpp */
