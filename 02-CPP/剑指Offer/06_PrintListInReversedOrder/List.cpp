//
//  List.cpp
//  06_PrintListInReversedOrder
//
//  Created by LiLe on 2020/3/7.
//  Copyright © 2020 lile. All rights reserved.
//

#include "List.hpp"
#include <iostream>

using namespace std;

ListNode* CreateListNode(int value)
{
    ListNode *node = new ListNode();
    node->m_nValue = value;
    node->m_pNext = nullptr;
    return node;
}

void ConnectListNodes(ListNode* pCurrent, ListNode* pNext)
{
    if (pCurrent == nullptr) {
        cout << "Error to connect two nodes." << endl;
        exit(1);
    }
    pCurrent->m_pNext = pNext;
}

void PrintListNode(ListNode* pNode)
{
    if(pNode == nullptr)
    {
        cout << "The node is nullptr" << endl;
    } else {
        cout << "The key in node is " << pNode->m_nValue << endl;
    }
}

void PrintList(ListNode* pHead)
{
    ListNode *node = pHead;
    while (node != nullptr) {
        PrintListNode(node);
        node = node->m_pNext;
    }
}

void DestroyList(ListNode* pHead)
{
    ListNode *pNode = pHead;
    while (pNode != nullptr) {
        pHead = pNode->m_pNext;
        delete pNode;
        pNode = pHead;
    }
}

void AddToTail(ListNode** pHead, int value)
{
    ListNode *pNew = new ListNode();
    pNew->m_nValue = value;
    pNew->m_pNext = nullptr;
    
    if (*pHead == nullptr) {
        *pHead = pNew;
    } else {
        ListNode *pNode = *pHead;
        while (pNode->m_pNext != nullptr) {
            pNode = pNode->m_pNext;
        }
        
        pNode->m_pNext = pNew;
    }
}

void RemoveNode(ListNode** pHead, int value)
{
    if (pHead == nullptr || *pHead == nullptr) {
        return;
    }
    
    ListNode *pToDeleted = nullptr;
    if ((*pHead)->m_nValue == value) {
        pToDeleted = *pHead;
        *pHead = (*pHead)->m_pNext;
    } else {
        ListNode *pNode = *pHead;
        while (pNode->m_pNext != nullptr && pNode->m_pNext->m_nValue != value) {
            pNode = pNode->m_pNext;
        }
        
        if (pNode->m_pNext != nullptr && pNode->m_pNext->m_nValue == value) {
            pToDeleted = pNode->m_pNext;
            pNode->m_pNext = pNode->m_pNext->m_pNext;
        }
    }
    
    if (pToDeleted != nullptr) {
        delete pToDeleted;
        pToDeleted = nullptr;
    }
}
