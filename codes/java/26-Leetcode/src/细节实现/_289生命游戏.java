package 细节实现;

import tools.Asserts;

import java.util.Arrays;

/**
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 */
public class _289生命游戏 {
    /**
     * 算法描述：
     * 1、拷贝一份 board，作为备份，用作后序状态判断
     * 2、遍历 board，判断当前位置的邻居存活情况，并更新当前位置的值
     * 3、根据更新后的值，更新 board
     * <p>
     * 时间复杂度：O(mn)，其中 m 和 n 分别为 board 的行数和列数。
     * 空间复杂度：O(mn)，为复制数组占用的空间。
     */
    public void gameOfLife(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][] copyBoard = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x == i && y == j) {
                            continue;
                        }
                        if (x >= 0 && x < rows && y >= 0 && y < cols) {
                            if (copyBoard[x][y] == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }

                if (copyBoard[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = 0;
                } else if (copyBoard[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }

    /**
     * 定义复合状态，观察规则发现只有活细胞会影响周围细胞的状态，所以可以定义-1表示一个细胞从活到死；定义2表示一个细胞从死到活
     *
     * 时间复杂度：O(mn)，其中 m，n 分别为 board 的行数和列数。
     * 空间复杂度：O(1)，除原数组外只需要常数的空间存放若干变量。
     */
    public void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x == i && y == j) {
                            continue;
                        }
                        if (x >= 0 && x < rows && y >= 0 && y < cols && Math.abs(board[x][y]) == 1) {
                            liveNeighbors++;
                        }
                    }
                }

                if (board[i][j] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[i][j] = -1;
                } else if (board[i][j] == 0 && liveNeighbors == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] > 0) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        _289生命游戏 obj = new _289生命游戏();
        test1(obj);
        test2(obj);
    }

    private static void test1(_289生命游戏 obj) {
        int[][] board = {{0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}};
        int[][] res = {{0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}};
        obj.gameOfLife(board);
        Asserts.test(Arrays.deepEquals(res, board));
    }

    private static void test2(_289生命游戏 obj) {
        int[][] board = {{0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}};
        int[][] res = {{0, 0, 0},
                {1, 0, 1},
                {0, 1, 1},
                {0, 1, 0}};
        obj.gameOfLife2(board);
        Asserts.test(Arrays.deepEquals(res, board));
    }
}
