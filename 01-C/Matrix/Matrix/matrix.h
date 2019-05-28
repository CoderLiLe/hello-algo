//
//  matrix.h
//  Matrix
//
//  Created by LiLe on 2019/5/28.
//  Copyright © 2019 LiLe. All rights reserved.
//

#ifndef matrix_h
#define matrix_h

#include <stdio.h>

typedef struct {
    int row, col;
    float **element;
}Mat;

Mat *matrix_create(Mat *mat, int row, int col);
void matrix_delete(Mat *mat);
Mat *matrix_set_val(Mat *mat, float *val);
void matrix_dump(const Mat *mat);

Mat *matrix_zeros(Mat *mat);
Mat *matrix_eye(Mat *mat);

Mat *matrix_add(Mat *src1, Mat *src2, Mat *dst);
Mat *matrix_sub(Mat *src1, Mat *src2, Mat *dst);
Mat *matrix_mul(Mat *src1, Mat *src2, Mat *dst);
Mat *matrix_trans(Mat *src, Mat *dst);
float matrix_det(Mat *mat);
Mat *matrix_adj(Mat *src, Mat *dst);
Mat *matrix_inv(Mat *src, Mat *dst);

void matrix_copy(Mat *src, Mat *dst);

#endif /* matrix_h */
