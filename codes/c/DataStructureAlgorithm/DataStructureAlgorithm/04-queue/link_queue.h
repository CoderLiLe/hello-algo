//
//  link_queue.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef link_queue_h
#define link_queue_h

#include <stdio.h>
#include <stdbool.h>
#include "Common.h"

typedef struct Link_Queue *LinkQueue;

LinkQueue lq_init(void);
void lq_destory(LinkQueue q);
void lq_clear(LinkQueue q);
bool lq_empty(LinkQueue q);
bool lq_head(LinkQueue q, T *e);
bool lq_enqueue(LinkQueue q, T e);
bool lq_dequeue(LinkQueue q, T *e);
int lq_length(LinkQueue q);
void lq_traverse(LinkQueue q);

#endif /* link_queue_h */
