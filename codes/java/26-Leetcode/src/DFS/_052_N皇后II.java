package DFS;

import java.util.Arrays;

public class _052_N皇后II {
    private int totalCount = 0;
    public int totalNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');
        }
        dfs(0, n, board);
        return totalCount;
    }

    private void dfs(int row, int n, char[][] board) {
        if (row == n) {
            totalCount++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) continue;
            board[row][col] = 'Q';
            dfs(row+1, n, board);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // up is valid
        for (char[] chars : board) {
            if (chars[col] == 'Q') return false;
        }

        // up left is valid
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        // up right is valid
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }
}
