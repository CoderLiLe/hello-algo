package 细节实现;

import tools.Asserts;

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
        _054_螺旋矩阵 obj = new _054_螺旋矩阵();

        int[][] matrix1 = {{1,2,3,},{4,5,6},{7,8,9}};
        List<Integer> res1 = List.of(1,2,3,6,9,8,7,4,5);
        Asserts.test(obj.spiralOrder(matrix1).equals(res1));
        Asserts.test(obj.spiralOrder2(matrix1).equals(res1));

        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> res2 = List.of(1,2,3,4,8,12,11,10,9,5,6,7);
        Asserts.test(obj.spiralOrder(matrix2).equals(res2));
        Asserts.test(obj.spiralOrder2(matrix2).equals(res2));
    }

    /**
     * 时间复杂度 O(MN) ： M,N 分别为矩阵行数和列数。
     * 空间复杂度 O(1) ： 四个边界 l , r , t , b 使用常数大小的额外空间。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
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
            if (beginY > --endY) break;

            // from left bottom to left top
            for (int i = endY; i >= beginY; --i) {
                result.add(matrix[i][beginX]);
            }
            if (++beginX > endX) break;
        }
        return result;
    }

    /**
     * 时间复杂度 O(MN) ： M,N 分别为矩阵行数和列数。
     * 空间复杂度 O(1) ： 四个边界 l , r , t , b 使用常数大小的额外空间。
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if (matrix.length == 0) return result;

        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        while (result.size() < matrix[0].length * matrix.length) {
            // 从左上到右上
            for (int y = left; y <= right; y++) {
                result.add(matrix[top][y]);
            }
            // 更新上边界，然后判断是否到达边界
            if (++top > bottom) break;

            // 从右上到右下
            for (int x = top; x <= bottom; x++) {
                result.add(matrix[x][right]);
            }
            // 更新右边界，然后判断是否到达边界
            if (--right < left) break;

            // 从右下到左下
            for (int y = right; y >= left; y--) {
                result.add(matrix[bottom][y]);
            }
            // 更新下边界
            if (--bottom < top) break;

            // 从左下到左上
            for (int x = bottom; x >= top; x--) {
                result.add(matrix[x][left]);
            }
            // 更新左边界
            if (++left > right) break;
        }
        return result;
    }
}
