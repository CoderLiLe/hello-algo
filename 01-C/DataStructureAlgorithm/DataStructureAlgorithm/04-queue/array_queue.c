//
//  array_queue.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "array_queue.h"
#include <stdlib.h>

typedef struct SequenceQueue SequenceQueue;

struct SequenceQueue {
    T data[kMaxSize];
    T front; // 头指针
    T rear; // 尾指针，若队列不为空，指向队列尾元素的下一个位置
};

SqQueue sq_init(void)
{
    SqQueue q = (SqQueue)malloc(sizeof(SequenceQueue));
    q->front = 0;
    q->rear = 0;
    return q;
}

void sq_destory(SqQueue q)
{
    
}

void sq_clear(SqQueue q)
{
    q->front = q->rear = 0;
}

bool sq_empty(SqQueue q)
{
    return q->front == q->rear;
}

bool sq_head(SqQueue q, T *e)
{
    if (sq_empty(q)) {
        printf("队列为空\n");
        return false;
    }
    *e = (T)q->data[q->front];
    return true;
}

bool sq_enqueue(SqQueue q, T e)
{
    if ((q->rear + 1) % kMaxSize == q->front) {
        printf("队列已满，入队失败！\n");
        return false;
    }
    
    q->data[q->rear] = e;
    q->rear = (q->rear + 1) % kMaxSize;
    
    return true;
}

bool sq_dequeue(SqQueue q, T *e)
{
    if (q->front == q->rear) {
        printf("队列为空，出队失败！\n");
        return false;
    }
    
    *e = (T)(q->data[q->front]);
    q->front = (q->front + 1) % kMaxSize; // front 指针向后移动一位，若到最后则转到数组头部
    return true;
}

int sq_length(SqQueue q)
{
    return (q->rear - q->front + kMaxSize) % kMaxSize;
}

void sq_traverse(SqQueue q)
{
    int i = q->front;
    while((i + q->front) != q->rear)
    {
        printf("%d ",q->data[i]);
        i = (i + 1) % kMaxSize;
    }
    printf("\n");
}

