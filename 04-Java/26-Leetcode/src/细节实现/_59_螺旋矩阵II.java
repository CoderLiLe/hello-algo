package 细节实现;

import java.util.ArrayList;
import java.util.List;

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

        System.out.println("-----------");

        int[][] matrix2 = generateMatrix2(3);
        for (int[] row : matrix2) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        System.out.println("-----------");

        int[][] matrix3 = generateMatrix3(3);
        for (int[] row : matrix3) {
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

    public static int[][] generateMatrix2(int n) {
        int[][] matrix = new int[n][n];
        if (n == 0) return matrix;

        int num = 1;
        int beginX = 0, beginY = 0, endX = n - 1, endY = n - 1;
        while (true) {
            // from left top to right top
            for (int j = beginX; j <= endX; ++j) {
                matrix[beginY][j] = num++;
            }
            if (++beginY > endY) break;

            // from right top to right bottom
            for (int i = beginY; i <= endY; ++i) {
                matrix[i][endX] = num++;
            }
            if (beginX > --endX) break;

            // from right bottom to left bottom
            for (int j = endX; j >= beginX; --j) {
                matrix[endY][j] = num++;
            }
            if (beginY > -- endY) break;

            // from left bottom to left top
            for (int i = endY; i >= beginY; --i) {
                matrix[i][beginX] = num++;
            }
            if (++beginX > endX) break;
        }
        return matrix;
    }

    public int[][] generateMatrix3(int n) {
        int[][] res = new int[n][n];
        for (int begin = 0, end = n - 1, num = 1; begin <= end; begin++, end--) {
            for (int y = begin; y <= end; y++) res[begin][y] = num++;
            for (int x = begin + 1; x <= end; x++) res[x][end] = num++;
            for (int y = end - 1; end >= begin; y--) res[end][y] = num++;
            for (int x = end - 1; end >= begin + 1; x--) res[x][begin] = num++;
        }
        return res;
    }
}
