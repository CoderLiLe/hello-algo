//
//  main.cpp
//  09_QueueWithTwoStacks
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题9:用两个栈实现队列
 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead，分别完成在队列尾部
 插入节点和在队列头部删除节点的功能。
 
 拓展题：用两个队列实现一个栈。
 */

#include <iostream>
#include "CPPQueue.cpp"
#include "CPPStack.cpp"

// ====================测试代码====================
void Test(char actual, char expected)
{
    if(actual == expected)
        printf("Test passed.\n");
    else
        printf("Test failed.\n");
}

void TestCPPQueue() {
    CPPQueue<char> queue;

    queue.appendTail('a');
    queue.appendTail('b');
    queue.appendTail('c');

    char head = queue.deleteHead();
    Test(head, 'a');

    head = queue.deleteHead();
    Test(head, 'b');

    queue.appendTail('d');
    head = queue.deleteHead();
    Test(head, 'c');

    queue.appendTail('e');
    head = queue.deleteHead();
    Test(head, 'd');

    head = queue.deleteHead();
    Test(head, 'e');
}

void TestCPPStack() {
    CPPStack<char> stack;
    
    stack.push('a');
    stack.push('b');
    stack.push('c');
    
    char top = stack.top();
    Test(top, 'c');
    
    stack.pop();
    top = stack.top();
    Test(top, 'b');
    
    stack.pop();
    top = stack.top();
    Test(top, 'a');
    
    stack.push('d');
    top = stack.top();
    Test(top, 'd');
    
    stack.pop();
    top = stack.top();
    Test(top, 'a');
}

int main(int argc, const char * argv[]) {
//    TestCPPQueue();
    TestCPPStack();
    return 0;
}
