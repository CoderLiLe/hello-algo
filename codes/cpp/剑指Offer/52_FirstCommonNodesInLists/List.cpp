//
//  List.cpp
//  52_FirstCommonNodesInLists
//
//  Created by LiLe on 2020/2/28.
//  Copyright © 2020 lile. All rights reserved.
//

#include "List.hpp"
#include <iostream>

using namespace std;

ListNode *CreateListNode(int value)
{
    ListNode *pNode = new ListNode();
    pNode->value = value;
    pNode->next = nullptr;
    return pNode;
}

void ConnectListNodes(ListNode *pCurrent, ListNode *pNext)
{
    if (pCurrent == nullptr) {
        cout << "Error to connect two nodes." << endl;
        exit(1);
    }
    
    pCurrent->next = pNext;
}

void PrintListNode(ListNode *pNode)
{
    if (pNode == nullptr) {
        cout << "The node is nullptr." << endl;
    } else {
        cout << "The value in node is " + pNode->value << endl;
    }
}

void PrintList(ListNode *pHead)
{
    cout << "------- PrintList start -------" << endl;
    
    ListNode *node = pHead;
    while (node != nullptr) {
        cout << node->value << "  ";
        node = node->next;
    }
    cout << "------- PrintList end -------" << endl;
}

void DestroyList(ListNode *pHead)
{
    ListNode *node = pHead;
    while (pHead != nullptr) {
        pHead = pHead->next;
        delete node;
        node = pHead;
    }
}

void AddToTail(ListNode **pHead, int value)
{
    ListNode *newNode = new ListNode();
    newNode->value = value;
    newNode->next = nullptr;
    
    if (*pHead == nullptr) {
        *pHead = newNode;
    } else {
        ListNode *node = *pHead;
        while (node->next != nullptr) {
            node = node->next;
        }
        node->next = newNode;
    }
}

void RemoveNode(ListNode **pHead, int value)
{
    if (pHead == nullptr || *pHead == nullptr) {
        return;
    }
    
    ListNode *willDeleteNode = nullptr;
    if ((*pHead)->value == value) {
        willDeleteNode = *pHead;
        *pHead = (*pHead)->next;
    } else {
        ListNode *node = *pHead;
        while (node->next != nullptr && node->next->value != value) {
            node = node->next;
        }
        
        if (node->next != nullptr && node->next->value == value) {
            willDeleteNode = node->next;
            node->next = node->next->next;
        }
    }
    
    if (willDeleteNode != nullptr) {
        delete willDeleteNode;
        willDeleteNode = nullptr;
    }
}
