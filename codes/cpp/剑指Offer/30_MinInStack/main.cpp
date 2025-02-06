//
//  main.cpp
//  30_MinInStack
//
//  Created by LiLe on 2020/2/22.
//  Copyright © 2020 lile. All rights reserved.
//

/**
 面试题30:包含 min 函数的栈
 题目：定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数。在该栈中，调用 min、push、pop的
 时间复杂度为 O(1)。
 */

/**
 分析：
 每次有元素入栈时，对栈中元素排序，但此时已经不是栈这种数据结构了，无法保证后入栈的先出栈 ❌
 用一个成员变量保存最小值，如果当前最小元素出栈后，无法得到下一个最小元素 ❌
 用一个辅助栈保存最小元素 👌
 */

#include <iostream>
#include "StackWithMin.cpp"

void Test(const char* testName, const StackWithMin<int>& stack, int expected)
{
    if(testName != nullptr)
        printf("%s : ", testName);

    if(stack.min() == expected)
        printf("passed.\n");
    else
        printf("failed.\n");
}

int main(int argc, const char * argv[]) {
    
    StackWithMin<int> stack;

    stack.push(3);
    Test("Test1", stack, 3);

    stack.push(4);
    Test("Test2", stack, 3);

    stack.push(2);
    Test("Test3", stack, 2);

    stack.push(3);
    Test("Test4", stack, 2);

    stack.pop();
    Test("Test5", stack, 2);

    stack.pop();
    Test("Test6", stack, 3);

    stack.pop();
    Test("Test7", stack, 3);

    stack.push(0);
    Test("Test8", stack, 0);
    
    return 0;
}
