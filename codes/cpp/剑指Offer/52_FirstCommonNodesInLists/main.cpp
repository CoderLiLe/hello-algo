//
//  main.cpp
//  52_FirstCommonNodesInLists
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题51:两个链表的第一个公共节点
 题目：输入两个链表，找出它们的第一个公共节点。链表节点定义如下：
 struct ListNode
 {
    int value;
    ListNode *next;
 }
 */

/**
 分析：
 蛮力法：在第一个链表上顺序遍历每个节点，每遍历到一个节点，就在第二个链表上顺序遍历每个节点。
 如果在第二个链表上有一个节点和第一个链表上的节点一样，则说明这两个链表重合，就找到了它们的公
 共节点。时间复杂度:O(m*n)
 
 方法二：如果两个链表有公共节点，则公共节点出现在两个链表的尾部。如果从两个链表的尾部开始往前
 比较，那么最后一个相同的节点就是要找的节点。用栈“后进先出”的特点来实现：分别将两个链表的节点
 放入两个栈中，这样栈顶就是尾节点，然后比较两个栈顶的节点是否相同。如果相同，则弹出栈顶元素，比
 较下一个节点，直到找到最后一个相同的节点。S = O(m+n), T = O(m+n)，空间换时间
 
 方法三：首先遍历两个链表的长度，就能知道那个链表更长，以及长的链表比短的链表长几个节点。在第
 二次遍历的时候，在较长的链表上先走几步，接着在两个链表上同时遍历，找到第一个相同的节点就是它
 们的第一个公共节点。T = O(m+n)
 */

#include <iostream>
#include "List.hpp"

using namespace std;

unsigned int GetListLength(ListNode *pHead);

ListNode *FindFirstCommonNode(ListNode *pHead1, ListNode *pHead2) {
    unsigned int length1 = GetListLength(pHead1);
    unsigned int length2 = GetListLength(pHead2);
    int distance = length1 - length2;
    
    ListNode *longList = pHead1;
    ListNode *shortList = pHead2;
    if (length1 < length2) {
        longList = pHead2;
        shortList = pHead1;
        distance = length2 - length1;
    }
    
    // 先在长链表上走几步，再同时在两个链表上遍历
    for (int i = 0; i < distance; i++) {
        longList = longList->next;
    }
    
    while (longList != nullptr && shortList != nullptr && longList != shortList) {
        longList = longList->next;
        shortList = shortList->next;
    }
    
    ListNode *firstCommonNode = longList;
    
    return firstCommonNode;
}

unsigned int GetListLength(ListNode *pHead) {
    unsigned int length = 0;
    ListNode *node = pHead;
    while (node != nullptr) {
        ++length;
        node = node->next;
    }
    return length;
}

/*-------------------------------------------*/
void DestoryNode(ListNode *node);

void Test(char *testName, ListNode *listNode1, ListNode *listNode2, ListNode *expectListNode) {
    if (testName != nullptr) {
        printf("【%s】：", testName);
    }
 
    if (FindFirstCommonNode(listNode1, listNode2) == expectListNode) {
        cout << "Pass." << endl;
    } else {
        cout << "Fail." << endl;
    }
}

// 第一个公共结点在链表中间
// 1 - 2 - 3 \
//            6 - 7
//     4 - 5 /
void Test1()
{
    ListNode *node1 = CreateListNode(1);
    ListNode *node2 = CreateListNode(2);
    ListNode *node3 = CreateListNode(3);
    ListNode *node4 = CreateListNode(4);
    ListNode *node5 = CreateListNode(5);
    ListNode *node6 = CreateListNode(6);
    ListNode *node7 = CreateListNode(7);
    
    ConnectListNodes(node1, node2);
    ConnectListNodes(node2, node3);
    ConnectListNodes(node3, node6);
    ConnectListNodes(node6, node7);
    ConnectListNodes(node4, node5);
    ConnectListNodes(node5, node6);
    
    Test("Test1", node1, node4, node6);
    
    DestoryNode(node1);
    DestoryNode(node2);
    DestoryNode(node3);
    DestoryNode(node4);
    DestoryNode(node5);
    DestoryNode(node6);
    DestoryNode(node7);
}

// 没有公共结点
// 1 - 2 - 3 - 4
//
// 5 - 6 - 7
void Test2() {
    ListNode *node1 = CreateListNode(1);
    ListNode *node2 = CreateListNode(2);
    ListNode *node3 = CreateListNode(3);
    ListNode *node4 = CreateListNode(4);
    ListNode *node5 = CreateListNode(5);
    ListNode *node6 = CreateListNode(6);
    ListNode *node7 = CreateListNode(7);
    
    ConnectListNodes(node1, node2);
    ConnectListNodes(node2, node3);
    ConnectListNodes(node3, node4);
    ConnectListNodes(node5, node6);
    ConnectListNodes(node6, node7);
    
    Test("Test2", node1, node5, nullptr);
    
    DestoryNode(node1);
    DestoryNode(node2);
    DestoryNode(node3);
    DestoryNode(node4);
    DestoryNode(node5);
    DestoryNode(node6);
    DestoryNode(node7);
}

// 公共结点是最后一个结点
// 1 - 2 - 3 - 4 \
//                7
//         5 - 6 /
void Test3() {
    ListNode *node1 = CreateListNode(1);
    ListNode *node2 = CreateListNode(2);
    ListNode *node3 = CreateListNode(3);
    ListNode *node4 = CreateListNode(4);
    ListNode *node5 = CreateListNode(5);
    ListNode *node6 = CreateListNode(6);
    ListNode *node7 = CreateListNode(7);
    
    ConnectListNodes(node1, node2);
    ConnectListNodes(node2, node3);
    ConnectListNodes(node3, node4);
    ConnectListNodes(node4, node7);
    ConnectListNodes(node5, node6);
    ConnectListNodes(node6, node7);
    
    Test("Test3", node1, node5, node7);
    
    DestoryNode(node1);
    DestoryNode(node2);
    DestoryNode(node3);
    DestoryNode(node4);
    DestoryNode(node5);
    DestoryNode(node6);
    DestoryNode(node7);
}

// 公共结点是第一个结点
// 1 - 2 - 3 - 4 - 5
// 两个链表完全重合
void Test4() {
    ListNode *node1 = CreateListNode(1);
    ListNode *node2 = CreateListNode(2);
    ListNode *node3 = CreateListNode(3);
    ListNode *node4 = CreateListNode(4);
    ListNode *node5 = CreateListNode(5);
    
    ConnectListNodes(node1, node2);
    ConnectListNodes(node2, node3);
    ConnectListNodes(node3, node4);
    ConnectListNodes(node4, node5);
    
    Test("Test4", node1, node1, node1);
    
    DestoryNode(node1);
    DestoryNode(node2);
    DestoryNode(node3);
    DestoryNode(node4);
    DestoryNode(node5);
}

// 输入的两个链表有一个空链表
void Test5() {
    ListNode *node1 = CreateListNode(1);
    ListNode *node2 = CreateListNode(2);
    ListNode *node3 = CreateListNode(3);
    ListNode *node4 = CreateListNode(4);
    ListNode *node5 = CreateListNode(5);
    
    ConnectListNodes(node1, node2);
    ConnectListNodes(node2, node3);
    ConnectListNodes(node3, node4);
    ConnectListNodes(node4, node5);
    
    Test("Test5", node1, nullptr, nullptr);
    
    DestoryNode(node1);
    DestoryNode(node2);
    DestoryNode(node3);
    DestoryNode(node4);
    DestoryNode(node5);
}

// 输入的两个链表有一个空链表
void Test6() {
    Test("Test6", nullptr, nullptr, nullptr);
}

void DestoryNode(ListNode *node) {
    delete node;
    node = nullptr;
}

int main(int argc, const char * argv[]) {
    Test1();
    Test2();
    Test3();
    Test4();
    Test5();
    Test6();
    return 0;
}
