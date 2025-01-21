package 排序_数组;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix/description/
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
 */
public class _074搜索二维矩阵 {
    /**
     * 直接遍历整个矩阵 matrix，判断 target 是否出现
     * 时间复杂度：O(mn)。
     * 空间复杂度：O(1)
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] row : matrix) {
            for (int element : row) {
                if (element == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 由于矩阵 matrix 中每一行的元素都是升序排列的，因此我们可以对每一行都使用一次二分查找，
     * 判断 target 是否在该行中，从而判断 target 是否出现
     *
     * 时间复杂度：O(mlogn)。对一行使用二分查找的时间复杂度为 O(logn)，最多需要进行 m 次二分查找。
     * 空间复杂度：O(1)。
     */
    public boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] row : matrix) {
            int index = search(row, target);
            if (index >= 0) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 两次二分查找
     * 时间复杂度：O(logm+logn)=O(logmn)，其中 m 和 n 分别是矩阵的行数和列数。
     * 空间复杂度：O(1)。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        int minRow = -1, maxRow = matrix.length - 1;
        int minCol = 0, maxCol = matrix[0].length - 1;

        while (minRow < maxRow) {
            int mid = minRow + ((maxRow - minRow + 1) >> 1);
            if (matrix[mid][0] <= target) {
                minRow = mid;
            } else {
                maxRow = mid - 1;
            }
        }

        if (minRow < 0) {
            return false;
        }

        while (minCol <= maxCol) {
            int mid = minCol + ((maxCol - minCol) >> 1);
            if (matrix[minRow][mid] > target) {
                maxCol = mid - 1;
            } else if (matrix[minRow][mid] < target) {
                minCol = mid + 1;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * 一次二分查找
     * 若将矩阵每一行拼接在上一行的末尾，则会得到一个升序数组，我们可以在该数组上二分找到目标元素。
     * 时间复杂度：O(logmn)，其中 m 和 n 分别是矩阵的行数和列数。
     * 空间复杂度：O(1)。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix4(int[][] matrix, int target) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        int left = 0, right = rowNum * colNum - 1;

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int midNum = matrix[mid / colNum][mid % colNum];
            if (midNum < target) {
                left = mid + 1;
            } else if (midNum > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }

        return false;
    }

    /**
     * Z 字形查找
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
    public boolean searchMatrix5(int[][] matrix, int target) {
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
     * Z 字形查找
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
    public boolean searchMatrix6(int[][] matrix, int target) {
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
}
