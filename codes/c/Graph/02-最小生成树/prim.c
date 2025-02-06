//
//  prim.c
//  02-最小生成树
//
//  Created by LiLe on 2019/5/27.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "prim.h"
#include "Graph.h"

// Prim 算法生成最小生成树
void minimum_span_tree_prim(Graph graph)
{
    int min, i, j, k;
    int adjvex[MAXVEX]; // 保存相关顶点下标
    int lowcost[MAXVEX]; // 保存相关顶点间边的权值
    lowcost[0] = 0; // 初始化第一个权值为0，即 v0 加入生成树；lowcost 的值为0，在这里就是此下标的顶点已经加入生成树
    adjvex[0] = 0; // 初始化第一个顶点下标为0
    for (i = 1; i < graph.numVertexes; i++) {
        lowcost[i] = graph.arc[0][i]; // 将 v0 顶点与之有边的权值存入数组
        adjvex[i] = 0; // 初始化都为 v0 的下标
    }
    for (i = 1; i < graph.numVertexes; i++) {
        min = INFINITY; // 初始化最小权值为∞
        j = 1;
        k = 0;
        while (j < graph.numVertexes) {
            if (lowcost[j] != 0 && lowcost[j] < min) { // 如果权值不为0且权值小于min
                min = lowcost[j]; // 让当前权值成为最小值
                k = j; // 将当前最小值的下标存入 k
            }
            j++;
        }
        printf("(%d, %d)\n", adjvex[k], k);
        lowcost[k] = 0; // 将当前顶点的权值设置为0，表示此顶点已经完成任务
        for (j = 1; j < graph.numVertexes; j++) { // 循环所有顶点
            // 如果下标为k的顶点各边权值小于此前这些顶点未被加入生成树权值
            if (lowcost[j] != 0 && graph.arc[k][j] < lowcost[j]) {
                lowcost[j] = graph.arc[k][j]; // 将较小的权值存入 lowcost 相应位置
                adjvex[j] = k; // 将下标为 k 的顶点存入 adjvex
            }
        }
    }
}

void minimum_span_tree_prim_test()
{
    Graph graph;
    create_graph(&graph);
    minimum_span_tree_prim(graph);
}

