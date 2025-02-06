//
//  simplex.h
//  LinearProgramming
//
//  Created by LiLe on 2019/5/28.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef simplex_h
#define simplex_h

#include "common.h"
#include "matrix.h"

typedef struct {
    int bvar;   // basic variable: yi
    int nbvar;  // non-basic variable: xi
} PIVOT;

typedef enum {
    LP = 0,
    ILP
}STRATEGY;

u8 init_simplex_model(MATRIX mat, MATRIX g_mat, char *var_name[]);
void delete_simplex_model(void);
u8 simplex_run(STRATEGY strategy);
int get_max_intValue(void);
void get_int_solution(int *solution);

#endif /* simplex_h */
