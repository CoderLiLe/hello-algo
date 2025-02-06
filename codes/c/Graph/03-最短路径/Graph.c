//
//  Graph.c
//  02-最小生成树
//
//  Created by LiLe on 2019/5/27.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "Graph.h"

void create_graph(Graph *graph)
{
    int i, j;
    
    graph->numVertexes = 9;
    graph->numEdges = 15;
    
    for (i = 0; i < graph->numVertexes; i++) {
        graph->vexs[i] = i;
    }
    
    for (i = 0; i < graph->numVertexes; i++) {
        for (j = 0; j < graph->numVertexes; j++) {
            if (i == j) {
                graph->arc[i][j] = 0;
            } else {
                graph->arc[i][j] = graph->arc[j][i] = INFINITY;
            }
        }
    }
    
    graph->arc[0][1]=1;
    graph->arc[0][2]=5;
    graph->arc[1][2]=3;
    graph->arc[1][3]=7;
    graph->arc[1][4]=5;
    
    graph->arc[2][4]=1;
    graph->arc[2][5]=7;
    graph->arc[3][4]=2;
    graph->arc[3][6]=3;
    graph->arc[4][5]=3;
    
    graph->arc[4][6]=6;
    graph->arc[4][7]=9;
    graph->arc[5][7]=5;
    graph->arc[6][7]=2;
    graph->arc[6][8]=7;
    
    graph->arc[7][8]=4;
    
    for (i = 0; i < graph->numVertexes; i++) {
        for (j = i; j < graph->numVertexes; j++) {
            graph->arc[j][i] = graph->arc[i][j];
        }
    }
}
