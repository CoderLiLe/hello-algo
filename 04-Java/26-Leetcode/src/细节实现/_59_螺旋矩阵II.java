package 细节实现;

/**
 * 59. 螺旋矩阵 II
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 *
 * 1 <= n <= 20
 */
public class _59_螺旋矩阵II {

    public static void main(String[] args) {
        int[][] matrix = generateMatrix(3);
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    // T = O(n^2), S = O(n^2)
    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int begin = 0, end = n - 1;
        int num = 1;

        while (begin < end) {
            for (int j = begin; j < end; ++j) {
                matrix[begin][j] = num++;
            }
            for (int i = begin; i < end; ++i) {
                matrix[i][end] = num++;
            }
            for (int j = end; j > begin; --j) {
                matrix[end][j] = num++;
            }
            for (int i = end; i > begin; --i) {
                matrix[i][begin] = num++;
            }

            ++begin;
            --end;
        }

        if (begin == end) {
            matrix[begin][begin] = num;
        }
        return matrix;
    }
}
