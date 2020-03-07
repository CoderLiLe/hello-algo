//
//  main.cpp
//  06_PrintListInReversedOrder
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题6：从尾到头打印链表
 题目：输入一个链表的头结点，从尾到头反过来打印出每个结点的值。
 */

#include <iostream>
#include "List.hpp"
#include <stack>

using namespace std;

void PrintListReversedOrder_iteratively(ListNode *pHead) {
    stack<ListNode *> nodes;
    
    ListNode *pNode = pHead;
    while (pNode != nullptr) {
        nodes.push(pNode);
        pNode = pNode->m_pNext;
    }
    
    while (!nodes.empty()) {
        pNode = nodes.top();
        cout << pNode->m_nValue << " ";
        nodes.pop();
    }
    
    cout << endl;
}

void PrintListInReversedOrder_recursively(ListNode *pHead) {
    if (pHead != nullptr) {
        if (pHead->m_pNext != nullptr) {
            PrintListInReversedOrder_recursively(pHead->m_pNext);
        }
        
        cout << pHead->m_nValue << " ";
    }
}

// ====================测试代码====================
void Test(ListNode* pHead)
{
    PrintList(pHead);
    PrintListReversedOrder_iteratively(pHead);
    PrintListInReversedOrder_recursively(pHead);
    cout << endl;
}

// 1->2->3->4->5
void Test1()
{
    cout << "------------ Test1 ------------" << endl;

    ListNode* pNode1 = CreateListNode(1);
    ListNode* pNode2 = CreateListNode(2);
    ListNode* pNode3 = CreateListNode(3);
    ListNode* pNode4 = CreateListNode(4);
    ListNode* pNode5 = CreateListNode(5);

    ConnectListNodes(pNode1, pNode2);
    ConnectListNodes(pNode2, pNode3);
    ConnectListNodes(pNode3, pNode4);
    ConnectListNodes(pNode4, pNode5);

    Test(pNode1);

    DestroyList(pNode1);
}

// 只有一个结点的链表: 1
void Test2()
{
    cout << "------------ Test2 ------------" << endl;

    ListNode* pNode1 = CreateListNode(1);

    Test(pNode1);

    DestroyList(pNode1);
}

// 空链表
void Test3()
{
    cout << "------------ Test3 ------------" << endl;
    Test(nullptr);
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    
    return 0;
}
