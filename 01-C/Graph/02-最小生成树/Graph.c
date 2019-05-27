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
        for (j = 0; j < graph->numVertexes; j++) {
            if (i == j) {
                graph->arc[i][j] = 0;
            } else {
                graph->arc[i][j] = graph->arc[j][i] = INFINITY;
            }
        }
    }
    
    graph->arc[0][1] = 10;
    graph->arc[0][5] = 11;
    graph->arc[1][2] = 18;
    graph->arc[1][6] = 16;
    graph->arc[1][8] = 12;
    graph->arc[2][3] = 22;
    graph->arc[2][8] = 8;
    graph->arc[3][4] = 20;
    graph->arc[3][6] = 24;
    graph->arc[3][7] = 16;
    graph->arc[3][8] = 21;
    graph->arc[4][5] = 26;
    graph->arc[4][7] = 7;
    graph->arc[3][8] = 21;
    graph->arc[5][6] = 17;
    graph->arc[6][7] = 19;
    
    for (i = 0; i < graph->numVertexes; i++) {
        for (j = i; j < graph->numVertexes; j++) {
            graph->arc[j][i] = graph->arc[i][j];
        }
    }
}
