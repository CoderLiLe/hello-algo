package 细节实现;

import tools.Asserts;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 */
public class _240搜索二维矩阵II {
    /**
     * 在一个二维矩阵中搜索一个特定的目标值。
     * 该矩阵的特点是每行的元素从左到右按升序排列，每列的元素从上到下按升序排列。
     * 该方法从矩阵的右上角开始搜索，如果当前元素大于目标值，则当前元素所在列的元素全部大于目标值，排除该列；
     * 如果当前元素小于目标值，则当前元素所在行的元素全部小于目标值，排除该行。
     * <p>
     * 时间复杂度：O(m+n)，其中 m 和 n 分别为 matrix 的行数和列数。
     * 空间复杂度：O(1)。
     *
     * @param matrix 二维矩阵，其中每行的元素从左到右按升序排列，每列的元素从上到下按升序排列。
     * @param target 需要搜索的目标值。
     * @return 如果矩阵中存在目标值，则返回 true；否则返回 false。
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // 初始化行位置为0，即从第一行开始搜索
        int row = 0;
        // 初始化列位置为最后一列，即从右上角开始搜索
        int col = matrix[0].length - 1;
        // 当前行小于矩阵行数且当前列大于等于0时，继续搜索
        while (row < matrix.length && col >= 0) {
            // 如果找到目标值，返回true
            if (matrix[row][col] == target) {
                return true;
            }

            // 当前元素小于目标值，排除这一行，向下移动一行
            if (matrix[row][col] < target) {
                row++;
            } else { // 当前元素大于目标值，排除这一列，向左移动一列
                col--;
            }
        }

        // 如果没有找到目标值，返回false
        return false;
    }

    /**
     * 在一个二维矩阵中搜索目标值。矩阵的每行和每列都是升序排列的。
     * 该方法从矩阵的左下角开始搜索，利用矩阵的有序特性，逐步缩小搜索范围，直到找到目标值或搜索范围为空。
     *
     * 时间复杂度 O(M+N) ：其中，N 和 M 分别为矩阵行数和列数，此算法最多循环 M+N 次。
     * 空间复杂度 O(1) : i, j 指针使用常数大小额外空间。
     *
     * @param matrix 二维矩阵，每行和每列都是升序排列的。
     * @param target 目标值。
     * @return 如果矩阵中包含目标值，则返回true；否则返回false。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }

        // 从最后一行开始
        int row = matrix.length - 1;
        // 从第一列开始
        int col = 0;
        while (col < matrix[0].length && row >= 0) {
            if (target > matrix[row][col]) { // 如果目标值大于当前元素，向右移动一列
                col++;
            } else if (target < matrix[row][col]) { // 如果目标值小于当前元素，向上移动一行
                row--;
            } else {
                // 找到目标值
                return true;
            }
        }

        // 搜索完整个矩阵后仍未找到目标值
        return false;
    }

    public static void main(String[] args) {
        _240搜索二维矩阵II obj = new _240搜索二维矩阵II();
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        Asserts.test(obj.searchMatrix(matrix, 5));
        Asserts.test(obj.searchMatrix2(matrix, 5));

    }
}
