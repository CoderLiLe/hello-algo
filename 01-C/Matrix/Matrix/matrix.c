//
//  matrix.c
//  Matrix
//
//  Created by LiLe on 2019/5/28.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include "matrix.h"
#include "stdlib.h"

#define MAT_LEGAL_CHECKING
#define min(a, b) ((a) > (b) ? (b) : (a))
#define equal(a, b)    ((a-b)<1e-7 && (a-b)>-(1e-7))

void perm(int list[], int k, int m, int *p, Mat *mat, float *det);

#pragma mark - public function

Mat *matrix_create(Mat *mat, int row, int col)
{
    int i;
    
    mat->element = (float**)malloc(row * sizeof(float*));
    if (mat->element == NULL) {
        printf("mat create fail!\n");
        return NULL;
    }
    
    for (i = 0; i < row; i++) {
        mat->element[i] = (float*)malloc(col * sizeof(float));
        if (mat->element[i] == NULL) {
            int j;
            printf("mat create fail!\n");
            for (j = 0; j < i; j++) {
                free(mat->element[j]);
            }
            free(mat->element);
            return NULL;
        }
    }
    
    mat->row = row;
    mat->col = col;
    
    return mat;
}

void matrix_delete(Mat *mat)
{
    int i;
    
    for (i = 0; i < mat->row; i++) {
        free(mat->element[i]);
    }
    
    free(mat->element);
}

Mat *matrix_set_val(Mat *mat, float *val)
{
    int row, col;
    
    for (row = 0; row < mat->row; row++) {
        for (col = 0; col < mat->col; col++) {
            mat->element[row][col] = val[col + row * mat->col];
        }
    }
    
    return mat;
}

void matrix_dump(const Mat *mat)
{
    int row, col;
    
#ifdef MAT_LEGAL_CHECKING
    if (mat == NULL) {
        return;
    }
#endif
    
    printf("Mat %dx%d:\n", mat->row, mat->col);
    for (row = 0; row < mat->row; row++) {
        for (col = 0; col < mat->col; col++) {
            printf("%.4f\t", mat->element[row][col]);
        }
        printf("\n");
    }
}

Mat *matrix_zeros(Mat *mat)
{
    int row, col;
    
    for (row = 0; row < mat->row; row++) {
        for (col = 0; col < mat->col; col++) {
            mat->element[row][col] = 0.0f;
        }
    }
    
    return mat;
}

Mat *matrix_eye(Mat *mat)
{
    int i;
    
    matrix_zeros(mat);
    
    for (i = 0; i < min(mat->row, mat->col); i++) {
        mat->element[i][i] = 1.0f;
    }
    
    return mat;
}

Mat *matrix_add(Mat *src1, Mat *src2, Mat *dst)
{
    int row, col;
    
#ifdef MAt_LEGAL_CHECKING
    if (!(src1->row == src2->row && src2->row == dst->row && src1->col == src2->col && src2->col == dst->col) {
        printf("err check, unmatch matrix for matrix_add\n");
        matrix_dump(src1);
        matrix_dump(src2);
        matrix_dump(dst);
        return NULL;
    }
#endif

    for(row = 0; row < src1->row ; row++){
        for(col = 0; col < src1->col; col++){
            dst->element[row][col] = src1->element[row][col] + src2->element[row][col];
        }
    }
        
    return dst;
}

Mat *matrix_sub(Mat *src1, Mat *src2, Mat *dst)
{
    int row, col;
    
#ifdef MAt_LEGAL_CHECKING
    if (!(src1->row == src2->row && src2->row == dst->row && src1->col == src2->col && src2->col == dst->col) {
        printf("err check, unmatch matrix for matrix_add\n");
        matrix_dump(src1);
        matrix_dump(src2);
        matrix_dump(dst);
        return NULL;
    }
#endif
        
    for(row = 0; row < src1->row ; row++){
        for(col = 0; col < src1->col; col++){
            dst->element[row][col] = src1->element[row][col] - src2->element[row][col];
        }
    }
        
    return dst;
}

Mat *matrix_mul(Mat *src1, Mat *src2, Mat *dst)
{
    int row, col;
    int i;
    float temp;
    
#ifdef MAt_LEGAL_CHECKING
    if (src1->col != src2->row || src1->row != dst->row || src2->col != dst->col) {
        printf("err check, unmatch matrix for matrix_add\n");
        matrix_dump(src1);
        matrix_dump(src2);
        matrix_dump(dst);
        return NULL;
    }
#endif
    
    for(row = 0; row < dst->row ; row++){
        for(col = 0; col < dst->col; col++){
            temp = 0.0f;
            for(i = 0; i < src1->col; i++){
                temp += src1->element[row][i] * src2->element[i][col];
            }
            dst->element[row][col] = temp;
        }
    }
    
    return dst;
}

/* dst = src` */
Mat *matrix_trans(Mat *src, Mat *dst)
{
    int row, col;
    
#ifdef MAt_LEGAL_CHECKING
    if (src->row != dst->col || src->col != dst->row) {
        printf("err check, unmatch matrix for matrix_add\n");
        matrix_dump(src1);
        matrix_dump(src2);
        matrix_dump(dst);
        return NULL;
    }
#endif
    
    for(row = 0; row < dst->row ; row++){
        for(col = 0; col < dst->col; col++){
            dst->element[row][col] = src->element[col][row];
        }
    }
    
    return dst;
}

float matrix_det(Mat *mat)
{
    float det = 0.0f;
    int plarity = 0;
    int *list;
    int i;
    
#ifdef MAT_LEGAL_CHECKING
    if( mat->row != mat->col){
        printf("err check, not a square matrix for MatDetermine\n");
        matrix_dump(mat);
        return 0.0f;
    }
#endif
    
    list = (int *)malloc(sizeof(int) * mat->col);
    if (NULL == list)
    {
        printf("malloc list fail\n");
        return 0.0f;
    }
    for (i = 0; i < mat->col; i++)
    {
        list[i] = i;
    }
    
    perm(list, 0, mat->row-1, &plarity, mat, &det);
    free(list);
    
    return det;
}

Mat *matrix_adj(Mat *src, Mat *dst)
{
    Mat smat;
    int row, col;
    int i,j,r,c;
    float det;
    
#ifdef MAT_LEGAL_CHECKING
    if( src->row != src->col || src->row != dst->row || src->col != dst->col){
        printf("err check, not a square matrix for matrix_adj\n");
        matrix_dump(src);
        matrix_dump(dst);
        return NULL;
    }
#endif
    
    matrix_create(&smat, src->row-1, src->col-1);
    
    for(row = 0 ; row < src->row ; row++){
        for(col = 0 ; col < src->col ; col++){
            r = 0;
            for(i = 0 ; i < src->row ; i++){
                if(i == row)
                    continue;
                c = 0;
                for(j = 0; j < src->col ; j++){
                    if(j == col)
                        continue;
                    smat.element[r][c] = src->element[i][j];
                    c++;
                }
                r++;
            }
            det = matrix_det(&smat);
            if((row+col)%2)
                det = -det;
            dst->element[col][row] = det;
        }
    }
    
    matrix_delete(&smat);
    
    return dst;
}

Mat *matrix_inv(Mat *src, Mat *dst)
{
    Mat adj_mat;
    float det;
    int row, col;
    
#ifdef MAT_LEGAL_CHECKING
    if( src->row != src->col || src->row != dst->row || src->col != dst->col){
        printf("err check, not a square matrix for matrix_inv\n");
        matrix_dump(src);
        matrix_dump(dst);
        return NULL;
    }
#endif
    
    matrix_create(&adj_mat, src->row, src->col);
    matrix_adj(src, &adj_mat);
    det = matrix_det(src);
    
    if (equal(det, 0.0f))
    {
        printf("err, determinate is 0 for matrix_inv\n");
        return NULL;
    }
    
    for(row = 0 ; row < src->row ; row++){
        for(col = 0 ; col < src->col ; col++)
            dst->element[row][col] = adj_mat.element[row][col]/det;
    }
    
    matrix_delete(&adj_mat);
    
    return dst;
}

void matrix_copy(Mat *src, Mat *dst)
{
    int row, col;
    
#ifdef MAT_LEGAL_CHECKING
    if( src->row != dst->row || src->col != dst->col){
        printf("err check, unmathed matrix for matrix_copy\n");
        matrix_dump(src);
        matrix_dump(dst);
        return ;
    }
#endif
    
    for(row = 0; row < src->row ; row++){
        for(col = 0; col < src->col; col++){
            dst->element[row][col] = src->element[row][col];
        }
    }
}

#pragma mark - privte function

#define equal(a, b) ((a-b)<1e-7 && (a-b)>-(1e-7))

void swap(int *a, int *b)
{
    int m;
    m = *a;
    *a = *b;
    *b = m;
}

void perm(int list[], int k, int m, int *p, Mat *mat, float *det)
{
    int i;
    
    if (k > m) {
        float res = mat->element[0][list[0]];
        
        for (i = 1; i < mat->row; i++) {
            res *= mat->element[i][list[i]];
        }
        
        if (*p % 2) {
            // odd is negative
            *det -= res;
        } else {
            // even is positive
            *det += res;
        }
    } else {
        // if the element is 0, we don't need to calculate the value for this permutation
        if (!equal(mat->element[k][list[k]], 0.0f)) {
            perm(list, k+1, m, p, mat, det);
        }
        
        for (i = k+1; i <= m; i++) {
            if (equal(mat->element[k][list[i]], 0.0f)) {
                continue;
            }
            
            swap(&list[k], &list[i]);
            *p += 1;
            perm(list, k+1, m, p, mat, det);
            swap(&list[k], &list[i]);
            *p -= 1;
        }
    }
}

