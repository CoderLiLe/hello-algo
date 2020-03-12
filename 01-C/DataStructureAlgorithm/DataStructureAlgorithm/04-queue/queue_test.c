//
//  queue_test.c
//  DataStructureAlgorithm
//
//  Created by LiLe on 2020/3/12.
//  Copyright © 2020 lile. All rights reserved.
//

#include "queue_test.h"
#include "array_queue.h"

void array_queue_test()
{
    int i = 0, l;
    T input, ele;
    
    SqQueue queue = sq_init();
    if (sq_empty(queue)) {
        printf("初始化队列后，队列为空\n");
    } else {
        printf("初始化队列后，队列不为空\n");
    }
    
    printf("请输入整型队列元素(不超过%d个), -1为提前结束符: ", kMaxSize-1);
    do {
        /* scanf("%d",&d); */
        input = i + 100;
        if(input == -1)
            break;
        i++;
        sq_enqueue(queue, input);
    } while (i < kMaxSize-1);
    
    printf("\n");
    printf("队列长度为: %d\n", sq_length(queue));
    if (sq_empty(queue)) {
        printf("队列为空\n");
    } else {
        printf("队列不为空\n");
    }
    
    printf("连续 %d 次由队头删除元素,队尾插入元素:\n", kMaxSize);
    for(l = 1; l <= kMaxSize; l++)
    {
        sq_dequeue(queue, &ele);
        printf("删除的元素是%d, 插入的元素:%d \n", ele, l+1000);
        /* scanf("%d",&d); */
        input = l + 1000;
        sq_enqueue(queue, input);
    }
    l = sq_length(queue);
    
    printf("现在队列中的元素为: \n");
    sq_traverse(queue);
    printf("共向队尾插入了%d个元素\n", i + kMaxSize);
    if (l - 2 > 0) {
        printf("现在由队头删除%d个元素:\n", l-2);
    }
    
    while (sq_length(queue) > 2) {
        sq_dequeue(queue, &ele);
        printf("删除的元素值为%d\n", ele);
    }
    
    sq_head(queue, &ele);
    printf("现在队头元素为: %d\n", ele);
    sq_clear(queue);
    if (sq_empty(queue)) {
        printf("队列为空\n");
    } else {
        printf("队列不为空\n");
    }
}
