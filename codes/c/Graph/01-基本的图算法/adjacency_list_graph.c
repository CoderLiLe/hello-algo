//
//  adjacency_list_graph.c
//  01-基本的图算法
//
//  Created by LiLe on 2019/5/21.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "adjacency_list_graph.h"

#include <stdlib.h>

#include "queue.h"

#pragma mark - 邻接表结构
// 边表结点
typedef struct EdgeNode
{
    int adjvex; // 邻接点域，存储该顶点对应的下标
    EdgeType info; // 用于存储权值，对于非网图可以不需要
    struct EdgeNode *next; // 链域，指向下一个邻接点
}EdgeNode;

// 顶点表结点
typedef struct VertexNode
{
    int in; // 顶点入度
    VertexType data; // 顶点域，存储顶点信息
    EdgeNode *firstEdge; // 边表头指针
}VertexNode, AdjList[MAXVEX];

typedef struct
{
    AdjList adjList;
    int numVertexes, numEdges; // 图中当前顶点数和边数
}graphAdjList, *GraphAdjList;

#pragma mark -
void create_graph(MGraph *graph)
{
    int i, j;
    
    graph->numEdges = 15;
    graph->numVertexes = 9;
    
    // 读入顶点信息，建立顶点表
    graph->vexs[0] = 'A';
    graph->vexs[1] = 'B';
    graph->vexs[2] = 'C';
    graph->vexs[3] = 'D';
    graph->vexs[4] = 'E';
    graph->vexs[5] = 'F';
    graph->vexs[6] = 'G';
    graph->vexs[7] = 'H';
    graph->vexs[8] = 'I';
    
    for (i = 0; i < graph->numVertexes; i++) { // 初始化图
        for (j = 0; j < graph->numVertexes; j++) {
            graph->arc[i][j] = 0;
        }
    }
    
    graph->arc[0][1] = 1;
    graph->arc[0][5] = 1;
    
    graph->arc[1][2] = 1;
    graph->arc[1][6] = 1;
    graph->arc[1][8] = 1;
    
    graph->arc[2][3] = 1;
    graph->arc[2][8] = 1;
    
    graph->arc[3][4] = 1;
    graph->arc[3][6] = 1;
    graph->arc[3][7] = 1;
    graph->arc[3][8] = 1;
    
    graph->arc[4][5] = 1;
    graph->arc[4][7] = 1;
    
    graph->arc[5][6] = 1;
    graph->arc[6][7] = 1;
    
    for (i = 0; i < graph->numVertexes; i++) {
        for (j = i; j < graph->numVertexes; j++) {
            graph->arc[j][i] = graph->arc[i][j];
        }
    }
}

// 利用邻接矩阵构建邻接表
void create_adjacency_list_graph(MGraph graph, GraphAdjList *GL)
{
    int i, j;
    EdgeNode *e;
    
    *GL = (GraphAdjList)malloc(sizeof(graphAdjList));
    
    (*GL)->numVertexes = graph.numVertexes;
    (*GL)->numEdges = graph.numEdges;
    for (i = 0; i < graph.numVertexes; i++) {
        (*GL)->adjList[i].in = 0;
        (*GL)->adjList[i].data = graph.vexs[i];
        (*GL)->adjList[i].firstEdge = NULL; // 将边表置为空表
    }
    
    for (i = 0; i < graph.numVertexes; i++) {
        for (j = 0; j < graph.numVertexes; j++) {
            if (1 == graph.arc[i][j]) {
                e = (EdgeNode *)malloc(sizeof(EdgeNode));
                e->adjvex = j; // 邻接序号为 j
                e->next = (*GL)->adjList[i].firstEdge; // 将当前顶点指向的结点指针赋值给 e
                (*GL)->adjList[i].firstEdge = e;
                (*GL)->adjList[j].in++;
            }
        }
    }
}

// 访问标志数组
Boolean adjacenncy_list_graph_visited[MAXSIZE];

// 邻接表的深度优先操作
void adjacenncy_list_graph_DFS(GraphAdjList GL, int i)
{
    EdgeNode *p;
    adjacenncy_list_graph_visited[i] = TRUE;
    printf("%c ", GL->adjList[i].data);
    p = GL->adjList[i].firstEdge;
    while (p) {
        if (!adjacenncy_list_graph_visited[p->adjvex]) {
            adjacenncy_list_graph_DFS(GL, p->adjvex); // 对访问的邻接顶点递归调用
        }
        p = p->next;
    }
}

// 邻接表深度遍历操作
void adjacenncy_list_graph_DFS_Traverse(GraphAdjList GL)
{
    int i;
    for (i = 0; i < GL->numVertexes; i++) {
        adjacenncy_list_graph_visited[i] = FALSE; // 初始所有顶点状态都是未访问过状态
    }
    for (i = 0; i < GL->numVertexes; i++) {
        if (!adjacenncy_list_graph_visited[i]) { // 对未访问过的顶点调用DFS，若是连通图只会执行一次
            adjacenncy_list_graph_DFS(GL, i);
        }
    }
}

// 邻接链表的广度遍历算法
void adjacenncy_list_graph_BFS_traverse(GraphAdjList GL)
{
    int i;
    EdgeNode *p;
    Queue queue;
    for (i = 0; i < GL->numVertexes; i++) {
        adjacenncy_list_graph_visited[i] = FALSE;
    }
    
    initQueue(&queue);
    
    for (i = 0; i < GL->numVertexes; i++) {
        if (!adjacenncy_list_graph_visited[i]) {
            adjacenncy_list_graph_visited[i] = TRUE;
            printf("%c ", GL->adjList[i].data);
            enQueue(&queue, i);
            while (!queueEmpty(queue)) {
                deQueue(&queue, &i);
                p = GL->adjList[i].firstEdge; // 找到当前顶点的边表链表头指针
                while (p) {
                    if (!adjacenncy_list_graph_visited[p->adjvex]) { // 若此顶点未被访问
                        adjacenncy_list_graph_visited[p->adjvex] = TRUE;
                        printf("%c ", GL->adjList[p->adjvex].data);
                        enQueue(&queue, p->adjvex); // 将此顶点加入队列
                    }
                    p = p->next;
                }
            }
        }
    }
}

void adjacenncy_list_graph_traverse()
{
    MGraph graph;
    GraphAdjList GL;
    create_graph(&graph);
    create_adjacency_list_graph(graph, &GL);
    
    printf("\n深度遍历：");
    adjacenncy_list_graph_DFS_Traverse(GL);
    printf("\n广度遍历：");
    adjacenncy_list_graph_BFS_traverse(GL);
}
