//
//  matrix.h
//  LinearProgramming
//
//  Created by LiLe on 2019/5/28.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef matrix_h
#define matrix_h

#include "common.h"

typedef struct {
    int   row, col;
    float **mem;
}MATRIX;

u8 create_matrix(MATRIX *mat, int row, int col);
void delete_matrix(MATRIX *mat);
void set_matrix(MATRIX *mat, float *val);
void copy_matrix(MATRIX *des_mat, MATRIX src_mat);
void matrix_expand_row(MATRIX *mat, u32 exp_num, float *val);
void matrix_expand_column(MATRIX *mat, u32 exp_num, float *val);
void matrix_remove_column(MATRIX *mat, int col_index);
void print_matrix(MATRIX mat);


#endif /* matrix_h */
