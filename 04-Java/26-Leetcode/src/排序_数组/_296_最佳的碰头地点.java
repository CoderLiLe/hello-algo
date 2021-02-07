package 排序_数组;

import tools.Asserts;

import java.util.ArrayList;
import java.util.List;

/**
 * 296. 最佳的碰头地点
 * 困难
 *
 * 有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。
 *
 * 给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。
 *
 * 1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。
 *
 *
 * 示例：
 *
 * 输入:
 *
 * 1 - 0 - 0 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 *
 * 输出: 6
 *
 * 解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
 *      (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
 */

/**
 * 【思路】
 * 由于题目是二维问题，且距离计算时，横纵坐标的计算是相互独立的，因此把问题拆分成两个一维问题之和。
 *
 * 1、找出所有 1 所在的行(列)索引；
 *
 * 2、最优点肯定为中位数的地方（如果 1 的个数是偶数个，那么最优点在中间的两个 1 其中任意一个）；
 *
 * 3、分别计算行距离和纵距离，相加即可。
 */

public class _296_最佳的碰头地点 {

    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = collectRows(grid);
        List<Integer> cols = collectCols(grid);
        return minDistance(rows) + minDistance(cols);
    }

    // T = O(mn), S = O(m + n)
    private int minDistance(List<Integer> points) {
        int distance = 0;
        int left = 0;
        int right = points.size() - 1;
        while (left < right) {
            distance += points.get(right) - points.get(left);
            left++;
            right--;
        }
        return distance;
    }

    private List<Integer> collectRows(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    rows.add(row);
                }
            }
        }

        return rows;
    }

    private List<Integer> collectCols(int[][] grid) {
        List<Integer> cols = new ArrayList();
        for (int col = 0; col < grid[0].length; col++) {
            for (int row = 0; row < grid.length; row++) {
                if (grid[row][col] == 1) {
                    cols.add(col);
                }
            }
        }
        return cols;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0}
        };

        _296_最佳的碰头地点 address = new _296_最佳的碰头地点();
        Asserts.test(address.minTotalDistance(grid) == 6);
    }
}
