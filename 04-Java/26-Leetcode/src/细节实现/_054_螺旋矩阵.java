package 细节实现;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 中等，美团面试题
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class _054_螺旋矩阵 {

    public static void main(String[] args) {
        int[][] matrix1 = {{1,2,3,},{4,5,6},{7,8,9}};
        System.out.println(spiralOrder(matrix1));

        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(spiralOrder(matrix2));
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;

        int beginX = 0, beginY = 0, endX = matrix[0].length - 1, endY = matrix.length - 1;
        while (true) {
            // from left top to right top
            for (int j = beginX; j <= endX; ++j) {
                result.add(matrix[beginY][j]);
            }
            if (++beginY > endY) break;

            // from right top to right bottom
            for (int i = beginY; i <= endY; ++i) {
                result.add(matrix[i][endX]);
            }
            if (beginX > --endX) break;

            // from right bottom to left bottom
            for (int j = endX; j >= beginX; --j) {
                result.add(matrix[endY][j]);
            }
            if (beginY > -- endY) break;

            // from left bottom to left top
            for (int i = endY; i >= beginY; --i) {
                result.add(matrix[i][beginX]);
            }
            if (++beginX > endX) break;
        }
        return result;
    }
}
