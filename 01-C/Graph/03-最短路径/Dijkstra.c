//
//  Dijkstra.c
//  03-最短路径
//
//  Created by LiLe on 2019/5/27.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "Dijkstra.h"
#include "Graph.h"

typedef int Patharc[MAXVEX];
typedef int ShortPathTable[MAXVEX];

/** Dijkstra 最短路径算法 */
void shortest_path_dijkstra(Graph graph, int v0, Patharc *p, ShortPathTable *d)
{
    int v, w, k, min;
    int final[MAXVEX];
    
    for (v = 0; v < graph.numVertexes; v++) {
        final[v] = 0;
        (*d)[v] = graph.arc[v0][v];
        (*p)[v] = -1;
    }
    
    (*d)[v0] = 0;
    final[v0] = 1;
    
    for (v = 1; v < graph.numVertexes; v++) {
        min = INFINITY;
        for (w = 0; w < graph.numVertexes; w++) {
            if (!final[w] && (*d)[w] < min) {
                k = w;
                min = (*d)[w];
            }
        }
        final[k] = 1;
        for (w = 0; w < graph.numVertexes; w++) {
            if (!final[w] && (min+graph.arc[k][w] < (*d)[w])) {
                (*d)[w] = min + graph.arc[k][w];
                (*p)[w] = k;
            }
        }
    }
}

void shortest_path_dijkstra_test()
{
    int i, j, v0;
    Graph graph;
    Patharc p;
    ShortPathTable d;
    v0 = 0;
    
    create_graph(&graph);
    
    shortest_path_dijkstra(graph, v0, &p, &d);
    
    for (i = 1; i < graph.numVertexes; ++i) {
        printf("v%d - v%d : ", v0, i);
        j = i;
        while (p[j] != -1) {
            printf("%d ", p[j]);
            j = p[j];
        }
        printf("\n");
    }
    
    for (i = 1; i < graph.numVertexes; ++i) {
        printf("v%d - v%d : %d \n", graph.vexs[0], graph.vexs[i], d[i]);
    }
}
