package 细节实现;

import tools.Asserts;

import java.util.Arrays;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class _048旋转矩阵 {
    /**
     * 方法一：辅助矩阵
     * 如下图所示，矩阵顺时针旋转 90º 后，可找到以下规律：
     *
     * 「第 i 行」元素旋转到「第 n−1−i 列」元素；
     * 「第 j 列」元素旋转到「第 j 行」元素；
     * 因此，对于矩阵任意第 i 行、第 j 列元素 matrix[i][j] ，矩阵旋转 90º 后「元素位置旋转公式」为：
     * matrix[i][j] → matrix[j][n−1−i]
     * 原索引位置     → 旋转后索引位置
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 深拷贝 matrix -> tmp
        int[][] tmp = new int[n][];
        for (int i = 0; i < n; i++) {
            tmp[i] = matrix[i].clone();
        }

        // 根据元素旋转公式，遍历修改原矩阵 matrix 的各元素
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[j][n - 1 - i] = tmp[i][j];
            }
        }
    }

    /**
     * 方法二：原地修改
     *
     * 将给定的二维矩阵顺时针旋转90度
     * 通过直接在原矩阵上修改值，而不是使用额外的存储空间，来实现旋转效果
     * 该方法基于对矩阵对角线和中线的反射原理，通过四角元素的循环交换来完成旋转
     *
     * 时间复杂度 O(N^2) ： 其中 N 为输入矩阵的行（列）数。需要将矩阵中每个元素旋转到新的位置，即对矩阵所有元素操作一次
     * 空间复杂度 O(1) ： 临时变量 tmp 使用常数大小的额外空间。值得注意，当循环中进入下轮迭代，上轮迭代初始化的 tmp 占用的内存就会被自动释放，因此无累计使用空间。
     *
     * @param matrix 一个二维整数数组，代表待旋转的矩阵
     */
    public void rotate2(int[][] matrix) {
        // 获取矩阵的大小
        int rowNum = matrix.length;

        // 外层循环负责矩阵的每一层，从外向内
        for (int i = 0; i < rowNum / 2; i++) {
            // 内层循环负责每一层中的元素，确保每个元素都被处理
            for (int j = 0; j < (rowNum + 1) / 2; j++) {
                // 临时变量用于存储当前元素的值，以便进行交换
                int tmp = matrix[i][j];
                // 以下四行代码实现了四个角的元素循环交换，从而完成旋转
                matrix[i][j] = matrix[rowNum - 1 - j][i];
                matrix[rowNum - 1 - j][i] = matrix[rowNum - 1 - i][rowNum - 1 - j];
                matrix[rowNum - 1 - i][rowNum - 1 - j] = matrix[j][rowNum - 1 - i];
                matrix[j][rowNum - 1 - i] = tmp;
            }
        }
    }

    /**
     * 将给定的二维矩阵顺时针旋转90度
     * 旋转通过两个步骤完成：
     * 1. 沿着对角线反转矩阵
     * 2. 反转每一行
     * 这种方法利用了矩阵的对称性，通过最少的计算达到旋转的效果
     *
     * @param matrix 二维整数数组，代表需要旋转的矩阵
     */
    public void rotate3(int[][] matrix) {
        // 获取矩阵的行数，由于是方阵，行数等于列数
        int rowNum = matrix.length;

        // 先沿对角线反转二维矩阵
        for (int row = 0; row < rowNum; row++) {
            for (int col = row; col < rowNum; col++) {
                // 交换元素
                int temp = matrix[row][col];
                matrix[row][col] = matrix[col][row];
                matrix[col][row] = temp;
            }
        }

        // 然后反转二维矩阵的每一行
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    /**
     * 反转一维数组的元素顺序
     *
     * @param array 需要反转的一维数组
     */
    private void reverse(int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            // 交换元素
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            // 移动指针
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        _048旋转矩阵 obj = new _048旋转矩阵();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] res = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        obj.rotate(matrix);
        Asserts.test(Arrays.deepEquals(matrix, res));
    }
}
