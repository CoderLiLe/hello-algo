package com.lile.棋盘问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/n-queens/description
 */
public class _051N皇后 {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        // '.' 表示空，'Q' 表示皇后，初始化空棋盘
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return res;
    }


    /**
     * 使用回溯法解决N皇后问题
     *
     * @param n 皇后数量，也是棋盘的大小
     * @param row 当前处理的行号
     * @param chessboard 表示棋盘的字符数组，'Q'表示皇后的位置，'.'表示空位
     */
    public void backTrack(int n, int row, char[][] chessboard) {
        // 当递归到最后一行的下一行时，表示已经找到一个合法的N皇后放置方案
        if (row == n) {
            // 将当前棋盘的状况转换为字符串列表并添加到结果集中
            res.add(Array2List(chessboard));
            return;
        }

        // 遍历当前行的每一列，尝试放置皇后
        for (int col = 0;col < n; ++col) {
            // 检查当前位置是否可以放置皇后（即不受到其他皇后的攻击）
            if (isValid(row, col, n, chessboard)) {
                // 在当前位置放置皇后
                chessboard[row][col] = 'Q';
                // 继续处理下一行
                backTrack(n, row+1, chessboard);
                // 回溯，撤销当前位置的皇后，以探索其他可能的解决方案
                chessboard[row][col] = '.';
            }
        }

    }


    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }

    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
