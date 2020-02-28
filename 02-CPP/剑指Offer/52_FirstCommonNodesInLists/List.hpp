//
//  List.hpp
//  52_FirstCommonNodesInLists
//
//  Created by LiLe on 2020/2/28.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef List_hpp
#define List_hpp

#include <stdio.h>

struct ListNode
{
   int value;
   ListNode *next;
};

ListNode *CreateListNode(int value);
void ConnectListNodes(ListNode *pCurrent, ListNode *pNext);
void PrintListNode(ListNode *pNode);
void PrintList(ListNode *pHead);
void DestroyList(ListNode *pHead);
void AddToTail(ListNode **pHead, int value);
void RemoveNode(ListNode **pHead, int value);

#endif /* List_hpp */
