//
//  link_queue.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include <stdlib.h>
#include "link_queue.h"

typedef struct ListQueueNode Node;

// 节点结构
struct ListQueueNode {
    T ele;
    Node *next;
};

// 队列的链表结构
struct Link_Queue {
    Node *front; // 队头指针
    Node *rear;  // 队尾指针
};

LinkQueue lq_init(void)
{
    LinkQueue q = (LinkQueue)malloc(sizeof(LinkQueue));
    q->front = q->rear = (Node *)malloc(sizeof(Node));
    if (q->front) {
        q->front->next = NULL;
        return q;
    } else {
        return NULL;
    }
}

void lq_destory(LinkQueue q)
{
    while (q->front) {
        q->rear = q->front->next;
        free(q->front);
        q->front = q->rear;
    }
}

void lq_clear(LinkQueue q)
{
    Node *node1;
    Node *node2;
    q->rear = q->front;
    node1 = q->front->next;
    q->front->next = NULL;
    while (node1) {
        node2 = node1;
        node1 = node1->next;
        free(node2);
    }
}

bool lq_empty(LinkQueue q)
{
    return q->front == q->rear;
}

bool lq_head(LinkQueue q, T *e)
{
    if (lq_empty(q)) {
        printf("队列为空\n");
        return false;
    }
    Node *node = q->front->next;
    *e = node->ele;
    return true;
}

bool lq_enqueue(LinkQueue q, T e)
{
    Node *node = (Node *)malloc(sizeof(Node));
    if (!node) {
        printf("创建节点失败\n");
        return false;
    }
    node->ele = e;
    node->next = NULL;
    q->rear->next = node;
    q->rear = node;
    return true;
}

bool lq_dequeue(LinkQueue q, T *e)
{
    Node *node;
    if (lq_empty(q)) {
        printf("队列为空\n");
        return false;
    }
    node = q->front->next;
    *e = node->ele;
    q->front->next = node->next;
    if (q->rear == node) {
        q->rear = q->front;
    }
    free(node);
    return true;
}

int lq_length(LinkQueue q)
{
    int length = 0;
    Node *node;
    node = q->front;
    while (q->rear != node) {
        length++;
        node = node->next;
    }
    return length;
}

void lq_traverse(LinkQueue q)
{
    Node *node = q->front->next;
    while (node) {
        printf("%d ", node->ele);
        node = node->next;
    }
    printf("\n");
}

