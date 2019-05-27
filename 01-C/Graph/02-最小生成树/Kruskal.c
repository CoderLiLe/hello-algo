//
//  Kruskal.c
//  02-最小生成树
//
//  Created by LiLe on 2019/5/27.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "Kruskal.h"
#include "Graph.h"

// 交换权值，以及头和尾
void swapn(Edge *edges, int i, int j)
{
    int temp;
    
    temp = edges[i].begin;
    edges[i].begin = edges[j].begin;
    edges[j].begin = temp;
    
    temp = edges[i].end;
    edges[i].end = edges[j].end;
    edges[j].end = temp;
    
    temp = edges[i].weight;
    edges[i].weight = edges[j].weight;
    edges[j].weight = temp;
}

// 对权值进行排序
void sort(Edge edges[], Graph *graph)
{
    int i, j;
    for (i = 0; i < graph->numEdges; i++) {
        for (j = i + 1; j < graph->numEdges; j++) {
            if (edges[i].weight > edges[j].weight) {
                swapn(edges, i, j);
            }
        }
    }
    
    printf("权排序之后为：\n");
    for (i = 0; i < graph->numEdges; i++) {
        printf("(%d, %d) %d\n", edges[i].begin, edges[i].end, edges[i].weight);
    }
}

// 查找连接顶点的尾部下标
int find(int *parent, int f)
{
    while (parent[f] > 0) {
        f = parent[f];
    }
    return f;
}

// Kruskal 算法生成最小生成树
void minimum_span_tree_kruskal(Graph graph)
{
    int i, j, n, m;
    int k = 0;
    int parent[MAXVEX]; // 定义一个数组用来判断边与边是否形成环路
    
    Edge edges[MAXEDGE]; // 定义边集数组
    
    // 构建边集数组并排序
    for (i = 0; i < graph.numVertexes-1; i++) {
        for (j = i + 1; j < graph.numVertexes; j++) {
            if (graph.arc[i][j] < INFINITY) {
                edges[k].begin = i;
                edges[k].end = j;
                edges[k].weight = graph.arc[i][j];
                k++;
            }
        }
    }
    sort(edges, &graph);
    
    for (i = 0; i < graph.numVertexes; i++) {
        parent[i] = 0; // 初始化数组为 0
    }
    
    printf("\n打印最小生成树：\n");
    for (i = 0; i < graph.numEdges; i++) { // 循环每条边
        n = find(parent, edges[i].begin);
        m = find(parent, edges[i].end);
        
        if (n != m) {
            parent[n] = m; // 将此边的结尾顶点放入下标为起点的 parent 中，表示此顶点已经在生成树集合中
            printf("(%d, %d) %d\n", edges[i].begin, edges[i].end, edges[i].weight);
        }
    }
}

void minimum_span_tree_kruskal_test()
{
    Graph graph;
    create_graph(&graph);
    minimum_span_tree_kruskal(graph);
}

