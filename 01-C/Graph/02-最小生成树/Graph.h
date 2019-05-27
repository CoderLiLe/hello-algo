//
//  Graph.h
//  02-最小生成树
//
//  Created by LiLe on 2019/5/27.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef Graph_h
#define Graph_h

#include <stdio.h>

#define MAXVEX 20
#define INFINITY 65535
#define MAXEDGE 20

typedef struct
{
    int arc[MAXVEX][MAXVEX];
    int numVertexes, numEdges;
}Graph;

// 对边集数组 Edge 结构的定义
typedef struct {
    int begin;
    int end;
    int weight;
}Edge;

/** 创建图 */
void create_graph(Graph *graph);

#endif /* Graph_h */
