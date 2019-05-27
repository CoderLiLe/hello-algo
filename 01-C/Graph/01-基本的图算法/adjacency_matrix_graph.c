//
//  adjacency_matrix_graph.c
//  01-基本的图算法
//
//  Created by LiLe on 2019/5/21.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "adjacency_matrix_graph.h"

#include <stdlib.h>

#include "queue.h"

#pragma mark -
void CreateMGraph(MGraph *G)
{
    int i, j;
    
    G->numVertexes = 9;
    G->numEdges = 15;
    
    // 读入顶点信息，建立顶点表
    G->vexs[0] = 'A';
    G->vexs[1] = 'B';
    G->vexs[2] = 'C';
    G->vexs[3] = 'D';
    G->vexs[4] = 'E';
    G->vexs[5] = 'F';
    G->vexs[6] = 'G';
    G->vexs[7] = 'H';
    G->vexs[8] = 'I';
    
    for (i = 0; i < G->numVertexes; i++) {
        for (j = 0; j < G->numVertexes; j++) {
            G->arc[i][j] = 0;
        }
    }
    
    G->arc[0][1]=1;
    G->arc[0][5]=1;
    
    G->arc[1][2]=1;
    G->arc[1][8]=1;
    G->arc[1][6]=1;
    
    G->arc[2][3]=1;
    G->arc[2][8]=1;
    
    G->arc[3][4]=1;
    G->arc[3][7]=1;
    G->arc[3][6]=1;
    G->arc[3][8]=1;
    
    G->arc[4][5]=1;
    G->arc[4][7]=1;
    
    G->arc[5][6]=1;
    
    G->arc[6][7]=1;
    
    for (i = 0; i < G->numVertexes; i++) {
        for (j = 0; j < G->numVertexes; j++) {
            G->arc[j][i] = G->arc[i][j];
        }
    }
}

Boolean visited[MAXVEX]; // 访问标志的数组

// 邻接矩阵的深度优先递归算法
void DFS(MGraph G, int i)
{
    int j;
    visited[i] = TRUE;
    printf("%c ", G.vexs[i]); // 打印顶点，也可以其他操作
    for (j = 0; j < G.numVertexes; j++) {
        if (1 == G.arc[i][j] && !visited[j]) {
            DFS(G, j); // 对访问的邻接顶点递归调用
        }
    }
}

// 邻接矩阵的深度遍历操作
void DFSTraverse(MGraph G)
{
    int i;
    for (i = 0; i < G.numVertexes; i++) {
        visited[i] = FALSE; // 初始所有顶点状态都是未访问过的状态
    }
    
    for (i = 0; i < G.numVertexes; i++) {
        if (!visited[i]) { // 对未访问过的顶点调用DFS，若是连通图，只会执行一次
            DFS(G, i);
        }
    }
}

// 邻接矩阵的广度遍历算法
void BFSTraverse(MGraph G)
{
    int i, j;
    Queue Q;
    
    for (i = 0; i < G.numVertexes; i++) {
        visited[i] = FALSE;
    }
    
    initQueue(&Q); // 初始化一辅助用的队列
    for (i = 0; i < G.numVertexes; i++) { // 对每个顶点做循环
        if (!visited[i]) {
            visited[i] = TRUE;
            printf("%c ", G.vexs[i]);
            enQueue(&Q, i); // 将此顶点入队列
            while (!queueEmpty(Q)) { // 若当前队列不为
                deQueue(&Q, &i); // 将队列元素出队列，赋值给 i
                for (j = 0; j < G.numVertexes; j++) {
                    // 判断其他顶点若与当前顶点存在边切未访问过
                    if (1 == G.arc[i][j] && !visited[j]) {
                        visited[j] = TRUE;
                        printf("%c ", G.vexs[j]);
                        enQueue(&Q, j);
                    }
                }
            }
        }
    }
    
    printf("\n");
}

void adjacenncy_matrix_graph_traverse()
{
    MGraph G;
    CreateMGraph(&G);
    printf("\n深度遍历：");
    DFSTraverse(G);
    printf("\n广度遍历：");
    BFSTraverse(G);
}
