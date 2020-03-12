//
//  array_queue.h
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#ifndef array_queue_h
#define array_queue_h

#include <stdio.h>
#include <stdbool.h>
#include "Common.h"

typedef struct SequenceQueue *SqQueue;
static const int kMaxSize = 20;

SqQueue sq_init(void);
void sq_destory(SqQueue q);
void sq_clear(SqQueue q);
bool sq_empty(SqQueue q);
bool sq_head(SqQueue q, T *e);
bool sq_enqueue(SqQueue q, T e);
bool sq_dequeue(SqQueue q, T *e);
int sq_length(SqQueue q);
void sq_traverse(SqQueue q);


#endif /* array_queue_h */
