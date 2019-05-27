//
//  graph_const.h
//  Graph
//
//  Created by LiLe on 2019/5/21.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef graph_const_h
#define graph_const_h

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

#define MAXSIZE 9
#define MAXEDGE 15
#define MAXVEX 9
#define INFINITY 65535

typedef int Status; // 值为函数的结果状态码，如 OK 等
typedef int Boolean; // Boolean 是布尔类型，其值是 TRUE 或 FALSE

typedef char VertexType; // 顶点类型应该由用户定义
typedef int EdgeType; // 边上的权值类型应该由用户定义

typedef struct
{
    VertexType vexs[MAXVEX]; // 顶点表
    EdgeType arc[MAXVEX][MAXVEX]; // 邻接矩阵，可看作边表
    int numVertexes, numEdges; // 图中当前的顶点数和边数
}MGraph;

#endif /* graph_const_h */
