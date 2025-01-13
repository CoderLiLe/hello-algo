package 细节实现;

import tools.Asserts;
import tools.Times;

import java.util.Arrays;

public class _073矩阵置零 {
    /**
     * 方法一：使用标记数组
     * 首先遍历该数组一次，如果某个元素为 0，那么就将该元素所在的行和列所对应标记数组的位置置为 true。
     * 最后我们再次遍历该数组，用标记数组更新原数组即可
     * <p>
     * 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。我们至多只需要遍历该矩阵两次。
     * 空间复杂度：O(m+n)，其中 m 是矩阵的行数，n 是矩阵的列数。我们需要分别记录每一行或每一列是否有零出现。
     *
     * @param matrix
     */
    public void setZeroes1(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        boolean[] row = new boolean[rowNum];
        boolean[] col = new boolean[colNum];

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * 方法二：使用两个标记变量
     * 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。我们至多只需要遍历该矩阵两次。
     * 空间复杂度：O(1)。我们只需要常数空间存储若干变量。
     *
     * @param matrix 一个二维整数数组，表示待处理的矩阵。
     *               该方法会将矩阵中所有值为0的元素所在的行和列的所有元素都设置为0。
     */
    public void setZeroes2(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        // 标记第一行是否有零
        boolean flagRow0 = false;
        // 标记第一列是否有零
        boolean flagCol0 = false;

        // 检查第一列是否有零
        for (int i = 0; i < rowNum; i++) {
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }
        }
        // 检查第一行是否有零
        for (int j = 0; j < colNum; j++) {
            if (matrix[0][j] == 0) {
                flagRow0 = true;
            }
        }
        // 使用第一行和第一列作为标记，记录其余行列是否有零
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 根据标记将对应行列设置为零
        for (int i = 1; i < rowNum; i++) {
            for (int j = 1; j < colNum; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 根据flagCol标记，如果第一列有零，则将第一列全部设置为零
        if (flagCol0) {
            for (int i = 0; i < rowNum; i++) {
                matrix[i][0] = 0;
            }
        }
        // 根据flagRow标记，如果第一行有零，则将第一行全部设置为零
        if (flagRow0) {
            for (int j = 0; j < colNum; j++) {
                matrix[0][j] = 0;
            }
        }
    }

    /**
     * 方法三：使用一个标记变量
     * <p>
     * 时间复杂度：O(mn)，其中 m 是矩阵的行数，n 是矩阵的列数。我们至多只需要遍历该矩阵两次。
     * 空间复杂度：O(1)。我们只需要常数空间存储若干变量。
     *
     * @param matrix 二维整数数组，代表输入的矩阵
     */
    public void setZeroes3(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        // 使用一个标记变量来记录第一列是否原本含有0
        boolean flagCol0 = false;

        // 第一次遍历矩阵，使用第一行和第一列作为标记
        for (int i = 0; i < rowNum; i++) {
            // 如果当前行的第一列元素为0，则标记第一列为true
            if (matrix[i][0] == 0) {
                flagCol0 = true;
            }

            // 从第二列开始遍历当前行，如果遇到0，则将当前行的第一列和当前列的第一行元素标记为0
            for (int j = 1; j < colNum; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 第二次遍历矩阵（从右下角开始），根据第一行和第一列的标记，将相应元素设置为0
        for (int i = rowNum - 1; i >= 0; i--) {
            for (int j = 1; j < colNum; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // 根据flagCol0的值，处理第一列
            if (flagCol0) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        _073矩阵置零 obj = new _073矩阵置零();

        int[][] res = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};

        Times.test("方法一：使用标记数组", () -> {
            int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            obj.setZeroes1(matrix);
            Asserts.test(Arrays.deepEquals(res, matrix));
        });


        Times.test("方法二：使用两个标记变量", () -> {
            int[][] matrix2 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            obj.setZeroes2(matrix2);
            Asserts.test(Arrays.deepEquals(res, matrix2));
        });

        Times.test("方法三：使用一个标记变量", () -> {
            int[][] matrix3 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
            obj.setZeroes3(matrix3);
            Asserts.test(Arrays.deepEquals(res, matrix3));
        });
    }
}
