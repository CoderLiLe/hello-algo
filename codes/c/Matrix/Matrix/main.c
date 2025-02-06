//
//  main.c
//  Matrix
//
//  Created by LiLe on 2019/5/28.
//  Copyright © 2019 LiLe. All rights reserved.
//

#include <stdio.h>
#include "matrix.h"

int main(int argc, const char * argv[]) {
    Mat a;
    float val[] = {
        1, 2, 3,
        4, 5, 6,
    };
    Mat b;
    float val2[] = {
        3, 6,
        8, 1,
        9, 2
    };
    Mat c;
    Mat d;
    float val3[] = {
        3, 2, -3,
        10, -3, 2,
        -3, 5, 9,
    };
    float det;
    
    matrix_create(&a, 2, 3);
    matrix_dump(matrix_set_val(&a, val));
    
    matrix_create(&b, 3, 2);
    matrix_dump(matrix_set_val(&b, val2));
    
    matrix_create(&c, 3, 3);
    matrix_create(&d, 3, 3);
    matrix_dump(matrix_set_val(&c, val3));
    
    matrix_dump(matrix_mul(&b, &a, &c));
    matrix_dump(matrix_trans(&a, &b));
    matrix_dump(matrix_inv(&d, &c));
    
    det = matrix_det(&d);
    printf("det(d) = %.d2f\n", det);
    
    
    matrix_delete(&a);
    matrix_delete(&b);
    matrix_delete(&c);
    matrix_delete(&d);
    
    return 0;
}
