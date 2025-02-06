package 图论;

import tools.Asserts;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/rotting-oranges/
 *
 * 在给定的 m x n 网格 grid 中，每个单元格可以有以下三个值之一：
 *
 * 值 0 代表空单元格；
 * 值 1 代表新鲜橘子；
 * 值 2 代表腐烂的橘子。
 * 每分钟，腐烂的橘子 周围 4 个方向上相邻 的新鲜橘子都会腐烂。
 *
 * 返回 直到单元格中没有新鲜橘子为止所必须经过的最小分钟数。如果不可能，返回 -1 。
 */
public class _994腐烂的橘子 {
    // 四方向
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 使用 BFS 策略解决腐烂橘子问题
     * 该方法通过模拟橘子腐烂的过程，来计算所有橘子腐烂所需的最短时间
     * 在 BFS 中，每有一个新鲜橘子被腐烂，就把 fresh 减一，这样最后如果发现 fresh>0，就意味着有橘子永远不会腐烂，返回 −1
     * <p>
     * 时间复杂度：O(mn)，其中 m 和 n 分别为 grid 的行数和列数
     * 空间复杂度：O(mn)
     *
     * @param grid 二维数组，表示网格，1 代表新鲜橘子，2 代表腐烂橘子
     * @return 所有橘子腐烂的最短时间，如果无法使所有橘子腐烂则返回 -1
     */
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0; // 输入为空，直接返回 0
        }

        // 获取网格的行数和列数
        int rowNum = grid.length;
        int colNum = grid[0].length;

        // 统计初始新鲜橘子的个数
        int fresh = 0;
        // 统计所有初始就腐烂的橘子的位置
        List<int[]> rotten = new ArrayList<>();

        // 遍历网格，统计新鲜橘子的个数和初始腐烂橘子的位置
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    // 统计新鲜橘子个数
                    fresh++;
                } else if (grid[i][j] == 2) {
                    // 一开始就腐烂的橘子
                    rotten.add(new int[]{i, j});
                }
            }
        }

        // 初始化时间为 0
        int res = 0;
        boolean hasNewRotten = true;
        // 当还有新鲜橘子且还有腐烂橘子待处理时，继续 BFS
        while (fresh > 0 && !rotten.isEmpty() && hasNewRotten) {
            hasNewRotten = false;
            // 经过一分钟
            res++;
            // 临时保存当前轮的腐烂橘子，用于下一轮的腐烂传播
            List<int[]> tmp = rotten;
            // 清空列表，用于存储下一轮新腐烂的橘子
            rotten = new ArrayList<>();
            // 遍历当前轮所有腐烂橘子
            for (int[] pos : tmp) {
                // 尝试向四个方向腐烂新鲜橘子
                for (int[] d : DIRECTIONS) {
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    // 新鲜橘子开始腐烂
                    if (0 <= i && i < rowNum && 0 <= j && j < colNum && grid[i][j] == 1) {
                        hasNewRotten = true;
                        fresh--;
                        // 变成腐烂橘子
                        grid[i][j] = 2;
                        // 加入下一轮腐烂橘子的列表
                        rotten.add(new int[]{i, j});
                    }
                }
            }
        }

        // 如果还有新鲜橘子，说明无法全部腐烂，返回 -1，否则返回所用时间
        return fresh > 0 ? -1 : res;
    }

    /**
     * 使用 BFS 策略解决腐烂橘子问题
     * 该方法通过模拟橘子腐烂的过程，来计算所有橘子腐烂所需的最短时间
     * 在 BFS 中，每有一个新鲜橘子被腐烂，就把 fresh 减一，这样最后如果发现 fresh>0，就意味着有橘子永远不会腐烂，返回 −1
     * <p>
     * 时间复杂度：O(mn)，其中 m 和 n 分别为 grid 的行数和列数
     * 空间复杂度：O(mn)
     *
     * @param grid 二维数组，表示网格，1 代表新鲜橘子，2 代表腐烂橘子
     * @return 所有橘子腐烂的最短时间，如果无法使所有橘子腐烂则返回 -1
     */
    public int orangesRotting2(int[][] grid) {
        // 输入为空，直接返回 0
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        // 获取网格的行数和列数
        int rowNum = grid.length;
        int colNum = grid[0].length;

        // 统计初始新鲜橘子的个数
        int fresh = 0;
        // 统计所有初始就腐烂的橘子的位置
        Queue<int[]> rotten = new LinkedList<>();

        // 遍历网格，统计新鲜橘子的个数和初始腐烂橘子的位置
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    // 统计新鲜橘子个数
                    fresh++;
                } else if (grid[i][j] == 2) {
                    // 一开始就腐烂的橘子
                    rotten.add(new int[]{i, j});
                }
            }
        }

        // 如果一开始就没有新鲜橘子，直接返回 0
        if (fresh == 0) {
            return 0;
        }

        // 初始化时间为 0
        int res = 0;
        boolean hasNewRotten = true;

        // 当还有新鲜橘子且还有腐烂橘子待处理时，继续 BFS
        while (fresh > 0 && !rotten.isEmpty() && hasNewRotten) {
            hasNewRotten = false;
            res++;
            int n = rotten.size();
            while (n-- > 0) {
                int[] pos = rotten.poll();
                // 尝试向四个方向腐烂新鲜橘子
                for (int[] d : DIRECTIONS) {
                    int i = pos[0] + d[0];
                    int j = pos[1] + d[1];
                    // 新鲜橘子开始腐烂
                    if (0 <= i && i < rowNum && 0 <= j && j < colNum && grid[i][j] == 1) {
                        fresh--;
                        hasNewRotten = true;
                        // 变成腐烂橘子
                        grid[i][j] = 2;
                        // 加入下一轮腐烂橘子的列表
                        rotten.add(new int[]{i, j});
                    }
                }
            }
        }

        // 如果还有新鲜橘子，说明无法全部腐烂，返回 -1，否则返回所用时间
        return fresh > 0 ? -1 : res;
    }


    public static void main(String[] args) {
        _994腐烂的橘子 obj = new _994腐烂的橘子();
        int[][] grid = new int[][]{
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        Asserts.test(4 == obj.orangesRotting(grid));

        int[][] grid2 = new int[][]{
                {2,1,1},
                {1,1,0},
                {0,1,1}
        };
        Asserts.test(4 == obj.orangesRotting2(grid2));
    }
}
