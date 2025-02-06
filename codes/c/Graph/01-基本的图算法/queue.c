//
//  queue.c
//  01-基本的图算法
//
//  Created by LiLe on 2019/5/21.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "queue.h"

/// 初始化一个空队列 q
Status initQueue(Queue *q)
{
    q->front = 0;
    q->rear = 0;
    return OK;
}

/// 若队列为空，则返回 TRUE，否则返回 FALSE
Status queueEmpty(Queue q)
{
    if (q.front == q.rear) {
        return TRUE;
    } else {
        return FALSE;
    }
}

/// 若队列未满，则插入元素 e 为 q 新的队尾元素
Status enQueue(Queue *q, int e)
{
    if ((q->rear+1)%MAXSIZE == q->front) { // 队列满的判断
        return ERROR;
    }
    
    q->data[q->rear] = e; // 将元素 e 赋值给队尾
    q->rear = (q->rear+1)%MAXSIZE; // rear 指针向后移动一位
    
    return OK;
}

/// 若队列不空，则删除 q 中队头元素，用 e 返回其值
Status deQueue(Queue *q, int *e)
{
    if (q->front == q->rear) { // 队列判空
        return ERROR;
    }
    
    *e = q->data[q->front]; // 将队头元素赋值给 e
    q->front = (q->front+1)%MAXSIZE; // front 指针向后移动一位，若到最后则转到数组头部
    
    return OK;
}
