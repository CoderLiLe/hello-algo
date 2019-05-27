//
//  queue.h
//  01-基本的图算法
//
//  Created by LiLe on 2019/5/21.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef queue_h
#define queue_h

#include <stdio.h>
#include "graph_const.h"

// 循环队列的顺序存储结构
typedef struct
{
    int data[MAXSIZE];
    int front; // 头指针
    int rear; // 尾指针，若队列不空，指向队列尾元素的下一个位置
}Queue;

/// 初始化一个空队列 q
Status initQueue(Queue *q);

/// 若队列为空，则返回 TRUE，否则返回 FALSE
Status queueEmpty(Queue q);

/// 若队列未满，则插入元素 e 为 q 新的队尾元素
Status enQueue(Queue *q, int e);

/// 若队列不空，则删除 q 中队头元素，用 e 返回其值
Status deQueue(Queue *q, int *e);

#endif /* queue_h */
